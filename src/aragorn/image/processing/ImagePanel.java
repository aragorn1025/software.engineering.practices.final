package aragorn.image.processing;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import aragorn.gui.ver3_0.FileIOConstraints;
import aragorn.gui.ver3_0.GUIFileChooserField;
import aragorn.gui.ver3_0.GUIPanel;

/**
 * Create {@code ImagePanel} for the input image or the output image.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
class ImagePanel extends GUIPanel implements ActionListener {

	/** The inner panel for the image. */
	private InnerPanel inner_panel = new InnerPanel();

	/** The file chooser field for choosing the input image. */
	private GUIFileChooserField file_chooser_field;

	/**
	 * Create the empty panel that can put the image in with the title.
	 * 
	 * @param io_type
	 *            the input or output type
	 */
	ImagePanel(FileIOConstraints io_type) {
		this(io_type, null);
	}

	/**
	 * Create the panel for the image with the title.
	 * 
	 * @param io_type
	 *            the input or the output type for FileIO
	 * @param image
	 *            the image to set
	 */
	ImagePanel(FileIOConstraints io_type, BufferedImage image) {
		super(String.format("%s image", io_type.toString()));
		setDefaultMargin(10);
		setImage(image);

		// create file chooser field and its settings
		file_chooser_field = new GUIFileChooserField(io_type) {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switch (io_type) {
						case INPUT:
							super.actionPerformed(e);
							if (getSelectedFile() != null) {
								setImage(ImageIO.read(getSelectedFile()));
							} else {
								setImage(null);
							}
							actionPerformedAlias(e);
							break;
						case OUTPUT:
							actionPerformedAlias(e);
							if (getSelectedFile() != null && getImage() != null) {
								ImageIO.write((BufferedImage) getImage(), "png", getSelectedFile());
							}
							super.actionPerformed(e);
							break;
						default:
							throw new InternalError("Unknown input/output type");
					}
				} catch (IOException exception) {
					exception.printStackTrace();
				}

			}
		};
		file_chooser_field.setButtonText(io_type.getActionString());
		file_chooser_field.setChooserDialogTitle(String.format("Choose the image to %s", io_type.getActionString().toLowerCase()));
		switch (io_type) {
			case INPUT:
				file_chooser_field.addChoosableFileFilter(new FileNameExtensionFilter("PNG image", "png"));
				file_chooser_field.addChoosableFileFilter(new FileNameExtensionFilter("JPG image", "jpg"));
				break;
			case OUTPUT:
				file_chooser_field.addChoosableFileFilter(new FileNameExtensionFilter("PNG image", "png"));
				break;
			default:
				throw new InternalError("Unknown input/output type");
		}

		addComponent(inner_panel, 0, 0, 1, 1, 1, 1, GUIPanel.CENTER, GUIPanel.BOTH);
		addComponent(file_chooser_field, 0, 1, 1, 1, 1, 0, GUIPanel.CENTER, GUIPanel.HORIZONTAL);
	}

	BufferedImage getImage() {
		return inner_panel.image;
	}

	/**
	 * Set the image to the inner panel.
	 * 
	 * @param image
	 *            the image to set
	 */
	void setImage(BufferedImage image) {
		inner_panel.setImage(image);
	}

	/**
	 * The {@code InnerPanel} is the panel that have put image with the same stretch ratio of the width and the height.
	 * 
	 * @author Aragorn
	 */
	private static class InnerPanel extends JPanel {

		/** The image to be placed. */
		private BufferedImage image;

		/** Create the empty panel that can put the image in. */
		InnerPanel() {
			this(null);
		}

		/**
		 * Create the panel for the image.
		 * 
		 * @param image
		 *            the image to set
		 */
		InnerPanel(BufferedImage image) {
			super();
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (image != null) {
				int x, y, width, height;
				if ((double) getWidth() / (double) image.getWidth(null) >= (double) getHeight() / (double) image.getHeight(null)) {
					width = (int) ((double) image.getWidth(null) * (double) getHeight() / (double) image.getHeight(null));
					height = getHeight();
					x = (getWidth() - width) / 2;
					y = 0;
				} else {
					width = getWidth();
					height = (int) ((double) image.getHeight(null) * (double) getWidth() / (double) image.getWidth(null));
					x = 0;
					y = (getHeight() - height) / 2;
				}
				g.drawImage(image, x, y, width, height, this);
			}
		}

		/**
		 * Set the image to the panel.
		 * 
		 * @param image
		 *            the image to set
		 */
		void setImage(BufferedImage image) {
			this.image = image;
			repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	private void actionPerformedAlias(ActionEvent e) {
		actionPerformed(e);
	}
}