package com.osm2xp.utils.helpers;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import com.osm2xp.exceptions.Osm2xpBusinessException;

/**
 * XmlHelper.
 * 
 * @author Benjamin Blanchet
 * 
 */
public class XmlHelper {

	/**
	 * @param file
	 * @param bean
	 * @throws Osm2xpBusinessException
	 */
	public static void saveToXml(Object bean, File file)
			throws Osm2xpBusinessException {
		try {
			JAXBContext jc = JAXBContext.newInstance(bean.getClass()
					.getPackage().getName());
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			JAXBElement<?> jaxbElement = new JAXBElement(new QName("", bean
					.getClass().getSimpleName()), bean.getClass(), bean);
			marshaller.marshal(jaxbElement, file);
		} catch (JAXBException e) {
			throw new Osm2xpBusinessException(e.getMessage());
		}
	}

	/**
	 * @return
	 * @throws Osm2xpBusinessException
	 */
	public static Object loadFileFromXml(File file, Class<?> type)
			throws Osm2xpBusinessException {
		Object result = new Object();
		try {
			JAXBContext jc = JAXBContext.newInstance(type.getPackage()
					.getName());
			Unmarshaller u = jc.createUnmarshaller();

			JAXBElement<?> root = u.unmarshal(new StreamSource(file), type);
			if (!root.getName().getLocalPart()
					.equalsIgnoreCase(type.getSimpleName())) {
				throw new Osm2xpBusinessException("File " + file.getName()
						+ " is not of type " + type.getSimpleName());
			}
			result = root.getValue();
		} catch (JAXBException e) {
			throw new Osm2xpBusinessException(e.getMessage());
		}
		return result;
	}

}
