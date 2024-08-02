package com.stacksimplify.restservices.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.repo.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


	//updateUserById
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
		
	}
	
	
	//deleteUserById
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			
		}
	}
	
	
	//getUserByUsername
	
	public User getUserByUsername(String username) {
		return userRepository.findByUserName(username);
	}

}
