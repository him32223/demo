package com.example.demo.entity;






import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public void registerNewUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
	}
	 // return all user's information, but hey, we may want to retrieve all except password right?! think about it!
		public List<User> retrieveAllUserProfile() {
			return userRepository.findAll();
		}
		

}
