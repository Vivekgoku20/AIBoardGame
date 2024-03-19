package placements;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Player;
import gamerules.RuleEngine;

import java.util.Optional;

public interface Placement {
    RuleEngine ruleEngine = new RuleEngine();
    Optional<Cell> place(TicTacToeBoard board, Player player);

    Placement next();

    static Placement get()
    {
        return null;
    }
}
