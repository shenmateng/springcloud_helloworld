package com.mt.exception;


import com.mt.bean.page.I18nUtil;

public interface JowtoExceptionResponse {

    String getCode();

    String getMsg();

    enum BaseCode implements JowtoExceptionResponse{
        //系统响应码
        OK("1", I18nUtil.get("OK")),
        ERROR("0", I18nUtil.get("ERROR")),
        PARAMETER_INVALID("5", I18nUtil.get("PARAMETER_INVALID")),
        PARAMETER_UNMATCHED("6", I18nUtil.get("PARAMETER_UNMATCHED")),
        FEIGN_CALL_EXCEPTION("7", I18nUtil.get("FEIGN_CALL_EXCEPTION")),
        AUTH_ERROR("10", I18nUtil.get("AUTH_ERROR")),
        FILE_SIZE_LIMIT("11", I18nUtil.get("FILE_SIZE_LIMIT")),
        ES_SEARCH_UPPER_LIMIT("100", I18nUtil.get("ES_SEARCH_UPPER_LIMIT")),
        ;

        private final String code;
        private final String msg;

        BaseCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }


        @Override
        public String getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }

}
