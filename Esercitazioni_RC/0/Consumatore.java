import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Consumatore {

	public static void main(String[] args) {
		BufferedReader[] in = new BufferedReader[25];
		ConsumatoreThread[] jobs = new ConsumatoreThread[25];
		String filterString;
		int found, c;
		char read;
		
		if (args.length < 2){ //Invocation error
			System.out.println("Utilizzo: consumatore <prefix> [filename1] ... [filenameN]");
			System.exit(0);
		}
		else {
			System.out.println("Prefisso = "+args[0] + " di lunghezza "+args[0].length());
			try {
				for (int i = 0; i < args.length - 1; i++)
					in[i] = new BufferedReader(new FileReader(args[i + 1]));
				System.out.println("File trovati. Filtraggio dei file...");
			}catch(FileNotFoundException e){
				System.out.println("Un file non è stato trovato");
				System.exit(1);
			}
		}

		filterString = args[0];
		for (int i = 0; i < args.length - 1; i++)
		{
			jobs[i] = new ConsumatoreThread(in[i], filterString);
			jobs[i].start();
		}
	}
		
}

