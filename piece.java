
public class piece 
{
	protected int[][] piece;
	protected int rowSize, columnSize; //Might change when rotated to the right or the left
	protected String color;
	public piece(int[][] piece, int rowSize, int columnSize, String color)
	{
		this.piece = piece;
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.color = color;
	}
	public String getColor()
	{
		return color;
	}

	public int getDepth(int row, int column)
	{
		return piece[row][column];
	}
	public int getRowSize()
	{
		return rowSize;
	}
	public int getColumnSize()
	{
		return columnSize;
	}
	public void rotateRight()
	{
		//Will rotate the piece right and adjust the row and column sizes if necessary
		//There are three cases to consider:
		//Case 1: When rowSize < columnSize
		//Case 2: When rowSize > columnSize
		//Case 3: When rowSize = columnSize
		//a and b will be the index in the rotatedPiece where any piece[i][j] will go
		//will need to calculate a and b for each piece
		int[][] rotatedPiece = new int[columnSize][rowSize];
		if (rowSize > columnSize)
		{
			int a = 0;
			int b = rowSize-1;
			for (int i = 0; i < rowSize; i++)
			{
				a = 0;
				for (int j = 0; j < columnSize; j++)
				{
					rotatedPiece[a][b] = piece[i][j];
					a++;
				}
				b--;
			}
		}
		else if (rowSize < columnSize)
		{
			int a = 0;
			int b = rowSize-1;
			for (int i = 0; i < rowSize; i++)
			{
				a = 0;
				for (int j = 0; j < columnSize; j++)
				{
					rotatedPiece[a][b] = piece[i][j];
					a++;
				}
				b--;
			}
		}
		else
		{
			int a = 0;
			int b =  columnSize-1;
			for (int i = 0; i < rowSize; i++)
			{
				
				a= 0;
				for (int j = 0; j < columnSize; j++)
				{
					rotatedPiece[a][b] = piece[i][j];
					a++;
				}
				b--;
			}
		}
		int a = rowSize;
		int b = columnSize;
		rowSize = b;
		columnSize = a;
		piece = rotatedPiece;
	}
	public void rotateLeft()
	{
		//Will rotate the piece left and adjust the row and column sizes if necessary
		//There are three cases to consider:
		//Case 1: When rowSize < columnSize
		//Case 2: When rowSize > columnSize
		//Case 3: When rowSize = columnSize
		//a and b will be the index in the rotatedPiece where any piece[i][j] will go
		//will need to calculate a and b for each case
		int[][] rotatedPiece = new int[columnSize][rowSize];
		int b = 0;
		int a = 0;
		if (rowSize > columnSize)
		{
			 a = columnSize-1;
			b = 0;
			for (int i = 0; i < rowSize; i++)
			{
				a = columnSize-1;
				for (int j = 0; j < columnSize; j++)
				{
					
					rotatedPiece[a][b] = piece[i][j];
					a--;
				}
				b++;
			}
		}
		else if (rowSize < columnSize)
		{
			 a = columnSize-1;
			 b = 0;
			for (int i = 0; i < rowSize; i++)
			{
				a = columnSize-1;
				for (int j = 0; j < columnSize; j++)
				{
					rotatedPiece[a][b] = piece[i][j];
					a--;
				}
				b++;
			}
		}
		else if (rowSize == columnSize)
		{
			 a = rowSize-1;
			 b =  0;
			for (int i = 0; i < rowSize; i++)
			{
				a =rowSize-1;
				for (int j = 0; j < columnSize; j++)
				{

					rotatedPiece[a][b] = piece[i][j];
					a--;
				}
				b++;
			}
		}
		 a = rowSize;
		 b = columnSize;
		rowSize = b;
		columnSize = a;
		piece = rotatedPiece;
	}

	public String toString()
	{
		//Will print out the piece
		String returner = "Color: " + color + "\n";
		for (int i = 0; i < rowSize; i++)
		{
			for (int j = 0; j < columnSize; j++)
			{
				returner += piece[i][j] + " ";
			}
			returner += "\n";
		}
		return returner;
	}
	public static void main(String[] args)
	{
		int[][] testPiece = {{3,1,2},{4,0,0}};
		int rowSize = 2;
		int columnSize = 3;
		String color = "a";
		piece test = new piece(testPiece, rowSize, columnSize,color);
		System.out.println("Orginal Position:");
		System.out.println(test);
		System.out.println("Rotated Right");
		test.rotateLeft();
		System.out.println(test);
		System.out.println("Rotated Right");
		test.rotateLeft();
		System.out.println(test);
		System.out.println("Rotated Right");
		test.rotateLeft();
		System.out.println(test);
		System.out.println("Rotated Right Back to Original Position");
		test.rotateLeft();
		System.out.println(test);
	}
}
