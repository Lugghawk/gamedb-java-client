package com.gamesdb.client.service;

import com.gamesdb.client.model.Platform;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.List;

/**
 * <p>Copyright 2000-2015, NetSuite, Inc.</p>
 *
 * @author dlugg
 * @since 25/06/2015
 */
public class GamesDbServiceImpl implements GamesDbService {


	@Override
	public List<Platform> getPlatformList() {
		Document doc = null;
		try {
			doc = getDocumentFromUrl("http://thegamesdb.net/api/GetPlatformsList.php");
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		assert doc != null;
		XPath xpath = XPathFactory.newInstance().newXPath();
		try {
			NodeList nodes = (NodeList) xpath.evaluate("/Data/Platforms/Platform", doc.getDocumentElement(), XPathConstants.NODESET);
			for (int i = 0; i <= nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				System.out.println(element);
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Document getDocumentFromUrl(String url) throws IOException, ParserConfigurationException, SAXException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);

		HttpResponse response = httpClient.execute(httpGet);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document doc = null;

		db = dbf.newDocumentBuilder();
		if (response != null) {
			doc = db.parse(response.getEntity().getContent());
		}

		return doc;
	}
}
