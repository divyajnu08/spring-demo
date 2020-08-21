package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@RestController
@EnableHystrix
public class HelloController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	@RequestMapping(value = "/hello")
	   @HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
	      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
	   })
	
	public String hello() throws InterruptedException {
	      Thread.sleep(3000);
	      return "Welcome Hystrix";
	   }
	   private String fallback_hello() {
	      return "Request fails. It takes long time to response";
	   }

	   
	   @RequestMapping(value = "/getDiscount", method = RequestMethod.GET, produces = "application/json")
		public Product getQuestions(@RequestParam(required = true) String type) {

			Product product = new Product();
			product.setType(type);
			service.getProductDiscount(product);
			return product;
		}


}
