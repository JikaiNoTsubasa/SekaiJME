package fr.triedge.sekai.pixis.ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.bind.JAXBException;

import fr.triedge.sekai.common.utils.Utils;
import fr.triedge.sekai.pixis.controller.PixisController;
import fr.triedge.sekai.pixis.model.EditableMap;
import fr.triedge.sekai.pixis.model.Project;
import fr.triedge.sekai.pixis.model.Tile;

public class MapEditor extends JPanel{

	private static final long serialVersionUID = -2436162250351121940L;
	private static final String LAYER_GROUND				= "Ground";
	private static final String LAYER_OBJECTS				= "Objects";
	private static final String LAYER_EVENTS				= "Events";

	private Project project;
	private EditableMap editableMap;
	private PixisController controller;
	private MapDisplayer mapDisplayer;
	private ChipsetDisplayer chipsetDisplayer;

	private JButton btnSelectChipset, btnExportMap;
	private JToolBar toolbar;
	private JComboBox<Integer> comboZoomFactor;
	private JComboBox<String> comboCurrentLayer;

	private BufferedImage currentChipset;
	private String currentLayer;
	private Tile selectedTile;
	private JSplitPane splitPane;
	private JScrollPane scrollMapDisplayer, scrollChipsetDisplayer;


	private int zoomFactor = 1;

	public MapEditor(PixisController controller, EditableMap map, Project project) {
		setController(controller);
		setEditableMap(map);
		setProject(project);
	}

	public void build() {
		setLayout(new BorderLayout());
		setToolbar(new JToolBar());
		setBtnSelectChipset(new JButton("Chipset"));
		setBtnExportMap(new JButton("Export"));
		setMapDisplayer(new MapDisplayer());
		setChipsetDisplayer(new ChipsetDisplayer());
		setSplitPane(new JSplitPane(JSplitPane.VERTICAL_SPLIT));
		setCurrentLayer(LAYER_GROUND);

		setScrollMapDisplayer(new JScrollPane(getMapDisplayer()));
		getScrollMapDisplayer().getViewport().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				redraw();
			}
		});
		setScrollChipsetDisplayer(new JScrollPane(getChipsetDisplayer()));
		getScrollChipsetDisplayer().getViewport().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				redraw();
			}
		});

		getSplitPane().add(getScrollMapDisplayer());
		getSplitPane().add(getScrollChipsetDisplayer());

		Integer[] comb = {1,2,3,4,5};
		setComboZoomFactor(new JComboBox<Integer>(comb));
		getComboZoomFactor().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				Integer value = (Integer)e.getItem();
				updateZoomFactor(value);
			}
		});
		String[] layers = {LAYER_GROUND, LAYER_OBJECTS, LAYER_EVENTS};
		setComboCurrentLayer(new JComboBox<String>(layers));
		getComboCurrentLayer().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String value = (String)e.getItem();
				setCurrentLayer(value);
			}
		});

		getToolbar().setFloatable(false);
		getToolbar().add(getBtnSelectChipset());
		getToolbar().add(getBtnExportMap());
		getToolbar().add(new JLabel("Layer:"));
		getToolbar().add(getComboCurrentLayer());
		getToolbar().add(new JLabel("Zoom:"));
		getToolbar().add(getComboZoomFactor());

		getBtnSelectChipset().addActionListener(e -> actionSelectChipset());
		getBtnExportMap().addActionListener(e -> actionExportMap());

		add(getToolbar(), BorderLayout.NORTH);
		add(getSplitPane(), BorderLayout.CENTER);

		updateCurrentChipset();
		updateMapDisplayerSize();
	}

	private void actionExportMap() {
		BufferedImage img = new BufferedImage(
				getEditableMap().getMapWidth()*getEditableMap().getTileSize(), 
				getEditableMap().getMapHeight()*getEditableMap().getTileSize(), 
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = img.getGraphics();
		drawTilesLayer(getEditableMap().getGoundTiles(), getEditableMap().getTileSize(), g);
		drawTilesLayer(getEditableMap().getObjectTiles(), getEditableMap().getTileSize(), g);
		File outputfile = new File("pixis/img/sprite/test.png");
		try {
			ImageIO.write(img, "png", outputfile);
			PixisUI.info("Export done to pixis/img/sprite/test.png");
		} catch (IOException e) {
			PixisUI.error("Cannot save image", e);
		}
	}

	private void redraw() {
		this.revalidate();
		this.repaint();
	}

	private class MapDisplayer extends JPanel implements MouseListener, MouseMotionListener{

		private static final long serialVersionUID = -2823063788900209076L;

		public MapDisplayer() {
			addMouseListener(this);
			addMouseMotionListener(this);
		}

		@Override
		protected void paintComponent(Graphics g) {
			EditableMap map = getEditableMap();
			if (map == null)
				return;
			int rectWidth = map.getMapWidth()*map.getTileSize()*getZoomFactor();
			int rectHeight = map.getMapHeight()*map.getTileSize()*getZoomFactor();
			// Background color
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, rectWidth, rectHeight);

			// Draw map
			drawTilesLayer(map.getGoundTiles(), map.getTileSize(), g);
			drawTilesLayer(map.getObjectTiles(), map.getTileSize(), g);

			// Foreground limit rectangle
			g.setColor(Color.red);
			g.drawRect(0, 0, rectWidth, rectHeight);
		}

		

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			updateTile(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			updateTile(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
		
		private void updateTile(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (getSelectedTile() != null) {
					int x = Utils.roundDown((e.getX()/getEditableMap().getTileSize())/getZoomFactor());
					int y = Utils.roundDown((e.getY()/getEditableMap().getTileSize())/getZoomFactor());
					//System.out.println("Clicked tile: "+x+":"+y);
					getSelectedTile().setX(x);
					getSelectedTile().setY(y);
					Tile newTile = getSelectedTile().copy();

					switch(getCurrentLayer()) {
					case LAYER_GROUND:
						removeTilesAt(getEditableMap().getGoundTiles(), x, y);
						getEditableMap().getGoundTiles().add(newTile);
						break;
					case LAYER_OBJECTS:
						removeTilesAt(getEditableMap().getObjectTiles(), x, y);
						getEditableMap().getObjectTiles().add(newTile);
						break;
					default:
						break;
					}
					//System.out.println("Created new: "+newTile);
					redraw();
					saveProject();
				}
			}
		}
	}
	
	private void drawTilesLayer(ArrayList<Tile> tiles, int tileSize, Graphics g) {
		for (Tile tile : tiles) {
			if (getCurrentChipset() == null) {
				System.out.println("Current chipset null, cannot paint");
				break;
			}
			int size = tileSize;
			g.drawImage(
					getCurrentChipset(), 
					tile.getX() * size * getZoomFactor(), 
					tile.getY() * size * getZoomFactor(), 
					((tile.getX() * size) + size) * getZoomFactor(), 
					((tile.getY() * size) + size) * getZoomFactor(), 
					tile.getChipsetX() * size, 
					tile.getChipsetY() * size, 
					(tile.getChipsetX() * size) + size, 
					(tile.getChipsetY() * size) + size, 
					null);
		}
	}
	
	private void removeTilesAt(ArrayList<Tile> tiles, int x, int y) {
		Iterator<Tile> it = tiles.iterator();
		while(it.hasNext()) {
			Tile tile = it.next();
			if (tile.getX() == x && tile.getY() == y)
				it.remove();
		}
	}
	
	private void saveProject() {
		try {
			getProject().save();
		} catch (JAXBException e) {
			PixisUI.error("Cannot save project", e);
		}
	}

	private class ChipsetDisplayer extends JPanel implements MouseListener{

		private static final long serialVersionUID = -3000027470876270723L;

		public ChipsetDisplayer() {
			addMouseListener(this);
		}

		@Override
		protected void paintComponent(Graphics g) {
			EditableMap map = getEditableMap();
			if (map == null || getCurrentChipset() == null)
				return;
			g.drawImage(getCurrentChipset(), 0, 0, null);
			if (getSelectedTile() != null) {
				int size = map.getTileSize();
				g.setColor(Color.red);
				//creates a copy of the Graphics instance
				Graphics2D g2d = (Graphics2D) g.create();

				//set the stroke of the copy, not the original 
				Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4,2}, 0);
				g2d.setStroke(dashed);

				//gets rid of the copy
				g2d.drawRect(
						getSelectedTile().getChipsetX()*size, 
						getSelectedTile().getChipsetY()*size, 
						size, 
						size);
				g2d.dispose();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (getEditableMap() != null) {
					int chipsetX = Utils.roundDown(e.getX() / getEditableMap().getTileSize());
					int chipsetY = Utils.roundDown(e.getY() / getEditableMap().getTileSize());
					Tile tile = new Tile();
					tile.setChipsetX(chipsetX);
					tile.setChipsetY(chipsetY);
					setSelectedTile(tile);
					redraw();
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

	private void updateChipsetDisplayerSize() {
		if (getCurrentChipset() == null)
			return;
		Dimension dim = new Dimension(getCurrentChipset().getWidth(),getCurrentChipset().getHeight());
		getChipsetDisplayer().setPreferredSize(dim);
		getChipsetDisplayer().setSize(dim);
		redraw();
	}

	private void updateMapDisplayerSize() {
		if (getEditableMap() != null) {
			Dimension dim = new Dimension(
					(getEditableMap().getMapWidth()*getEditableMap().getTileSize()*getZoomFactor()) + 1, 
					(getEditableMap().getMapHeight()*getEditableMap().getTileSize()*getZoomFactor()) + 1);
			getMapDisplayer().setPreferredSize(dim);
			getMapDisplayer().setSize(dim);
			redraw();
		}
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
			updateCurrentChipset();
		} catch (IOException e) {
			PixisUI.error("Cannot load chipset image", e);
		} catch (JAXBException e) {
			PixisUI.error("Cannot save project", e);
		}
	}

	private void updateZoomFactor(int value) {
		setZoomFactor(value);
		updateMapDisplayerSize();
		redraw();
	}

	private void updateCurrentChipset() {
		if (getEditableMap() != null && getEditableMap().getChipset() != null) {
			try {
				setCurrentChipset(Utils.stringToImage(getEditableMap().getChipset()));
				updateChipsetDisplayerSize();
				redraw();
			} catch (IOException e) {
				PixisUI.error("Cannot load current chipset", e);
			}
		}else {
			PixisUI.warn("Current chipset is null, please select one");
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

	public MapDisplayer getMapDisplayer() {
		return mapDisplayer;
	}

	public void setMapDisplayer(MapDisplayer mapDisplayer) {
		this.mapDisplayer = mapDisplayer;
	}

	public int getZoomFactor() {
		return zoomFactor;
	}

	public void setZoomFactor(int zoomFactor) {
		this.zoomFactor = zoomFactor;
	}

	public BufferedImage getCurrentChipset() {
		return currentChipset;
	}

	public void setCurrentChipset(BufferedImage currentChipset) {
		this.currentChipset = currentChipset;
	}

	public JComboBox<Integer> getComboZoomFactor() {
		return comboZoomFactor;
	}

	public void setComboZoomFactor(JComboBox<Integer> comboZoomFactor) {
		this.comboZoomFactor = comboZoomFactor;
	}

	public JComboBox<String> getComboCurrentLayer() {
		return comboCurrentLayer;
	}

	public void setComboCurrentLayer(JComboBox<String> comboCurrentLayer) {
		this.comboCurrentLayer = comboCurrentLayer;
	}

	public String getCurrentLayer() {
		return currentLayer;
	}

	public void setCurrentLayer(String currentLayer) {
		this.currentLayer = currentLayer;
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}

	public ChipsetDisplayer getChipsetDisplayer() {
		return chipsetDisplayer;
	}

	public void setChipsetDisplayer(ChipsetDisplayer chipsetDisplayer) {
		this.chipsetDisplayer = chipsetDisplayer;
	}

	public JScrollPane getScrollMapDisplayer() {
		return scrollMapDisplayer;
	}

	public void setScrollMapDisplayer(JScrollPane scrollMapDisplayer) {
		this.scrollMapDisplayer = scrollMapDisplayer;
	}

	public JScrollPane getScrollChipsetDisplayer() {
		return scrollChipsetDisplayer;
	}

	public void setScrollChipsetDisplayer(JScrollPane scrollChipsetDisplayer) {
		this.scrollChipsetDisplayer = scrollChipsetDisplayer;
	}

	public Tile getSelectedTile() {
		return selectedTile;
	}

	public void setSelectedTile(Tile selectedTile) {
		this.selectedTile = selectedTile;
	}

	public JButton getBtnExportMap() {
		return btnExportMap;
	}

	public void setBtnExportMap(JButton btnExportMap) {
		this.btnExportMap = btnExportMap;
	}

}
