import commands.builder.EmailCommandBuilder;
import commands.builder.SMSCommandBuilder;
import commands.implementations.EmailCommand;
import commands.implementations.SMSCommand;
import events.*;
import services.EmailService;
import game.Cell;
import game.Move;
import game.Player;
import AI.AIEngine;
import gamerules.GameEngine;
import boards.Board;
import gamerules.RuleEngine;
import services.SMSService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static  void main(String[] args) throws IOException {
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToeBoard" );
        //make moves
        Player computer = new Player("O");
        Player human = new Player("X");
        EmailService emailService = new EmailService();
        SMSService smsService = new SMSService();
        EventBus eventBus = new EventBus();
        eventBus.subscribe(new Subscriber(event -> emailService.send(new EmailCommand(event))));
        eventBus.subscribe(new Subscriber(event -> smsService.send(new SMSCommand(event))));
        if(human.getUser().activeAfter(10, TimeUnit.DAYS)){
            eventBus.publish(new Event(human.getUser(), "We are happy to have you back after a while!", "https://ViveS@gmail.com","ACTIVITY"));
        }
        while(!ruleEngine.getState(board).isOver())
        {
            System.out.println( " Make you move ! \n");
            System.out.println(board);
            BufferedReader br = new BufferedReader( new InputStreamReader((System.in)));
            String[] input = br.readLine().split(" ");
            //getting input from the user
            int row = Integer.parseInt(input[0]), column = Integer.parseInt(input[1]);
            Move oppMove = new Move( Cell.getCell( row, column ), human );
            board = gameEngine.move( board, oppMove );

            if(!ruleEngine.getState(board).isOver())
            {
                Move computerMove = aiEngine.suggestMove(computer, board);
                board = gameEngine.move( board, computerMove );
            }

        }
        if(ruleEngine.getState(board).getWinner().equals(human.getPlayerSymbol())) {
            eventBus.publish(new Event(human.getUser(), "Congrtulation, you won!", "https://ViveS@gmail.com","WIN"));
        }
        System.out.println( "Game is over, game result: " + ruleEngine.getState(board).getWinner());
        System.out.println(board);
    }
}
