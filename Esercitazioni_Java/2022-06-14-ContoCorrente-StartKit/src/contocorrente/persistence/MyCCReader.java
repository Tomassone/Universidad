package contocorrente.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;

import contocorrente.model.Movimento;
import contocorrente.model.ContoCorrente;
import contocorrente.model.Tipologia;


public class MyCCReader implements CCReader {

	/*  CC N.123456789
	 * 	31/01/22 31/01/22	10.317,81 	SALDO INIZIALE A VOSTRO CREDITO
		02/02/22 02/02/22	- 2,90 		IMP.BOLLO CC
		07/02/22 07/02/22 	- 61,59 	SPESE GESTIONE
		...
	 * */

	private long leggiPrimaRiga(String lineaLetta) throws BadFileFormatException
	{
		long result;
		
		if (lineaLetta.split(" ").length != 2 && lineaLetta.split(" ").length != 3)
			throw new BadFileFormatException("La prima riga non ha abbastanza elementi");
		
		if (!lineaLetta.split(" ")[0].trim().toUpperCase().equals("CC"))
			throw new BadFileFormatException("La prima riga non ha la sigla CC");
		
		if (!lineaLetta.split(" ")[1].trim().toUpperCase().contains("N."))
			throw new BadFileFormatException("La prima riga non ha N.");
				
		if (lineaLetta.split(" ").length == 2)
		{
			try
			{
				result = Long.parseLong(lineaLetta.split(" ")[1].trim().toUpperCase().replace("N.", ""));
			}
			catch(NumberFormatException e)
			{
				throw new BadFileFormatException("La prima riga non contiene numero");
			}
		}
		else
		{			
			try
			{
				result = Long.parseLong(lineaLetta.split(" ")[2].trim());
			}
			catch(NumberFormatException e)
			{
				throw new BadFileFormatException("La prima riga non contiene numero");
			}
		}
		
		return result;
	}
	
	@Override
	public ContoCorrente readCC(Reader rdr) throws IOException
	{
		// *** DA FARE *** 
		// vedi testo per le specifiche
		
		if (rdr == null)
			throw new BadFileFormatException("Reader null");
		
		BufferedReader bf = new BufferedReader(rdr);
		String lineaLetta, causale;
		
		Long numeroConto = this.leggiPrimaRiga(bf.readLine());
		
		NumberFormat cf = NumberFormat.getNumberInstance(Locale.ITALY);
		Double valore;
		LocalDate dataContabile;
		LocalDate dataValuta;
		Tipologia tipologia;
		
		ContoCorrente result = new ContoCorrente(numeroConto);
		
		while ((lineaLetta = bf.readLine()) != null)
		{				
			if (lineaLetta.trim().split("\t+").length != 4)
			{
				System.out.println(lineaLetta + " " + lineaLetta.split("\t").length);
				for (String riga : lineaLetta.split("\t"))
					System.out.println(riga);
			}
			
			if (lineaLetta.trim().split("\t").length != 4)
				throw new BadFileFormatException("Una riga (non la prima) non ha abbastanza elementi");
						
			if (lineaLetta.split("\t")[0].contains(" "))
				throw new BadFileFormatException("Una riga (non la prima) ha un separatore sbagliato");
			
			try
			{
				dataContabile = LocalDate.parse(lineaLetta.split("\t")[0].trim(), 
						DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.ITALY));
			}
			catch (DateTimeParseException e)
			{
				throw new BadFileFormatException("Data contabile mal formattata");
			}
			
			try
			{
				dataValuta = LocalDate.parse(lineaLetta.split("\t")[1].trim(), 
						DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.ITALY));
			}
			catch (DateTimeParseException e)
			{
				throw new BadFileFormatException("Data valuta mal formattata");
			}
						
			try
			{
				valore = cf.parse(lineaLetta.split("\t")[2].trim().replace(" ", "")).doubleValue();
			}
			catch (ParseException e)
			{
				throw new BadFileFormatException("Valore transazione mal formattato");
			}
			
			causale = lineaLetta.split("\t")[3].trim();
			
			if (causale.contains("SALDO"))
				tipologia = Tipologia.SALDO;
			else
			{
				if (valore > 0)
					tipologia = Tipologia.ACCREDITO;
				else if (valore < 0)
					tipologia = Tipologia.ADDEBITO;
				else
					tipologia = Tipologia.NULLO;
			}
			
			result.aggiungi(new Movimento(dataContabile, dataValuta, tipologia, valore, causale));
		}
		
		return result; // (fake)
	}
}