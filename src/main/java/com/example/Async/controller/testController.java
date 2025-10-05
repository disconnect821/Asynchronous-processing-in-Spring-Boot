package com.example.Async.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class testController {

    @Async
    @GetMapping("/customThreadPoolTaskExecutor")
    public void testThreadExecutor(){
        System.out.println(" current thread : " + Thread.currentThread().getName());
    }


    //If using the custom ThreadPoolExecutor (JAVA) then , as Springboot default check for ThreadPoolTaskExecutor
    //So if not found it will use the default one : SimpleAsyncTaskExecutor
    @Async
    @GetMapping("/customThreadPoolExecutor")
    public void testThreadPoolExecutor(){
        System.out.println(" current thread : " + Thread.currentThread().getName());
    }

    //So using this annotation with name of bean to use the custom executor
    @Async("myCustomThreadPoolExecutor")
    @GetMapping("/customThreadPoolExecutorWithName")
    public void testThreadPoolExecutorwithName(){
        System.out.println(" current thread : " + Thread.currentThread().getName());
    }
}
