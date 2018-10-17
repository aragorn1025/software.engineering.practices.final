package aragorn.gui.ver3_0;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The {@code FileIOActionListener} is the action listener that contains the file chooser within the specified parameters for it.
 * 
 * @author Aragorn
 * @since 3.0
 */
public class FileOutputActionListener extends FileIOActionListener {

	/** Create a action listener to choose file. */
	public FileOutputActionListener() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chooser.setCurrentDirectory(chooserDefaultDirectory);
		int returnValue = showDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			if (chooser.getChoosableFileFilters().length > 0) {
				selectedFile = new File(toNormalizedFilePathString(chooser.getSelectedFile(),
						((FileNameExtensionFilter) (chooser.getChoosableFileFilters()[0])).getExtensions()[0]));
			} else {
				selectedFile = chooser.getSelectedFile();
			}
			JOptionPane.showMessageDialog(null, "Save file: " + selectedFile.getPath());
		} else {
			selectedFile = null;
			JOptionPane.showMessageDialog(null, "File output command cancelled by user.");
		}
	}

	protected int showDialog(Component parent) {
		return chooser.showSaveDialog(parent);
	}

	private String toNormalizedFilePathString(File file, String extension) {
		if (file.isDirectory()) {
			throw new IllegalArgumentException("File should not be directory.");
		}
		String file_path = file.getAbsolutePath();
		String file_name = file.getName();
		String file_directory = file_path.substring(0, file_path.length() - file_name.length());
		if (file_name.length() <= 0) {
			file_name = "output";
		} else if (file_name.length() <= extension.length() + 1) {
			if (file_name.length() == extension.length() + 1 && file_name.equals(String.format(".%s", extension))) {
				file_name = "output";
			}
		} else {
			if (file_name.endsWith(String.format(".%s", extension))) {
				return String.format("%s/%s", file_directory, file_name);
			}
		}
		return String.format("%s/%s.%s", file_directory, file_name, extension);
	}
}