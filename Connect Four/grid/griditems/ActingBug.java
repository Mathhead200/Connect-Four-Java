package grid.griditems;

import grid.*;

public class ActingBug extends Bug implements GridActor
{
	public ActingBug(String url, String n, Box b) throws NullParentGridException {
		super(url, n, b);
	}

	public ActingBug(String url, String n) {
		super(url, n);
	}

	public ActingBug(String url, Box b) throws NullParentGridException {
		super(url, b);
	}

	public ActingBug(String url) {
		super(url);
	}

	public ActingBug() {
		super();
	}

	public void keyDown(int k)
	{
		ActingBug a = this;
		try {
			switch(k) {
				case 37:
					a.moveWest();
					break;
				case 38:
					a.moveNorth();
					break;
				case 39:
					a.moveEast();
					break;
				case 40:
					a.moveSouth();
					break;
				default:
					System.out.println( "press an arrow key..." );
				}
			} catch(NullParentBoxException e) {
			} catch(NullParentGridException e) {
			} catch(GridItemIsDeadException e) {
			}
	}

	public void keyUp(int k)
	{
		System.out.println( "keyCode - " + k );
	}
}
