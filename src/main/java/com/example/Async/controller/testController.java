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
}
