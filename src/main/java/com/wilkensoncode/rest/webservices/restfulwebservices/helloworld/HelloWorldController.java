
package com.wilkensoncode.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldController {

    //endpoint
    @GetMapping(path ="/hello")
    public String helloWorld(){
        return "Hello world";
    }


    @GetMapping("/hello-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello world bean");
    }

    @GetMapping("/hello/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello world, %s",name));
//        throw new RuntimeException("Something when wrong");
    }

}
