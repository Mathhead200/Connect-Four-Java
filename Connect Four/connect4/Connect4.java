package connect4;

import grid.*;
import java.io.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class Connect4 extends Grid
{
	private static final int WIDTH = 7;
	private static final int HEIGHT = 6;

	private int[] heights = {0, 0, 0, 0, 0, 0, 0};
	private int[][] moves = new int[2][WIDTH * HEIGHT / 2];
	private int turn = 0;
	private int count = 0;

	private static JFrame messageFrame = new JFrame("Connect 4");
	private static JLabel message = new JLabel();

	public Connect4() {
		super(WIDTH, HEIGHT);
		this.setTitle("Red's Turn");
		for( int i = 0; i < moves.length; i++ ) {
			for( int j = 0; j < moves[0].length; j++ ) {
				moves[i][j] = 0;
			}
		}
	}

	protected void keyWasPressed(int k) throws GridException {
		super.keyWasPressed(k);
		switch(k) { //number pad key codes...
		case 35: //1
			k = 49;
			break;
		case 40:
			k = 50;
			break;
		case 34:
			k = 51;
			break;
		case 37:
			k = 52;
			break;
		case 12:
			k = 53;
			break;
		case 39:
			k = 54;
			break;
		case 36:
			k = 55;
			break;
		}
		if( k == 8 )
			undo();
		else if( k == 27 ) {
			this.dispose();
		}
		else {
			if( (k >= 34 && k <=40 && k != 38) || k == 12 ) { //number pad key codes 1-7
				k = 49;
				switch(k) {
				case 36: k++; //7
				case 39: k++; //6
				case 12: k++; //5
				case 37: k++; //4
				case 34: k++; //3
				case 40: k++; //2
				}
			}
			int x = k - 49; //k=49 when the "1" key is pressed
			try {
				int y = 5 - heights[x];
				if( y < 6 ) {
					this.getBoxAt(x, y).addGridItem( turn == 0 ? new Red() : new Black() );
					heights[x]++;
					try {
						moves[turn][count] = x;
					} catch(IndexOutOfBoundsException e) {
						this.dispose();
					}
					if( checkIfWon(x, y) ) {
						alert("Connect 4... " + (turn == 0 ? "Red" : "Black") + " wins!");
					}
					if(turn == 1)
						count++;
					turn = -turn + 1;
					this.setTitle( (turn == 0 ? "Red" : "Black") + "'s Turn" );
				}
				else {
					alert( "Row " + (x + 1) + " is full." );
				}
			} catch(IndexOutOfBoundsException e) {
				alert("Press a number between 1 and 7 to move, or backspace to undo.");
			}
		}
	}

	public boolean undo() throws GridException {
		if( turn == 0 ) {
			if( count == 0 )
				return false;
			count--;
		}
		turn = -turn + 1;

		int x = moves[turn][count];
		int y = 5 - heights[x] + 1;

		this.getBoxAt(x, y).addGridItem( new Blank() );
		heights[x]--;
		this.setTitle( (turn == 0 ? "Red" : "Black") + "'s Turn" );
		return true;
	}

	public boolean checkIfWon(int move, int s)
	{
		int c = ((C4Piece)this.getBoxAt(move, s).getGridItem()).getOrder();

		if( s <= 2
			&& ((C4Piece)this.getBoxAt(move, s+1).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move, s+2).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move, s+3).getGridItem()).getOrder() == c
			) return true;

		else if( move <= 3
			&& ((C4Piece)this.getBoxAt(move+1, s).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+2, s).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+3, s).getGridItem()).getOrder() == c
			) return true;
		else if( 1 <= move && move <= 4
			&& ((C4Piece)this.getBoxAt(move-1, s).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+1, s).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+2, s).getGridItem()).getOrder() == c
			) return true;
		else if( 2 <= move && move <= 5
			&& ((C4Piece)this.getBoxAt(move-2, s).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move-1, s).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+1, s).getGridItem()).getOrder() == c
			) return true;
		else if( 3 <= move
			&& ((C4Piece)this.getBoxAt(move-3, s).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move-2, s).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move-1, s).getGridItem()).getOrder() == c
			) return true;

		else if( move <= 3 && 3 <= s
			&& ((C4Piece)this.getBoxAt(move+1, s-1).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+2, s-2).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+3, s-3).getGridItem()).getOrder() == c
			) return true;
		else if( 1 <= move && move <= 4 && 2 <= s && s <= 4
			&& ((C4Piece)this.getBoxAt(move-1, s+1).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+1, s-1).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+2, s-2).getGridItem()).getOrder() == c
			) return true;
		else if( 2 <= move && move <= 5 && 1 <= s && s <= 3
			&& ((C4Piece)this.getBoxAt(move-2, s+2).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move-1, s+1).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+1, s-1).getGridItem()).getOrder() == c
			) return true;
		else if( 3 <= move && s <= 2
			&& ((C4Piece)this.getBoxAt(move-3, s+3).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move-2, s+2).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move-1, s+1).getGridItem()).getOrder() == c
			) return true;

		else if( move <= 3 && s <= 2
			&& ((C4Piece)this.getBoxAt(move+1, s+1).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+2, s+2).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+3, s+3).getGridItem()).getOrder() == c
			) return true;
		else if( 1 <= move && move <= 4 && 1 <= s && s <= 3
			&& ((C4Piece)this.getBoxAt(move-1, s-1).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+1, s+1).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+2, s+2).getGridItem()).getOrder() == c
			) return true;
		else if( 2 <= move && move <= 5 && 2 <= s && s <= 4
			&& ((C4Piece)this.getBoxAt(move-2, s-2).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move-1, s-1).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move+1, s+1).getGridItem()).getOrder() == c
			) return true;
		else if( 3 <= move && 3 <= s
			&& ((C4Piece)this.getBoxAt(move-3, s-3).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move-2, s-2).getGridItem()).getOrder() == c
			&& ((C4Piece)this.getBoxAt(move-1, s-1).getGridItem()).getOrder() == c
			) return true;

		return false;
	}

	public static void alert(String s) {
		message.setText( "<html><h3>" + s + "</h3></html>" );
		messageFrame.pack();
		messageFrame.setVisible(true);
	}

	public static String ask(String str) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(str);
		return reader.readLine();
	}

	public static void main(String[] args) throws IOException, GridException
	{
		messageFrame.add(message);
		messageFrame.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );

		Connect4 board = new Connect4();

		for( int i = 0; i < WIDTH; i++ ) {
			for( int j = 0; j < HEIGHT; j++ ) {
				board.getBoxAt(i, j).addGridItem( new Blank() );
			}
		}
	}
}
