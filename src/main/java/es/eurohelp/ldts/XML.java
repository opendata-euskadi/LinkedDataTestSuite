package es.eurohelp.ldts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class XML {
	private final String URI_XML = "C:/Users/dmuchuari/git/LinkedDataTestSuite/src/main/resources/archivoPrueba.xml";
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;

	public XML() throws ParserConfigurationException {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}

	public void createFile(Junit pJunit) throws ParserConfigurationException, SAXException, IOException {
		DOMImplementation implementation = builder.getDOMImplementation();
		Document document = implementation.createDocument(null, "xml", null);
		File f = new File(URI_XML);
		if (!f.exists()) {
			saveFile(addJunitToXML(pJunit, document, true), URI_XML);
		} else {
			updateFile(pJunit);
		}
	}

	public Document addJunitToXML(Junit pJunit, Document document, boolean pEstado) {
		try {
			Element xmlHeader = document.createElement("testCases");
			Element junit = document.createElement("junit");
			Element junitName = document.createElement("name");
			Text junitLiteralName = document.createTextNode(pJunit.getNombre());
			Element junitComment = document.createElement("comment");
			Text junitLiteralComment = document.createTextNode(pJunit.getComentario());
			Element junitBaseUri = document.createElement("baseuri");
			Text junitLiteralBaseUri = document.createTextNode(pJunit.getBaseUri());
			Element junitPathUri = document.createElement("pathuri");
			Text junitLiteralPathUri = document.createTextNode(pJunit.getPathUri());
			Element junitMethod = document.createElement("method");
			Text junitLiteralMethod = document.createTextNode(pJunit.getMethod());
			Element junitAccept = document.createElement("accept");
			Text junitLiteralAccept = document.createTextNode(pJunit.getAccept());
			Element requestParameters = document.createElement("parameters");
			Element testCases = document.createElement("tests");

			int contAssert = 0;
			int contEquals = 0;
			for (int i = 0; i < pJunit.getTipoPrueba().length; i++) {
				Element junitTest = document.createElement("test");
				Element junitTestType = document.createElement("type");
				junitTest.appendChild(junitTestType);
				Text paramLiteraltestObject = document.createTextNode(pJunit.getObjetoPrueba()[i]);
				Text paramLiteralTestType = document.createTextNode(pJunit.getTipoPrueba()[i]);

				Element testObject = document.createElement("testObject");
				testObject.appendChild(paramLiteraltestObject);

				Element junitValueToTest = document.createElement("value");
				junitTest.appendChild(testObject);

				if (pJunit.getTipoPrueba()[i].contains("Equals")) {
					Text paramLiteralValueToTest = document
							.createTextNode(pJunit.getValorComparacionEquals()[contEquals]);
					contEquals++;
					junitTestType.appendChild(paramLiteralTestType);
					junitTest.appendChild(junitValueToTest);
					junitValueToTest.appendChild(paramLiteralValueToTest);
				} else {
					Element junitComparisonMode = document.createElement("comparisonmode");
					Text paramLiteralValueToTest = document
							.createTextNode(pJunit.getValorComparacionAssert()[contAssert]);
					Text paramLiteralComparisonMode = document
							.createTextNode(pJunit.getValorFormaComparacionAssert()[contAssert]);
					junitTestType.appendChild(paramLiteralTestType);
					junitTest.appendChild(junitComparisonMode);
					junitComparisonMode.appendChild(paramLiteralComparisonMode);
					junitTest.appendChild(junitValueToTest);
					junitValueToTest.appendChild(paramLiteralValueToTest);
					contAssert++;
				}
				testCases.appendChild(junitTest);
			}
			for (int i = 0; i < pJunit.getIdParametro().length; i++) {
				Element parameter = document.createElement("parameter");
				Element paramName = document.createElement("name");
				Text paramLiteralName = document.createTextNode(pJunit.getIdParametro()[i]);
				Element paramValue = document.createElement("value");
				Text paramLiteralValue = document.createTextNode(pJunit.getValorParametro()[i]);
				junit.appendChild(requestParameters);
				requestParameters.appendChild(parameter);
				parameter.appendChild(paramName);
				paramName.appendChild(paramLiteralName);
				parameter.appendChild(paramValue);
				paramValue.appendChild(paramLiteralValue);
			}

			document.setXmlVersion("1.0");
			if (pEstado) {
				document.getDocumentElement().appendChild(xmlHeader);
				xmlHeader.appendChild(junit);
			} else {
				NodeList nodoRaiz = document.getDocumentElement().getElementsByTagName("testCases");
				nodoRaiz.item(0).appendChild(junit);
			}
			junit.appendChild(junitName);
			junitName.appendChild(junitLiteralName);
			junit.appendChild(junitComment);
			junitComment.appendChild(junitLiteralComment);
			junit.appendChild(junitMethod);
			junitMethod.appendChild(junitLiteralMethod);
			junit.appendChild(junitAccept);
			junitAccept.appendChild(junitLiteralAccept);
			junit.appendChild(junitPathUri);
			junitPathUri.appendChild(junitLiteralPathUri);
			junit.appendChild(junitBaseUri);
			junitBaseUri.appendChild(junitLiteralBaseUri);
			junit.appendChild(requestParameters);
			junit.appendChild(testCases);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
		}
		return document;
	}

	public void updateFile(Junit pJunit) throws ParserConfigurationException, SAXException, IOException {
		Document document = builder.parse(new File(URI_XML));
		document.getDocumentElement().normalize();
		saveFile(addJunitToXML(pJunit, document, false), URI_XML);
	}

	public void saveFile(Document pDocument, String pURI) {
		try {
			TransformerFactory transFact = TransformerFactory.newInstance();

			// Formateamos el fichero. Añadimos sangrado y la cabecera de XML
			transFact.setAttribute("indent-number", new Integer(3));
			Transformer trans = transFact.newTransformer();
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

			// Hacemos la transformación
			StringWriter sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			DOMSource domSource = new DOMSource(pDocument);
			trans.transform(domSource, sr);

			// Mostrar información a guardar por consola (opcional)
			// Result console= new StreamResult(System.out);
			// trans.transform(domSource, console);
			try {
				// Creamos fichero para escribir en modo texto
				PrintWriter writer = new PrintWriter(new FileWriter(pURI));

				// Escribimos todo el árbol en el fichero
				writer.println(sw.toString());

				// Cerramos el fichero
				writer.close();
				System.out.println("TERMINO DE ESCRIBIR");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<String> getMethodsList() throws ParserConfigurationException, SAXException, IOException {
		Document document = builder.parse(new File(URI_XML));
		Element rootElement = document.getDocumentElement();
		ArrayList<String> result = new ArrayList<String>();
		NodeList list = rootElement.getElementsByTagName("name");
		if (list != null && list.getLength() > 0) {
			for (int i = 0; i < list.getLength(); i++) {
				NodeList subList = list.item(i).getChildNodes();
				for (int j = 0; j < subList.getLength(); j++) {
					result.add(subList.item(j).getNodeValue());
				}
			}
		}
		return result;
	}

}