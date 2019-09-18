package fr.triedge.sekai.server.storage;

import java.io.File;

import javax.xml.bind.JAXBException;

import fr.triedge.sekai.common.utils.XmlHelper;
import fr.triedge.sekai.server.model.Model;

public class XmlStorage implements Storage{
	
	private File file;

	@Override
	public void openStorage(String path) {
		setFile(new File(path));
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public Model loadModel() {
		try {
			Model model = XmlHelper.loadXml(Model.class, getFile());
			return model;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

}
