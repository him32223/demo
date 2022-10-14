package com.example.demo.entity;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class Service {
	@Autowired
	private UserRepository userRepository;
	
	// save, update user object
	public void saveUser(User user) {
		userRepository.save(user);
	}

}
