package com.Tanalysis;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GetTwitetr {

	public static void main(String[] args) throws TwitterException {
		Paging pg = new Paging();
	    String userName = "SergiSamper";
	    pg.setCount(200);
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	      .setOAuthConsumerKey("jCuMds8hkjry8JV8JDEuDVH9o")
	      .setOAuthConsumerSecret("psgKB7nb05kZqoD2ZFPrG78OqbObHySWUEhcLFcZ03qVMlsCwp")
	      .setOAuthAccessToken("814999527451148288-PVho6BBmmcQbSVKOHBt3E5jbPJM6Krl")
	      .setOAuthAccessTokenSecret("a30jMaE70P2kefPFOzrfGTlA06okUcifkjJB9g2JWq4Ih");
	    TwitterFactory nn=new TwitterFactory(cb.build());
        twitter4j.Twitter twitter=nn.getInstance();
        List<Status> stats=twitter.getHomeTimeline();
        
        for (Status status:stats ) {
            //System.out.println("*********Place Tweets :**************\npalce country :"+status.getPlace().getCountry()+"\nplace full name :"+status.getPlace().getFullName()+"\nplace name :"+status.getPlace().getName()+"\nplace id :"+status.getPlace().getId()+"\nplace tipe :"+status.getPlace().getPlaceType()+"\nplace addres :"+status.getPlace().getStreetAddress());
            System.out.println("Status id : "+status.getId());
            System.out.println("id user : "+status.getUser().getId());
            System.out.println("Length status :  "+status.getText().length());
            System.out.println("@" + status.getUser().getScreenName() +" . "+status.getCreatedAt()+ " : "+status.getUser().getName()+"--------"+status.getText());
            System.out.println("url :"+status.getUser().getURL());
        }

/*
	  Twitter twitter = new TwitterFactory(cb.build()).getInstance();
	  int numberOfTweets = 1000000;
	  long lastID = Long.MAX_VALUE;
	  
	  
	  ArrayList<Status> statuses = new ArrayList<>();
	  int pageno = 1;/*
	  while(true) {
	      try {
	          System.out.println("getting tweets");
	          int size = statuses.size(); // actual tweets count we got
	          Paging page = new Paging(pageno, 200);
	          statuses.addAll(twitter.getHomeTimeline());//getUserTimeline(userName, page)
	          System.out.println("total got : " + statuses.size());
	          if (statuses.size() == size) { break; } // we did not get new tweets so we have done the job
	          pageno++;
	          sleep(1000); // 900 rqt / 15 mn <=> 1 rqt/s
	          }
	      catch (TwitterException e) {
	          System.out.println(e.getMessage());
	          }
	      } */

	    }
	static void sleep(long ms) {
	    try { Thread.sleep(ms); }
	    catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
	    }
	}


