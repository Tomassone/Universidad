import java.util.Scanner; //importo la classe Scanner necessaria per leggere da console.

public class GetDateOfBirth
{
	public static void main(String[] args)
	{
		int year = 0, month = 0, day = 0;
		Scanner consoleIn = new Scanner(System.in); //creo un'istanza della classe scanner che legge dal canale di input standard (terminale).
		System.out.println("Enter your year of birth: ");
		year = consoleIn.nextInt();
		System.out.println("Enter your month of birth: ");
		month = consoleIn.nextInt();
		System.out.println("Enter your day of birth: ");
		day = consoleIn.nextInt();
		FindBirthDayDistance.getDistance(year, month, day);
	}
}
