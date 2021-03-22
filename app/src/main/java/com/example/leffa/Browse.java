package com.example.leffa;

import android.os.StrictMode;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Browse {
    protected String id;
    protected String name;
    protected String Title;
    protected String Klo;
    protected int i;
    protected ArrayList<area> areas = new ArrayList();
    protected ArrayList<theatreInfo> infot = new ArrayList();

    public void findTheatres() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 3; i < nList.getLength(); i++) {
                if ((i == 5) || (i == 17)) {
                } else {
                    Node node = nList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        id = element.getElementsByTagName("ID").item(0).getTextContent();
                        name = element.getElementsByTagName("Name").item(0).getTextContent();
                        area temp = new area(Integer.parseInt(id), name);
                        areas.add(temp);
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void findShows(String date, int selection) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Schedule/?area="+Integer.toString(areas.get(selection).getId())+"&dt="+date;
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("Show");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Title = element.getElementsByTagName("Title").item(0).getTextContent();
                    Klo = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                    theatreInfo Temp = new theatreInfo(Title, Klo);
                    infot.add(Temp);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getList() {
        return infot;
    }
    public void printAll() {
        for (i = 0; i < areas.size(); i++) {
            System.out.print("Paikka: " + areas.get(i).getName() + "\nID: " + areas.get(i).getId() + "\n");
        }
    }
}
