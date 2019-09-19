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
import fr.triedge.sekai.client.ui.UI;
import fr.triedge.sekai.common.utils.XmlHelper;

public class SekaiClient {

	private static Logger log;

	private static String CONFIG_FILE							= "client/config/client.xml";
	private static String CONFIG_LOG_LOCATION					= "client/config/log4j2.xml";

	private ClientConfig config;

	public void init(){
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
			System.exit(-1);
		}

	}

	private void loadClientConfig() throws JAXBException {
		log.debug("START: loadClientConfig()");
		log.info("Loading configuration file from: "+CONFIG_FILE);
		config = XmlHelper.loadXml(ClientConfig.class, new File(CONFIG_FILE));
		log.info("Configuration file loaded");
		log.debug("END: loadClientConfig()");
	}

	private void configureLogging() throws FileNotFoundException, IOException {
		// Set configuration file for log4j2
		ConfigurationSource source = new ConfigurationSource(new FileInputStream(CONFIG_LOG_LOCATION));
		Configurator.initialize(null, source);
		log = LogManager.getLogger(SekaiClient.class);
	}

	public ClientConfig getConfig() {
		return config;
	}

	public void setConfig(ClientConfig config) {
		this.config = config;
	}
}
