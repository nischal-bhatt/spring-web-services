package com.in28minutes.rest.webservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET, path= "/hello-world")
	public String helloWorld()
	{
		return "hello world";
	}
	
	@GetMapping("/hi")
	public String hi()
	{
		return "hi";
	}
	
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("hello world");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("hello world, %s",name));
	}
	
	
}
