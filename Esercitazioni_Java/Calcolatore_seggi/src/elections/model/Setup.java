package elections.model;

public class Setup
{
	public static void main(String[] args)
	{
		StringBuilder sb = new StringBuilder();
		CalcolatoreSeggi calculator = new CalcolatoreSeggi("proporzionale", sb);
		test1(calculator);
	}
		
	private static void test1(CalcolatoreSeggi calculator)
	{
		RisultatoDelPartito[] partiti = 
		{
			new RisultatoDelPartito("Formiche rosse", 10, -1),
			new RisultatoDelPartito("Topolini grigi", 100, -1),
			new RisultatoDelPartito("Farfalle blu", 200, -1),
			new RisultatoDelPartito("Bruchi verdi", 300, -1),
			new RisultatoDelPartito("Canarini ocra", 400, -1)
		};
		long[] seggiAttesi = {1, 10, 20, 30, 39};
		calculator.calcolaSeggi(100, partiti);
		for (int i = 0; i < partiti.length; i++)
			assert(partiti[i].getSeggi() == seggiAttesi[i]);
	}
}
