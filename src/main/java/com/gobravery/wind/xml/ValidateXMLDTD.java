package com.gobravery.wind.xml;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ValidateXMLDTD {
    
    public static void main(String[] args) {
//        System.out.println("���Է���DTD�淶��XML�ļ�");
        test("cptables.xml");
        
//        System.out.println("���Բ�����DTD�淶��XML�ļ�");
//        testWorkder();
        
    }
    
    public static boolean test(String xml) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.parse(ValidateXMLDTD.class.getResourceAsStream(xml));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
