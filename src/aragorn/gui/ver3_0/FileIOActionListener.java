package aragorn.gui.ver3_0;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * The {@code FileIOActionListener} is the action listener that contains the file chooser within the specified parameters for it.
 * 
 * @author Aragorn
 * @since 3.0
 */
public abstract class FileIOActionListener implements ActionListener {

	/** The file chooser to choose the file. */
	protected JFileChooser chooser = new JFileChooser();

	/** The default directory of the file chooser. */
	protected File chooserDefaultDirectory;

	/** The selected file chosen by the file chooser. */
	protected File selectedFile;

	/** Create a action listener to choose file. */
	protected FileIOActionListener() {
		super();
		chooser.setMultiSelectionEnabled(false);
		for (int i = 0; i < chooser.getChoosableFileFilters().length; i++) {
			chooser.removeChoosableFileFilter(chooser.getChoosableFileFilters()[i]);
		}
		setDefaultDirectory(null);
	}

	public void addChoosableFileFilter(FileFilter filter) {
		chooser.addChoosableFileFilter(filter);
	}

	/**
	 * Get the selected file chosen by the file chooser.
	 * 
	 * @return selected file
	 */
	public File getSelectFile() {
		return selectedFile;
	}

	/**
	 * Set the specified default directory of the file chooser.
	 * 
	 * @param defaultDirectory
	 *            the specified default directory of the file chooser
	 */
	public void setDefaultDirectory(File defaultDirectory) {
		if (defaultDirectory == null) {
			this.chooserDefaultDirectory = new File(System.getProperty("user.home") + "/Desktop");
		} else {
			this.chooserDefaultDirectory = defaultDirectory;
		}
	}

	/**
	 * Set the specified dialog title of the file chooser.
	 * 
	 * @param title
	 *            the specified dialog title of the file chooser
	 */
	public void setDialogTitle(String title) {
		if (title != null) {
			chooser.setDialogTitle(title);
		}
	}

	protected abstract int showDialog(Component parent);
}