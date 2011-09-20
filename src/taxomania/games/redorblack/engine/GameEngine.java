package taxomania.games.redorblack.engine;

public abstract class GameEngine {
    // @formatter:off
    public static enum Colour { RED, BLACK };
    // @formatter:on

    public GameEngine() {
    } // GameEngine()

    public final double getProbability(final int guess) {
        return Math.pow(0.5, guess + 1);
    } // getProbability(int)

    protected final Colour selectColour() {
        return ((int) Math.round(Math.random()) == 0) ? Colour.RED : Colour.BLACK;
    } // selectColour()

    public abstract Colour getColour(int pos);
} // GameEngine
