package grid;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * A container for a single GridItem. One Grid can be made up of multiple Box Objects
 * @author Christopher D'Angelo
 * @author JBD Computers &trade;
 * @see <a href="grid/Read Me.html#Box">Read Me.html</a>
 */
@SuppressWarnings("serial")
public class Box extends JLabel
{
	private static final Color DEFAULT_TURF = new Color(0xffffff);
	private Grid parentGrid;
	private Color turf;
	private GridItem gridItem;


	/**
	 * creates a box for adding grid items
	 * @param t - background color
	 */
	public Box(Color t) {
		turf = t;

		this.setMinimumSize(new Dimension(32, 32) );
		this.setPreferredSize( new Dimension(32, 32) );
		this.setBorder( new LineBorder(new Color(0x000000), 1, false) );
		this.setBackground(t);
		this.setOpaque(true);
	}

	/**
	 * synonymous with - <b>{ <br />
	 * &nbsp; Box b = new Box( t ); <br />
	 * &nbsp; b.addGridItem( item ); <br />
	 * }</b>
	 * @param t - background color
	 * @param item - item to be added to the box
	 * @throws ParentBoxIsSetException - see <b>addGridItem( GridItem )</b>
	 * @throws GridItemIsDeadException - see <b>addGridItem( GridItem )</b>
	 * @see #Box(Color)
	 * @see #addGridItem(GridItem)
	 */
	public Box(Color t, GridItem item) throws ParentBoxIsSetException, GridItemIsDeadException
	{
		this(t);
		addGridItem(item);
	}

	/**
	 * synonymous with - <b>new Box( new java.awt.Color(0xffffff), item );</b> -
	 * setting the new box's background color to white
	 * @param item - item to be added to the box
	 * @throws ParentBoxIsSetException - see <b>addGridItem( GridItem )</b>
	 * @throws GridItemIsDeadException - see <b>addGridItem( GridItem )</b>
	 * @see #Box(Color, GridItem)
	 */
	public Box(GridItem item) throws ParentBoxIsSetException, GridItemIsDeadException {
		this( DEFAULT_TURF, item );
	}

	/**
	 * synonymous with - <b>new Box( new java.awt.Color(0xffffff) )</b> -
	 * setting the new Box's background color to white
	 * @see #Box(Color)
	 */
	public Box() {
		this( DEFAULT_TURF );
	}


	/**
	 * @return the grid that this box belongs to
	 */
	public Grid getParentGrid()
	{
		return this.parentGrid;
	}

	/**
	 * @return the background color of this box
	 */
	public Color getTurf() {
		return this.turf;
	}

	/**
	 * @return the GridItem found in this box, or null if empty
	 */
	public GridItem getGridItem() {
		return this.gridItem;
	}

	/**
	 * @return the coordinates of this box in the form of {x, y} on its parent grid,
	 * or null if this Box dosn't belong to a grid
	 */
	public int[] getBoxCord() {
		if( this.getParentGrid() == null ) {
			return null; }
		return this.getParentGrid().findBoxCord( this );
	}


	void setParentGrid(Grid g) {
		this.parentGrid = g;
	}

	void setGridItem(GridItem item) throws GridItemIsDeadException
	{
		if( item == null ) {
			this.setIcon( new ImageIcon() );
			this.setToolTipText(null);
			this.gridItem = null;
			this.validate();
			return;
		}

		if( !item.isAlive() ) {
			throw new GridItemIsDeadException();
		}

		if( this.gridItem != null ) {
			this.gridItem.setParentBox(null);
		}

		//if()... Removes from old parentBox the item
		if( item.getParentBox() != null ) {
			item.getParentBox().setGridItem(null);
		}
		item.setParentBox(this);
		this.setIcon(item.getIcon());
		this.setToolTipText( item.getName() );
		this.gridItem = item;
		this.validate();
	}

	/**
	 * adds an item to the box
	 * @param item - item to add to this box
	 * @throws ParentBoxIsSetException - if item already belongs to a box
	 * @throws GridItemIsDeadException - if item has invoked the kill() method
	 */
	public void addGridItem(GridItem item) throws ParentBoxIsSetException, GridItemIsDeadException
	{
		if( item == null ) {
			setGridItem(null);
			return;
		}

		if( item.getParentBox() != null ) {
			throw new ParentBoxIsSetException(); }
		setGridItem(item);
	}
}
