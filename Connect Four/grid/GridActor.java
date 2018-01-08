package grid;


/**
 * This interface was made to be implemented by a GridItem.
 * It allows the item to act on events sent from the keyboard to its parent grid.
 * @author Christopher D'Angelo
 * @author JBD Computers &trade;
 * @see <a href="grid/Read Me.html#GridActor">Read Me.html</a>
 */
public interface GridActor
{
	/**
	 * invoked when a key is released
	 * @param k - key code
	 * @throws GridException
	 */
	public void keyUp(int k) throws GridException;

	/**
	 * invoked when a key is pressed down
	 * @param k - key code
	 * @throws GridException
	 */
	public void keyDown(int k) throws GridException;
}
