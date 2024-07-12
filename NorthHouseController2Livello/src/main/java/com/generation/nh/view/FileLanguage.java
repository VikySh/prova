package com.generation.nh.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileLanguage implements Language
{
	Map<String,String> dictionary =
			new HashMap<String,String>();
	
	/**
	 * Entra il percorso completo di un file contenente chiavi e valori
	 * Leggo il file, e creo una mappa con chiavi e valori
	 * la chiave è il codice del testo da tradurre
	 * il valore è il testo tradotto in una data lingua
	 * @param filename
	 * @throws FileNotFoundException
	 */
	FileLanguage(String filename) throws FileNotFoundException 
	{
		Scanner reader = new Scanner(new File(filename));
		
		while(reader.hasNextLine())
		{
			String line = reader.nextLine();
			String[] parts = line.split("=");
			dictionary.put(parts[0], parts[1]);
		}
		reader.close();		
	}

	/**
	 * Mi chiedono di tradurre una chiave.
	 * La cerco nella mappa.
	 * Se non la trovo la restituisco
	 */
	@Override
	public String translate(String key) 
	{
		return 	dictionary.containsKey(key) 	?
				dictionary.get(key) 			:
				key								;
	}

	
	
	
	
}
