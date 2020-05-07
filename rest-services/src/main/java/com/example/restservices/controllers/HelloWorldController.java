package com.example.restservices.controllers;

import com.example.restservices.models.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController

public class HelloWorldController {
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
}
