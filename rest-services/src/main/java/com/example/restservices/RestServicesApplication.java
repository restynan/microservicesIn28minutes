package com.example.restservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.ResourceBundle;

@SpringBootApplication

public class RestServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServicesApplication.class, args);
    }
@Bean
    public LocaleResolver localResolver(){
    AcceptHeaderLocaleResolver localeResolver= new    AcceptHeaderLocaleResolver();
    localeResolver.setDefaultLocale(Locale.US);
    return  localeResolver;

}

/*@Bean
    public ResourceBundleMessageSource messageSource(){
    ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource();
    messageSource.setBasename("messages");

    return messageSource;
}*/
}
