import game.Cell;
import game.Move;
import game.Player;
import AI.AIEngine;
import gamerules.GameEngine;
import boards.Board;
import gamerules.RuleEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static  void main(String[] args) throws IOException {
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        Player computer = new Player("O");
        Player human = new Player("X");
        while(ruleEngine.getState(board).isOver())
        {
            System.out.println( " Make you move ! ");
            System.out.println(board);
            BufferedReader br = new BufferedReader( new InputStreamReader((System.in)));
            String[] input = br.readLine().split(" ");
            //getting input from the user
            int row = Integer.parseInt(input[0]), column = Integer.parseInt(input[1]);
            Move oppMove = new Move( Cell.getCell( row, column ), human );
            gameEngine.move( board, oppMove );

            if(ruleEngine.getState(board).isOver())
            {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move( board, computerMove );
            }

        }
        System.out.println( "Game is over, game result: " + ruleEngine.getState(board).getWinner());
        System.out.println(board);
    }
}
