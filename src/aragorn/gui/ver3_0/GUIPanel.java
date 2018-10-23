package aragorn.gui.ver3_0;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * {@code GUIPanel} is a panel which extends {@code javax.swing.JPanel} and uses {@code java.awt.GridBagLayout}.
 * 
 * @author Aragorn
 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
 */
@SuppressWarnings("serial")
public class GUIPanel extends JPanel {

	/**
	 * Constants for fill:<br>
	 * Resize the component both horizontally and vertically.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int BOTH = GridBagConstraints.BOTH;

	/**
	 * Constants for anchor:<br>
	 * Put the component in the center of its display area.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int CENTER = GridBagConstraints.CENTER;

	/**
	 * Constants for anchor:<br>
	 * Put the component on the right side of its display area, centered vertically.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int EAST = GridBagConstraints.EAST;

	/**
	 * Constants for fill:<br>
	 * Resize the component horizontally but not vertically.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int HORIZONTAL = GridBagConstraints.HORIZONTAL;

	/**
	 * Constants for fill:<br>
	 * Do not resize the component.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int NONE = GridBagConstraints.NONE;

	/**
	 * Constants for anchor:<br>
	 * Put the component at the top of its display area, centered horizontally.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int NORTH = GridBagConstraints.NORTH;

	/**
	 * Constants for anchor:<br>
	 * Put the component at the top-right corner of its display area.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int NORTHEAST = GridBagConstraints.NORTHEAST;

	/**
	 * Constants for anchor:<br>
	 * Put the component at the top-left corner of its display area.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int NORTHWEST = GridBagConstraints.NORTHWEST;

	/**
	 * Constants for anchor:<br>
	 * Put the component at the bottom of its display area, centered horizontally.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int SOUTH = GridBagConstraints.SOUTH;

	/**
	 * Constants for anchor:<br>
	 * Put the component at the bottom-right corner of its display area.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int SOUTHEAST = GridBagConstraints.SOUTHEAST;

	/**
	 * Constants for anchor:<br>
	 * Put the component at the bottom-left corner of its display area.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int SOUTHWEST = GridBagConstraints.SOUTHWEST;

	/**
	 * Constants for fill:<br>
	 * Resize the component vertically but not horizontally.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int VERTICAL = GridBagConstraints.VERTICAL;

	/**
	 * Constants for anchor:<br>
	 * Put the component on the left side of its display area, centered vertically.
	 * 
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public static final int WEST = GridBagConstraints.WEST;

	/**
	 * Set default margin between components.<br>
	 * The margin should be non-negative and its default value is {@code 0}.
	 */
	private int margin = 0;

	/** Creates a {@code GUIPanel} which extends {@code JPanel} and uses {@code GridBagLayout}. */
	public GUIPanel() {
		setLayout(new GridBagLayout());
	}

	/**
	 * Creates a {@code GUIPanel} which extends {@code JPanel} with {@code TitledBorder} and uses {@code GridBagLayout}.
	 * 
	 * @param title
	 *            the title for {@code TitledBorder}
	 */
	public GUIPanel(String title) {
		this();
		setBorder(new TitledBorder(null, title));
	}

	/**
	 * Add component with almost all constraints to the grid, horizontal margin length and vertical margin length included.
	 * 
	 * @param component
	 *            the component to be add
	 * @param gridx
	 *            the grid location of x which should be non-negative, and the first cell in a row has {@code gridx=0}
	 * @param gridy
	 *            the grid location of y which should be non-negative, and the first cell in a column has {@code gridy=0}
	 * @param gridwidth
	 *            the non-negative number of grids used by the component in the row, and its default value is {@code 1}
	 * @param gridheight
	 *            the non-negative number of grids used by the component in the column, and its default value is {@code 1}
	 * @param weightx
	 *            the non-negative weight value in row, and its default value is {@code 0}
	 * @param weighty
	 *            the non-negative weight value in column, and its default value is {@code 0}
	 * @param anchor
	 *            determines where, within the display area, to place the component, and its default value is {@code CENTER}
	 * @param fill
	 *            determines whether and how to resize the component,and its default value is {@code NONE}
	 * @param insets
	 *            the custom insets
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public void addComponent(JComponent component, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor,
			int fill, Insets insets) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.anchor = anchor;
		gbc.fill = fill;
		gbc.insets = insets;
		add(component, gbc);
	}

	/**
	 * Add component on the grid with chosen anchor, chosen fill and default margin.
	 * 
	 * @param component
	 *            the component to be add
	 * @param gridx
	 *            the grid location of x which should be non-negative, and the first cell in a row has {@code gridx=0}
	 * @param gridy
	 *            the grid location of y which should be non-negative, and the first cell in a column has {@code gridy=0}
	 * @param gridwidth
	 *            the non-negative number of grids used by the component in the row, and its default value is {@code 1}
	 * @param gridheight
	 *            the non-negative number of grids used by the component in the column, and its default value is {@code 1}
	 * @param weightx
	 *            the non-negative weight value in row, and its default value is {@code 0}
	 * @param weighty
	 *            the non-negative weight value in column, and its default value is {@code 0}
	 * @param anchor
	 *            determines where, within the display area, to place the component, and its default value is {@code CENTER}
	 * @param fill
	 *            determines whether and how to resize the component,and its default value is {@code NONE}
	 * @see #addComponent(JComponent, int, int, int, int, double, double, int, int, Insets)
	 */
	public void addComponent(JComponent component, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor,
			int fill) {
		Insets insets = new Insets((gridy == 0 ? this.margin : 0), (gridx == 0 ? this.margin : 0), this.margin, this.margin);
		addComponent(component, gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets);
	}

	/**
	 * Add component to the chosen grid at the center.
	 * 
	 * @param component
	 *            the component to be add
	 * @param gridx
	 *            the grid location of x which should be non-negative, and the first cell in a row has {@code gridx=0}
	 * @param gridy
	 *            the grid location of y which should be non-negative, and the first cell in a column has {@code gridy=0}
	 * @see #addComponent(JComponent, int, int, int, int, double, double, int, int, Insets)
	 * @see #addComponent(JComponent, int, int, int, int, double, double, int, int)
	 */
	public void addComponent(JComponent component, int gridx, int gridy) {
		addComponent(component, gridx, gridy, 1, 1, 0, 0, GUIPanel.CENTER, GUIPanel.NONE);
	}

	/**
	 * Print message on the bottom of the panel.
	 * 
	 * @param g
	 *            the pointer of the parameter {@code Graphics} g
	 * @param message
	 *            message to be printed
	 */
	public void printMessage(Graphics g, String message) {
		printMessages(g, new String[] { message });
	}

	/**
	 * Print messages on the bottom of the panel line by line.
	 * 
	 * @param g
	 *            the pointer of the parameter {@code Graphics} g
	 * @param messages
	 *            messages to be printed
	 */
	public void printMessages(Graphics g, String[] messages) {
		for (int i = 0; i < messages.length; i++) {
			g.drawString(messages[i], 10, getHeight() - 20 * (messages.length - i) + 10);
		}
	}

	/** Reset the panel. */
	public void reset() {
	}

	/**
	 * Set default margin between components.<br>
	 * The margin should be non-negative and its default value is {@code 0}.
	 * 
	 * @param margin
	 *            the new margin to be set
	 */
	public void setDefaultMargin(int margin) {
		this.margin = margin;
	}

	/** Set panel size for grid bag layout. */
	@Override
	public void setSize(Dimension dimension) {
		setMaximumSize(dimension);
		setMinimumSize(dimension);
	}

	/** Set panel size for grid bag layout. */
	@Override
	public void setSize(int width, int height) {
		this.setSize(new Dimension(width, height));
	}
}