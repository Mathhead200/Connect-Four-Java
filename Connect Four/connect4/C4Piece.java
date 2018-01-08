package connect4;

import grid.*;


public class C4Piece extends GridItem
{
	private int order;

	public C4Piece(String url, int o) {
		super(url);
		order = o;
	}

	public int getOrder() {
		return this.order;
	}
}
