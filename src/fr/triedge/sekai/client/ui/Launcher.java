package fr.triedge.sekai.client.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.triedge.sekai.client.controller.LauncherImpl;

public class Launcher extends JFrame{

	private static final long serialVersionUID = 7187019555370115990L;
	
	private LauncherImpl impl;
	
	private JLabel labelStatus;
	private JTextField textUsername;
	private JPasswordField textPassword;
	private JButton btnLogin, btnCreateUser;
	private JPanel mainPanel, gridPanel;
	
	public Launcher(LauncherImpl laucnher) {
		setImpl(laucnher);
	}

	public void build() {
		setTitle("Launcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(800, 400);
		
		// Components
		setMainPanel(new JPanel(new BorderLayout()));
		setGridPanel(new JPanel(new GridLayout(2,0)));
		setLabelStatus(new JLabel("Ready"));
		setTextUsername(new JTextField());
		setTextPassword(new JPasswordField());
		setBtnCreateUser(new JButton("Create Account"));
		setBtnLogin(new JButton("Login"));
		
		getBtnLogin().addActionListener(e -> actionLogin());
		getBtnCreateUser().addActionListener(e -> actionCreateUser());
		
		getGridPanel().add(new JLabel("Username"));
		getGridPanel().add(getTextUsername());
		getGridPanel().add(getBtnLogin());
		getGridPanel().add(new JLabel("Password"));
		getGridPanel().add(getTextPassword());
		getGridPanel().add(getBtnCreateUser());
		
		getMainPanel().add(getGridPanel(),BorderLayout.SOUTH);
		setContentPane(getMainPanel());
		
		setVisible(true);
	}
	
	private void actionLogin() {
		String username = getTextUsername().getText();
		String password = new String(getTextPassword().getPassword());
		
		System.out.println(username+" "+password);
	}
	
	private void actionCreateUser() {
		
	}

	public JLabel getLabelStatus() {
		return labelStatus;
	}

	public void setLabelStatus(JLabel labelStatus) {
		this.labelStatus = labelStatus;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JButton getBtnCreateUser() {
		return btnCreateUser;
	}

	public void setBtnCreateUser(JButton btnCreateUser) {
		this.btnCreateUser = btnCreateUser;
	}

	public JTextField getTextUsername() {
		return textUsername;
	}

	public void setTextUsername(JTextField textUsername) {
		this.textUsername = textUsername;
	}

	public JPasswordField getTextPassword() {
		return textPassword;
	}

	public void setTextPassword(JPasswordField textPassword) {
		this.textPassword = textPassword;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JPanel getGridPanel() {
		return gridPanel;
	}

	public void setGridPanel(JPanel gridPanel) {
		this.gridPanel = gridPanel;
	}

	public LauncherImpl getImpl() {
		return impl;
	}

	public void setImpl(LauncherImpl impl) {
		this.impl = impl;
	}
}
