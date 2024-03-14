import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import gameai.AIEngine;
import gameai.GameEngine;
import gameai.RuleEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class GamePlayTest {
    GameEngine gameEngine;
    AIEngine aiEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }
    @Test
    public void checkForRowWin()  {
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        int[][] moves = new int[][]{{1,0}, {1,1}, {1,2}};
        playGame( board, moves );
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        int[][] moves = new int[][]{{0,1}, {1,1}, {2,1}};
        playGame( board, moves );
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagonalWin() {
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        int[][] moves = new int[][]{{0,0}, {1,1}, {2,2}};
        playGame( board, moves );
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagonalWin() {
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        int[][] moves = new int[][]{{2,0}, {1,1}, {0,2}};
        playGame( board, moves );
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin() {
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        int[][] moves = new int[][]{{1,1}, {1,1}, {2,0}};
        playGame( board, moves );
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame( Board board, int [][] moves )
    {
        int row, col;
        int next = 0;
        Player computer = new Player("O");
        Player human = new Player("X");

        while(!ruleEngine.getState(board).isOver())
        {
            //getting input from the user
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move oppMove = new Move( new Cell( row, col ), human );
            gameEngine.move( board, oppMove );

            if(!ruleEngine.getState(board).isOver())
            {
                Move computerMove = aiEngine.suggestMove( board, computer );
                gameEngine.move( board, computerMove );
            }
        }
    }
}
