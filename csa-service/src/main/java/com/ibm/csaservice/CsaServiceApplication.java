package com.ibm.csaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@ComponentScan(basePackages = { "com.ibm.csaservice","com.ibm.controller"} )
public class CsaServiceApplication {

	
	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(CsaServiceApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter("csaservice.pid"));
		springApplication.run(args);
	}
}
