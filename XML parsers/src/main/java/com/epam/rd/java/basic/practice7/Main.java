package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.controllers.DOMController;
import com.epam.rd.java.basic.practice7.controllers.SAXcontroller;
import com.epam.rd.java.basic.practice7.controllers.STAXController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public final class Main {

    public static void main(final String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException, XMLStreamException {
        Catalog songsCatalog;
        String filename = args[0];

        //SAX
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXcontroller handler = new SAXcontroller();
        parser.parse(new File(filename), handler);
        songsCatalog = SAXcontroller.getCatalog();
        Sorter.sortSongsByTitle(songsCatalog);
        String outputXmlFile = "output.sax.xml";
        SAXcontroller.saveToXML(songsCatalog, outputXmlFile);

        //DOM
        DOMController domController = new DOMController(filename);
        domController.parse();
        songsCatalog = domController.getCatalog();
        Sorter.sortSongsByYear(songsCatalog);
        outputXmlFile = "output.dom.xml";
        SAXcontroller.saveToXML(songsCatalog, outputXmlFile);

        //StAX
        STAXController staxController = new STAXController(filename);
        staxController.parse();
        songsCatalog = staxController.getCatalog();
        Sorter.sortSongsByRating(songsCatalog);
        outputXmlFile = "output.stax.xml";
        SAXcontroller.saveToXML(songsCatalog, outputXmlFile);
    }
}
