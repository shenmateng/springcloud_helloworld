package com.mt.ResponseResult;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author ：lichenghuai
 * @version ：V1.0
 * @program ：vevor-bmp
 * @date : 2020/2/3 9:39
 * @description ：定义 获取后端接口数据渲染到前端的通用类
 */
public class ResponseResult<T> implements Serializable {
    /**
     * 序列化时为了保持版本的兼容性，即在版本升级时反序列化保持对象的唯一性
     */
    private static final long serialVersionUID = -8145865776285690954L;
    /**
     * 常量 默认状态码code200
     */
    public static final int DEFAULT_STATUS_CODE = 200;
    /**
     * 常量 默认描述success
     */
    public static final String DEFAULT_MESSAGE = "success";

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息描述
     */
    private String message;
    /**
     * 返回内容
     */
    private T data;

    /**
     * 分页信息
     * 注解不返回null值字段
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageResponse<T> page;

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(int code, String message, T data, PageResponse<T> pageResponse) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.page = pageResponse;
    }

    public ResponseResult(int code, String message, T data, Long total, Integer currentPage, Integer pageSize) {
        PageResponse<T> pageResponse = new PageResponse<>(total, currentPage, pageSize);
        this.code = code;
        this.message = message;
        this.data = data;
        this.page = pageResponse;
    }

    /**
     * 分页返回数据方法
     *
     * @param data        数据集
     * @param total       总条数
     * @param currentPage 当前页
     * @param pageSize    每页的数量大小
     * @return 响应体
     */
    public static <T> ResponseResult<T> successPage(T data, Long total, Integer currentPage, Integer pageSize) {
        PageResponse<T> pageResponse = new PageResponse<>(total, currentPage, pageSize);
        return new ResponseResult<>(DEFAULT_STATUS_CODE, DEFAULT_MESSAGE, data, pageResponse);
    }

    /**
     * 无参构造函数
     */
    public ResponseResult() {
    }


    /**
     * 有参构造函数
     *
     * @param code    响应码
     * @param message 响应消息
     * @param data    相应内容
     */
    public ResponseResult(int code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 内部自定义返无参数回结果的静态方法
     *
     * @return 响应体构建对象，用于构架响应体
     */
    public static <T> ResponseVOBuilder<T> response() {
        return new ResponseVOBuilder<>();
    }

    /**
     * 内部 自定义 有参数返回结果的静态方法
     *
     * @param code    响应码
     * @param message 响应消息
     * @return 响应体构建对象，用于构架响应体
     */
    public static <T> ResponseVOBuilder<T> response(int code, String message) {
        return new ResponseVOBuilder<>(code, message);
    }

    /**
     * 返回成功带状态码、描述的静态方法
     *
     * @return 返回成功的消息体
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseVOBuilder<T>(DEFAULT_STATUS_CODE, DEFAULT_MESSAGE).build();
    }

    /**
     * 返回错误状态码、描述的静态方法
     *
     * @return 返回一个状态异常的响应体，返回内容为null
     */
    public static <T> ResponseResult<T> error(int code, String message) {
        return new ResponseResult<>(code, message);
    }

    /**
     * 返回带状态码、描述、数据的静态方法
     *
     * @param data 响应内容
     * @return 响应体
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseVOBuilder<T>(DEFAULT_STATUS_CODE, DEFAULT_MESSAGE).setData(data).build();
    }

    public PageResponse<T> getPage() {
        return page;
    }

    public void setPage(PageResponse<T> page) {
        this.page = page;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 重写toString方法
     *
     * @return toString
     */
    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
