

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<Marshaller> marshaller = null;

	public Parser() {
		marshaller = new ArrayList<Marshaller>();
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representaci�n DOM
			dom = db.parse(fichero);

			dom.getDocumentElement().normalize();
			System.out.println("Nombre XML :" + dom.getDocumentElement().getNodeName());
			NodeList nList = dom.getElementsByTagName("accion");
		
			System.out.println("----------------------------");

			for (int x = 0; x < nList.getLength(); x++) {
			
				Node nNode = nList.item(x);
				System.out.println("----------------------------");
				System.out.println("\nElemento actual :" + nNode.getNodeName());	
				System.out.println("----------------------------");
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Nombre : " + eElement.getAttribute("nombre"));
					System.out.println("----------------------------");
					
					NodeList nList2 = dom.getElementsByTagName("operacion");
					for ( int c=0 ; c<nList2.getLength(); c++) {
						Node nNode2 = nList2.item(c);
						
						if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement2 = (Element) nNode2;
					
							System.out.println("Tipo : " + eElement2.getElementsByTagName("tipo").item(0).getTextContent());
							System.out.println("Cantidad : " + eElement2.getElementsByTagName("cantidad").item(0).getTextContent());
							System.out.println("Precio : " + eElement2.getElementsByTagName("precio").item(0).getTextContent());
						}
					}
				
				}
					
			}
				
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	

}
