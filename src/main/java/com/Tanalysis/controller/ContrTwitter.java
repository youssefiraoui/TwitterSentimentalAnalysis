package com.Tanalysis.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

import com.Tanalysis.GetTwitetr;
import com.Tanalysis.SentimentAnalysisWithCount;
import com.Tanalysis.entities.Result;
import com.Tanalysis.entities.SearchedWord;
import com.Tanalysis.entities.Tweet;
import com.Tanalysis.entities.User;
import com.Tanalysis.repositories.ResultRepository;
import com.Tanalysis.repositories.SearchWordRepository;
import com.Tanalysis.repositories.TweetRepository;
import com.Tanalysis.repositories.UserRepository;

import opennlp.tools.doccat.DoccatModel;
import twitter4j.TwitterException;
@CrossOrigin()

@RestController
public class ContrTwitter {
	DoccatModel model;


	GetTwitetr engineSearch = new GetTwitetr();
	
	@Autowired
	TweetRepository repository;
	@Autowired
	UserRepository userRepo;
	@Autowired
	ResultRepository resultRepo;
	@Autowired                    
	SearchWordRepository reSearchWordRepository;   
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Collection<Tweet> getTweetsBySearch(
			@RequestParam(name = "search") String search)
	{


		
		 return repository.findAll();	}
	ArrayList<Tweet> tweets = null;
	@CrossOrigin
	@RequestMapping(value ="/search/{id}", method = RequestMethod.GET)
	public ArrayList<Tweet>  user2(@PathVariable String id) throws TwitterException, IOException {
		ArrayList<Tweet> tweets = null;
		SearchedWord word = new SearchedWord(id);
		//User user = new User("admin","a@ad.com","12345678");
		//userRepo.save(user);
		User user = userRepo.findOne((long) 1);
		word.setUser(user);
		reSearchWordRepository.save(word);
		SentimentAnalysisWithCount twitterCategorizer = new SentimentAnalysisWithCount();
		twitterCategorizer.trainModel();
		int result1 = 0;
	  //  SearchedWord  searchedWord = reSearchWordRepository.findSearchedWordByWord(id);
		GetTwitetr twitetr = new GetTwitetr();
		int positive = 0;
		int negative = 0;

		tweets = twitetr.getTweets(id);
		 if(tweets != null)
				 {String   test=("ID is "+id);
        			// repository.saveAll(tweets);
        				for( Tweet twita:tweets)
        				{
        					twita.setWord(word);	
        					repository.save(twita); 
        					result1 = twitterCategorizer.classifyNewTweet(twita.getTweet());
        					if (result1 == 1) {
        						positive++;
        					} else  {
        						negative++;
        					}
        				}
        				Result result = new Result();
        				result.setDate_result(new Date());
        				result.setNumberTweetsAnalysis(negative + positive);
        				result.setTweetsNeg(negative);
        				result.setTweetsPos(positive);
        				result.setTweetsNeutral(0);
        				result.setWord(word);
        				resultRepo.save(result);
        				System.out.println("finiiiiiiiiiiiiiiiiiiiiiiiish");


        		 }


		return (tweets);
	}

}
