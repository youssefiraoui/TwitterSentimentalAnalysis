package com.Tanalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.Tanalysis.entities.DataSet;
import com.Tanalysis.entities.Result;
import com.Tanalysis.entities.SearchedWord;
import com.Tanalysis.entities.Tweet;
import com.Tanalysis.repositories.DataSetRepository;
import com.Tanalysis.repositories.ResultRepository;
import com.Tanalysis.repositories.SearchWordRepository;
import com.Tanalysis.repositories.TweetRepository;
import com.Tanalysis.weka.ClassifierClass;

import opennlp.tools.doccat.DoccatModel;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@SpringBootApplication
public class TwitterAnalysisApplication {
	DoccatModel model;
	private static int positive = 0;
	private static int negative = 0;

//	@Autowired
	// TweetRepository repository;

	public static void main(String[] args) throws TwitterException, IOException {

		GetTwitetr twitetr = new GetTwitetr();
		ArrayList<Tweet> tweets = null;
		ArrayList<DataSet> dataSets = null;

		ConfigurableApplicationContext ctx = SpringApplication.run(TwitterAnalysisApplication.class, args);
/*
		TweetRepository repository = ctx.getBean(TweetRepository.class);
		DataSetRepository dataSetRepository = ctx.getBean(DataSetRepository.class);
		SearchWordRepository Searchedrepository = ctx.getBean(SearchWordRepository.class);
		ResultRepository resultRepo  = ctx.getBean(ResultRepository.class);

		dataSets = RemplirDataSet.remplirDataSet(
				"C:\\Users\\Mahdi\\Desktop\\P\\TwitterSentimentalAnalysis\\src\\main\\resources\\static\\DataSet1.csv");
		if (dataSets != null) {
			for (DataSet dataSet : dataSets) {
				dataSetRepository.save(dataSet);
			}
			System.out.println("finiiiiiiiiiiiiiiiiiiiiiiiish 2 ");

		}

		SentimentAnalysisWithCount twitterCategorizer = new SentimentAnalysisWithCount();
		twitterCategorizer.trainModel();
		
		int result1 = 0;

		String search = "trump";
		SearchedWord word = new SearchedWord(search);
		Searchedrepository.save(word);

		tweets = twitetr.getTweets(search);
		System.out.println("hhhhhhhhhhh" + tweets.size());
		if (tweets != null) {
			// repository.saveAll(tweets);
			for (Tweet twita : tweets)
			{
				twita.setWord(word);
				repository.save(twita);
				result1 = twitterCategorizer.classifyNewTweet(twita.getTweet());
				if (result1 == 4) {
					positive++;
				} else {
					negative++;
				}
			}
			
			Result result = new Result();
			result.setDate_result(new Date());
			result.setNumberTweetsAnalysis(tweets.size());
			result.setTweetsNeg(negative);
			result.setTweetsPos(positive);
			result.setTweetsNeutral(0);

			result.setWord(word);
			resultRepo.save(result);
			System.out.println("finiiiiiiiiiiiiiiiiiiiiiiiish");

		}
		// ClassifierClass.Classify(1);


		BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Mahdi\\Desktop\\results.csv")); // Path to the
																											// results
																											// file
		bw.write("Positive Tweets," + positive);
		bw.newLine();
		bw.write("Negative Tweets," + negative);
		bw.close();
		System.out.println("finiiiiiiiiiiiiiiiiiiiiiiiish 2");*/
	}

}
