package com.example.restservices.controllers;

import com.example.restservices.models.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
    @Autowired
    MessageSource messageSource;



   // @RequestMapping(method = RequestMethod.GET,path = "/hello")

    @GetMapping(path="/hello-world")
    public String hello(){
        return "hello world America";
    }



    @GetMapping(path="/hello-world-Bean")
    public HelloWorldBean helloBean(){
        return new HelloWorldBean("Hello world");
    }

    @GetMapping(path="/hello-world-Bean/path-variable/{name}")
    public HelloWorldBean helloPath(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello world %s",name));
    }

    //internationalization
    @GetMapping(path="/hello-world-internationalized")
    public String helloInternationalized(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }


}
