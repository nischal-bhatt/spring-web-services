package com.in28minutes.learning.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.learning.jpa.entity.User;
import com.in28minutes.learning.jpa.service.UserDaoService;

@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);
	
	
	@Autowired
	private UserDaoService uds;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("jack","admin");
		User user1 = new User("jack","manager");
		long insert = uds.insert(user);
		long insert1 = uds.insert(user1);
		log.info("new user is created " + user + " " + user1);
	}

}
