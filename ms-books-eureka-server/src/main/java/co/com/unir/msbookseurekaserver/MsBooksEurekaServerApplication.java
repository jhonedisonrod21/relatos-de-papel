package co.com.unir.msbookseurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsBooksEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBooksEurekaServerApplication.class, args);
    }

}
