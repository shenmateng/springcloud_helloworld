package com.mt.exception;

import com.mt.bean.page.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 统一异常处理
 *
 * @author zhaolm
 * @data 2018/1/24
 */
@ControllerAdvice
@Component
@Slf4j
public class JowtoExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Map vaildExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> fields = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            log.error(fieldError.getField() + fieldError.getDefaultMessage());
            fields.add(fieldError.getField());
        }
        ErrorDomain errorDomain = new ErrorDomain(JowtoExceptionResponse.BaseCode.PARAMETER_INVALID.getCode(), I18nUtil.get("PARAM") + fields.toString() + I18nUtil.get("ILLEGAL"));
        errorDomain.setException(e);
        return resolverErrorInfo(request, errorDomain);
    }

    @ExceptionHandler(value = JowtoException.class)
    @ResponseBody
    public Map jowtoExceptionHandler(HttpServletRequest request, JowtoException jowtoException) {
        ErrorDomain errorDomain = new ErrorDomain(jowtoException.getCode(), jowtoException.getMsg());
        errorDomain.setData(jowtoException.getData());
        errorDomain.setException(jowtoException);
        return resolverErrorInfo(request, errorDomain);
    }

    @ExceptionHandler(value = JowtoRuntimeException.class)
    @ResponseBody
    public Map jowtoRuntimeExceptionHandler(HttpServletRequest request, JowtoRuntimeException jowtoRuntimeException) {
        ErrorDomain errorDomain = new ErrorDomain(jowtoRuntimeException.getCode(), jowtoRuntimeException.getMsg());
        errorDomain.setData(jowtoRuntimeException.getData());
        errorDomain.setException(jowtoRuntimeException);
        return resolverErrorInfo(request, errorDomain);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map exception(HttpServletRequest request, Exception exception) {
        String code = JowtoExceptionResponse.BaseCode.ERROR.getCode();
        String msg = JowtoExceptionResponse.BaseCode.ERROR.getMsg();
        ErrorDomain errorDomain = new ErrorDomain(code, msg);
        errorDomain.setException(exception);
        return resolverErrorInfo(request, errorDomain);
    }




    /**
     * 请求参数与接收的参数类型不对应
     *
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public Map httpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        ErrorDomain errorDomain = new ErrorDomain(JowtoExceptionResponse.BaseCode.PARAMETER_UNMATCHED.getCode(), JowtoExceptionResponse.BaseCode.PARAMETER_UNMATCHED.getMsg());
        errorDomain.setException(exception);
        return resolverErrorInfo(request, errorDomain);
    }

    /**
     * 上传文件大小异常处理
     *
     * @return
     */
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseBody
    public Map maxUploadSizeExceededException(HttpServletRequest request, MaxUploadSizeExceededException exception) {
        ErrorDomain errorDomain = new ErrorDomain(JowtoExceptionResponse.BaseCode.FILE_SIZE_LIMIT.getCode(), JowtoExceptionResponse.BaseCode.FILE_SIZE_LIMIT.getMsg());
        errorDomain.setException(exception);
        return resolverErrorInfo(request, errorDomain);
    }

    private Map resolverErrorInfo(HttpServletRequest request, ErrorDomain errorDomain) {
        log.error("请求URI:" + request.getRequestURI());
        String body = "";
        try {
            body = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException ignored) {
        }
        log.error("请求参数:" + body);
        log.error(errorDomain.getCode() + "," + errorDomain.getMsg());
        if (!ignorePrintErrorLog(errorDomain.getCode())) {
            log.error("", errorDomain.getException());
        }

        Map responseMap = Map.of("msg", errorDomain.getMsg(), "code", errorDomain.getCode());
        if (errorDomain.getData() != null) {
            responseMap = Map.of("msg", errorDomain.getMsg(), "code", errorDomain.getCode(), "data", errorDomain.getData());
        }
        return responseMap;
    }

    /**
     * 不输出指定的errorCode的错误日志
     */
    private boolean ignorePrintErrorLog(String code) {
        List<String> errorCodes = List.of(JowtoExceptionResponse.BaseCode.PARAMETER_INVALID.getCode(), JowtoExceptionResponse.BaseCode.PARAMETER_UNMATCHED.getCode());
        return errorCodes.contains(code);
    }

}
