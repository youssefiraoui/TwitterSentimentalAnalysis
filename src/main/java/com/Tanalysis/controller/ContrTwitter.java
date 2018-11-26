package com.Tanalysis.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

import com.Tanalysis.GetTwitetr;
import com.Tanalysis.entities.Tweet;
import com.Tanalysis.repositories.TweetRepository;


import twitter4j.TwitterException;
@CrossOrigin()

@RestController
public class ContrTwitter {
	
	GetTwitetr engineSearch = new GetTwitetr();
	
	@Autowired
	TweetRepository repository;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Collection<Tweet> getTweetsBySearch(
			@RequestParam(name = "search") String search)
	{
		//ArrayList<Tweet> tweets = null;
		/*try {
			 tweets = engineSearch.getTweets(search);
			 if(tweets != null)
			 {
				// repository.saveAll(tweets);
					for( Tweet twita:tweets)
						repository.save(twita);
					System.out.println("finiiiiiiiiiiiiiiiiiiiiiiiish");


			 }
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		 return repository.findAll();	}

}
