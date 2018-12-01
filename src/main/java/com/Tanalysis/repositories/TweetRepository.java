package com.Tanalysis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.Tanalysis.entities.Tweet;

@RepositoryRestResource
@CrossOrigin("*")
public interface TweetRepository extends JpaRepository<Tweet, Long> {
	
	

}
