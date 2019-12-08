package br.com.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@RibbonClient(name = "produto")
@Configuration
public class AppConfig {

    @Bean("clientRest")
    @LoadBalanced()
    public RestTemplate registrarRestTemplate(){
        return new RestTemplate();
    }
}
