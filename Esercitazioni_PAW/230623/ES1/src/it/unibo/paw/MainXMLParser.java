
package it.unibo.paw;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class MainXMLParser {
	
	public static void main(String[] args) throws Exception{
		
		String xmlFilename;
		
		if ( args.length != 0 ) {
			System.out.println("usage: "+MainXMLParser.class.getSimpleName()+" xmlFilename");
		}
		else{
			try {
				xmlFilename = "resources/InterrogazioniMM.xml";
				
				String schemaFeature = "http://apache.org/xml/features/validation/schema";
				String ignorableWhitespace = "http://apache.org/xml/features/dom/include-ignorable-whitespace";
				
				// DOM
				// 1) Costruire un DocumentBuilder che validi il documento XML
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setValidating(true);
				dbf.setNamespaceAware(true);
				
				// seguente istruzione per specificare che stiamo validando tramite XML Schema 
				dbf.setFeature(schemaFeature,true);
				
				// seguente istruzione per specificare che gli "ignorable whitespace" (tab, new line...) 
				// tra un tag ed un altro devono essere scartati e non considerati come text node
				dbf.setFeature(ignorableWhitespace, false);
			
				// 2) Attivare un gestore di non-conformita'
				DocumentBuilder db = dbf.newDocumentBuilder();
				db.setErrorHandler(new ErrorHandler());
				
				// 3) Parsificare il documento, ottenendo un documento DOM
				Document domDocument = db.parse(new File(xmlFilename));
				domDocument.getDocumentElement().normalize();
				
				// 4) utilizzare il documento DOM
				BufferedWriter br = new BufferedWriter(new FileWriter("resources/MM.txt"));
			
				System.out.println("DOM ID QUERY CATEGORIA A = ");
				br.append("DOM ID QUERY CATEGORIA A = " + "\n");
				int [] result = getQuery(domDocument, "A");
				for (int i = 0; i < result.length; i++) {
					if (result[i] != 0) {
						System.out.println(result[i]);
						br.append(Integer.toString(result[i]) + "\n");
					} 
				}
				System.out.println("DOM ID QUERY CATEGORIA B = ");
				br.append("DOM ID QUERY CATEGORIA B = " + "\n");
				result = getQuery(domDocument, "B");
				for (int i = 0; i < result.length; i++) {
					if (result[i] != 0) {
						System.out.println(result[i]);
						br.append(Integer.toString(result[i]) + "\n");
					} 			
				}
				System.out.println("DOM ID QUERY CATEGORIA C = ");
				br.append("DOM ID QUERY CATEGORIA C = " + "\n");
				result = getQuery(domDocument, "C");
				for (int i = 0; i < result.length; i++) {
					if (result[i] != 0) {
						System.out.println(result[i]);
						br.append(Integer.toString(result[i]) + "\n");
					} 				
				}
				br.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private static int[] getQuery(Document domDocument, String modalitaScelta) {
		NodeList interrogazioniList = domDocument.getElementsByTagName("Interrogazione_MM");
		int[] result = new int[15];
		int dim_res = 0;
		
		for (int i = 0; i < interrogazioniList.getLength(); i++) {
            Element interrogazione = (Element) interrogazioniList.item(i);
			String idInput = interrogazione.getElementsByTagName("ID_input_image").item(0).getTextContent();
	        String modalita = interrogazione.getElementsByTagName("modalita").item(0).getTextContent();
			if (modalita.equals(modalitaScelta)) {
				result[dim_res] = Integer.parseInt(idInput);
				dim_res++;
			}
		}
		return result;
	}
}
