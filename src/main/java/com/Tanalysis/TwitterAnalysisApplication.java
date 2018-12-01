package com.Tanalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.Tanalysis.entities.DataSet;
import com.Tanalysis.entities.Tweet;
import com.Tanalysis.repositories.DataSetRepository;
import com.Tanalysis.repositories.TweetRepository;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
@SpringBootApplication
public class TwitterAnalysisApplication {
	
	
	
//	@Autowired
	//TweetRepository repository;
	
	public static void main(String[] args) throws TwitterException, IOException {
		GetTwitetr twitetr = new GetTwitetr();
		ArrayList<Tweet> tweets = null;
		ArrayList<DataSet> dataSets = null;
		
		ConfigurableApplicationContext ctx = SpringApplication.run(TwitterAnalysisApplication.class, args);
		
		TweetRepository repository  = ctx.getBean(TweetRepository.class);
		DataSetRepository dataSetRepository  = ctx.getBean(DataSetRepository.class);
		
		/*
		dataSets = RemplirDataSet.remplirDataSet("C:\\Users\\IRAOUI\\Nouveau dossier\\TwitterAnalysis\\src\\main\\resources\\static\\DataSet1.csv");
		 if ( dataSets != null )
		 {
			 for (DataSet dataSet:dataSets)
			 {
				 dataSetRepository.save(dataSet);
			 }
			 System.out.println("finiiiiiiiiiiiiiiiiiiiiiiiish 2 ");

		 }*/
		tweets = twitetr.getTweets("trump");
		System.out.println("hhhhhhhhhhh"+ tweets.size());
		 if(tweets != null)
		 {
			// repository.saveAll(tweets);
				for( Tweet twita:tweets)
					repository.save(twita);
				System.out.println("finiiiiiiiiiiiiiiiiiiiiiiiish");


		 }
		
		}

	
}
