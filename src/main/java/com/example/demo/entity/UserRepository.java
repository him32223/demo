package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.example.demo.CustomUserDetails.CustomUserDetails;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u where u.email = :email")
	public User findUserByEmail(String email);
	
public void save(UserRepository user);
			
      @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
		public User findUserByVerificationCode(String code);
	
	@Query("SELECT u FROM User u WHERE u.username =:username")
	public User findUserByUsername(String username);


}
	

	
