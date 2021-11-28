
/**
 * Assignment No. 1
 * 
 * Due: November 30, 2021 (Tuesday) at 11:55PM
 * 
 * Complete the stubbed getMove() method commented with three successive
 * question marks (???) below. The method should compute the optimal move for
 * the current player using the basic minimax algorithm as discussed in class.
 * You can add your own methods for use in this class. To facilitate player
 * competition, you must rename this class (including the filename) by changing
 * the first three letters of the class name to your capitalized initials
 * (first, middle, last). There are sources included in this homework that
 * implement various kinds of players. You can compile and use them as players.
 * You must submit the whole homework package with your modifications/additions
 * in the electronic submission. This homework must comply with the homework
 * policy specified in the "Assignments" page.
 * 
 * To compile and/or run the sources in this homework, include the provided
 * HW1.jar file as well as the current directory containing the sources in the
 * classpath. This can be done by using the "-cp [classpaths]" option of the
 * "javac" and "java" executables. For example, assuming that the HW1.jar file
 * and the source files are in the current directory, use the following commands
 * to compile the sources and run the program.
 * 
 * To compile the source files:
 * 
 * javac -cp HW1.jar;. Filename.java
 * 
 * Example:
 * 
 * javac -cp HW1.jar;. FMLPerfectPlayerHW1.java
 * 
 * 
 * To run:
 * 
 * java -cp HW1.jar;. TicTacToeLoader [PlayerXClassName] [PlayerOClassName]
 * 
 * Example:
 * 
 * java -cp HW1.jar;. TicTacToeLoader FMLPerfectPlayerHW1 RandomPlayer
 */
import java.util.Random;

public class IAPPerfectPlayerHW1 implements PlayerInterface {
	/*
	 * This method returns the position of the move made by this player on the given
	 * gameState parameter. This method always returns the optimal move of the
	 * current player on the given gameState parameter. The optimal move is computed
	 * using the basic minimax algorithm.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see PlayerInterface#getMove(TicTacToeState)
	 */

	/*
	 * public int[] max(int a, int b, int c, int d) { if (a > b) { return new int[]
	 * { a, c }; } else { return new int[] { b, d }; } }
	 * 
	 * public int[] min(int a, int b, int c, int d) { if (a < b) { return new int[]
	 * { a, c }; } else { return new int[] { b, d }; } }
	 * 
	 * public int[] MiniMax(TicTacToeState gameState, int alpha, int beta, int
	 * playerMode, int getpos) { if (gameState.getWinner() != -1) { return new int[]
	 * { gameState.getWinner(), getpos }; } else if (playerMode == 2) { int[]
	 * maxEval = {-999999,-1}; int formerMaxEval = maxEval[0]; int savepos = 0; for
	 * (int position = 0; position < 9; position++) { if
	 * (!gameState.isValidMove(position, gameState.getCurrentPlayer())) { continue;
	 * } int[] eval = MiniMax(gameState.doMove(position,
	 * gameState.getCurrentPlayer()), alpha, beta, 1, position); maxEval =
	 * max(maxEval, eval[0],-1,eval[1]); if (maxEval[0] != formerMaxEval && getpos
	 * == -1) { // maxEval = position; formerMaxEval = maxEval[0]; savepos =
	 * position; } alpha = max(alpha, eval[0],0,0)[0]; if (beta <= alpha) { break; }
	 * } if (getpos == -1) { return savepos; } return maxEval; } else { int minEval
	 * = 999999; int formerMinEval = minEval; int savepos = 0; for (int position =
	 * 0; position < 9; position++) { if (!gameState.isValidMove(position,
	 * gameState.getCurrentPlayer())) { continue; } int eval =
	 * MiniMax(gameState.doMove(position, gameState.getCurrentPlayer()), alpha,
	 * beta, 2, position); minEval = min(minEval, eval); if (minEval !=
	 * formerMinEval && getpos == -1) { // minEval = position; formerMinEval =
	 * minEval; savepos = position; } beta = min(alpha, eval); if (beta <= alpha) {
	 * break; } } if (getpos == -1) { return savepos; } return minEval; } }
	 */

	public int scoreSwitcher(int a) {
		if (a == 1) {
			a = 0;
		} else if (a == 0) {
			a = 1;
		}
		return a;
	}

	public int max(int a, int b) {
		// a=scoreSwitcher(a);
		// b=scoreSwitcher(b);
		// System.out.println("max" + a + "==" + b);
		if (a > b) {
			// System.out.println("==" + a);
			return a;
		} else {
			// System.out.println("==" + b);
			return b;
		}
	}

	public int min(int a, int b) {
		// a=scoreSwitcher(a);
		// b=scoreSwitcher(b);
		// System.out.println("min" + a + "==" + b);
		if (a < b) {
			// System.out.println("==" + a);
			return a;
		} else {
			// System.out.println("==" + b);
			return b;
		}
	}

	public int[] minimax(TicTacToeState gameState, int playerType) {
		if (gameState.getWinner() != -1) {
			int winner = gameState.getWinner();
			winner = scoreSwitcher(gameState.getWinner());
			return new int[] { winner, 0 };
		} else if (playerType == 2) {
			int maxEval = -999999;
			int former = maxEval;
			int savepos = -1;
			for (int position = 0; position < 9; position++) {
				if (!gameState.isValidMove(position, gameState.getCurrentPlayer())) {
					continue;
				}
				int eval = minimax(gameState.doMove(position, gameState.getCurrentPlayer()), 1)[0];
				maxEval = max(maxEval, eval);
				if (former != maxEval) {
					former = maxEval;
					savepos = position;
				}
			}
			return new int[] { maxEval, savepos };
		} else {
			int blankboard = 1;
			int minEval = 999999;
			int former = minEval;
			int savepos = -1;
			for (int position = 0; position < 9; position++) {
				if (!gameState.isValidMove(position, gameState.getCurrentPlayer())) {
					blankboard = 0;
					continue;
				}
				int eval = minimax(gameState.doMove(position, gameState.getCurrentPlayer()), 2)[0];
				minEval = min(minEval, eval);
				if (former != minEval) {
					former = minEval;
					savepos = position;
				}
			}
			if (blankboard == 1) {
				Random rand = new Random();
				return new int[] { 0, rand.nextInt(9), 0 };
			}
			return new int[] { minEval, savepos };
		}
		// return new int[] { 0 };
	}

	public int zz() {
		int x = 5;
		int former = x;
		for (int i = 0; i < 5; i++) {
			x += i;
			System.out.println(former);
			if (former != x) {
				former = x;
			}
			System.out.println(former);
		}
		return 0;
	}

	@Override
	public int getMove(TicTacToeState gameState) {
		// System.out.println(this.max(3, -4));
		int position;
		position = minimax(gameState, gameState.getCurrentPlayer())[1];
		// zz();
		// return 1;
		return position;

		// this.Max_Value(gameState, -999999, 999999);

		/*
		 * // System.out.println(gameState.doMove(1, gameState.getCurrentPlayer())); //
		 * System.out.println(gameState.getPiece(0));
		 * System.out.println(gameState.getWinner()); int position; for (position = 0;
		 * position < 9; ++position) { if (gameState.getWinner() == -1) { if
		 * (gameState.isValidMove(position, gameState.getCurrentPlayer())) {
		 * gameState.doMove(position, gameState.getCurrentPlayer()); break; } } } //
		 * return position;
		 * 
		 * // dummy position, just return a random blank position // return new
		 * HumanPlayer().getMove(gameState);
		 * 
		 */
		// return new RandomPlayer().getMove(gameState);
	}
}
