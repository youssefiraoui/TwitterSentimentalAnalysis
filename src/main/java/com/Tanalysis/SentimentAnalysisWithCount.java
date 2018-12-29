package com.Tanalysis;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.Tanalysis.entities.Result;

import com.Tanalysis.entities.Tweet;


import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import twitter4j.TwitterException;



public class SentimentAnalysisWithCount {

    DoccatModel model;
    private static int positive = 0;
    private static int negative = 0;
   
    public static void main(String[] args) throws IOException, TwitterException {
		
		
        SentimentAnalysisWithCount twitterCategorizer = new SentimentAnalysisWithCount();
        twitterCategorizer.trainModel();

        GetTwitetr twitetr = new GetTwitetr();
        ArrayList<Tweet> tweets = null;
      
        int result1 = 0;
        for (Tweet result : tweets)
        {
        	result1 = twitterCategorizer.classifyNewTweet(result.getTweet());
        	if (result1 ==4) {
                positive++;
            } else {
                negative++;
            }
        }
      
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Mahdi\\Desktop\\results.csv")); // Path to the results file
        bw.write("Positive Tweets," + positive);
        bw.newLine();
        bw.write("Negative Tweets," + negative);
        bw.close();
    }

    public void trainModel() {
        InputStream dataIn = null;
        try {
        	
            dataIn = new FileInputStream("C:\\Users\\Mahdi\\Desktop\\P\\TwitterSentimentalAnalysis\\src\\main\\resources\\static\\DataSet.txt"); // Path to dataset
            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);
            // Specifies the minimum number of times a feature must be seen
            int cutoff = 2;
            int trainingIterations = 30;
            model = DocumentCategorizerME.train("en", sampleStream, cutoff,
                    trainingIterations);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataIn != null) {
                try {
                    dataIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int classifyNewTweet(String tweet) throws IOException {
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);
        double[] outcomes = myCategorizer.categorize(tweet);
        String category = myCategorizer.getBestCategory(outcomes);

        System.out.print("-----------------------------------------------------\nTWEET :" + tweet + " ===> ");
        if (category.equalsIgnoreCase("4")) {
            System.out.println(" POSITIVE ");
            return 1;
        } else {
            System.out.println(" NEGATIVE ");
            return 0;
        }

    }
}