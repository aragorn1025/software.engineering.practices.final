package aragorn.image.processing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import aragorn.gui.ver3_0.FileIOConstraints;
import aragorn.gui.ver3_0.GUIFrame;
import aragorn.gui.ver3_0.GUIPanel;

/**
 * Create {@code MyFrame} for the project of the class Image Processing.
 * 
 * @author Aragorn
 * @version 1.0
 */
@SuppressWarnings("serial")
class MyFrame extends GUIFrame {

	/** The title of the frame. */
	private final static String TITLE = "Image Processing";

	/** The default dimension of the frame. */
	private final static Dimension DEFAULT_DIMENSION = new Dimension(800, 450);

	/** To maximize the frame while launch or not. */
	private final static boolean IS_MAXIMIZE_WHILE_LAUNCH = true;

	/** The panel for the input image. */
	private ImagePanel inputt_panel;

	/** The panel for the output image. */
	private ImagePanel output_panel;

	private MethodPanel method_panel;

	/** To create a frame for the project of the class image processing. */
	MyFrame() {
		super(TITLE, DEFAULT_DIMENSION, IS_MAXIMIZE_WHILE_LAUNCH);
	}

	@Override
	protected void editContentPane() {
		getContentPane().addComponent(getCombinedImagePanel(), 0, 1, 1, 1, 1, 1, GUIPanel.CENTER, GUIPanel.BOTH);

		method_panel = new MethodPanel(inputt_panel, output_panel);
		getContentPane().addComponent(method_panel, 0, 0, 1, 1, 1, 0, GUIPanel.CENTER, GUIPanel.HORIZONTAL);
	}

	private JPanel getCombinedImagePanel() {
		// layout setting
		GridLayout layout = new GridLayout(1, 2);
		layout.setHgap(10);

		// create image panels
		inputt_panel = new ImagePanel(FileIOConstraints.INPUT) {
			@Override
			public void actionPerformed(ActionEvent e) {
				super.actionPerformed(e);
				if (method_panel != null) {
					method_panel.setSelectedIndex(0);
				}
			}
		};
		output_panel = new ImagePanel(FileIOConstraints.OUTPUT);

		// mother panel
		JPanel mother_panel = new JPanel();
		mother_panel.setLayout(layout);
		mother_panel.add(inputt_panel);
		mother_panel.add(output_panel);
		return mother_panel;
	}
}