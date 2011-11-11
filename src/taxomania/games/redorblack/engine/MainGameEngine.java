package taxomania.games.redorblack.engine;

public class MainGameEngine extends GameEngine {
    private static final int COLOURS_NUM = 20;

    private final Colour[] mColours = new Colour[COLOURS_NUM];

    public MainGameEngine() {
        for (int i = 0; i < mColours.length; i++) {
            mColours[i] = selectColour();
        } // for
    } // MainGameEngine()

    public Colour getColour(final int pos) {
        return mColours[pos];
    } // getColour(int)


    // Not used in testing
    @Override
    public boolean checkColour(Colour colour) {
        return checkColour(colour,0);
    } // checkColour
    public boolean checkColour(final Colour colour, final int pos) {
        return getColour(pos).equals(colour);
    } // checkColour(Colour, int)
} // MainGameEngine
