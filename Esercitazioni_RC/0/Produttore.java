import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Produttore {
	public static void main(String[] args) {		
		BufferedReader in = null;
		String inputl;
		
		if (args.length < 1){
			System.out.println("Utilizzo: produttore <inputFilename_1> <inputFilename_2> ... <inputFilename_n>");
			System.exit(0);
		}
		System.out.println("Inserire il contenuto del file fino a EOF");
		in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter[] fout = new BufferedWriter[25];
		int i = 0;
		String[] fileNumbers = new String[25];
		String[] lines = new String[25];
		try {
			for (i = 0; i < args.length; i++)
				fout[i] = new BufferedWriter(new FileWriter(args[i]));
			i = 0;
			while((inputl = in.readLine())!=null) {	//till EOF (CTRL+D)
				//fout.write(inputl+"\n", 0, inputl.length()+1);
				fileNumbers[i] = inputl.split(":")[0];
				lines[i] = inputl.split(":")[1];
				i++;
			}
			System.out.println("Raggiunto EOF");
			
			for (i = 0; i < fileNumbers.length && fileNumbers[i] != null; i++)
				fout[Integer.parseInt(fileNumbers[i]) - 1].append(lines[i] + "\n");
			
			for (i = 0; i < fout.length && fout[i] != null; i++)
				fout[i].close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

