package taxomania.games.redorblack;

final class GameEngine {
    // @formatter:off
    static enum Colour { RED, BLACK };
    // @formatter:on

    final double getProbability(final int guess) {
        return Math.pow(0.5, guess + 1);
    } // getProbability(int)

    Colour getColour() {
        return ((int) Math.round(Math.random()) == 0) ? Colour.RED : Colour.BLACK;
    } // getColour()

    boolean checkColour(final Colour colour) {
        return getColour().equals(colour);
    } // checkColour(Colour)
} // GameEngine
