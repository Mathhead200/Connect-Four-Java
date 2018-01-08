package grid;

import java.awt.event.*;


class GridEventHandler implements KeyListener
{
	private Grid grid;

	GridEventHandler(Grid g) {
		grid = g;
	}

	//KeyListener methods
	public void keyPressed(KeyEvent e) {
		try {
			grid.keyWasPressed( e.getKeyCode() );
		} catch(GridException f) {
			f.printStackTrace( System.out );
		}
	}

	public void keyReleased(KeyEvent e) {
		try {
			grid.keyWasReleased( e.getKeyCode() );
		} catch(GridException f) {
			f.printStackTrace( System.out );
		}
	}

	public void keyTyped(KeyEvent e) {}
}
