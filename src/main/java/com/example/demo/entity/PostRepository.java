package com.example.demo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	@Query("SELECT p FROM Post p WHERE p.user.id = :id")
	public List<Post> findAllPostsByUserId(Integer id);
	
	@Query("SELECT p FROM Post p WHERE p.status = TRUE")
	public List<Post> findAllOpeningJobs();
}
