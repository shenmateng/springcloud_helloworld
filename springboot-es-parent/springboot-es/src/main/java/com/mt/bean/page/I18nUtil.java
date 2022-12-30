package com.mt.bean.page;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Objects;

@Configuration
public class I18nUtil {

    private static MessageSource messageSource;

    @Value("${language}")
    public String language;

    private static Locale locale;


    @PostConstruct
    public void init(){
        if(Objects.equals(language,"en_US")){
            locale = Locale.US;
        }else {
            locale = Locale.SIMPLIFIED_CHINESE;
        }
    }

    public I18nUtil(MessageSource messageSource) {
        I18nUtil.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值
     */
    public static String get(String msgKey) {

        try {
            return messageSource.getMessage(msgKey, null,locale);
        } catch (Exception e) {
            return msgKey;
        }
    }

}