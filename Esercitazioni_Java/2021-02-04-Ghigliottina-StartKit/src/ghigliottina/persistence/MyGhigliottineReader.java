package ghigliottina.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import ghigliottina.model.*;

import java.util.regex.*;

public class MyGhigliottineReader implements GhigliottineReader
{
	private List<Ghigliottina> ghigliottine;
	
	@Override
	public List<Ghigliottina> getGhigliottine()
	{
		if (this.ghigliottine == null)
			throw new NullPointerException();
		
		return this.ghigliottine;
	}
	
	private Ghigliottina readOne(BufferedReader reader) throws IOException, BadFileFormatException
	{
		String lineaLetta = reader.readLine();
		
		if (lineaLetta == null)
			return null;
		
		if (lineaLetta.isBlank())
			lineaLetta = reader.readLine();
				
		if (!lineaLetta.contains("/") || !lineaLetta.contains("="))
			throw new BadFileFormatException("Una delle righe non contiene / o =");
				
		List<String> primaParola = new ArrayList<String>();
		List<String> secondaParola = new ArrayList<String>();
		List<Esatta> esattezza = new ArrayList<Esatta>();
		String parolaEsatta = null;
		
		List<Terna> terne = new ArrayList<Terna>();;
		Ghigliottina result;
	
		while (!lineaLetta.toLowerCase().contains("risposta"))
		{
			primaParola.add(lineaLetta.split("/")[0].trim());
			secondaParola.add(lineaLetta.split("/")[1].split("=")[0].trim());
			
			try
			{
				esattezza.add(Esatta.valueOf(lineaLetta.split("/")[1].split("=")[1].trim()));
			}
			catch(IllegalArgumentException | NullPointerException e)
			{
				throw new BadFileFormatException("La parola esatta della terna non è nè la prima nè la seconda");
			}
			
			lineaLetta = reader.readLine();
			
			if (lineaLetta.contains("-"))
				throw new BadFileFormatException("Manca la risposta corretta");
		}
		
		if (!lineaLetta.contains("Risposta esatta="))
			throw new BadFileFormatException("La locuzione *Risposta Esatta* è scritta male");
		
		if (!(reader.read() == 45))
			throw new BadFileFormatException("Mancano i separatori");
	
		//faccio in modo di saltare la linea composta da spazi:
		int caratteriDaSkippare = 0;
		
		reader.mark(100);
		while (reader.read() == 45)
			caratteriDaSkippare++;
		reader.reset();
		reader.skip(caratteriDaSkippare);
		
		parolaEsatta = lineaLetta.split("=")[1].trim();
		
		//creo le varie terne necessarie per la ghigliottina:
		for (int i = 0; i < primaParola.size(); i++)
			terne.add(new Terna(primaParola.get(i), secondaParola.get(i), esattezza.get(i)));
		
		result = new Ghigliottina(terne, parolaEsatta);
		
		return result;
	}

	@Override
	public List<Ghigliottina> readAll(BufferedReader reader) throws IOException, BadFileFormatException
	{
		if (reader == null)
			throw new IOException();
		
		List<Ghigliottina> result = new ArrayList<Ghigliottina>();
		Ghigliottina temp;
		
		while ((temp = this.readOne(reader)) != null)
			result.add(temp);
			
		this.ghigliottine = result;	
				
		return result;
	}
}
