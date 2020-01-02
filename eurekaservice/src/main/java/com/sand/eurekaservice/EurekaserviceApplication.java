package com.sand.eurekaservice;

        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class EurekaserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaserviceApplication.class, args);
    }


    @Value("${server.port}")
    String port;

    @RequestMapping("/demo")
    public String home(@RequestParam String name) {
        return "hi " + name + "i am from port" + port;
    }
}
