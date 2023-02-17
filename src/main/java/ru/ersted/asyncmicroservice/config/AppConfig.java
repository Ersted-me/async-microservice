package ru.ersted.asyncmicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


//TODO: add tests
@Configuration
public class AppConfig {

//    @Value("${app.threads.amount}")
//    private Integer threadsAmount;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public ExecutorService executorService() {
//        return Executors.newFixedThreadPool(threadsAmount);
//    }
}
