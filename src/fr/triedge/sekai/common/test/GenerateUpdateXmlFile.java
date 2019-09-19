package fr.triedge.sekai.common.test;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import fr.triedge.sekai.common.model.Update;
import fr.triedge.sekai.common.utils.XmlHelper;
import fr.triedge.sekai.common.utils.ZipHelper;

public class GenerateUpdateXmlFile {

	public static void main(String[] args) {
		String VERSION = "0.1";
		String SITE = "http://triedge.fr/sekai/";
		
		Update update = new Update(VERSION);
		update.setZipPath(SITE+"client_"+VERSION+".zip");
		
		try {
			XmlHelper.storeXml(update, new File("dev/update.xml"));
			ZipHelper.zipFolder("D:\\Dev\\GIT\\DCTMUI", "dev/client_"+VERSION+".zip");
			System.out.println("Done");
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
