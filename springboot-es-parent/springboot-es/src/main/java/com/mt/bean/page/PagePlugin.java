package com.mt.bean.page;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagePlugin implements Interceptor {

    private static String dialect = "";
    private static String pageSqlId = "";

    @Override
    @SuppressWarnings("unchecked")
    public Object intercept(Invocation ivk) throws Throwable {

        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
            if (mappedStatement.getId().matches(pageSqlId)) {
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();
                Boolean isPage = true;
                if (isPage) {
                    if (parameterObject == null) {
                        throw new NullPointerException("parameterObject"+ I18nUtil.get("NULL_POINTER"));
                    } else {
                        Connection connection = (Connection) ivk.getArgs()[0];
                        String sql = boundSql.getSql();
                        StringBuffer strBuff = new StringBuffer(sql);
                        int start = strBuff.indexOf("select") + 6;
                        int end = strBuff.indexOf("from");

                        String countSql = strBuff.replace(start, end, " count(0) ").toString();
                        PreparedStatement countStmt = connection.prepareStatement(countSql);
                        ReflectHelper.setValueByFieldName(boundSql,"sql",countSql);
                        setParameters(countStmt, mappedStatement, boundSql, parameterObject);
                        ResultSet rs = countStmt.executeQuery();
                        int count = 0;
                        if (rs.next()) {
                            count = rs.getInt(1);
                        }
                        rs.close();
                        countStmt.close();

                        Page page = null;
                        if (parameterObject instanceof Page) {
                            page = (Page) parameterObject;
                            page.setTotalResults(count);
                        } else if (parameterObject instanceof Map) {
                            Map<String, Object> map = (Map<String, Object>) parameterObject;
                            page = (Page) map.get("page");
                            if (page == null) {
                                page = new Page();
                            }
                            page.setTotalResults(count);
                        } else {
                            Field pageField = ReflectHelper.getFieldByFieldName(parameterObject, "page");
                            if (pageField != null) {
                                page = (Page) ReflectHelper.getValueByFieldName(parameterObject, "page");
                                if (page == null) {
                                    page = new Page();
                                }
                                page.setTotalResults(count);
                                ReflectHelper.setValueByFieldName(parameterObject, "page", page);
                            } else {
                                throw new NoSuchFieldException(parameterObject.getClass().getName());
                            }
                        }
                        String pageSql = generatePageSql(sql, page);
                        ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
                    }
                }
            }
        }
        return ivk.proceed();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Autowired(required = false)
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    private String generatePageSql(String sql, Page page) {
        if (page != null && (dialect != null || !dialect.equals(""))) {
            StringBuffer pageSql = new StringBuffer();
            if ("mysql".equals(dialect)) {
                pageSql.append(sql);
                pageSql.append(" limit " + page.getFirstResult() + "," + page.getMaxResults());
            } else if ("oracle".equals(dialect)) {
                pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
                pageSql.append(sql);
                pageSql.append(")  tmp_tb where ROWNUM<=");
                pageSql.append(page.getFirstResult() + page.getMaxResults());
                pageSql.append(") where row_id>");
                pageSql.append(page.getMaxResults());
            }
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    @Override
    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    @Override
    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        pageSqlId = p.getProperty("pageSqlId");

    }

}
