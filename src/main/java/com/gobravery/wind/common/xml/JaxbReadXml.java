package com.gobravery.wind.common.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class JaxbReadXml  {
    
	 @SuppressWarnings("unchecked")
	    public static <T> T readStream(Class<T> clazz, InputStream context) throws JAXBException, SAXException, ParserConfigurationException {
	        try {
	            JAXBContext jc = JAXBContext.newInstance(clazz);
	            Unmarshaller u = jc.createUnmarshaller();
	            SAXParserFactory sax = SAXParserFactory.newInstance();
	            XMLReader xmlReader = sax.newSAXParser().getXMLReader();
	            //不进行dtd验证
	            xmlReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	            Source source = new SAXSource(xmlReader, new InputSource(context));
	            System.out.println("systemid:"+source.getSystemId());
	            return (T) u.unmarshal(source);
	        } catch (JAXBException e) {
	            // logger.trace(e);
	            throw e;
	        }
	    }

	    @SuppressWarnings("unchecked")
	    public static <T> T readConfig(Class<T> clazz, String config, Object... arguments) throws IOException,
	            JAXBException {
	        InputStream is = null;
	        try {
	            if (arguments.length > 0) {
	                config = MessageFormat.format(config, arguments);
	            }
	            // logger.trace("read configFileName=" + config);
	            JAXBContext jc = JAXBContext.newInstance(clazz);
	            Unmarshaller u = jc.createUnmarshaller();
	            is = new FileInputStream(config);
	            return (T) u.unmarshal(is);
	        } catch (IOException e) {
	            // logger.trace(config, e);
	            throw e;
	        } catch (JAXBException e) {
	            // logger.trace(config, e);
	            throw e;
	        } finally {
	            if (is != null) {
	                is.close();
	            }
	        }
	    }

	    @SuppressWarnings("unchecked")
	    public static <T> T readConfigFromString(Class<T> clazz, String filePath) throws JAXBException {
	        try {
	            JAXBContext jc = JAXBContext.newInstance(clazz);
	            Unmarshaller u = jc.createUnmarshaller();
	            return (T) u.unmarshal(new java.io.File(filePath));
	        } catch (JAXBException e) {
	            // logger.trace(e);
	            throw e;
	        }
	    }
	    public static void to() throws JAXBException, SAXException, ParserConfigurationException {
	    	Tables tbs = JaxbReadXml.readStream(Tables.class, JaxbReadXml.class.getClassLoader().getResourceAsStream("cptables.xml"));
	        System.out.println("getTables()"+tbs.getTables().size());
	        System.out.println("primarycol:"+tbs.primarycol);
	        for (Table o : tbs) {
	            System.out.println("Table:"+o.name+",cpkey:"+o.cpkey);
	            System.out.println("o.columns"+o.columns.getColumns().size());
	            for(Column c:o.columns.getColumns()){
	            	System.out.println("Column type:"+c.type+",col:"+c.value+",auto:"+c.auto);
	            }
	        }
	    }

}
