package unident.persistence;

import java.io.*;
import java.util.*;

import unident.model.AttivitaFormativa;
import unident.model.Ssd;
import unident.model.Tipologia;

public class UniDentPianoDidatticoReader implements PianoDidatticoReader
{
	private AttivitaFormativa readLine(String lineaLetta) throws BadFileFormatException
	{		
		if (lineaLetta.isBlank())
			return null;
		
		if (lineaLetta.split("\t+").length != 5 && lineaLetta.split("\t+").length != 6)
			throw new BadFileFormatException("Numero di elementi errato");

		if (lineaLetta.split("\t+").length == 5 && (!lineaLetta.split("\t+")[3].equals("E") &&
				!lineaLetta.split("\t+")[3].equals("F")))
			throw new BadFileFormatException("Settore scientifico-disciplinare mancante");
		
		int cfu;
		try
		{
			if (lineaLetta.split("\t+").length == 6)
				cfu = Integer.parseInt(lineaLetta.split("\t+")[5]);
			else
				cfu = Integer.parseInt(lineaLetta.split("\t+")[4]);
		}
		catch(NumberFormatException e)
		{
			throw new BadFileFormatException("Valore cfu mancante o non intero");
		}
		
		if (lineaLetta.split("\t+").length == 6)
			return new AttivitaFormativa(lineaLetta.split("\t+")[1], Tipologia.valueOf(lineaLetta.split("\t+")[3]),
				Ssd.of(lineaLetta.split("\t+")[4]), cfu);
		else
			return new AttivitaFormativa(lineaLetta.split("\t+")[1], Tipologia.valueOf(lineaLetta.split("\t+")[3]),
				Ssd.SENZASETTORE, cfu);
	}
	
	@Override
	public Set<AttivitaFormativa> readAll(Reader rdr) throws IOException
	{
		if (rdr == null)
			throw new BadFileFormatException("Problema di lettura");
		
		BufferedReader bf = new BufferedReader(rdr);
		String lineaLetta;
		AttivitaFormativa temp;
		Set<AttivitaFormativa> result = new HashSet<AttivitaFormativa>();
		
		while ((lineaLetta = bf.readLine()) != null)
			if ((temp = readLine(lineaLetta)) != null)
				result.add(temp);
		
		return result;
	}
}
