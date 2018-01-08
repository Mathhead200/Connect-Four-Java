package grid;

import javax.swing.*;


/**
 * These are items to be placed on the 2D grid.
 * All GridItems can be placed within a parent Box.
 * If that Box is on a Grid then the GridItem will display there.
 * @author Christopher D'Angelo
 * @author JBD Computers &trade;
 * @see <a href="grid/Read Me.html#GridItem">Read Me.html</a>
 */
public abstract class GridItem
{
	private Box parentBox;
	private ImageIcon icon;
	private String name;
	private boolean alive = true;

	/**
	 * creates a new GridItem
	 * @param url - where the icon is found used to display the GridItem
	 * @param n - name
	 */
	public GridItem(String url, String n)
	{
		icon = new ImageIcon(url);
		name = n;
	}

	/**
	 * synonymous with - <b>{ <br />
	 * &nbsp; GridItem g = new GridItem( url, n ); <br />
	 * &nbsp; b.addGridItem( g ); <br />
	 * }</b>
	 * @param url - where the icon is found to display the GridItem
	 * @param n - name
	 * @param b - a box to place the GridItem in
	 * @see #GridItem(String, String)
	 * @see Box#addGridItem(GridItem)
	 */
	public GridItem(String url, String n, Box b)
	{
		this( url, n );
		try {
			b.setGridItem(this);
		} catch(GridItemIsDeadException e) {}
	}

	/**
	 * synonymous with - <b>new GridItem( url, "", b )</b>;
	 * @param url - where the icon is found to display the GridItem
	 * @param b - a box to place the GridItem in
	 * @see #GridItem(String, String, Box)
	 */
	public GridItem(String url, Box b) {
		this( url, "", b );
	}

	/**
	 * synonymous with - <b>new GridItem(url, "");</b>
	 * @param url - where the icon is found to display the GridItem
	 * @see #GridItem(String, String)
	 */
	public GridItem(String url) {
		this( url, "" );
	}

	/**
	 * synonymous with - <b>new GridItem("grid/default_griditem.dmi");</b> -
	 * this icon is supplied by the grid package
	 * @see #GridItem(String)
	 */
	public GridItem() {
		this("grid/default_griditem.dmi");
	}


	/**
	 * @return the box with which this GridItem belongs to
	 */
	public Box getParentBox() {
		return parentBox;
	}

	/**
	 * synonymous with - <b>this.getParentBox().getParentGrid()</b>
	 * @throws NullParentBoxException - if getParentBox() returns null
	 * @see Box#getParentGrid()
	 */
	public Grid getParentGrid() throws NullParentBoxException {
		if( this.parentBox == null ) {
			throw new NullParentBoxException();
		}
		return parentBox.getParentGrid();
	}

	/**
	 * @return the javax.swing.ImageIcon that is used to display your GridItem
	 */
	public ImageIcon getIcon() {
		return this.icon;
	}

	/**
	 * @return the name of this GridItem,
	 * the name of a GridItem is a String that represents the GridItem,
	 * as well the name will be displayed on hover
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * synonymous with - <b>this.getParentBox().getBoxCord()</b>
	 * @return
	 * @throws NullParentBoxException - if getParentBox() returns null
	 * @see Box#getBoxCord()
	 */
	public int[] getBoxCord() throws NullParentBoxException {
		if( this.getParentBox() == null ) {
			throw new NullParentBoxException(); }
		return this.getParentBox().getBoxCord();
	}

	/**
	 * @return false if the GridItem has ever invoked the kill() method, else it returns true
	 */
	public boolean isAlive() {
		return this.alive;
	}


	void setParentBox(Box b) {
		this.parentBox = b;
	}

	/**
	 * sets the GridItems icon
	 * @param i - the icon that will be used to display the GridItem
	 */
	public void setIcon(ImageIcon i) {
		this.icon = i;
		if( this.getParentBox() != null )
			this.getParentBox().setIcon(i);
	}

	/**
	 * set the GridItem's name
	 * @param n - name
	 */
	public void setName(String n) {
		this.name = n;
	}

	/**
	 * synonymous with - <b>b.addGridItem(this)</b>
	 * @param b - intended parent box
	 * @throws ParentBoxIsSetException - if the GridItem has a parent box
	 * @throws GridItemIsDeadException - if the GridItem has invoked kill()
	 * @see Box#addGridItem(GridItem)
	 */
	public void addToBox(Box b) throws ParentBoxIsSetException, GridItemIsDeadException {
		b.addGridItem(this);
	}

	/**
	 * removes the GridItem from its parent box ergo removing it from the grid
	 * @throws GridItemIsDeadException - if the GridItem has even invoked the kill() method
	 * @throws NullParentBoxException - if the GridItem dosn't belong to any parent box
	 */
	public void removeFromGrid() throws GridItemIsDeadException, NullParentBoxException {
		if( !this.isAlive() ) {
			throw new GridItemIsDeadException(); }
		else if( this.getParentBox() == null ) {
			throw new NullParentBoxException(); }

		this.getParentBox().setGridItem( null );
		this.parentBox = null;
	}

	/**
	 * used to delete the GridItem and insure it can not be re-placed in any box
	 */
	public void kill() {
		try {
			this.removeFromGrid();
		} catch(GridItemIsDeadException e) {
		} catch(NullParentBoxException e) {
		}
		this.alive = false;
	}

	/**
	 * a fail-safe that will allow the GridItem to behave as if kill() was never invoked on it
	 */
	public void revive() {
		this.alive = true;
	}
}
