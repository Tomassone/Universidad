import java.time.*; //importo le classi del pacchetto java.time (necessario per gestire le date).


public class FindBirthDayDistance
{
	public static void getDistance(int year, int month, int day)
	{
		LocalDate today = LocalDate.now(); //creo un oggetto data locale che contenga la data di oggi.
		LocalDate dateOfBirth = LocalDate.of(year, month, day); //creo un oggetto data locale che contenga la data di nascita inserita.
		int currentYear = today.getYear();
		LocalDate nextBirthDay = dateOfBirth.withYear(currentYear); //trovo il compleanno relativo a quest'anno modificando la data di nascita.
		if (today.isAfter(nextBirthDay)) //se la data del compleanno viene prima di quella di oggi.
			nextBirthDay.plusYears(1);
		Period distance = Period.between(today, nextBirthDay); //creo un periodo definito come la distanza tra le due date create.
		System.out.println(distance);
	}
}
