package taxomania.games.redorblack;

public class SimpleGameEngine extends GameEngine {
    public SimpleGameEngine() {
    } // SimpleGameEngine()

    public Colour getColour(final int position) {
        return selectColour();
    } // getColour(int)
} // SimpleGameEngine
