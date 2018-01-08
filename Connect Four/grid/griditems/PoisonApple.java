package grid.griditems;

import grid.*;


public class PoisonApple extends Food
{
	private static final boolean EDIBLE = false;

	public PoisonApple(String url, String n, Box b) {
		super(EDIBLE, url, n, b);
	}

	public PoisonApple(String url, String n) {
		super(EDIBLE, url, n);
	}

	public PoisonApple(String url, Box b) {
		super(EDIBLE, url, b);
	}

	public PoisonApple(String url) {
		super(EDIBLE, url);
	}

	public PoisonApple() {
		super(EDIBLE);
	}
}
