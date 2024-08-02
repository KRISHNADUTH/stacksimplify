package com.stacksimplify.restservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.exceptions.UserNotFoundException;
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

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("User not fount in User repo");
		}
		return user.get();
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
