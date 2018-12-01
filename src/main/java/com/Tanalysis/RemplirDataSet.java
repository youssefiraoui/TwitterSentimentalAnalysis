package com.Tanalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.Tanalysis.entities.DataSet;
import com.Tanalysis.repositories.DataSetRepository;

public class RemplirDataSet {
	
	
	
  public static ArrayList<DataSet> remplirDataSet(String src) throws IOException
  {
			String ligne="";
			ArrayList<DataSet> dataSetRepository = new ArrayList<>();
			try {
				@SuppressWarnings("resource")
				BufferedReader file = new BufferedReader(new FileReader(src));
				while ((ligne = file.readLine()) != null){
					String[] champ= ligne.split(";");
					DataSet dataSet = new DataSet("0",1);
				    dataSet.setClasse(Integer.parseInt(champ[0]));
				    dataSet.setTweet(champ[1]);
					dataSetRepository.add(dataSet);
					//create(new Table.Thematic(champ[0],champ[1]));
					System.out.println(champ[0]+" >>********************** <<"+champ[1]);
					
				}
				return dataSetRepository;
			} catch (FileNotFoundException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return dataSetRepository;
}
  
}
