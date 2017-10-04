package es.eurohelp.ldts;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author ssantamariap xml file handler acting class
 */
public class XMLUtils {

	LinkedDataRequestBean requestBean;

	String name, pathUri, method, baseUri, accept, comment, parameters;

	File xmlFile = new File(
			"C:/Users/dmuchuari/git/LinkedDataTestSuite/src/main/resources/templates/test_template.xml");

	/**
	 * 
	 * @param testName
	 * @return LinkedDataRequestBean object formed according to the data stored
	 *         on the test_template.xml relative to the testName received.
	 */
	public LinkedDataRequestBean getXMLData(String testName) {

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName(testName);

			for (int i = 0; i < nodes.getLength(); i++) {

				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					name = element.getElementsByTagName("name").item(0).getTextContent();
					pathUri = element.getElementsByTagName("pathUri").item(0).getTextContent();
					comment = element.getElementsByTagName("comment").item(0).getTextContent();
					method = element.getElementsByTagName("method").item(0).getTextContent();
					accept = element.getElementsByTagName("accept").item(0).getTextContent();
					baseUri = element.getElementsByTagName("baseUri").item(0).getTextContent();

					Map<String, String> parameters = new HashMap<String, String>();
					requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment,
							parameters);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestBean;

	}

	/**
	 * 
	 * @param testName
	 * @return contains called node content, relative to the testName, for the
	 *         cases wich require response comparison
	 */
	public String getXmlContainsNode(String testName) {

		String contains = "";

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName(testName);

			for (int i = 0; i < nodes.getLength(); i++) {

				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					contains = element.getElementsByTagName("contains").item(0).getTextContent();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return contains;

	}
}
