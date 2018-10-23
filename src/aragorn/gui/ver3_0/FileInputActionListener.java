package aragorn.gui.ver3_0;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * The {@code FileInputActionListener} is the action listener for file input that contains the file chooser within the specified parameters for it.
 * 
 * @author Aragorn
 */
public class FileInputActionListener extends FileIOActionListener {

	/** Create a action listener to choose file. */
	public FileInputActionListener() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chooser.setCurrentDirectory(chooserDefaultDirectory);
		int returnValue = showDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile();
			JOptionPane.showMessageDialog(null, "Open file: " + selectedFile.getAbsolutePath());
		} else {
			selectedFile = null;
			JOptionPane.showMessageDialog(null, "File input command cancelled by user.");
		}
	}

	protected int showDialog(Component parent) {
		return chooser.showOpenDialog(parent);
	}
}