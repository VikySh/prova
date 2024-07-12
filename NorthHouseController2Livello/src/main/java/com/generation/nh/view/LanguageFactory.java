package com.generation.nh.view;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class LanguageFactory 
{

	private static Map<String,Language> languages = 
			new HashMap<String,Language>();
	
	private static String LANGUAGEPATH = "C:\\Users\\FP80\\jaita121\\NorthHouse\\src\\main\\webapp\\WEB-INF\\languages\\";
	
	static
	{
		try
		{
			languages.put("ita", new FileLanguage(LANGUAGEPATH+"italian.txt"));
			languages.put("eng", new FileLanguage(LANGUAGEPATH+"english.txt"));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("ERRORE NELLA CREAZIONE DEI LINGUAGGI! NON POSSO PARTIRE!");
			System.exit(-1);
		}
	}
	
	
	public static Language make(String code)
	{		
		return	languages.containsKey(code) 		?
				languages.get(code)					:
				languages.get("eng") 				;
		
	}
	
	
	
}
