package fr.triedge.sekai.common.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Utils {

	public static byte[] imageToBytes(BufferedImage img) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( img, "png", baos );
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}

	public static BufferedImage bytesToImage(byte[] imageData) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
		return ImageIO.read(bais);
	}

	public static void downloadFileTo(String source, String target) throws IOException {
		BufferedInputStream inputStream = new BufferedInputStream(new URL(source).openStream());
		FileOutputStream fileOS = new FileOutputStream(target);
				byte data[] = new byte[1024];
		int byteContent;
		while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
			fileOS.write(data, 0, byteContent);
		}
		fileOS.close();
	}
	
	public static void generateUpdateXmlFile(String resourcePath, String xmlPath) {
		File root = new File(resourcePath);
		File[] files = root.listFiles();
	}
	
	public static void generateZipFile(String resourcePath, String targetPath) {
		
	}
}
