package core;

import java.io.IOException;
import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLStuff {
	public static Document xmlFile2Document(String file){
		/*return just a conversion of the file in a Document*/
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document dom=null;
		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			dom = (Document) db.parse(file);

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return dom;
	}
	public static ArrayList<model.Link> parseTrustedLinks(){
		/*Parse trusted_streaming_sities.xml searching for sites*/
		ArrayList<model.Link> links = new ArrayList<model.Link>();
		model.Link neo;
		Element docEle = xmlFile2Document("trusted_streaming_sites.xml").getDocumentElement();

		//get a nodelist of  elements
		NodeList nl = docEle.getElementsByTagName("site");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the employee element
				Element el = (Element)nl.item(i);
				neo = new model.Link(getTextValue(el,"name"), getTextValue(el,"url"));
				links.add(neo);
							}
		}
		return links;
	}
	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
}
