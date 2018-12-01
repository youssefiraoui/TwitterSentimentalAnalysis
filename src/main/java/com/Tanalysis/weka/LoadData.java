package com.Tanalysis.weka;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class LoadData {
	
	public static void main(String[] args)
	{
		try {
			DataSource source = new DataSource("C:\\Program Files\\Weka-3-8\\data\\weather.numeric.arff");
			Instances dataset = source.getDataSet();
			System.out.println(dataset.toSummaryString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
