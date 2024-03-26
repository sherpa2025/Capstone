package com.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

	@Query("SELECT p FROM Photo p WHERE p.name LIKE %:keyword% OR p.photoCategory.name LIKE %:keyword%")
	Optional<Photo> searchPhoto(@Param("keyword") String keyword);

//	List<Photo> findByCategory(String photoCategory);
}