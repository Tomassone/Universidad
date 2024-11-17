import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsumatoreThread extends Thread {

    private BufferedReader in = null;
	private String filterString;

    public ConsumatoreThread(BufferedReader in, String filterString)
    {
        super();
        this.in = in;
        this.filterString = filterString;
    }

    public void run()
    {
        int found, c;
		char read;
        try {
			// Filtro a carattere
			found = 0;
			while((c=in.read()) > 0){
				read = (char)c;
				for (int i=0; i<filterString.length(); i++) {
					if(read == filterString.charAt(i)) {
						found = 1;
					}
				}
				if(found == 0) {
					System.out.print(read);
				}
				found = 0;
			}
		} catch(IOException ex){
			System.out.println("Errore di input");
			System.exit(2);
		}
    }
}

