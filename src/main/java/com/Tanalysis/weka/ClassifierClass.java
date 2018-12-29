package com.Tanalysis.weka;

import java.util.Random;
import weka.core.*;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

public class ClassifierClass {
	
	public static Instances addClass(Instances data){
		/*
		 * ajouter l'attribute class(yes,no)
		 */
		data.deleteAttributeAt(data.numAttributes()-1);
		FastVector<String> champ = new FastVector(2);
		champ.addElement("0");
		champ.addElement("4");
		
		Attribute classe = new Attribute("classe", champ);
		data.insertAttributeAt(classe, data.numAttributes());
		
		return data;
	}
	


	public static void Classify(int m){
		
	try {
		InstanceQuery query = new InstanceQuery();
		query.setDatabaseURL("jdbc:mysql://localhost:3306/burger");
		query.setUsername("root");
		query.setPassword("");
		/*
		 * selection table tweetscalcculee
		 */
		query.setQuery("SELECT  `tweet`, `classe` FROM `data_set`");
		Instances tweetscalculee = query.retrieveInstances();
		/*
		 * ajouter l'attribute class(yes,no)
		 */
		tweetscalculee= addClass(tweetscalculee);
		/**************/
		tweetscalculee.setClassIndex(tweetscalculee.numAttributes()-1);
		/*
		 * selection table classifiaction
		 */
		query.setQuery("SELECT  `tweet`, `classe` FROM `tweet`");
		Instances classification = query.retrieveInstances();
		/*
		 * ajouter l'attribute class(yes,no)
		 */
		//classification=addClass(classification);
		/**************/
		classification.setClassIndex(classification.numAttributes()-1);
		query.close();
		
		System.out.println(classification);
		FilteredClassifier fc = new FilteredClassifier();
		
		
                    Classifier[] model = { 
			new J48(), // a decision tree
			new PART(), 
			new DecisionTable(),//decision table majority classifier
			new DecisionStump(), //one-level decision tree
			new NaiveBayes(),
			new SMO()
	};
		
		fc.setClassifier(model[m]);
	
		fc.buildClassifier(classification);
		System.out.println(model.toString());
		
		
		
		Classifier cls = new J48();
		cls.buildClassifier(classification);
		// evaluate classifier and print some statistics
		Evaluation eval = new Evaluation(classification);
		eval.evaluateModel(cls, tweetscalculee);
		System.out.println(eval.toSummaryString("\nResults\n======\n", false));
		//Vector data = new Vector(tweetscalculee.numInstances());
                               
		for (int i = 0; i < tweetscalculee.numInstances(); i++) {
			double pred = cls.classifyInstance(tweetscalculee.instance(i));
			System.out.print("ID: " + (tweetscalculee.instance(i).value(0)+1)); 
			System.out.print(", actual: " + tweetscalculee.classAttribute().value((int) tweetscalculee.instance(i).classValue()));
			System.out.println(", predicted: " + tweetscalculee.classAttribute().value((int) pred));
			/*DatabaseFactory d = new DatabaseFactory();
			Table.Tweets t=d.getTweetDAO().select(""+(i+1));
			Resultat r = new Resultat(Connecty.getInstance());
                            Table.Resultat res = new Table.Resultat(i+1, t.getUserName(), tweetscalculee.instance(i).stringValue(0), t.getLocation(), t.getDate(), tweetscalculee.classAttribute().value((int) pred));
                            
			r.create(res);*/
			/*
                            data[i][0]=res.getId();
                            data[i][1]=res.getUserName();
                            data[i][2]=res.getContenu();
                            data[i][3]=res.getLocation();
                            data[i][4]=res.getDate();
                            data[i][5]=res.getClasse();*/
                            
		}
		
			
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
}
}

