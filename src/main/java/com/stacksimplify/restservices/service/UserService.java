package com.stacksimplify.restservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.exceptions.UserExistException;
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

	public User createUser(User user) throws UserExistException {
		User existingUserWiththeSameName = userRepository.findByUserName(user.getUserName());
		if (existingUserWiththeSameName != null) {
			throw new UserExistException("User already exists");
		}
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
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not fount in User repo, please enter the correct user id.");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	
	//deleteUserById
	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User to be delete not fount in User repo, please enter the correct user id.");
		}
		userRepository.deleteById(id);
	}
	
	
	//getUserByUsername
	
	public User getUserByUsername(String username) {
		return userRepository.findByUserName(username);
	}

}
