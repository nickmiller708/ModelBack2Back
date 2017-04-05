import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

//Game Rules
//Try to place the puzzle pieces on the grid. 
//You can choose whether to place them on the front side or on the back side
//You can't place a puzzle piece on the flip side of a protruding part of another puzzle
public class game {
	//Game class that will hold all of the pieces and run the logic of the game
	protected board gameBoard;
	protected piece[] pieces;

	public game(String filename)
	{
		//Constructor will create the board and read in all the pieces.
		gameBoard = new board();
		try {
			FileReader fin = new FileReader(filename);
			Scanner scan = new Scanner(fin);
			int arraySize = scan.nextInt();
			pieces = new piece[arraySize];
			scan.nextLine();
			for (int i = 0; i < arraySize; i++)
			{
				String piece = scan.nextLine();
				String[] split = piece.split(" ");
				String color = split[0];
				Integer row = new Integer(split[1]);
				int rowSize = row.intValue();
				Integer column = new Integer(split[2]);
				int columnSize = column.intValue();
				int[][] pieceArray = new int[rowSize][columnSize];
				int stringReader = 3;
				for (int k = 0; k < rowSize; k++)
				{
					for (int j = 0; j < columnSize; j++)
					{
						Integer entryfromTextFile = new Integer(split[stringReader]);
						int depthofPiece = entryfromTextFile.intValue();
						pieceArray[k][j] = depthofPiece;
						stringReader++;
					}
				}
				piece piecetoAdd = new piece(pieceArray, rowSize, columnSize, color);
				pieces[i] = piecetoAdd;
			}
			//Read in all of the the pieces from a text file and add them to the piece array (might be best to do a list)
			scan.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void runGame()
	{
		//Will run the game and be able to add a piece
try{
		Scanner readinScanner = new Scanner(new InputStreamReader(System.in));

		for (int piecenumber = 0; piecenumber < pieces.length; piecenumber++)
		{
			System.out.println("Current Board Layout:");
			System.out.println(gameBoard);
			System.out.println("What action do you wish to perform:" );
			System.out.println("[1]: Clear the Board");
			System.out.println("[2]: Add a piece");
			System.out.println("Enter your choice here: " );

			int choice = readinScanner.nextInt();
			readinScanner.nextLine();
			if (choice == 1)
			{
				gameBoard.clearBoard();
				piecenumber = 0;
			}
			else if (choice == 2)
			{
				boolean added = pieceLogic(piecenumber, readinScanner);
				if (added == false)
				{
					piecenumber--;
				}
			}
		}
		readinScanner.close();
		}
	catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

	public boolean pieceLogic(int piecenumber, Scanner readinScanner)
	{
		//will handle adding, rotating a piece
		boolean finished = false;
		boolean added = false;
		while (finished == false)
		{
		System.out.println("Here is the next piece to add:");
		piece piecetoadd = pieces[piecenumber];
		System.out.println(piecetoadd);
		System.out.println("What action do you wish to perform: " );
		System.out.println("[1]: Rotate the Piece to the Right");
		System.out.println("[2]: Rotate the Piece to the Left");
		System.out.println("[3]: Add the Piece to the Board");
		System.out.println("Enter your choice here: " );
		int choice = readinScanner.nextInt();
		if (choice == 1)
		{
			piecetoadd.rotateRight();
			System.out.println("Current Board Layout:");
			System.out.println(gameBoard);
		}
		else if (choice == 2)
		{
			piecetoadd.rotateLeft();
			System.out.println("Current Board Layout:");
			System.out.println(gameBoard);
		}
		else
		{
			System.out.println("Which side of the board do you want to add to: ");
			System.out.println("[f]: The Front");
			System.out.println("[b] The Back");
			char frontorback = readinScanner.next().charAt(0);
			System.out.println("In order to add a piece, put the row then column where you want to add it");
			
			System.out.println("For example: 2 2");
			int choicex = readinScanner.nextInt();
			int choicey = readinScanner.nextInt();
			if (frontorback =='f')
			{
				 added = gameBoard.addPieceonFront(piecetoadd, choicex,choicey);
				finished = true;
			}
			else if (frontorback=='b')
			{
				 added = gameBoard.addPieceonBack(piecetoadd,choicex,choicey);
				finished = true;
			}
		}
		}
		return added;
	}
	public static void main(String[] arg)
	{
		game tester = new game("src/pieces.txt");
		tester.runGame();
	}
}
