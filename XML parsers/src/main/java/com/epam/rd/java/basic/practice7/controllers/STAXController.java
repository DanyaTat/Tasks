package com.epam.rd.java.basic.practice7.controllers;

import com.epam.rd.java.basic.practice7.Song;
import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.XML;
import com.epam.rd.java.basic.practice7.Catalog;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;
import java.util.Arrays;

public class STAXController extends DefaultHandler {

	private String xmlFileName;

	// main container
	private Catalog catalog;

	public Catalog getCatalog() {
		return catalog;
	}

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse() throws XMLStreamException {
		Song song = new Song();
		
		// current element name holder
		String currentElement = null;
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		
		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLEventReader reader = factory.createXMLEventReader(
				new StreamSource(xmlFileName));

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			// skip any empty content
			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			// handler for start tags
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				currentElement = startElement.getName().getLocalPart();

				if (XML.CATALOG.equalsTo(currentElement)) {
					catalog = new Catalog();
					continue;
				}
			}

			// handler for contents
			if (event.isCharacters()) {
				Characters characters = event.asCharacters();

				if (XML.TITLE.equalsTo(currentElement)) {
					song.setTitle(characters.getData());
				}
				if (XML.MUSICIAN.equalsTo(currentElement)) {
					song.setMusician(Arrays.asList(characters.getData().split(" ")));
				}
				if (XML.COUNTRY.equalsTo(currentElement)) {
					song.setCountry(characters.getData());
				}
				if (XML.YEAR.equalsTo(currentElement)) {
					song.setYear(Integer.parseInt(characters.getData()));
				}
				if (XML.RATING.equalsTo(currentElement)) {
					song.setRating(characters.getData().charAt(0));
				}
			}

			// handler for end tags
			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if (XML.SONG.equalsTo(localName)) {
					catalog.add(song);
				}
			}
		}
		reader.close();
	}

	public static void main(String[] args) throws Exception {
		STAXController staxContr = new STAXController(Constants.VALID_XML_FILE);
		staxContr.parse();

		Catalog catalog = staxContr.getCatalog();

		System.out.println("====================================");
		System.out.print("Here is the test: \n" + catalog);
		System.out.println("====================================");
	}
}