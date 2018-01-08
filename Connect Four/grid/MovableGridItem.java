package grid;


/**
 * As subclass of GridItem,
 * a MovableGridItem represents a GridItem that will be capable of moving from Box to Box
 * as to not be limited to its original parent Box and in turn move around the Grid.
 * @author Christopher D'Angelo
 * @author JBD Computers &trade;
 * @see <a href="grid/Read Me.html#MovableGridItem">Read Me.html</a>
 */
public abstract class MovableGridItem extends GridItem
{
	/**
	 * calls - <b>super( url, n, b )</b>
	 * @param url - location of icon used to display the item
	 * @param n - name
	 * @param b - parent box
	 * @see GridItem#GridItem(String, String, Box)
	 */
	public MovableGridItem(String url, String n, Box b) {
		super(url, n, b);
	}

	/**
	 * calls - <b>super( url, n )</b>
	 * @param url - location of icon used to display the item
	 * @param n - name
	 * @see GridItem#GridItem(String, String)
	 */
	public MovableGridItem(String url, String n) {
		super(url, n);
	}

	/**
	 * calls - <b>super( url, b )</b>
	 * @param url - location of icon used to display the item
	 * @param b - name
	 * @see GridItem#GridItem(String, Box)
	 */
	public MovableGridItem(String url, Box b) {
		super(url, b);
	}

	/**
	 * calls - <b>super( url )</b>
	 * @param url - location of icon used to display the item
	 * @see GridItem#GridItem(String)
	 */
	public MovableGridItem(String url) {
		super(url);
	}

	/**
	 * calls - <b>super()</b>
	 * @see GridItem#GridItem()
	 */
	public MovableGridItem() {
		super();
	}


	/**
	 * invoked automatically when the move() method causes the item to try and move to a box
	 * that already contains another GridItem
	 * @param item - the target box's item
	 * @throws GridException
	 */
	abstract protected void bump(GridItem item) throws GridException;


	/**
	 * moves the item <b>x</b> boxes right (east) and <b>y</b> boxes down (south),
	 * this method was made to be called to from within some public method of a subclass of MovableGridItem,
	 * i.e. - <b>{ <br />
	 * &nbsp; Bug b = new Bug(); <br />
	 * &nbsp; b.moveNorth(); <br />
	 * }</b> <i>//assuming</i> <br />
	 * <b>public class Bug extends MovableGridItem { <br />
	 * &nbsp; public void moveNorth() { move( 0, -1 ); } <br />
	 * }</b>
	 * @param x - spaces to move right (negative values move left)
	 * @param y - spaces to move down (negative values move up)
	 * @throws NullParentBoxException - if the item is not in a box
	 * @throws NullParentGridException - if the item's parent Box is not in a Grid
	 * @throws GridItemIsDeadExcpetion - if the item has invoked kill()
	 */
	protected void move(int x, int y) throws NullParentBoxException, NullParentGridException, GridItemIsDeadException
	{
		if( this.getParentBox() == null ) {
			throw new NullParentBoxException(); }
		else if( this.getParentBox().getParentGrid() == null ) {
			throw new NullParentGridException(); }

		int[] cord = this.getParentBox().getBoxCord();
		try {
			Box destBox = this.getParentBox().getParentGrid().getBoxAt(cord[0]+x, cord[1]+y);

			if( destBox.getGridItem() != null ) {
				try {
					this.bump( destBox.getGridItem() );
				} catch(GridException e) {
					e.printStackTrace( System.out );
				}
			}
			else {
				destBox.setGridItem(this);
			}
		}
		catch(IndexOutOfBoundsException e) {
			this.movedOffGrid();
		}
	}

	/**
	 * invoked automatically when the move() method causes the item to try and move
	 * passed the bounds of its parent Grid,
	 * by default it prints a message and then invokes the kill() method <br /><br />
	 * consider overriding this method
	 */
	protected void movedOffGrid() {
		System.out.println( this.getName() + " fell off the edge of the grid..." );
		this.kill();
	}
}
