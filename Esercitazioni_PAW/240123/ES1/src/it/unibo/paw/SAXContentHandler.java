package it.unibo.paw;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAXContentHandler extends DefaultHandler {

	boolean inScelta = false;
	String scelta = null;
	boolean inImage = false;
	String image = null;
	boolean inDescription = false;
	String description = null;
	boolean inPrice = false;
	String price = null;
	boolean inSelected = false;
	String selected = null;

	List<String> allItems = new ArrayList<>();

	public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) {
		if (localName.equals("abiti") || localName.equals("camice") || localName.equals("giacche")
				|| localName.equals("pantaloni") || localName.equals("gonne") || localName.equals("ultimi_arrivi")) {
			inScelta = true;
			scelta = localName;
		} else if (localName.equals("image")) {
			inImage = true;
		} else if (localName.equals("description")) {
			inDescription = true;
		} else if (localName.equals("price")) {
			inPrice = true;
		} else if (localName.equals("selected")) {
			inSelected = true;
		}
	}

	public void characters(char ch[], int start, int length) {
		String content = new String(ch, start, length).trim();
		if (inImage) {
			image = content;
		} else if (inDescription) {
			description = content;
		} else if (inPrice) {
			price = content;
		} else if (inSelected) {
			selected = content;
		}
	}

	public void endElement(String namespaceURI, String localName, String qName) {
		if (localName.equals("abiti") || localName.equals("camice") || localName.equals("giacche")
				|| localName.equals("pantaloni") || localName.equals("gonne") || localName.equals("ultimi_arrivi")) {
			inScelta = false;
		} else if (localName.equals("image")) {
			inImage = false;
		} else if (localName.equals("description")) {
			inDescription = false;
		} else if (localName.equals("price")) {
			inPrice = false;
		} else if (localName.equals("selected")) {
			inSelected = false;
			if (selected != null && selected.equalsIgnoreCase("true")) {
				allItems.add(scelta + " " + image + " " + description + " " + price);
			}
			// reset per il prossimo item
			image = null;
			description = null;
			price = null;
			selected = null;
		}
	}

	public List<String> getScelte() {
		return this.allItems;
	}
}
