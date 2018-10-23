package aragorn.image.processing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import aragorn.gui.ver3_0.GUIPanel;

/**
 * @author Aragorn
 */
@SuppressWarnings("serial")
class MethodPanel extends GUIPanel implements ActionListener {

	private JComboBox<String> method_box;

	private ImagePanel inputt_panel;

	private ImagePanel output_panel;

	public MethodPanel(ImagePanel inputt_panel, ImagePanel output_panel) {
		super("Method setting");
		this.inputt_panel = inputt_panel;
		this.output_panel = output_panel;

		method_box = new JComboBox<>(Methods.toStrings());
		method_box.addActionListener(this);

		setDefaultMargin(10);
		addComponent(method_box, 0, 0, 1, 1, 1, 0, GUIPanel.CENTER, GUIPanel.HORIZONTAL);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (inputt_panel != null && output_panel != null) {
				if (inputt_panel.getImage() != null) {
					output_panel.setImage(Method.getInstance(Methods.getByIndex(method_box.getSelectedIndex())).getOutputImage(inputt_panel.getImage()));
				} else {
					output_panel.setImage(null);
				}
			}
		} catch (OutOfMemoryError error) {
			JOptionPane.showMessageDialog(this, "Out of memory: the image is too large.");
		}
	}

	void setSelectedIndex(int index) {
		method_box.setSelectedIndex(index);
	}
}