package fr.triedge.sekai.common.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Utility class to provide some handy method about xml management
 * 
 * @author sbi
 *
 */
public class XmlHelper {

	/**
	 * Generic method to store xml files based on classes with XML anotations
	 * 
	 * @param element - The object to store as xml
	 * @param file - The file path where to store the xml
	 * @throws JAXBException
	 */
	public static <T> void storeXml(T element, File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(element.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(element, file);
	}
	
	public static <T> void storeXml(T element, String path) throws JAXBException {
		storeXml(element,new File(path));
	}
	
	/**
	 * Generic method to load xml and transform it into an object which was declared with annotations
	 * @param clazz - The class of the object in which we want the xml to be casted
	 * @param file - The XML file located on the file system
	 * @return An object converted from XML
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T loadXml(Class<?> clazz, File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (T) jaxbUnmarshaller.unmarshal(file);
	}
	
	public static <T> T loadXml(Class<?> clazz, String path) throws JAXBException {
		return loadXml(clazz,new File(path));
	}
}
