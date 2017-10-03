package es.eurohelp.ldts;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XML {

	public void createFile(Junit pJunit) {
		System.out.println("VA A ESCRIBIR");
		 Document document = null;
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "xml", null);

            //Creaci�n de elementos
            //creamos el elemento principal que englobara a todos los junits
            Element testCases = document.createElement("TestCases"); 
            //creamos un nuevo elemento junit para fijar sus caracteristicas
            Element junit= document.createElement(pJunit.getNombre());
            //creamos un nuevo elemento. Habitaci�n tiene color
            Element junitName = document.createElement("name"); 
            //Ingresamos la info. El color de esta habitaci�n es azul
            Text junitLiteralName = document.createTextNode(pJunit.getNombre()); 
            //A�adimos el comentario de el junit
            Element junitComment = document.createElement("comment"); 
            //Ingresamos la info. El color de esta habitaci�n es azul
            Text junitLiteralComment = document.createTextNode(pJunit.getComentario()); 
            
            //Asignamos la versi�n de nuestro XML
            document.setXmlVersion("1.0"); 
            //A�adimos la casa al documento
            document.getDocumentElement().appendChild(testCases); 
            //A�adimos el elemento hijo a la ra�z
            testCases.appendChild(junit); 
            //A�adimos elemento
            junit.appendChild(junitName); 
            //A�adimos valor
            junitName.appendChild(junitLiteralName);
            junit.appendChild(junitComment);
            junitComment.appendChild(junitLiteralComment);
            saveFile(document, "C:/Users/dmuchuari/git/LinkedDataTestSuite/src/main/resources/archivoPrueba.xml");
         }catch(Exception e){
             System.err.println("Error");
         }
	}

	public void updateFile(Junit pJunit) {

	}

	public void saveFile(Document pDocument, String pURI) {
		 try {
		        TransformerFactory transFact = TransformerFactory.newInstance();

		        //Formateamos el fichero. A�adimos sangrado y la cabecera de XML
		        transFact.setAttribute("indent-number", new Integer(3));
		        Transformer trans = transFact.newTransformer();
		        trans.setOutputProperty(OutputKeys.INDENT, "yes");
		        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

		        //Hacemos la transformaci�n
		        StringWriter sw = new StringWriter();
		        StreamResult sr = new StreamResult(sw);
		        DOMSource domSource = new DOMSource(pDocument);
		        trans.transform(domSource, sr);

		        //Mostrar informaci�n a guardar por consola (opcional)
		        //Result console= new StreamResult(System.out);
		        //trans.transform(domSource, console);
		        try {
		            //Creamos fichero para escribir en modo texto
		            PrintWriter writer = new PrintWriter(new FileWriter(pURI));

		            //Escribimos todo el �rbol en el fichero
		            writer.println(sw.toString());

		            //Cerramos el fichero
		            writer.close();
		            System.out.println("TERMINO DE ESCRIBIR");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    } catch(Exception ex) {
		        ex.printStackTrace();
		    }
	}

	public void deleteJunit() {

	}
}