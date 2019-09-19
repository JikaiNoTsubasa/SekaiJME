package fr.triedge.sekai.client.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import fr.triedge.sekai.client.config.ClientConfig;
import fr.triedge.sekai.client.ui.Launcher;
import fr.triedge.sekai.client.ui.UI;
import fr.triedge.sekai.common.utils.Utils;
import fr.triedge.sekai.common.utils.XmlHelper;

public class LauncherImpl {

	private static Logger log;

	private static String CONFIG_FILE							= "client/config/client.xml";
	private static String CONFIG_LOG_LOCATION					= "client/config/log4j2.xml";
	private static String TMP_UPDATE_FILE						= "client/tmp/update.xml";
	
	private ClientConfig config;
	private Launcher launcherUI;
	
	public static void main(String[] args) {
		LauncherImpl impl = new LauncherImpl();
		impl.init();
	}

	public void init() {
		// Configure logging
		try {
			configureLogging();
		} catch (IOException e) {
			UI.error("Cannot create logging, is "+CONFIG_LOG_LOCATION+" missing?", e);
		}

		// Load client configuration
		try {
			loadClientConfig();
		} catch (JAXBException e) {
			log.error("Cannot load config file "+CONFIG_FILE,e);
			UI.error("Cannot load config file "+CONFIG_FILE,e);
		}
		
		// Start UI
		startLauncherUI();
		
		// Start update
		try {
			updateStatus("Downloading MANIFEST...");
			checkForUpdates();
			updateStatus("Extracting MANIFEST...");
		} catch (IOException e) {
			log.error("Cannot check for updates",e);
			UI.error("Cannot check for updates",e);
		}
	}
	
	public void updateStatus(String status) {
		UI.queue(new Runnable() {
			
			@Override
			public void run() {
				getLauncherUI().getLabelStatus().setText(status);
				getLauncherUI().repaint();
			}
		});
	}
	
	private void startLauncherUI() {
		setLauncherUI(new Launcher(this));
		getLauncherUI().build();
	}

	private void checkForUpdates() throws IOException {
		String url = config.getLinkUpdate();
		Utils.downloadFileTo(url, TMP_UPDATE_FILE);
		
		
	}
	
	private void loadClientConfig() throws JAXBException {
		log.debug("START: loadClientConfig()");
		log.info("Loading configuration file from: "+CONFIG_FILE);
		setConfig(XmlHelper.loadXml(ClientConfig.class, new File(CONFIG_FILE)));
		log.info("Configuration file loaded");
		log.debug("END: loadClientConfig()");
	}

	private void configureLogging() throws FileNotFoundException, IOException {
		// Set configuration file for log4j2
		ConfigurationSource source = new ConfigurationSource(new FileInputStream(CONFIG_LOG_LOCATION));
		Configurator.initialize(null, source);
		log = LogManager.getLogger(LauncherImpl.class);
	}

	public ClientConfig getConfig() {
		return config;
	}

	public void setConfig(ClientConfig config) {
		this.config = config;
	}

	public Launcher getLauncherUI() {
		return launcherUI;
	}

	public void setLauncherUI(Launcher launcherUI) {
		this.launcherUI = launcherUI;
	}
}
