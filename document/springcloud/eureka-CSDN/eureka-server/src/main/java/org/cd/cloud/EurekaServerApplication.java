package org.cd.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Scanner;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input profile name which you need to active: ");
        String profiles = scanner.nextLine();
        new SpringApplicationBuilder(EurekaServerApplication.class).profiles(profiles.trim()).run(args);
	}

}


