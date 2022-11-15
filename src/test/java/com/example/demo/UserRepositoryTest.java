package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;

import net.bytebuddy.utility.RandomString;

@DisplayName("Unit Tests of UserRepository Class")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUser() {
		User user = new User();
		user.setUsername("bad");
		user.setEmail("add@gmail.com");
		user.setPassword("boo");
		
		User savedUser = entityManager.persistAndFlush(user); // retrive object using em
		
		assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void testNumberRegisteredUsers() {
		long count = userRepo.count();
		System.err.println("size: " + count);
		assertThat(count).isEqualTo(userRepo.findAll().size());
	}
	
	@Test
	public void testSearchByKeyword() {
		String keyword = "bryan";
		List<User> results = userRepo.search(keyword);
		
		assertThat(results.size()).isEqualTo(userRepo.search(keyword).size());
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "bryanisawesome209@gmail.com";
		User user = userRepo.findUserByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}
	
	@Test
	public void testFindUserByUsername() {
		String username = "admin";
		User user = userRepo.findUserByUsername(username);
		
		assertThat(user.getUsername()).isEqualTo(username);
	}
	
	
	@Test
	public void testDeleteUserById() {
		User user = userRepo.findUserByUsername("bad");		
		userRepo.deleteById(user.getId()); // delete the user by id
		
		User deletedUser = userRepo.findUserByUsername("bad"); // test retrieve it back
		
		assertThat(deletedUser).isEqualTo(null);
	}
}
