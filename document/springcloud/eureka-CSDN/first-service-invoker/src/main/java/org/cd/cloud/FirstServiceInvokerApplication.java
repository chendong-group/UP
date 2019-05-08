package org.cd.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class FirstServiceInvokerApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder(FirstServiceInvokerApplication.class)
				.run(args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@RequestMapping("/test")
	public String getMsg() {
		RestTemplate restTemplate = getRestTemplate();
		ResponseEntity<String> result = restTemplate.exchange("http://first-service-provider/message", HttpMethod.GET, null, String.class);
    	return result.getBody();
	}
}
