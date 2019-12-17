package br.com.app.consigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConsigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsigServerApplication.class, args);
    }

}
