package it.unibo.paw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

public class MainXMLParser {

    public static void main(String[] args) {

        if (args.length != 0) {
            System.out.println("usage: " + MainXMLParser.class.getSimpleName() + " (no arguments)");
            return;
        }

        String xmlFilename = "resources/Visita.xml";
        String outputFilename = "resources/Shopping.txt";

        try {
            // Feature per validazione tramite schema e ignorare whitespace irrilevante
            final String schemaFeature = "http://apache.org/xml/features/validation/schema";

            // 1) Costruzione parser SAX con validazione e namespace abilitato
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setValidating(true);
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();

            // 2) Lettore XML e setup handler
            XMLReader xmlReader = saxParser.getXMLReader();
            ErrorHandler errorHandler = new ErrorHandler();
            SAXContentHandler handler = new SAXContentHandler();

            xmlReader.setErrorHandler(errorHandler);
            xmlReader.setContentHandler(handler);
            xmlReader.setFeature(schemaFeature, true);

            // 3) Parsing del file XML
            xmlReader.parse(xmlFilename);

            // 4) Recupero e scrittura delle scelte
            List<String> scelte = handler.getScelte();
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));

            writer.write("Scelte effettuate dall’utente:\n");
            for (int i = 0; i < scelte.size(); i++) {
                writer.write(String.format("Scelta #%d: %s\n", i + 1, scelte.get(i)));
            }

            writer.close();
            System.out.println("Risultato scritto su " + outputFilename);

        } catch (Exception e) {
            System.err.println("Errore durante l'elaborazione: ");
            e.printStackTrace();
        }
    }
}
