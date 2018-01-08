package grid.griditems;

import grid.*;


public class Bug extends MovableGridItem
{
	private boolean fed = false;

	public Bug(String url, String n, Box b) {
		super(url, n, b);
	}

	public Bug(String url, String n) {
		super(url, n);
	}

	public Bug(String url, Box b) {
		super(url, b);
	}

	public Bug(String url) {
		super(url);
	}

	public Bug() {
		super();
	}


	protected void bump(GridItem item) {
		if( item instanceof Food ) {
			int[] destCord = item.getParentBox().getBoxCord();
			int[] thisCord = this.getParentBox().getBoxCord();

			eat( (Food)item );

			int deltaX = destCord[0] - thisCord[0], deltaY = destCord[1] - thisCord[1];
			try { //bump() can only be called if 'this' called move()
				if( deltaX == 0 && deltaY == 1 )
					this.moveSouth();
				else if( deltaX == 1 && deltaY == 0 )
					this.moveEast();
				else if( deltaX == 0 && deltaY == -1 )
					this.moveNorth();
				else
					moveWest();
			} catch(NullParentBoxException e) {
			} catch(NullParentGridException e) {
			} catch(GridItemIsDeadException e) {
			}

		}
		else {
			say("ooff!"); }
	}


	public boolean isFed() {
		return this.fed;
	}

	public void eat(Food f) {
		if( f.isEdible() ) {
			this.say( "Yummy " + f.getName() + "." );
			this.fed = true;
		}
		else {
			this.say("Yuck!");
			this.kill();
		}
		f.kill();
	}


	public void say(String s) {
		System.out.println( this.getName() + ": " + s );
	}

	public void moveNorth() throws NullParentBoxException, NullParentGridException, GridItemIsDeadException {
		this.move(0, -1);
	}

	public void moveEast() throws NullParentBoxException, NullParentGridException, GridItemIsDeadException {
		this.move(1, 0);
	}

	public void moveSouth() throws NullParentBoxException, NullParentGridException, GridItemIsDeadException {
		this.move(0, 1);
	}

	public void moveWest() throws NullParentBoxException, NullParentGridException, GridItemIsDeadException {
		this.move(-1, 0);
	}
}
