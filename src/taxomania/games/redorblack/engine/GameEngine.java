package taxomania.games.redorblack.engine;

public class GameEngine {
    // @formatter:off
    public static enum Colour { RED, BLACK };
    // @formatter:on

    public GameEngine() {
    } // GameEngine()

    public final double getProbability(final int guess) {
        return Math.pow(0.5, guess + 1);
    } // getProbability(int)

    public Colour getColour() {
        return ((int) Math.round(Math.random()) == 0) ? Colour.RED : Colour.BLACK;
    } // getColour()

    public boolean checkColour(final Colour colour){
        return getColour().equals(colour);
    } // checkColour(Colour)
} // GameEngine
