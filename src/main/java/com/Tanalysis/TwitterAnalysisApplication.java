package com.Tanalysis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@SpringBootApplication
public class TwitterAnalysisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TwitterAnalysisApplication.class, args);
		   
		}

	@Override
	public void run(String... args) throws Exception {
		 ConfigurationBuilder cb = new ConfigurationBuilder();
		    cb.setDebugEnabled(true)
		      .setOAuthConsumerKey("jLTcD4sJU0ZE0U22ZsFc3rUkg")
		      .setOAuthConsumerSecret("WQNZujZPac8TgI9XckfqpVjrp9u1nd0VNTFzUbPWRK8RlCvInO")
		      .setOAuthAccessToken("4756525031-bDgVuVS00KS99r8Wlz4hoZTPir0qaieu1qjyca0")
		      .setOAuthAccessTokenSecret("kCVmCYKHyKw6JmRMGiojpzNF9YIFxoUC2WaGkewVuAWKt");
		    TwitterFactory tf = new TwitterFactory(cb.build());
		    Twitter twitter = tf.getInstance();

		    int pageno = 1;
		    String user = "indtravel";
		    List<Status> statuses = new ArrayList<Status>();
		    while (true) {
		        try {
		            int size = statuses.size();
		            Paging page = new Paging(pageno++, 100);
		            statuses.addAll(twitter.getUserTimeline(user, page));
		            System.out.println("***********************************************");
		            System.out.println("Gathered " + twitter.getUserTimeline(user, page).size() + " tweets");

		            //get status dan user
		            for (Status status: twitter.getUserTimeline(user, page)) {
		                //System.out.println("*********Place Tweets :**************\npalce country :"+status.getPlace().getCountry()+"\nplace full name :"+status.getPlace().getFullName()+"\nplace name :"+status.getPlace().getName()+"\nplace id :"+status.getPlace().getId()+"\nplace tipe :"+status.getPlace().getPlaceType()+"\nplace addres :"+status.getPlace().getStreetAddress());
		                System.out.println("Status id : "+status.getId());
		                System.out.println("id user : "+status.getUser().getId());
		                System.out.println("Length status :  "+status.getText().length());
		                System.out.println("@" + status.getUser().getScreenName() +" . "+status.getCreatedAt()+ " : "+status.getUser().getName()+"--------"+status.getText());
		                System.out.println("url :"+status.getUser().getURL());
		            }
		            if (statuses.size() == size)
		                break;
		        }catch(TwitterException e) {
		            e.printStackTrace();
		        }
		    }
		    System.out.println("Total: "+statuses.size());
		
	}
	
}
