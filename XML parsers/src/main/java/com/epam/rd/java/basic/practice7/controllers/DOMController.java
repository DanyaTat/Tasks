package com.epam.rd.java.basic.practice7.controllers;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.epam.rd.java.basic.practice7.Catalog;
import com.epam.rd.java.basic.practice7.Song;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.rd.java.basic.practice7.constants.XML;

public class DOMController {

    private final String xmlFileName;

    private Catalog catalog;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void parse()
            throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(xmlFileName);
        Element root = document.getDocumentElement();

        catalog = new Catalog();

        NodeList songNodes = root.getElementsByTagName(XML.SONG.value());

        for (int j = 0; j < songNodes.getLength(); j++) {
            Song song = getSong(songNodes.item(j));
            catalog.add(song);
        }
    }

    private static Song getSong(Node sNode) {
        Song song = new Song();
        Element sElement = (Element) sNode;

        Node titleNode = sElement.getElementsByTagName(XML.TITLE.value()).item(0);
        song.setTitle(titleNode.getTextContent());

        Node musicianNode = sElement.getElementsByTagName(XML.MUSICIAN.value()).item(0);
        song.setMusician(Arrays.asList(musicianNode.getTextContent().split(" ")));

        Node countryNode = sElement.getElementsByTagName(XML.COUNTRY.value()).item(0);
        song.setCountry(countryNode.getTextContent());

        Node yearNode = sElement.getElementsByTagName(XML.YEAR.value()).item(0);
        song.setYear(Integer.parseInt(yearNode.getTextContent()));

        Node ratingNode = sElement.getElementsByTagName(XML.RATING.value()).item(0);
        song.setRating(ratingNode.getTextContent().charAt(0));
        return song;
    }
}
