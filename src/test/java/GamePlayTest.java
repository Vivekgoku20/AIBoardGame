import boards.Board;
import game.Cell;
import game.Move;
import game.Player;
import gamerules.GameEngine;
import gamerules.RuleEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {
    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }
    @Test
    public void checkForRowWin()  {
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        int[][] firstPlayerMoves = new int[][]{{1,0}, {1,1}, {1,2}};
        int[][] secondPlayerMoves = new int[][]{{0,0}, {0,1}, {0,2}};
        board = playGame( board, firstPlayerMoves,  secondPlayerMoves );
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        int[][] firstPlayerMoves = new int[][]{{0,1}, {1,1}, {2,1}};
        int[][] secondPlayerMoves = new int[][]{{0,0}, {1,2}, {0,2}};
        board = playGame( board, firstPlayerMoves,  secondPlayerMoves );
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagonalWin() {
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        int[][] firstPlayerMoves = new int[][]{{0,0}, {1,1}, {2,2}};
        int[][] secondPlayerMoves = new int[][]{{1,0}, {0,1}, {0,2}};
        board = playGame( board, firstPlayerMoves,  secondPlayerMoves );
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagonalWin() {
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        int[][] firstPlayerMoves = new int[][]{{2,0}, {1,1}, {0,2}};
        int[][] secondPlayerMoves = new int[][]{{0,0}, {0,1}, {1,2}};
        board = playGame( board, firstPlayerMoves,  secondPlayerMoves );

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForSecondPlayerWin() {
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        int[][] firstPlayerMoves = new int[][]{{1,1}, {1,2}, {2,0}};
        int[][] secondPlayerMoves = new int[][]{{0,0}, {0,1}, {0,2}};
        board = playGame( board, firstPlayerMoves,  secondPlayerMoves );

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private Board playGame( Board board, int [][] firstPlayerMoves, int [][] secondPlayerMoves )
    {
        int row, col;
        int next = 0;
        Player firstPlayer = new Player("X");
        Player secondPlayer = new Player("O");

        while(!ruleEngine.getState(board).isOver())
        {
            System.out.println(board);
            //getting input from the user
            row = firstPlayerMoves[next][0];
            col = firstPlayerMoves[next][1];
            Move oppMove = new Move( Cell.getCell( row, col ), firstPlayer );
            board = gameEngine.move( board, oppMove );
            if(!ruleEngine.getState(board).isOver())
            {
                int secondPlayerRow = secondPlayerMoves[next][0];
                int secondPlayerCol = secondPlayerMoves[next][1];

                Move computerMove = new Move( Cell.getCell( secondPlayerRow, secondPlayerCol ), secondPlayer );
                board = gameEngine.move( board, computerMove );
            }
            next++;
        }
        return board;
    }
}
