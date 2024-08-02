package com.stacksimplify.restservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacksimplify.restservices.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByUserName(String username);
    
}
