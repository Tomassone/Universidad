package beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CounterBean extends Thread{
	private List<File> elencoFile;
	private String f1;
	private String f2;
	private String f3;
	private double durataCalcolo;
	
	public CounterBean() {
		this.elencoFile = null;
		this.f1 = "";
		this.f2 = "";
		this.f3 = "";
		this.durataCalcolo = 0;
	}

	public List<File> getElencoFile() {
		return elencoFile;
	}

	public void setElencoFile(List<File> elencoFile) {
		this.elencoFile = elencoFile;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public String getF3() {
		return f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	public double getDurataCalcolo() {
		return durataCalcolo;
	}

	public void setDurataCalcolo(double durataCalcolo) {
		this.durataCalcolo = durataCalcolo;
	}

	public int countWords(String path)
	{
		int nMaiusc = 0;
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(path));
			String line = bf.readLine();
			while (line != null)
			{
				for (int i = 0; i < line.length(); i++)
					if (Character.isUpperCase(line.charAt(i)))
						nMaiusc++;
	            line = bf.readLine(); // Continue to the next line
			}
			bf.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nMaiusc;
	}
	
	public void run()
	{
		int[] elencoConteggi = new int[3];
		this.durataCalcolo = System.currentTimeMillis();
		
		for (File f : elencoFile)
		{
			if (f.getName().equals(f1))
				elencoConteggi[0] = this.countWords(f.getAbsolutePath());
			if (f.getName().equals(f2))
				elencoConteggi[1] = this.countWords(f.getAbsolutePath());
			if (f.getName().equals(f3))
				elencoConteggi[2] = this.countWords(f.getAbsolutePath());
		}
		
		this.durataCalcolo = ((-durataCalcolo + System.currentTimeMillis()));
	}
}

