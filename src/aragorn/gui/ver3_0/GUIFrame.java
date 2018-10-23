package aragorn.gui.ver3_0;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * {@code GUIFrame} is a frame which extends {@code javax.swing.JFrame} and uses {@code GUIPanel} as the content pane.<br>
 * Also, ask before close it.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public abstract class GUIFrame extends JFrame {

	/** The default content pane which is {@code GUIPanel} for {@code GUIFrame}. */
	private GUIPanel contentPane = new GUIPanel();

	/** The {@code java.util.TimerTask} for {@code java.util.Timer}. */
	private TimerTask task;

	/** The task state that can be switch from running to pause or from pause to running. */
	private TaskState taskState = new PauseState();

	/** The {@code java.util.Timer} using in {@code GUIFrame}. */
	private Timer timer = new Timer(true);

	/** The period for {@code java.util.Timer}. */
	private int updatingPeriod;

	/**
	 * Create a {@code GUIFrame} which extends {@code JFrame} with {@code GUIPanel} as the content pane.
	 * 
	 * @param title
	 *            the title of the {@code GUIFrame}
	 * @param dimension
	 *            the dimension of the {@code GUIFrame}
	 * @param maximizeWhileLaunch
	 *            to maximize frame while launch
	 */
	public GUIFrame(String title, Dimension dimension, boolean maximizeWhileLaunch) {
		resetSize(dimension);
		setContentPane();
		setDefaultCloseOperation(0);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GUIFrame.this.close();
			}
		});
		setTitle(title);
		if (maximizeWhileLaunch) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
	}

	/**
	 * Create a {@code GUIFrame} which extends {@code JFrame} with {@code GUIPanel} as the content pane and using {@code java.util.Timer}.
	 * 
	 * @param title
	 *            the title of the {@code GUIFrame}
	 * @param dimension
	 *            the dimension of the {@code GUIFrame}
	 * @param maximizeWhileLaunch
	 *            to maximize frame while launch
	 * @param updatingPeriod
	 *            the period for timer that updates content pane on {@code GUIFrame}
	 */
	public GUIFrame(String title, Dimension dimension, boolean maximizeWhileLaunch, int updatingPeriod) {
		this(title, dimension, maximizeWhileLaunch);
		task = new TimerTask() {
			@Override
			public void run() {
				taskState.run();
				contentPane.repaint();
			}
		};
		this.updatingPeriod = updatingPeriod;
		timer.scheduleAtFixedRate(task, 0, this.updatingPeriod);
	}

	/** Edit timer task action. */
	protected void addTimerTaskAction() {
	}

	/**
	 * Ask before close the {@code GUIFrame}.<br>
	 * Cancel timer before close frame.<br>
	 * Additionally, pause timer as the option pane shown and continue the state of the timer if return to the GUIFrame.
	 */
	public void close() {
		int state = (taskState.getClass() == RunningState.class) ? 1 : 0;
		this.stop();
		String[] exitPaneButtons = { "Yes", "No" };
		int n = JOptionPane.showOptionDialog(this, "Do you really want to exit?", "Exit", 0, 2, null, exitPaneButtons, exitPaneButtons[0]);
		if (n == 0) {
			timer.cancel();
			timer.purge();
			dispose();
		} else {
			if (state == 1) {
				this.start();
			}
		}
	}

	/** Initial content pane as creating {@code GUIFrame}. */
	protected abstract void editContentPane();

	@Override
	public GUIPanel getContentPane() {
		return contentPane;
	}

	/**
	 * Initial size of content pane to specific dimension.
	 * 
	 * @param dimension
	 *            the dimension to be set
	 */
	private void resetSize(Dimension dimension) {
		setSize(dimension);
		setLocationRelativeTo(null);
	}

	/** Initial content pane. */
	private void setContentPane() {
		this.contentPane.setDefaultMargin(10);
		editContentPane();
		setContentPane(this.contentPane);
	}

	/**
	 * Set default look and feel decorated for frames and dialogs.
	 * 
	 * @param defaultLookAndFeelDecorated
	 *            parameter to be set
	 * @see <a target="_blank" href=
	 *      "https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html#setDefaultLookAndFeelDecorated(boolean)">JFrame.setDefaultLookAndFeelDecorated(boolean)</a>
	 */
	public static void setDefaultLookAndFeelDecorated(boolean defaultLookAndFeelDecorated) {
		JFrame.setDefaultLookAndFeelDecorated(defaultLookAndFeelDecorated);
		JDialog.setDefaultLookAndFeelDecorated(defaultLookAndFeelDecorated);
	}

	/** Start timer by switching task state from pause to running. */
	public void start() {
		if (task != null && taskState.getClass().equals(PauseState.class)) {
			taskState = taskState.switchState();
		}
	}

	/** Stop timer by switching task state from running to pause. */
	public void stop() {
		if (task != null && taskState.getClass().equals(RunningState.class)) {
			taskState = taskState.switchState();
		}
	}

	/** The task state that do nothing for pausing timer. */
	private class PauseState implements TaskState {
		@Override
		public void run() {
		} // do nothing

		@Override
		public TaskState switchState() {
			return new RunningState();
		}
	}

	/** The task state for running timer. */
	private class RunningState implements TaskState {
		@Override
		public void run() {
			addTimerTaskAction();
		}

		@Override
		public TaskState switchState() {
			return new PauseState();
		}
	}
}