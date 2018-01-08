package grid;

import java.awt.*;
import javax.swing.*;


/**
 * The 2-dimensional world in which all grid programs exist.
 * Having only width and height all grids contain a matrix of Boxes in which to store their GridItems.
 * @author Christopher D'Angelo
 * @author JBD Computers &trade;
 * @see <a href="grid/Read Me.html#Grid">Read Me.html</a>
 */
@SuppressWarnings("serial")
public class Grid extends JFrame
{
	private JPanel myStuff = new JPanel(new GridBagLayout());
	private Box[][] boxes;

	/**
	 * creates a grid
	 * @param x - width
	 * @param y - height
	 * @param t - background color
	 */
	public Grid(int x, int y, Color t)
	{
		this.setTitle("Grid - Class");
		boxes = new Box[x][y];
		for( int i=0; i<x; i++ ) {
			for( int j=0; j<y; j++ ) {
				boxes[i][j] = new Box(t);
				boxes[i][j].setParentGrid(this);

				GridBagConstraints c = new GridBagConstraints();
				c.gridx = i;
				c.gridy = j;
				this.myStuff.add(boxes[i][j], c);
			}
		}
		this.add(myStuff);
		this.pack();
		this.setLocation(50, 50);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible(true);

		this.addKeyListener( new GridEventHandler(this) );
	}

	/**
	 * synonymous with - <b>Grid( x, y, new java.awt.Color(0xffffff) )</b>
	 * @param x - width
	 * @param y - height
	 * @see #Grid(int, int, Color)
	 */
	public Grid(int x, int y) {
		this( x, y, new Color(0xffffff) );
	}


	/**
	 * @return a multidimensional array of boxes that make up the grid
	 */
	public Box[][] getBoxes() {
		return this.boxes;
	}

	/**
	 * synonymous with - <b>getBoxes()[x][y]</b>
	 * @param x - x position, start at 0, oriented left to right
	 * @param y - y position, start at 0, oriented top to bottom
	 * @return a box found at coordinate (x, -y) on the grid,
	 *  where the upper-left most box of the grid is at the origin
	 * @see #getBoxes()
	 */
	public Box getBoxAt(int x, int y) {
		return this.boxes[x][y];
	}

	/**
	 * @param b - target box
	 * @return two integers in the form of {x, y} where - <b>getBoxAt(x, y)</b> - would return <b>b</b>,
	 *  or <b>null</b> if <b>b</b> dosn't exist on the grid
	 */
	public int[] findBoxCord(Box b) {
		for( int i=0; i<this.boxes.length; i++ ) {
			for( int j=0; j<this.boxes[0].length; j++ ) {
				if( b.equals(this.boxes[i][j]) ) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}

	/**
	 * synonymous with - <b>findBoxCord( i.getParentBox() )</b>
	 * @param i - target item
	 * @throws NullParentBoxException - if <b>i</b> dosn't have a parent Box
	 * @see #findBoxCord(Box)
	 */
	public int[] findBoxCord(GridItem i) throws NullParentBoxException {
		if( i.getParentBox() == null ) {
			throw new NullParentBoxException(); }
		return this.findBoxCord( i.getParentBox() );
	}


	/**
	 * sets all the boxes in the grid to those in the <b>b</b> matrix
	 * @param b - a box matrix to replace the ones on currently the grid
	 * @throws WrongGridSizeException - if <b>b</b>'s size is different from that of the size of the grid
	 */
	public void setBoxes(Box[][] b) throws WrongGridSizeException
	{
		if( b.length != this.boxes.length || b[0].length != this.boxes[0].length ) {
			throw new WrongGridSizeException();
		}
		for( int x=0; x<b.length; x++ ) {
			for( int y=0; y<b[0].length; y++ ) {
				setBoxAt( b[x][y], x, y );
			}
		}
	}

	/**
	 * sets the box at coordinate (x, -y) in the grid to b,
	 * where the upper-left most box is at the origin
	 * @param b - box to put on the grid
	 * @param x - x position, start at 0, oriented left to right
	 * @param y - y position, start at 0, oriented top to bottom
	 */
	public void setBoxAt(Box b, int x, int y)
	{
		this.myStuff.remove(this.boxes[x][y]);

		this.boxes[x][y] = b;
		b.setParentGrid(this);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		this.myStuff.add(b, c);
		this.myStuff.validate();
	}

	/**
	 * @return all GridItems that exist on this Grid and are also GridActors
	 */
	public GridActor[] findGridActors() {
		GridActor[] a = new GridActor[0];
		Box[][] b = this.getBoxes();
		for( int i=0; i<b.length; i++ ) {
			for( int j=0; j<b[0].length; j++ ) {
				if( b[i][j].getGridItem() instanceof GridActor ) {
					GridActor[] newA = new GridActor[a.length + 1];
					System.arraycopy(a, 0, newA, 0, a.length);
					newA[newA.length - 1] = (GridActor)b[i][j].getGridItem();
					a = newA;
				}
			}
		}
		return a;
	}

	/**
	 * called automatically when a key is pressed down on the keyboard,
	 * causes all GridActors on the grid to invoke - <b>keyDown( k )</b>
	 * @param k - key code
	 * @throws GridException
	 * @see GridActor#keyDown(int)
	 */
	protected void keyWasPressed(int k) throws GridException {
		GridActor[] actors = this.findGridActors();
		for( GridActor a : actors ) {
			a.keyDown(k);
		}
	}

	/**
	 * called automatically when a key is released on the keyboard,
	 * causes all GridActors on the grid to invoke - <b>keyUp( k )</b>
	 * @param k - key code
	 * @throws GridException
	 * @see GridActor#keyUp(int)
	 */
	protected void keyWasReleased(int k) throws GridException {
		GridActor[] actors = this.findGridActors();
		for( GridActor a : actors ) {
			a.keyUp(k);
		}
	}
}
