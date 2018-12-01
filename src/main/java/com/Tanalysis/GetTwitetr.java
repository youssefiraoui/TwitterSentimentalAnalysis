package com.Tanalysis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Tanalysis.entities.Tweet;
import com.Tanalysis.repositories.TweetRepository;

import twitter4j.HashtagEntity;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GetTwitetr {
	
	
    public ArrayList<Tweet> getTweets(String search) throws TwitterException {
    	//Query query = new Query(search);
        QueryResult result;
    	Paging pg = new Paging(1, 200);
	    String userName = search;       
	    pg.setCount(200);
        ArrayList<Tweet> tweets = new ArrayList<>();
	    Twitter twitter= new TwitterFactory().getSingleton();
	   
	    	Query query = new Query(search);
	    	query.setCount(100);
	    	query.since("2016-05-28");
	    	try {
	      	  result = twitter.search(query);
	            List<Status> stats = result.getTweets();
	            
	            for (Status status:stats ) {
	            	
	    			Tweet t = new Tweet();
	    			          
	    			t.setUser_id(String.valueOf(status.getUser().getId()));
	    			t.setTweet(status.getText());
	    			t.setUsername(status.getUser().getName());
	    			t.setCreation_date(status.getCreatedAt());
	    			
	    			tweets.add(t);
	            }
	        }catch(TwitterException x) {
	            System.out.println(x.getMessage());
	          }
	    
       // List<Status> stats=twitter.getUserTimeline(userName, pg);///twitter.getHomeTimeline();
      
	    return tweets;
			        	
		
    
	}
}


