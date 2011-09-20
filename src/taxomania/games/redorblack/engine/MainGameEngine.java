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
} // MainGameEngine
