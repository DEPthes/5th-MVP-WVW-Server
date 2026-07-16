package com.mmw.mmw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean//스프링 컨테이너가 관리하는 객체에 등록
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();//BaseURL, Header, 타임아웃을 튜닝 가능
    }
}
