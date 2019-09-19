package fr.triedge.sekai.client.controller;

import java.io.File;

import javax.xml.bind.JAXBException;

import fr.triedge.sekai.client.config.ClientConfig;
import fr.triedge.sekai.common.utils.XmlHelper;

public class DefaultFactory {

	public static void generateDefaultClientConfigFile(String path) {
		File configFile = new File(path);
		ClientConfig clientConfig = new ClientConfig();
		
		clientConfig.setServerHost("localhost");
		clientConfig.setServerPort(1989);
		clientConfig.setLinkUpdate("http://triedge.fr/sekai/update.xml");
		
		
		try {
			XmlHelper.storeXml(clientConfig, configFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
