package grid.griditems;

import grid.*;


public abstract class Food extends GridItem
{
	private boolean edible;

	public Food(boolean e, String url, String n, Box b) {
		super(url, n, b);
		edible = e;
	}

	public Food(boolean e, String url, String n) {
		super(url, n);
		edible = e;
	}

	public Food(boolean e, String url, Box b) {
		super(url, b);
		edible = e;
	}

	public Food(boolean e, String url) {
		super(url);
		edible = e;
	}

	public Food(boolean e) {
		super();
		edible = e;
	}


	boolean isEdible() {
		return this.edible;
	}
}
