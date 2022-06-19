package com.in28minutes.rest.webservices.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class VersioningController {

	@GetMapping("/personv1")
	 public PersonV1 personV1()
	 {
		 return new PersonV1("Bob Charlie");
	 }
	 
	@GetMapping("/personv2")
	 public PersonV2 personV2()
	 {
		 return new PersonV2(new Name("bob","charlie"));
	 }
	
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramV1()
	{
		return new PersonV1("naomni");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramV2()
	{
		return new PersonV2(new Name("namoi","capb"));
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 headerV1()
	{
		return new PersonV1("naomni");
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 headerV2()
	{
		return new PersonV2(new Name("namoi","capb"));
	}
	
	
	
	
	
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1()
	{
		return new PersonV1("naomni");
	}
	
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2()
	{
		return new PersonV2(new Name("namoi","capb"));
	}
}
