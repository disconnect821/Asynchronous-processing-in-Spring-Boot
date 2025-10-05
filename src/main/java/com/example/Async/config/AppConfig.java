package com.example.Async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
public class AppConfig implements AsyncConfigurer {

    // Custom ThreadPoolTaskExecutor - Wrapper on ThreadPoolExecutor (Java)
//    @Bean
//    public Executor taskPoolExecutor(){
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.setCorePoolSize(2);
//        threadPoolTaskExecutor.setMaxPoolSize(4);
//        threadPoolTaskExecutor.setQueueCapacity(3);
//        threadPoolTaskExecutor.setThreadNamePrefix("My Custom thread : ");
//        threadPoolTaskExecutor.initialize();
//
//        return threadPoolTaskExecutor;
//    }


    //Custom ThreadPoolExecutor (Java)

//    @Bean(name = "myCustomThreadPoolExecutor")
//    public Executor taskExecutor(){
//        //Also you can provide your own thread factory (optional)
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(3), new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread thread = new Thread(r, "From Custom Thread Factory");
//                thread.setDaemon(false); // Example: make it a user thread
//                thread.setPriority(Thread.NORM_PRIORITY); // Example: set normal priority
//                // Optional: Set an UncaughtExceptionHandler
//                thread.setUncaughtExceptionHandler((t, e) -> {
//                    System.err.println("Uncaught exception in thread " + t.getName() + ": " + e.getMessage());
//                    e.printStackTrace();
//                });
//                return thread;
//            }
//        });
//        return threadPoolExecutor;
//    }



    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public synchronized Executor getAsyncExecutor(){
        if(threadPoolExecutor == null){
            threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(3), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r, "Our Custom Thread Factory");
                    thread.setDaemon(false); // Example: make it a user thread
                    thread.setPriority(Thread.NORM_PRIORITY); // Example: set normal priority
                    // Optional: Set an UncaughtExceptionHandler
                    thread.setUncaughtExceptionHandler((t, e) -> {
                        System.err.println("Uncaught exception in thread " + t.getName() + ": " + e.getMessage());
                        e.printStackTrace();
                    });
                    return thread;
                }
            });
        }
        return threadPoolExecutor;
    }

}
