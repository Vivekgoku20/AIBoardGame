package game;

public class GameConfig {
    public Integer timePerMove;
    boolean timed = false;

    public GameConfig(Integer timePerMove, boolean timed) {
        this.timePerMove = timePerMove;
        this.timed = timed;
    }
}
