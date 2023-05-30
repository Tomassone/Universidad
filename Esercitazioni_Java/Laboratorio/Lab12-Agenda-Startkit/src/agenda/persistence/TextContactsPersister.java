package agenda.persistence;

import java.io.*;
import java.util.*;

import agenda.model.Contact;
import agenda.model.Detail;

public class TextContactsPersister implements ContactsPersister
{
	private Optional<Contact> readContact(BufferedReader br) throws IOException, BadFileFormatException
	{
		if (!br.readLine().equals("StartContact"))
			throw new BadFileFormatException("StartContact expected");
		
		String nameTag = br.readLine();

		Contact unit = new Contact(nameTag.split(";")[0], nameTag.split(";")[1]);
		readDetails(unit, br);
		
		return Optional.of(unit);
	}
	
	private void readDetails(Contact contatto, BufferedReader br) throws IOException, BadFileFormatException
	{
		do
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			String tipoDettaglio = st.nextToken();
			
			if (!tipoDettaglio.equals("EMail") && !tipoDettaglio.equals("Phone") && !tipoDettaglio.equals("Address"))
				throw new BadFileFormatException("Unknown Detail Type");
			
			DetailPersister db = DetailPersister.of(tipoDettaglio);
			contatto.getDetailList().add(db.load(st));
		}
		while (!br.readLine().equals("EndContact"));
	}
	
	private void saveDetails(List<Detail> elencoDettagli, StringBuilder sb)
	{
		for (Detail dettaglio : elencoDettagli)
		{
			DetailPersister db = DetailPersister.of(dettaglio.getName());
			db.save(dettaglio, sb);
		}
	}
	
	@Override
	public List<Contact> load(Reader reader) throws IOException, BadFileFormatException
	{
		if (reader == null)
			throw new IOException("reader null");
		
		BufferedReader br = new BufferedReader(reader);
		List<Contact> elencoContatti = new ArrayList<Contact>();
		
		do
		{
			Contact contatto = this.readContact(br).get();
			elencoContatti.add(contatto);
		}
		while(br.readLine() != null);
		
		return elencoContatti;
	}

	@Override
	public void save(List<Contact> contacts, Writer writer) throws IOException 
	{
		 PrintWriter pw = new PrintWriter(writer);
		 StringBuilder sb = new StringBuilder();
		 pw.println("StartContact");
		 for (Contact contatto : contacts)
		 {
			 Contact temp = contatto;
			 pw.println(temp.getName() + ";" + temp.getSurname());
			 this.saveDetails(temp.getDetailList(), sb);
			 pw.println(sb.toString());
			 sb.delete(0, sb.length() - 1);
		 }
	}
}
