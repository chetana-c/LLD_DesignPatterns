import java.util.HashMap;
import java.util.Map;

public class TicTacToe {

    public class Board{
        private int n;
        int[][] board;
        int[] rowSum;
        int[] colSum;
        int winner =0;
        Board(int n){
            this.n = n;
            this.board = new int[n][n];
            this.rowSum = new int[n];
            this.colSum = new int[n];
        }

        public int getPlayerMove(int row, int col){
            return board[row][col];
        }

        public int move(int player, int row, int col){
            if(player == 0)
                player = -1;
            else
                player = 1;

            board[row][col] = player;
            rowSum[row] += player;
            colSum[col] += player;
            boolean winRow = true, winCol = true, winDiag = true, winRevDiag = true;

            int diagSum = 0, revDiagSum = 0;
            // check row, col, diagonal, reverse-diagonal
            if(row == col)
                diagSum += player;
            if(row == n-1-col)
                revDiagSum += player;
            if(rowSum[row] == Math.abs(n) || colSum[col] == Math.abs(n) || diagSum == Math.abs(n) || revDiagSum == Math.abs(n))
                winner =  player;

            return getWinner();
        }

        public int getWinner(){
            return winner;
        }
    }



}
