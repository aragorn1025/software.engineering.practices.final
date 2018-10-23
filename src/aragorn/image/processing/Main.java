package aragorn.image.processing;

import aragorn.gui.ver3_0.GUIFrame;

/**
 * Main class for the project of the class Image Processing.
 * 
 * @author Aragorn
 */
class Main {

	/**
	 * The main function.
	 * 
	 * @param args
	 *            the arguments for main function
	 */
	public static void main(String[] args) {
		GUIFrame.setDefaultLookAndFeelDecorated(true);

		MyFrame frame = new MyFrame();
		frame.setVisible(true);
	}
}