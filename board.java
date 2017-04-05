
public class board 
{
	protected String[][] frontBoard;
	protected String[][] backBoard;
	protected int rowSize, columnSize;
	public board()
	{
		rowSize = 5;
		columnSize = 6;
		frontBoard = new String[rowSize][columnSize];
		backBoard = new String[rowSize][columnSize];
		for (int i = 0; i < rowSize; i++)
		{
			for (int j = 0; j < columnSize; j++)
				{
				
					frontBoard[i][j] = "_";
					backBoard[i][j] = "_";
				}
		}
	}
	public void clearBoard()
	{
		for (int i = 0; i < rowSize; i++)
		{
			for (int j = 0; j < columnSize; j++)
				{
					frontBoard[i][j] = "_";
					backBoard[i][j] = "_";
				}
		}
	}
	public String getOpenorCloseFront(int rowindex, int columnindex)
	{
		//If it's open, then it will return a _
		//If it's closed, then there will be a string of some sort
		//will return the depth of any index of the array
		return frontBoard[rowindex][columnindex];
	}
	public String getOpenorCloseBack(int rowindex, int columnindex)
	{
		//If it's open, then it will return a _
		//If it's closed, then there will be a string of some sort
		//will return the depth of any index of the array
		return backBoard[rowindex][columnindex];
	}
	public String toString()
	{
		String returner = "      Front Board                Back Board\n";
		returner +="   0  1  2  3  4  5           0  1  2  3  4  5 \n";
		for (int i = 0; i < rowSize; i++)
		{
			for (int j = 0; j < columnSize+1; j++)
			{
				if( j ==0)
				{
					returner += (i+1)%5 + "  ";
					
				}
				else
				{
					returner += frontBoard[i][j-1].toString() + "  ";
				}
				
				
			}
			returner += " |    ";
			for (int j = 0; j < columnSize+1; j++)
			{
				if (j == 0 )
				{
					returner += (i+1)%5 + "  ";

				}
				else
				{
					returner += backBoard[i][j-1].toString() + "  ";
				}
				
			}
			returner += "\n";
		}
	
		return returner;
	}
	public boolean checkBoundary(piece piecetoAdd, int rowIndexonBoard, int columnIndexonBoard)
	{
		//Will check the size of the piece relative to the row and column indices selected and will return true or false if adding the piece would cause an out of bounds exception
		//True: There is a boundary issue
		//False: There is no boundary issue
		if (rowIndexonBoard + piecetoAdd.getRowSize() > this.rowSize)
		{
			return true;
		}
		else if (columnIndexonBoard + piecetoAdd.getColumnSize() > this.columnSize)
		{
			return true;
		}
		return false;
	}
	public boolean checkValidMoveforAddFront(piece piecetoAdd, int rowIndexonBoard, int columnIndexonBoard)
	{
		//will return true or false depending on whether there is a conflict between the piece being added and what's on the board
		//True: It is a valid move for that piece in that row and column index on the board
		//False: It is not a valid move for that piece in that row and column index on the board
		int a = rowIndexonBoard;
		int b = columnIndexonBoard;
		for (int i = 0; i < piecetoAdd.getRowSize(); i++)
		{
			a = rowIndexonBoard;
			b = columnIndexonBoard;
			for (int j = 0; j < piecetoAdd.getColumnSize(); j++)
			{
				int depth = piecetoAdd.getDepth(i, j);
				if (depth > 0)
				{
					if ((frontBoard[a][b].equals("_") == false))
					{
						//This is when the board is trying to add a piece and there is a conflict on the same side of the board (that spot on that side is full)
						return false;
					}
					else if ((frontBoard[a][b].equals("_") == true) && (backBoard[a][b].equals("_") == false) && (depth == 2))
					{
						//When trying to add to, there is a depth one on the backside and the piece depth is 2
						return false;
					}
					
				}
				a++;
			}
		}
		return true;
	}
	public boolean checkValidMoveforAddBack(piece piecetoAdd, int rowIndexonBoard, int columnIndexonBoard)
	{
		//will return true or false depending on whether there is a conflict between the piece being added and what's on the board
		//True: It is a valid move for that piece in that row and column index on the board
		//False: It is not a valid move for that piece in that row and column index on the board
		int a = rowIndexonBoard;
		int b = columnIndexonBoard;
		for (int i = 0; i < piecetoAdd.getRowSize(); i++)
		{
			a = rowIndexonBoard;
			b = columnIndexonBoard;
			for (int j = 0; j < piecetoAdd.getColumnSize(); j++)
			{
				int depth = piecetoAdd.getDepth(i, j);
				if (depth > 0)
				{
					if ((backBoard[a][b].equals("_") == false))
					{
						//This is when the board is full on both sides
						return false;
					}
					else if ((frontBoard[a][b].equals("_") == false) && (backBoard[a][b].equals("_") == true) && (depth == 2))
					{
						return false;
					}
					
				}
				a++;
			}
		}
		return true;
	}
	public boolean addPieceonFront(piece piecetoAdd, int rowIndexonBoard, int columnIndexonBoard)
	{
		//Function to add a piece at any point on the board
		if (checkBoundary(piecetoAdd, rowIndexonBoard, columnIndexonBoard))
		{
			System.out.println("Piece would have gone off of the board. Please try again");
			return false;
		}
		//Need another else if statement for the unallowed crossings
		else
		{
			//The first double for loop will need to check if it is a viable move
			//This will check for any protrusions
			//If it's allowed, then there will be another double four loop to check it out
			//On the frontBoard, you will add the color to all indices on the frontBoard
			//On the backBoard, you will only put the color on if the depth of the piece for that spot is 2
			

			if (checkValidMoveforAddFront(piecetoAdd, rowIndexonBoard, columnIndexonBoard))
					{
				//Then you can add the piece
				int a = rowIndexonBoard;
				for (int i = 0; i < piecetoAdd.getRowSize(); i++)
				{
					int b = columnIndexonBoard;
					for (int j = 0; j < piecetoAdd.getColumnSize(); j++)
					{
						System.out.println("a: " + a + " b: " +b);
						int depth = piecetoAdd.getDepth(i, j);
						if (depth > 0)
						{
							if (depth == 2)
							{
								frontBoard[a][b] = piecetoAdd.getColor();
								backBoard[a][b] = piecetoAdd.getColor();
							}
							else if (depth == 1)
							{
								frontBoard[a][b] = piecetoAdd.getColor();
							}
							
						}
						b++;
					}
					a++;
				}
				return true;
					}
			else
			{
				System.out.println("This was an invalid move due to conflict. Please try again");
				return false;
			}
		}
	}
	public boolean addPieceonBack(piece piecetoAdd, int rowIndexonBoard, int columnIndexonBoard)
	{
		//Function to add a piece at any point on the board
				if (checkBoundary(piecetoAdd, rowIndexonBoard, columnIndexonBoard))
				{
					System.out.println("Piece would have gone off of the board. Please try again");
					return false;
				}
				//Need another else if statement for the unallowed crossings
				else
				{
					//The first double for loop will need to check if it is a viable move
					//This will check for any protrusions
					//If it's allowed, then there will be another double four loop to check it out
					//When adding to the back, you will add the color to all indices on the backBoard
					//On the front array, you will only put the color on if the depth of the piece for that spot is 2
					if (checkValidMoveforAddBack(piecetoAdd, rowIndexonBoard, columnIndexonBoard))
							{
						int a = rowIndexonBoard;
						for (int i = 0; i < piecetoAdd.getRowSize(); i++)
						{
							int b = columnIndexonBoard;
							for (int j = 0; j < piecetoAdd.getColumnSize(); j++)
							{
								int depth = piecetoAdd.getDepth(i, j);
								if (depth > 0)
								{
									if (depth == 2)
									{
										frontBoard[a][b] = piecetoAdd.getColor();
										backBoard[a][b] = piecetoAdd.getColor();
									}
									else if (depth == 1)
									{
										backBoard[a][b] = piecetoAdd.getColor();
									}
									
								}
								b++;
							}
							a++;
						
						}
						return true;
						}
					else
					{
						System.out.println("This was an invalid move due to conflict. Please try again");
						return false;
					}
				}
	}
	public static void main(String[] args)
	{
		board tester = new board();
		System.out.println(tester);
	}
}
