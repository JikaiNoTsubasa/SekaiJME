package fr.triedge.sekai.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.network.Server;

import fr.triedge.sekai.common.net.MSGClientAskLoginServer;
import fr.triedge.sekai.common.utils.XmlHelper;
import fr.triedge.sekai.server.model.Model;
import fr.triedge.sekai.server.storage.Storage;
import fr.triedge.sekai.server.storage.XmlStorage;

public class SekaiServer implements Runnable, MessageListener<HostedConnection>{
	
	private static String CONFIG_FILE							= "server/config/server.xml";
	private static String CONFIG_LOG_LOCATION					= "server/config/log4j2.xml";
	private Server server;
	private ServerConfig config;
	private static Logger log;
	private boolean running = true;
	private Storage storage;
	private Model model;
	
	private void init() {
		// Init logs
		try {
			configureLogging();
		} catch (IOException e1) {
			log.error("Cannot initiate logging", e1);
		}
		
		try {
			// Init config
			config = XmlHelper.loadXml(ServerConfig.class, new File(CONFIG_FILE));
			server = Network.createServer(config.getPort());
			server.addMessageListener(this);
		} catch (JAXBException | IOException e) {
			log.error("Cannot initiate server", e);
		}
		
		// Init storage
		storage = new XmlStorage();
		storage.openStorage("storage/FileStorage/model.xml");
		
		// Load storage
		model = storage.loadModel();
		
		if (model == null) {
			log.error("Model is null");
			return;
		}
		
		log.debug("Server initialized");
	}

	@Override
	public void run() {
		// Init server
		init();
		// Start listening
		server.start();
		
		log.debug("Server running");
		while (isRunning()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void messageReceived(HostedConnection source, Message mes) {
		if (mes instanceof MSGClientAskLoginServer) {
			String username = ((MSGClientAskLoginServer) mes).getUsername();
			String password = ((MSGClientAskLoginServer) mes).getPassword();
			
			
		}
	}
	
	private void configureLogging() throws FileNotFoundException, IOException {
		// Set configuration file for log4j2
		ConfigurationSource source = new ConfigurationSource(new FileInputStream(CONFIG_LOG_LOCATION));
		Configurator.initialize(null, source);
		log = LogManager.getLogger(SekaiServer.class);
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public ServerConfig getConfig() {
		return config;
	}

	public void setConfig(ServerConfig config) {
		this.config = config;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
