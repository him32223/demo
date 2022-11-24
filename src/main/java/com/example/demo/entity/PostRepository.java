package com.example.demo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	public void save(PostRepository post);
	@Query("SELECT p FROM Post p WHERE p.user.id = :id")
	public List<Post> findAllPostsByUserId(Integer id);
	
	@Query("SELECT p FROM Post p WHERE p.status = TRUE")
	public List<Post> findAllOpeningJobs();
	
	@Query(value = "SELECT p FROM Post p WHERE p.position LIKE '%' || :keyword || '%'"
			+ " OR p.position LIKE '%' || :keyword || '%'"
			+ " OR p.company LIKE '%' || :keyword || '%'"
			+ " OR p.city LIKE '%' || :keyword || '%'"
			+ " OR p.country LIKE '%' || :keyword || '%'"
			)
	public List<Post> search(@Param("keyword") String keyword);
	
}
  