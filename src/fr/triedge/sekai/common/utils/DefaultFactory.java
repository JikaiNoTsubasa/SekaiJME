package fr.triedge.sekai.common.utils;

import java.io.File;

import javax.xml.bind.JAXBException;

import fr.triedge.sekai.client.config.ClientConfig;
import fr.triedge.sekai.common.model.Update;

public class DefaultFactory {

	public static void generateDefaultClientConfigFile(String path) {
		File configFile = new File(path);
		ClientConfig clientConfig = new ClientConfig();
		
		clientConfig.setServerHost("localhost");
		clientConfig.setServerPort(1989);
		clientConfig.setVersion("0.1");
		clientConfig.setLinkUpdate("http://triedge.fr/sekai/update.xml");
		
		
		try {
			XmlHelper.storeXml(clientConfig, configFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static void generateDefaultUpdateFile(String path) {
		Update update = new Update();
		update.setVersion("0.2");
		try {
			XmlHelper.storeXml(update, new File(path));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
