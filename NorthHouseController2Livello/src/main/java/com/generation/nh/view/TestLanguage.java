package com.generation.nh.view;

import java.io.FileNotFoundException;

public class TestLanguage 
{

	public static void main(String[] args) throws FileNotFoundException 
	{
	
		Language italian =
				new FileLanguage("C:\\Users\\FP80\\jaita121\\NorthHouse\\src\\main\\webapp\\WEB-INF\\languages\\italian.txt");

		Language english =
				new FileLanguage("C:\\Users\\FP80\\jaita121\\NorthHouse\\src\\main\\webapp\\WEB-INF\\languages\\english.txt");

		
		System.out.println(italian.translate("OURHOUSES"));
		System.out.println(italian.translate("CIPPIRIMERLO"));
		
		
		
	}

}
