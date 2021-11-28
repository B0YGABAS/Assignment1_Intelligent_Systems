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
	public int[] max(int a, int b, int c, int d) {
		if (a > b) {
			return new int[] { a, c };
		} else {
			return new int[] { b, d };
		}
	}

	public int[] min(int a, int b, int c, int d) {
		if (a < b) {
			return new int[] { a, c };
		} else {
			return new int[] { b, d };
		}
	}

	public int[] Max_Value(TicTacToeState gameState, int[] alpha, int[] beta) {
		System.out.println(alpha[0]);
		if (gameState.getWinner() != -1) {
			return new int[] { gameState.getWinner(), alpha[1] };
		} else {
			for (int position = 0; position < 9; position++) {
				if (!gameState.isValidMove(position, gameState.getCurrentPlayer())) {
					continue;
				}
				alpha = this.max(alpha[0], Min_Value(gameState.doMove(position, gameState.getCurrentPlayer()), alpha,
						new int[] { beta[0], position })[0], alpha[1], position);
				if (alpha[0] >= beta[0]) {
					return beta;
				}
			}
			return alpha;
		}
	}

	public int[] Min_Value(TicTacToeState gameState, int[] alpha, int[] beta) {
		System.out.println(alpha[0]);
		if (gameState.getWinner() != -1) {
			return new int[] { gameState.getWinner(), beta[1] };
		} else {
			for (int position = 0; position < 9; position++) {
				if (!gameState.isValidMove(position, gameState.getCurrentPlayer())) {
					continue;
				}
				beta = this.min(alpha[0], Max_Value(gameState.doMove(position, gameState.getCurrentPlayer()),
						new int[] { alpha[0], position }, beta)[0], beta[1], position);
				if (alpha[0] >= beta[0]) {
					return alpha;
				}
			}
			return beta;
		}
	}

	/*
	 * public int[] Min_Value(TicTacToeState gameState, int[] alpha, int[] beta) {
	 * System.out.println(alpha[0]); if (gameState.getWinner() != -1) { return new
	 * int[] { gameState.getWinner(), 0 }; } else { for (int position = 0; position
	 * < 9; position++) { if (!gameState.isValidMove(position,
	 * gameState.getCurrentPlayer())) { continue; } beta = new int[] {
	 * this.min(beta[0], Max_Value(gameState.doMove(position,
	 * gameState.getCurrentPlayer()), alpha, beta)[0]), position }; if (alpha[0] >=
	 * beta[0]) { return alpha; } } return beta; } }
	 */

	@Override
	public int getMove(TicTacToeState gameState) {
		// System.out.println(this.max(3, -4));
		int position;
		if (gameState.getCurrentPlayer() == 1) {
			position = this.Max_Value(gameState, new int[] { -999999, 0 }, new int[] { 999999, 0 })[1];
		} else {
			position = this.Min_Value(gameState, new int[] { -999999, 0 }, new int[] { 999999, 0 })[1];
		}
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
