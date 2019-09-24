package fr.triedge.sekai.pixis.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.xml.bind.JAXBException;

import fr.triedge.sekai.client.ui.UI;
import fr.triedge.sekai.common.utils.Utils;
import fr.triedge.sekai.pixis.controller.PixisController;
import fr.triedge.sekai.pixis.model.EditableMap;
import fr.triedge.sekai.pixis.model.Project;

public class MapEditor extends JPanel{

	private static final long serialVersionUID = -2436162250351121940L;
	
	private Project project;
	private EditableMap editableMap;
	private PixisController controller;
	
	private JButton btnSelectChipset;
	private JToolBar toolbar;
	
	public MapEditor(PixisController controller, EditableMap map, Project project) {
		setController(controller);
		setEditableMap(map);
		setProject(project);
		
	}
	
	public void build() {
		setLayout(new BorderLayout());
		setToolbar(new JToolBar());
		setBtnSelectChipset(new JButton("Chipset"));
		
		getToolbar().setFloatable(false);
		getToolbar().add(getBtnSelectChipset());
		
		getBtnSelectChipset().addActionListener(e -> actionSelectChipset());
		
		add(getToolbar(), BorderLayout.NORTH);
	}
	
	private class MapDisplayer extends JPanel{
		
	}
	
	public void actionSelectChipset() {
		File chipset = PixisUI.showChipsetChooser();
		if (chipset == null)
			return;
		String chipString;
		try {
			chipString = Utils.imageToString(ImageIO.read(chipset));
			getEditableMap().setChipset(chipString);
			getProject().save();
		} catch (IOException e) {
			UI.error("Cannot load chipset image", e);
		} catch (JAXBException e) {
			UI.error("Cannot save project", e);
		}
	}
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public PixisController getController() {
		return controller;
	}
	public void setController(PixisController controller) {
		this.controller = controller;
	}

	public EditableMap getEditableMap() {
		return editableMap;
	}

	public void setEditableMap(EditableMap editableMap) {
		this.editableMap = editableMap;
	}

	public JButton getBtnSelectChipset() {
		return btnSelectChipset;
	}

	public void setBtnSelectChipset(JButton btnSelectChipset) {
		this.btnSelectChipset = btnSelectChipset;
	}

	public JToolBar getToolbar() {
		return toolbar;
	}

	public void setToolbar(JToolBar toolbar) {
		this.toolbar = toolbar;
	}

}
