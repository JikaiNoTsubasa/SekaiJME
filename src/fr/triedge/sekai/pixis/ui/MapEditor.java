package fr.triedge.sekai.pixis.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToolBar;

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
		
		getBtnSelectChipset().addActionListener(e -> actionSelectChipset());
	}
	
	public void actionSelectChipset() {
		JFileChooser.
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
