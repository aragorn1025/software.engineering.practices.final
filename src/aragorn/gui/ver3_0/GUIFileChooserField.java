package aragorn.gui.ver3_0;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 * The {@code GUIFileChooserField} is the file chooser field within a text filed for the path of the chosen file and a button to access the file
 * chooser.
 * 
 * @author Aragorn
 * @since 3.0
 */
@SuppressWarnings("serial")
public class GUIFileChooserField extends GUIPanel implements ActionListener {

	/** The text field to print the directory of the chosen file. */
	private JTextField directoryField = new JTextField();

	/** The button to access the file chooser. */
	private JButton chooserButton = new JButton();

	/**
	 * Create the file chooser field within a text filed for the path of the chosen file and a button to access the file chooser.
	 * 
	 * @param io_type
	 *            input or output type to set
	 */
	public GUIFileChooserField(FileIOConstraints io_type) {
		super();

		directoryField.setBackground(Color.WHITE);
		directoryField.setEditable(false);
		switch (io_type) {
			case INPUT:
				chooserButton.addActionListener(new FileInputActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						super.actionPerformed(e);
						actionPerformedAlias(e);
					}
				});
				break;
			case OUTPUT:
				chooserButton.addActionListener(new FileOutputActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						super.actionPerformed(e);
						actionPerformedAlias(e);
					}
				});
				break;
			default:
				throw new InternalError("Unknown input/output type");
		}
		chooserButton.setText(io_type.getActionString());

		setDefaultMargin(0);
		addComponent(directoryField, 0, 0, 1, 1, 1, 0, GUIPanel.WEST, GUIPanel.HORIZONTAL, new Insets(0, 0, 0, 5));
		addComponent(chooserButton, 1, 0, 1, 1, 0, 0, GUIPanel.CENTER, GUIPanel.NONE, new Insets(0, 5, 0, 0));

		setButtonText(io_type.getActionString());
	}

	/** The function executes after selecting the file. */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (getSelectedFile() != null) {
			directoryField.setText(getSelectedFile().getPath());
		} else {
			directoryField.setText("");
		}
	}

	/**
	 * The alias function for the {@code actionPerform(e)} compared from the other action listener.
	 * 
	 * @param e
	 *            action event to set
	 */
	private void actionPerformedAlias(ActionEvent e) {
		actionPerformed(e);
	}

	/**
	 * Get the action listener of the open button.
	 * 
	 * @return the listener of the chooser
	 */
	private FileIOActionListener getFileIOActionListener() {
		return (FileIOActionListener) (chooserButton.getActionListeners()[0]);
	}

	public void addChoosableFileFilter(FileFilter filter) {
		getFileIOActionListener().addChoosableFileFilter(filter);
	}

	/**
	 * Get the selected file chosen by the file chooser.
	 * 
	 * @return the selectedFile
	 */
	public File getSelectedFile() {
		return getFileIOActionListener().getSelectFile();
	}

	public void setButtonText(String text) {
		chooserButton.setText(text);
	}

	/**
	 * Set the specified default directory of the file chooser.
	 * 
	 * @param defaultDirectory
	 *            the specified default directory of the file chooser
	 */
	public void setChooserDefaultDirectory(File defaultDirectory) {
		getFileIOActionListener().setDefaultDirectory(defaultDirectory);
	}

	/**
	 * Set the specified dialog title of the file chooser.
	 * 
	 * @param title
	 *            the specified dialog title of the file chooser
	 */
	public void setChooserDialogTitle(String title) {
		getFileIOActionListener().setDialogTitle(title);
	}
}