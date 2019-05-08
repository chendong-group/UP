package org.cd.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class FirstServiceProviderApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder(FirstServiceProviderApplication.class)
                .run(args);
	}

	@RequestMapping("/message")
	public String getMsg(Long messageID) {
	    return "This is test message, the messageID is: " + messageID;
    }

	@RequestMapping("/testMyFeign")
	public String testMyFeign() {
		return "This is test MyFeign";
	}
}
