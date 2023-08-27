package bancaore.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

import bancaore.model.Causale;
import bancaore.model.Cedolino;
import bancaore.model.SettimanaLavorativa;
import bancaore.model.VoceCedolino;


public class MyCedolinoReader implements CedolinoReader {

	/*  Dipendente:	Rossi Mario
		Mese di:	GENNAIO 2022
		Ore previste: 	 	6H/9H/6H/9H/6H/0H/0H
		Saldo precedente:	12H07M
		
		03 Lunedì	08:30	14:30
		04 Martedì	08:30	17:30
		05 Mercoledì	08:30	14:30	Riposo Compensativo
		07 Venerdì	08:30	14:30	Riposo Compensativo
		10 Lunedì	07:30	13:42	
		10 Lunedì	13:42	13:53	Pausa Pranzo
		10 Lunedì	13:53	15:45	
		11 Martedì	07:30	13:28		
		...
	 **/

	@Override
	public Cedolino leggiCedolino(Reader rdr) throws IOException {
		if (rdr==null) throw new IllegalArgumentException("reader is null");
		BufferedReader reader = new BufferedReader(rdr);
		
		// Lettura e validazione intestazione
		String nomeDipendente  		  = analizzaIntestazioneRiga1(reader.readLine()); // fornito
		LocalDate dataCedolino 		  = analizzaIntestazioneRiga2(reader.readLine()); // *** DA FARE *** 
		SettimanaLavorativa settimana = analizzaIntestazioneRiga3(reader.readLine()); // fornito
		Duration saldoPrecedente 	  = analizzaIntestazioneRiga4(reader.readLine()); // fornito
		
		// creazione del cedolino inizialmente vuoto
		Cedolino cedolino = new Cedolino(nomeDipendente,dataCedolino,settimana,saldoPrecedente);

		// Lettura ed elaborazione delle righe con le singole voci
		
		// *** DA FARE ***
		
		String lineaLetta;
		int giornoMese;
		LocalTime orarioInizio, orarioFine;
		DayOfWeek giorno = null;
		Causale causale = null;
		
		while ((lineaLetta = reader.readLine()) != null)
		{
			giorno = null;
			
			if (lineaLetta.split("\t+").length != 3 && lineaLetta.split("\t+").length != 4 && !lineaLetta.isBlank())
				throw new BadFileFormatException("Mancano elementi nella riga");
		
			if (!lineaLetta.split("\t+")[0].contains(" ") && !lineaLetta.isBlank())
				throw new BadFileFormatException("Mancano dei separatori nella riga");
			
			if (!lineaLetta.isBlank())
			{
				try
				{
					giornoMese = Integer.parseInt(lineaLetta.split("\t+")[0].split(" ")[0].trim());
				}
				catch(NumberFormatException e)
				{
					throw new BadFileFormatException("Numero mancante");
				}
				
				if (giornoMese < 0 || giornoMese > 31)
					throw new BadFileFormatException("Numero giorno non valido");
				
				for (DayOfWeek giornoSett : DayOfWeek.values())
				{
					if (CedolinoReader.getDayOfWeekName(giornoSett, Locale.ITALY).equals(lineaLetta.split("\t+")[0].split(" ")[1].trim().toLowerCase()))
						giorno = giornoSett;
				}
				
				if (giorno == null)
					throw new BadFileFormatException("Nome giorno non valido");
				
				try
				{
					orarioInizio = LocalTime.parse(lineaLetta.split("\t+")[1].trim());
				}
				catch(DateTimeParseException e)
				{
					throw new BadFileFormatException("Orario inizio mal formattato");
				}
				
				try
				{
					orarioFine = LocalTime.parse(lineaLetta.split("\t+")[2].trim());
				}
				catch(DateTimeParseException e)
				{
					throw new BadFileFormatException("Orario fine mal formattato");
				}
								
				if (lineaLetta.split("\t+").length == 4)
					switch (lineaLetta.split("\t+")[3].trim().toLowerCase())
					{
						case "riposo compensativo":
							causale = Causale.RIPOSO_COMPENSATIVO;
							break;
						case "riposo compensativo a ore":
							causale = Causale.RIPOSO_COMPENSATIVO_A_ORE;
							break;
						case "pausa pranzo":
							causale = Causale.PAUSA_PRANZO;
							break;
						default:
							throw new BadFileFormatException("Causale non valida");
					}
				
				if (causale != null)
					cedolino.aggiungi(new VoceCedolino(dataCedolino, orarioInizio, orarioFine, Optional.of(causale)));
				else
					cedolino.aggiungi(new VoceCedolino(dataCedolino, orarioInizio, orarioFine, Optional.empty()));
			}
		}

		/* il ciclo di lettura deve verificare che: 
			1) la riga contenga o tre o quattro elementi 
			2) il primo elemento sia costituito da un numero intero fra 1 e 31, seguito da uno o più spazi e dal 
			   nome (case-insensitive) del giorno della settimana corrispondente 
			   SUGGERIMENTO: a partire dalla data del cedolino, che contiene già mese e anno, modificare il 
			   giorno della settimana con quello dato, verificando poi, a parte, che il nome del giorno della nuova 
			   data così ottenuta sia quello atteso – sfruttando il metodo CedolinoReader.getDayOfWeekName 
			   OPPURE: combinare gli elementi relativi al giorno con quelli relativi a mese e anno (forniti 
			   nell’intestazione del cedolino) per ottenere una data in formato full, di cui sia facile fare il parse 
			3) il secondo e il terzo elemento siano orari correttamente formattati secondo la cultura italiana 
			   NB: provvede VoceCedolino a verificare che l’orario di entrata sia antecedente all’orario di uscita 
			4) l’eventuale quarto elemento, se presente, contenga una delle stringhe (case-insensitive) 
			   "PAUSA PRANZO", "RIPOSO COMPENSATIVO" o "RIPOSO COMPENSATIVO A ORE", da trasformare nella 
			   corrispondente Causale.
		*/
		return cedolino;
	}

	private Duration analizzaIntestazioneRiga4(String line) throws BadFileFormatException {
		// Elaborazione quarta riga di intestazione
		// Saldo precedente:	12H07M
		String[] items = line.split(":");
		// validazione input quarta riga di intestazione
		if (items.length!=2)
			throw new BadFileFormatException("Numero elementi errato nella quarta riga di intestazione: " + line);
		if (!items[0].toLowerCase().equals("saldo precedente"))
			throw new BadFileFormatException("La riga di intestazione non inizia con 'Saldo precedente:'\nRiga: " + items[0]);
		String strSaldoPrec = items[1].trim().toUpperCase();
		return Duration.parse("PT"+strSaldoPrec);
	}

	private SettimanaLavorativa analizzaIntestazioneRiga3(String line) throws BadFileFormatException {
		// Elaborazione terza riga di intestazione
		// Ore previste: 9H/6H/9H/6H/9H/0H/0H
		String[] items = line.split(":");
		if (items.length!=2)
			throw new BadFileFormatException("Numero elementi errato nella terza riga di intestazione: " + line);
		if (!items[0].toLowerCase().equals("ore previste"))
			throw new BadFileFormatException("La riga di intestazione non inizia con 'Ore previste:'\nRiga: " + items[0]);
		String[] strSettLav = items[1].trim().toUpperCase().split("/+");
		
		try {
			return new SettimanaLavorativa(strSettLav);
		}
		catch(IllegalArgumentException | DateTimeParseException e) {
			throw new BadFileFormatException("La riga di intestazione non contiene una settimana lavorativa correttamente formattata\nRiga: " + items[1].trim());
		}
	}

	private Month traduciMese(String meseIta) throws BadFileFormatException
	{
		switch (meseIta)
		{
			case "GENNAIO":
				return Month.JANUARY;
			case "FEBBRAIO":
				return Month.FEBRUARY;
			case "MARZO":
				return Month.MARCH;
			case "APRILE":
				return Month.APRIL;
			case "MAGGIO":
				return Month.MAY;
			case "GIUGNO":
				return Month.JUNE;
			case "LUGLIO":
				return Month.JULY;
			case "SETTEMBRE":
				return Month.SEPTEMBER;
			case "OTTOBRE":
				return Month.OCTOBER;
			case "NOVEMBRE":
				return Month.NOVEMBER;
			case "DICEMBRE":
				return Month.DECEMBER;
		}
		
		throw new BadFileFormatException("Mese inserito non esistente");
	}
	
	private LocalDate analizzaIntestazioneRiga2(String line) throws BadFileFormatException {
		// Elaborazione seconda riga di intestazione
		// Mese di:	GENNAIO 2022
		// *** DA FARE ***
		
		if (!line.toLowerCase().contains("mese di:") || line.toLowerCase().contains("mmese")  || line.contains("-"))
			throw new BadFileFormatException("Errore nell'intestazione");
		
		String mese = line.split("\t+")[1].split(" +")[0].toUpperCase().trim();
		String annoStr = line.split("\t+")[1].split(" +")[1].trim();
		int anno;
		
		try
		{
			anno = Integer.parseInt(annoStr);
		}
		catch(NumberFormatException e)
		{
			throw new BadFileFormatException("Anno assente dall'intestazione");
		}
		
		return LocalDate.of(anno, this.traduciMese(mese), DayOfWeek.MONDAY.getValue()); // fake
	}

	private String analizzaIntestazioneRiga1(String line) throws BadFileFormatException {
		// Elaborazione prima riga di intestazione
		// Dipendente:	Rossi Mario
		String[] items = line.split(":");
		if (items.length!=2)
			throw new BadFileFormatException("Numero elementi errato nella prima riga di intestazione: " + line);
		if (!items[0].toLowerCase().equals("dipendente"))
			throw new BadFileFormatException("La riga di intestazione non inizia con 'Dipendente:'\nRiga: " + line);
		return items[1].trim();
	}
	
}