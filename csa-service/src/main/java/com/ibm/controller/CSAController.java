package com.ibm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.input.handler.TransformInput;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class CSAController {
	
	Logger logger = LoggerFactory.getLogger(CSAController.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EurekaClient eurekaClient;
	
	@HystrixCommand(fallbackMethod="reliable", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"), //default value , number of request which will trip the circuit
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="25000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "25000") })
	
	
	
	 
	@RequestMapping(value = "/csa", method = RequestMethod.POST)
	public String processTransaction(@RequestBody String recievedData) {
		
		logger.info("Starting CSA Transaction ");
		
		String response = invokeAribaService(recievedData);

		logger.info("Finishing CSA  transaction ");
		
		return response.toString();
	}
	
	
	public String reliable(@RequestBody String recievedData ) {
	
		logger.info(" Falling back for CSA Transaction ");
		
		String response = "<returnData><error>Circuit broke at Ariba Service for CSA transaction :"+new TransformInput().transformInput(recievedData).getApplicationTransactionNumber()+"</error></returnData>";


		logger.info("Finishing Fall back CSA  transaction ");
		
		return response.toString();
	}

	
	/**
	 * @param recievedData
	 * @return
	 */
	private String invokeAribaService(String recievedData) {
		InstanceInfo instanceInfo = null;
		String url = null;
		String response = null;
		Application aribaApplication = null;
		TransformInput transformInput = null;
		
		transformInput = new TransformInput();
		
		//perform transaction transform and update in Ariba service 
		aribaApplication = eurekaClient.getApplication("sapariba-service");
		instanceInfo = aribaApplication.getInstances().get(0);
		url = "http://" + instanceInfo.getIPAddr() + ":"+ instanceInfo.getPort() + "/" + "/ariba/";
		
		response = restTemplate.postForObject(url, transformInput.transformInput(recievedData), String.class);
		
		return response;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	
	
}

