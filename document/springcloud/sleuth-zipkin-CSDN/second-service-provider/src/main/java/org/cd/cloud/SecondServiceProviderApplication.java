package org.cd.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class SecondServiceProviderApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder(SecondServiceProviderApplication.class).run(args);
	}

	@RequestMapping("/message2")
	public String getMsg() {
		return "This is a message from second-service-provider";
	}
}
