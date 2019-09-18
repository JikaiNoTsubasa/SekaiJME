package fr.triedge.sekai.server.test;

import java.io.File;

import javax.xml.bind.JAXBException;

import fr.triedge.sekai.common.utils.XmlHelper;
import fr.triedge.sekai.server.model.Character;
import fr.triedge.sekai.server.model.Model;
import fr.triedge.sekai.server.model.User;

public class TestConfig {

	public static void main(String[] args) {
		/*
		ServerConfig conf = new ServerConfig();
		conf .setPort(1989);
		
		try {
			XmlHelper.storeXml(conf, new File("server/config/server.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		*/
		
		Model model = new Model();
		Character ch = new Character();
		
		User user = new User();
		user.setId(1);
		user.setUsername("spyroth");
		
		ch.setId(1);
		ch.setName("Jikai");
		
		user.getCharacters().add(ch);
		model.getUsers().add(user);
		
		try {
			XmlHelper.storeXml(model, new File("storage/FileStorage/model.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
