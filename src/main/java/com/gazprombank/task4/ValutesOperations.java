package com.gazprombank.task4;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ValutesOperations {
    private static final String VALUTE = "Valute";
    private static final String ID = "ID";
    private static final String NUM_CODE = "NumCode";
    private static final String CHAR_CODE = "CharCode";
    private static final String NOMINAL = "Nominal";
    private static final String NAME = "Name";
    private static final String VALUE = "Value";
    
    private String urlStr;

    public ValutesOperations(String urlStr){
        this.urlStr = urlStr;
    }

    public Document getDocFromUrl(){
        InputStream stream = null;
        DocumentBuilder builder = null;
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        URL url = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
            System.out.println("Can't create new Document Builder");
        }
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException ex){
            ex.printStackTrace();
            System.out.println("Wrong URL format");
        }
        try {
            assert url != null;
            stream = url.openConnection().getInputStream();
            assert builder != null;
            doc = builder.parse(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Can't read from the server");
        }catch (SAXException ex) {
            ex.printStackTrace();
            System.out.println("Can't parse the stream");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Can't close the stream");
                }
            }
        }
        return doc;
    }

    public List<Valute> getValutesAsList(Document doc) {
        List<Valute> valutes = new ArrayList<>();
        String id;
        int numCode;
        String charCode;
        int nominal;
        String name;
        String value;
        NodeList nList = doc.getElementsByTagName(VALUTE);
        for (int node = 0; node < nList.getLength(); node++) {
            Node nNode = nList.item(node);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element)nNode;
                id = eElement.getAttribute(ID);
                numCode = Integer.valueOf(eElement.getElementsByTagName(NUM_CODE).item(0).getTextContent());
                charCode = eElement.getElementsByTagName(CHAR_CODE).item(0).getTextContent();
                nominal = Integer.valueOf(eElement.getElementsByTagName(NOMINAL).item(0).getTextContent());
                name = eElement.getElementsByTagName(NAME).item(0).getTextContent();
                value = eElement.getElementsByTagName(VALUE).item(0).getTextContent();
                valutes.add(new Valute(id, numCode, charCode, nominal, name, value));
            }
        }
        return valutes;
    }
}
