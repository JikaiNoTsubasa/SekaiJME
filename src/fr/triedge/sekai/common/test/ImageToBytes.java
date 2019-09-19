package fr.triedge.sekai.common.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;

import fr.triedge.sekai.common.model.Map;
import fr.triedge.sekai.common.utils.Utils;
import fr.triedge.sekai.common.utils.XmlHelper;

public class ImageToBytes {

	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File("dev/Dummy.png"));
			String data = new String(Utils.imageToBytes(img));
			
			Map map = new Map();
			map.setMapName("Dummy");
			map.setMapImage(data);
			try {
				XmlHelper.storeXml(map, new File("dev/Dummy.map"));
				System.out.println("Done");
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
