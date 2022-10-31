package com.example.demo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.example.demo.CustomUserDetails.CustomUserDetails;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	
public void save(UserRepository user);

@Query("SELECT u FROM User u where u.email = :email")
public User findUserByEmail(String email);
			
      @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
		public User findUserByVerificationCode(String code);
	
	@Query("SELECT u FROM User u WHERE u.username =:username")
	public User findUserByUsername(String username);
	
	//dont put bio it was messing with the code

	@Query(value = "SELECT s FROM User s WHERE s.username LIKE '%' || :keyword || '%'"
			+ " OR s.email LIKE '%' || :keyword || '%'"
			+ " OR s.firstname LIKE '%' || :keyword || '%'"
			+ " OR s.lastname LIKE '%' || :keyword || '%'"
			+ " OR s.company LIKE '%' || :keyword || '%'"
			+ " OR s.city LIKE '%' || :keyword || '%'"
			+ " OR s.country LIKE '%' || :keyword || '%'"
			)
	public List<User> search(@Param("keyword") String keyword);
	
	@Query("SELECT u FROM User u WHERE u.resetPasswordToken = ?1")
	public User findUserByResetPasswordToken(String token);

}


	
