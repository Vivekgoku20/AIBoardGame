package gamerules;

import boards.Board;
import boards.TicTacToeBoard;
import game.*;

import java.util.HashMap;
import java.util.Map;

    public class RuleEngine {

        Map< String, RuleSet > ruleMap = new HashMap();
        public RuleEngine( )
        {
            ruleMap.put( TicTacToeBoard.class.getName(), TicTacToeBoard.getRules());
        }
        public GameState getState(Board board )
        {
            if ( board instanceof TicTacToeBoard)
            {
                TicTacToeBoard board1 = (TicTacToeBoard) board;
                for( Rule r: ruleMap.get(TicTacToeBoard.class.getName()) )
                {
                    GameState gameState = (GameState) r.condition.apply(board1);
                    if( gameState.isOver() ){
                        return gameState;
                    };
                }
               return new GameState( false, "-");
            }
            else {
                return new GameState(false, "******invalid board******");
            }
        }
        public GameInfo getGameInfo( Board board )
        {
            if( board instanceof TicTacToeBoard )
            {
                GameState gameState = getState( board );
                final  String[] players = new String[]{"X", "O"};
                Cell forkCell = null;
                boolean canStillWin = false;
                for (int index = 0; index < 2; index++) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            Player player = new Player( players[ index ]);
                            Board b = board.move( new Move( new Cell(i,j), player));
                            for (int k = 0; k < 3; k++) {
                                for (int l = 0; l < 3; l++) {
                                    Board b1 = b.copy();
                                    forkCell = new Cell( k, l );
                                    b1.move( new Move( forkCell, player.flip()));
                                    if( getState(b1).getWinner().equals( player.flip().getPlayerSymbol())){
                                        canStillWin = true;
                                        break;
                                    }
                                }
                                if( canStillWin){
                                    break;
                                }
                            }
                            if( !canStillWin ) {
                                return new GameInfoBuilder()
                                        .isOver( gameState.isOver())
                                        .winner( gameState.getWinner() )
                                        .hasFork(true)
                                        .forkCell( forkCell )
                                        .player(player.flip())
                                        .build();
                            }
                        }
                    }
                }
                return new GameInfoBuilder()
                        .isOver(gameState.isOver())
                        .winner(gameState.getWinner())
                        .build();
            } else {
                throw new IllegalArgumentException();
            }
        }
    }