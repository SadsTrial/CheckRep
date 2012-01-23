package com.sharedlibrary;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseXML {

	String location = "";

	public ParseXML(String location) {
		this.location = location; 

	}
	public FileInputStream getXMLInputStream(String fileName) throws IOException{
		File dir1 = new File (".");
		String strBasePath=dir1.getCanonicalPath();
		String file=strBasePath+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+
		location+File.separator+"XMLRepository"+
		File.separator+ fileName;
		File fXmlFile = new File(file);
		return new FileInputStream(fXmlFile);
	}
	public Hashtable<String, String> fetchData(String fileName,String parentTag) throws Exception
	{
		Hashtable< String, String> xmlData = new Hashtable<String, String>();
		File dir1 = new File (".");
		String strBasePath=dir1.getCanonicalPath();
		String file=strBasePath+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+location+File.separator+"XMLRepository"+
		File.separator+ fileName;
		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		NodeList nList = doc.getElementsByTagName(parentTag).item(0).getChildNodes();
		for (int i=0; i<nList.getLength(); i++) {
			if(nList.item(i).getNodeType()==1)
				if(nList.item(i).getChildNodes().getLength()>0)
					xmlData.put(nList.item(i).getNodeName(), nList.item(i).getChildNodes().item(0).getNodeValue());	
		}
		return xmlData;
	}

	public Hashtable<String, String> fetchData(InputStream fInputStream,String parentTag) throws Exception
	{
		Hashtable< String, String> xmlData = new Hashtable<String, String>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fInputStream);
		NodeList nList = doc.getElementsByTagName(parentTag).item(0).getChildNodes();
		for (int i=0; i<nList.getLength(); i++) {
			if(nList.item(i).getNodeType()==1)
				if(nList.item(i).getChildNodes().getLength()>0)
					xmlData.put(nList.item(i).getNodeName(), nList.item(i).getChildNodes().item(0).getNodeValue());	
		}
		return xmlData;
	}
	public String getTagValue(String fileName,String sTag ) throws Exception {

		File dir1 = new File (".");
		String strBasePath=dir1.getCanonicalPath();
		String file=strBasePath+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+location+File.separator+"XMLRepository"+
		File.separator+ fileName;
		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		NodeList nList = doc.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		return nValue.getNodeValue();
	}

	public String getTagValue(InputStream fInputStream,String sTag ) throws Exception {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fInputStream);
		NodeList nList = doc.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		return nValue.getNodeValue();

	}

	public  boolean updateXmlField(String fileName, String tagName, String tagValue) throws Exception
	{
		Boolean successFlag = false;

		File dir1 = new File (".");
		String strBasePath=dir1.getCanonicalPath();
		String file=strBasePath+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+location+File.separator+"XMLRepository"+File.separator+ fileName;
		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList nl = doc.getElementsByTagName("*");
		Element nsElement;
		String localName;

		for (int i=0; i < nl.getLength(); i++)
		{
			nsElement = (Element)nl.item(i);
			localName = nsElement.getNodeName();
			if(localName.equals(tagName))
			{
				nsElement.setTextContent(tagValue);
				successFlag = true;
			}
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result =  new StreamResult((new File(fileName)));
		transformer.transform(source, result);


		return successFlag;
	}

	public  InputStream updateXmlStreamField(String fileName, String tagName, String tagValue) throws Exception
	{
		InputStream valXMLStream = null;

		File dir1 = new File (".");
		String strBasePath=dir1.getCanonicalPath();
		String file=strBasePath+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+location+File.separator+"XMLRepository"+
		File.separator+ fileName;
		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList nl = doc.getElementsByTagName("*");
		Element nsElement;
		String localName;

		for (int i=0; i < nl.getLength(); i++)
		{
			nsElement = (Element)nl.item(i);
			localName = nsElement.getNodeName();
			if(localName.equals(tagName))
			{
				nsElement.setTextContent(tagValue);
			}
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(doc);
		Result outputTarget = new StreamResult(outputStream);
		TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
		valXMLStream = new ByteArrayInputStream(outputStream.toString().getBytes());


		return valXMLStream;
	}



	public  InputStream updateXmlStreamField(InputStream fInputStream, String tagName, String tagValue) throws Exception
	{
		InputStream valXMLStream = null;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fInputStream);
		doc.getDocumentElement().normalize();
		NodeList nl = doc.getElementsByTagName("*");
		Element nsElement;
		String localName;

		for (int i=0; i < nl.getLength(); i++)
		{
			nsElement = (Element)nl.item(i);
			localName = nsElement.getNodeName();
			if(localName.equals(tagName))
			{
				nsElement.setTextContent(tagValue);
			}
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(doc);
		Result outputTarget = new StreamResult(outputStream);
		TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
		valXMLStream = new ByteArrayInputStream(outputStream.toString().getBytes());



		return valXMLStream;
	}

}
