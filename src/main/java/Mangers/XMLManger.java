package Mangers;

import labmatt.space.Snowball;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

public class XMLManger {

    //private Snowball plugin;
    private File file;

    //public XMLManger(Snowball plugin) {this.plugin = plugin;}

    // Gets the file that the we want to read or write to.
    public XMLManger getXML(String filename) throws FileNotFoundException {

        if(filename.length() > 0){
            File file = new File(filename);

            if(file.exists())
            {

                this.file = file;
            } else {
                throw new FileNotFoundException("404 File not found.");
            }
        } else {
            throw new FileNotFoundException("404 File not found. You need to enter a file name.");
        }

        return this;
    }

    // Create a new file under that name and location.
    public XMLManger newXML(String name, String location) throws Exception {
        if (name.length() != 0 && location.length() != 0) {
            File dir = new File(location);
            if (dir.isDirectory()) {
                int llen = location.length() - 1;
                name = name.replace(".xml", "");

                // If we included a last backslash or forwards slash remove it and replace it with the oprating system appropate one.
                if (location.charAt(llen) == '/' || location.charAt(llen) == '\\')
                {
                    location = location.substring(0, llen);
                    this.file = new File(location + File.separator + name + ".xml");
                } else {
                    this.file = new File(location + File.separator + name + ".xml");
                }

            } else {
                throw new NotDirectoryException("No Directory called <" + location + "> was found.");
            }
        } else {
            throw new Exception("Must provide a file name and location.");
        }

        return this;
    }

    // Reads XML document you have made, much of this code was taken from https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
    public String readXML(String perantNode, String subNodes) throws Exception
    {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(this.file);
            doc.normalizeDocument();

            // Get an element by that name of perant node then loop though to get the inner nodes.
            NodeList nList = doc.getElementsByTagName(perantNode);

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    return eElement.getElementsByTagName(subNodes).item(0).getTextContent();
                }
            }

            throw new Exception("No Perant node or sub node by that name.");
    }


    // This returns the attribute of a XML element.
    public String readXMLat(String perantNode, String attribute) throws Exception {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(this.file);
        doc.normalizeDocument();

        // Get an element by that name of perant node then loop though to get the inner nodes.
        NodeList nList = doc.getElementsByTagName(perantNode);

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                return eElement.getAttribute(attribute);
            }
        }

        throw new Exception("No node or attribute found by that name.");
    }

    // Creates a new XML struture.
    public XMLManger createXML(String filelocname, String[] ele){

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            for (String newele: ele) {
                // add other elements
                Element element = doc.createElement(newele);
                rootElement.appendChild(element);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File nxmlf = new File(filelocname);
            StreamResult result = new StreamResult(nxmlf);
            transformer.transform(source, result);

            this.file = nxmlf;

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }


    public XMLManger editXML(String name, String val)
    {
        try {
            File inputFile = this.file;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
            Node supercar = doc.getElementsByTagName("").item(0);

            // loop the supercar child node
            NodeList list = supercar.getChildNodes();

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if (name.equals(eElement.getNodeName())) {

                        eElement.setTextContent(val);
                    }
                }
            }

            // write the content on console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            File nxmlf = this.file;
            StreamResult result = new StreamResult(nxmlf);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

}
