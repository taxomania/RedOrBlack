package taxomania.games.redorblack;

public class GameEngine {
    // @formatter:off
    public static enum Colour { RED, BLACK };
    // @formatter:on
    private static final int COLOURS_NUM = 20;

    private final Colour[] mColours = new Colour[COLOURS_NUM];

    public GameEngine() {
        for (int i = 0; i < mColours.length; i++) {
            final int rand = (int) Math.round(Math.random());
            mColours[i] = (rand == 0) ? Colour.RED : Colour.BLACK;
        } // for
    } // GameEngine()

    public Colour getPosition(final int pos) {
        return mColours[pos];
    } // getPosition(int)

    public double getProbability(final int guess) {
        return Math.pow(0.5, guess+1);
    } // getProbability(int)

} // GameEngine
