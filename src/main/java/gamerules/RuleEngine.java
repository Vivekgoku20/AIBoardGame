package gamerules;

import boards.Board;
import boards.CellBoard;
import boards.TicTacToeBoard;
import game.*;
import placements.DefensivePlacement;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        public GameInfo getGameInfo( CellBoard board )
        {
            if( board instanceof TicTacToeBoard ) {
                TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
                GameState gameState = getState(board);
                final String[] players = new String[]{"X", "O"};
                for ( TicTacToeBoard.Symbol symbol : TicTacToeBoard.Symbol.values()) {
                    Player player = new Player(symbol.marker());
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (ticTacToeBoard.getSymbol(i, j) == null) {

                                //Making a move here, should force the opponent to make a defensive move
                                TicTacToeBoard b = ticTacToeBoard.move(new Move(Cell.getCell(i, j), player));

                                DefensivePlacement defensivePlacement = DefensivePlacement.get();

                                //Checking if the opponent is forced to place a defensive move
                                Optional<Cell> defensiveCell = defensivePlacement.place(b, player.flip());

                                if( defensiveCell.isPresent() ) {
                                    b = b.move( new Move( defensiveCell.get(), player.flip()));
                                    OffensivePlacement offensivePlacement = OffensivePlacement.get();
                                    //We should still win after the opponent makes the defensive move
                                    Optional<Cell> offensiveCell = offensivePlacement.place(b, player);
                                    if( offensiveCell.isPresent() )
                                    {
                                        return new GameInfoBuilder()
                                                .isOver(gameState.isOver())
                                                .winner(gameState.getWinner())
                                                .hasFork(true)
                                                .forkCell(Cell.getCell(i,j))
                                                .player(player.flip())
                                                .build();
                                    }
                                }
                            }
                        }
                    }
                    return new GameInfoBuilder()
                            .isOver(gameState.isOver())
                            .winner(gameState.getWinner())
                            .build();
                    }
                }   else {
                    throw new IllegalArgumentException();
                }
            throw new IllegalArgumentException();
        }
    }