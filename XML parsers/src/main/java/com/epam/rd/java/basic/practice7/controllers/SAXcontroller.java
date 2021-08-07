package com.epam.rd.java.basic.practice7.controllers;

import com.epam.rd.java.basic.practice7.Catalog;
import com.epam.rd.java.basic.practice7.Song;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import com.epam.rd.java.basic.practice7.constants.XML;

public class SAXcontroller extends DefaultHandler {

    private static Catalog catalog = new Catalog();

    private String title;
    private String country;
    private Integer year;
    private Character rating;
    private String lastElementName;
    private ArrayList<String> musician = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        lastElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String information = new String(ch, start, length);

        information = information.replace("\n", "").trim();
        if (!information.isEmpty()) {
            if (lastElementName.equals("title")) {
                title = information;
            }
            if (lastElementName.equals("country")) {
                country = information;
            }
            if (lastElementName.equals("year")) {
                year = Integer.parseInt(information);
            }
            if (lastElementName.equals("rating")) {
                rating = information.charAt(0);
            }
            if (lastElementName.equals("musician")) {
                musician.addAll(Arrays.asList(information.split(" ")));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ((title != null && !title.isEmpty()) &&
                (country != null && !country.isEmpty()) &&
                year != null &&
                (rating != null) &&
                (musician != null && !musician.isEmpty())) {
            catalog.add(new Song(title, musician, country, year, rating));
            title = null;
            country = null;
            year = null;
            rating = null;
            musician = new ArrayList<>();
        }
    }

    public static Catalog getCatalog() {
        return catalog;
    }


    public static Document getDocument(Catalog catalog) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        Element tElement = document.createElement(XML.CATALOG.value());
        document.appendChild(tElement);

        for (Song song : catalog.getSongs()) {

            Element songElement = document.createElement(XML.SONG.value());
            tElement.appendChild(songElement);

            Element titleElement = document.createElement(XML.TITLE.value());
            titleElement.setTextContent(song.getTitle());
            songElement.appendChild(titleElement);

            Element musicianElement = document.createElement(XML.MUSICIAN.value());
            musicianElement.setTextContent(song.getStringMusician());
            songElement.appendChild(musicianElement);

            Element countryElement = document.createElement(XML.COUNTRY.value());
            countryElement.setTextContent(song.getCountry());
            songElement.appendChild(countryElement);

            Element yearElement = document.createElement(XML.YEAR.value());
            yearElement.setTextContent(Integer.toString(song.getYear()));
            songElement.appendChild(yearElement);

            Element ratingElement = document.createElement(XML.RATING.value());
            ratingElement.setTextContent(String.valueOf(song.getRating()));
            songElement.appendChild(ratingElement);
        }
        return document;
    }

    public static void saveToXML(Catalog catalog, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        saveToXML(getDocument(catalog), xmlFileName);
    }

    public static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {
        StreamResult result = new StreamResult(new File(xmlFileName));

        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        t.transform(new DOMSource(document), result);
    }
}