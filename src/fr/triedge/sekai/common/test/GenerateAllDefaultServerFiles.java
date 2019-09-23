package fr.triedge.sekai.common.test;

import java.io.File;

import javax.xml.bind.JAXBException;

import fr.triedge.sekai.common.model.Character;
import fr.triedge.sekai.common.model.Model;
import fr.triedge.sekai.common.model.User;
import fr.triedge.sekai.common.utils.XmlHelper;

public class GenerateAllDefaultServerFiles {

	public static void main(String[] args) {
		Model model = new Model();
		User user = new User();
		user.setId(8888);
		user.setUsername("test");
		user.setPassword("test");
		
		Character cha = new Character();
		cha.setName("Jikai");
		
		user.getCharacters().add(cha);
		
		model.getUsers().add(user);
		
		try {
			XmlHelper.storeXml(model, new File("server/storage/FileStorage/model.xml"));
			System.out.println("Done");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
