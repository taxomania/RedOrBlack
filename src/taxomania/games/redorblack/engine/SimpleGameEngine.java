package taxomania.games.redorblack.engine;

public class SimpleGameEngine extends GameEngine {
    public SimpleGameEngine() {
    } // SimpleGameEngine()

    public Colour getColour(final int position) {
        return selectColour();
    } // getColour(int)
} // SimpleGameEngine
