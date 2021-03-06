package day15Proj;

import java.util.ArrayList;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoadXml {

	static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	static DocumentBuilder db;
	static Document doc;

	CustomerData pl = new CustomerData();

	public static LoadXml setValues() throws Exception {
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setValidating(true);
		db = dbf.newDocumentBuilder();
		doc = db.parse("src/day15Proj/inputData.xml");
		return new LoadXml();
	}

	public void loadCustomerDetails() {
		Element e = (Element) doc.getElementsByTagName("Bacis").item(0);
		pl.name = e.getElementsByTagName("CustomerName").item(0).getTextContent();
		pl.BillNo = e.getElementsByTagName("BillNo").item(0).getTextContent();
		pl.date = e.getElementsByTagName("BillDate").item(0).getTextContent();
	}

	public void loadInvoiceDetails() {
		NodeList n = doc.getElementsByTagName("ItemInfo");
		for (int i = 0; i < n.getLength(); i++) {
			List<String> l = new ArrayList<String>();
			Node nNode = n.item(i);
			Element e = (Element) nNode;

			l.add(e.getElementsByTagName("S.nO").item(0).getTextContent());
			l.add(e.getElementsByTagName("Item").item(0).getTextContent());
			l.add(e.getElementsByTagName("Unit").item(0).getTextContent());
			l.add(e.getElementsByTagName("Price").item(0).getTextContent());
			l.add(e.getElementsByTagName("Qty").item(0).getTextContent());
			pl.addItem(l);
		}
	}

	public CustomerData load() throws Exception {
		this.loadCustomerDetails();
		this.loadInvoiceDetails();
		return pl;
	}
}
