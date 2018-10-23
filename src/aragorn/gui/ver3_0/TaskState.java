package aragorn.gui.ver3_0;

/**
 * {@code TaskState} is an interface for {@code java.util.TimerTask}.
 * 
 * @author Aragorn
 * @see <a target="_blank" href="http://stackoverflow.com/questions/11550561/pause-the-timer-and-then-continue-it">TaskState</a>
 */
public interface TaskState {

	/** Action for TimerTask for each period. */
	public void run();

	/**
	 * Return the reference of the next state switching to.
	 * 
	 * @return next task state
	 */
	public TaskState switchState();
}