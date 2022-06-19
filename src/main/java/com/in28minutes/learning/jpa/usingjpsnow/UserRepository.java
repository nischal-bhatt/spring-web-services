package com.in28minutes.learning.jpa.usingjpsnow;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.learning.jpa.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
