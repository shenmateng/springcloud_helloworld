package com.mt.header;

import com.mt.cnnotation.JowtoResponseBody;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author 段杨宇
 * @create 2019-01-21 11:20
 **/
public class JowtoResponseBodyHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        JowtoResponseBody[] jowtoResponseBodies = methodParameter.getMethod().getDeclaredAnnotationsByType(JowtoResponseBody.class);
        return jowtoResponseBodies != null &&  jowtoResponseBodies.length >0;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        JowtoResponseBody[] jowtoResponseBodies = methodParameter.getMethod().getDeclaredAnnotationsByType(JowtoResponseBody.class);
        CustomerJsonSerializer jsonSerializer = new CustomerJsonSerializer();
        Arrays.asList(jowtoResponseBodies).forEach(jsonSerializer::filter);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String json = jsonSerializer.toJson(returnValue);
        response.getWriter().write(json);
    }
}
