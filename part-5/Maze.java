/*
 * This code allows us to model and print a Maze.
 */

public class Maze {
	// 0 - obstacle
	// 1 - open space
	// 2 - path taken
	// 3 - goal 
	private static int[][] maze = 
		{{0, 0, 1, 1, 1, 1, 1, 1},
		{2, 0, 1, 0, 0, 0, 1, 1},
		{1, 0, 1, 0, 0, 0, 0, 0},
		{1, 1, 1, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 1, 3, 1, 1},
		{0, 0, 1, 0, 1, 0, 0, 1},
		{1, 0, 1, 1, 1, 0, 0, 0},
		{1, 1, 1, 0, 1, 1, 0, 0}};
	// use symbols to make reading the output easier...
	// 0 - obstacle - '#'
	// 1 - open space - '.'
	// 2 - path taken - '+'
	// 3 - goal - '*'
	private static final char[] MAZE_SYMBOLS = {'#', '.', '+', '*' };


	//Try to findPathFrom initial position if the maze is solved print the solution
	public static void main(String[] args) 
	{
		System.out.println("Maze Problem in Initial State: ");
		print();
		
		System.out.println("Checking available positions...");
		System.out.println("Is (0,0) available?: " + isAvailablePosition(0,0));
		System.out.println("Is (0,1) available?: " + isAvailablePosition(0,1));
		System.out.println("Is (0,2) available?: " + isAvailablePosition(0,2));
		System.out.println("Is (10,0) available?: " + isAvailablePosition(10,0));
		System.out.println("Is (1,0) available?: " + isAvailablePosition(1,0));
		
		if (findPathFrom(1, 0)) {
			print();
		} else {
			System.out.println("no solution found");
		}
	}

	//print the output using MAZE_SYMBOLS
	private static void print(){
		for(int row = 0; row < maze.length; ++row) {
			for(int col = 0; col < maze[row].length; ++col) {
				System.out.print(MAZE_SYMBOLS[maze[row][col]]);
			}
			System.out.println();
		}
	}
	// A position is available if: (1) it is inside the bounds of the maze 
	// (2) if it is an open space or (3) it is the goal 
	private static boolean isAvailablePosition(int row, int col) 
	{
		boolean result =  row >= 0 && row < maze.length
				&& col >= 0 && col < maze[row].length
				&& (maze[row][col] == 1 || maze[row][col] == 3);
		return result;
	}
	
	private static boolean findPathFrom(int row, int col) {

		// when we reach the goal we have solved the problem
		if (maze[row][col] == 3) {
			return true;
		}

		// add the position to our path changing its value to '2'
		maze[row][col] = 2;

		//try all available neighbors 
		//North (row-1, col), South (row+1, col), East (row, col+1) and West (row, col-1)
		// if any of these return true then we have solved the problem
		if (isAvailablePosition(row - 1, col) && findPathFrom(row - 1, col)) {
			return true;
		}
		if (isAvailablePosition(row + 1, col) && findPathFrom(row + 1, col)) {
			return true;
		}
		if (isAvailablePosition(row, col - 1) && findPathFrom(row, col - 1)) {
			return true;
		}
		if (isAvailablePosition(row, col + 1) && findPathFrom(row, col + 1)) {
			return true;
		}

		//If none of previous positions is valid or matches the goal, it is necessary to revert the 
		//temporary state. This reversal or backtrack is what gives name to the algorithm: backtracking
		maze[row][col] = 1;

		return false;
	}
}