//package com.mt.config;
//
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author 段杨宇
// * @create 2020-09-08 15:04
// **/
//@Configuration
//@ConfigurationProperties(prefix = "elasticsearch")
//public class ElasticSearchConfiguration {
//
//    /** 协议 */
//    private String schema;
//
//    /** 集群地址，如果有多个用“,”隔开 */
//    private String address;
//
//    /** 连接超时时间 */
//    private int connectTimeout;
//
//    /** Socket 连接超时时间 */
//    private int socketTimeout;
//
//    /** 获取连接的超时时间 */
//    private int connectionRequestTimeout;
//
//    /** 最大连接数 */
//    private int maxConnectNum;
//
//    /** 最大路由连接数 */
//    private int maxConnectPerRoute;
//
//    private String username;
//
//    private String password;
//
//    @Bean(name = "restHighLevelClient")
//    public RestHighLevelClient restHighLevelClient() {
//        // 拆分地址
//        List<HttpHost> hostLists = new ArrayList<>();
//        String[] hostList = address.split(",");
//        for (String addr : hostList) {
//            String host = addr.split(":")[0];
//            String port = addr.split(":")[1];
//            hostLists.add(new HttpHost(host, Integer.parseInt(port), schema));
//        }
//        // 转换成 HttpHost 数组
//        HttpHost[] httpHost = hostLists.toArray(new HttpHost[]{});
//        // 构建连接对象
//        RestClientBuilder builder = RestClient.builder(httpHost);
//        // 异步连接延时配置
//        builder.setRequestConfigCallback(requestConfigBuilder -> {
//            requestConfigBuilder.setConnectTimeout(connectTimeout);
//            requestConfigBuilder.setSocketTimeout(socketTimeout);
//            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
//            return requestConfigBuilder;
//        });
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
//        // 异步连接数配置
//        builder.setHttpClientConfigCallback(httpClientBuilder -> {
//            httpClientBuilder.setMaxConnTotal(maxConnectNum);
//            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
//            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//            return httpClientBuilder;
//        });
//        return new RestHighLevelClient(builder);
//    }
//
//    public String getSchema() {
//        return schema;
//    }
//
//    public void setSchema(String schema) {
//        this.schema = schema;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public int getConnectTimeout() {
//        return connectTimeout;
//    }
//
//    public void setConnectTimeout(int connectTimeout) {
//        this.connectTimeout = connectTimeout;
//    }
//
//    public int getSocketTimeout() {
//        return socketTimeout;
//    }
//
//    public void setSocketTimeout(int socketTimeout) {
//        this.socketTimeout = socketTimeout;
//    }
//
//    public int getConnectionRequestTimeout() {
//        return connectionRequestTimeout;
//    }
//
//    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
//        this.connectionRequestTimeout = connectionRequestTimeout;
//    }
//
//    public int getMaxConnectNum() {
//        return maxConnectNum;
//    }
//
//    public void setMaxConnectNum(int maxConnectNum) {
//        this.maxConnectNum = maxConnectNum;
//    }
//
//    public int getMaxConnectPerRoute() {
//        return maxConnectPerRoute;
//    }
//
//    public void setMaxConnectPerRoute(int maxConnectPerRoute) {
//        this.maxConnectPerRoute = maxConnectPerRoute;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
