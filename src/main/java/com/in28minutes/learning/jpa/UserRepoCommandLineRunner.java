package com.in28minutes.learning.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.learning.jpa.entity.User;
import com.in28minutes.learning.jpa.usingjpsnow.UserRepository;

@Component
public class UserRepoCommandLineRunner implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(UserRepoCommandLineRunner.class);
	
	
	@Autowired
	private UserRepository uds;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("kill","admin");
		User user1 = new User("nill","manager");
		//long insert = uds.insert(user);
		//long insert1 = uds.insert(user1);
		uds.save(user);
		uds.save(user1);
		log.info("new user is created " + user + " " + user1);
		Optional<User> userwithidone=uds.findById(1L);
		log.info("retrieved by nish" + userwithidone);
		
		List<User> users = uds.findAll();
		
		log.info("all users" + users);
		
	}

}
