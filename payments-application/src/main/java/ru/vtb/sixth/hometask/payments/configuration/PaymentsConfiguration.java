package ru.vtb.sixth.hometask.payments.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentsConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
