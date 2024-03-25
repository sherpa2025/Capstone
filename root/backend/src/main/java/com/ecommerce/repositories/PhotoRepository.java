package com.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

	@Query("SELECT p FROM Photo p WHERE p.name LIKE %:keyword% OR p.photoCategory.name LIKE %:keyword%")
	List<Photo> searchPhoto(@Param("keyword") String keyword);

//	List<Photo> findByCategory(String photoCategory);
}
