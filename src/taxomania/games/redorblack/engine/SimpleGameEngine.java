package taxomania.games.redorblack.engine;

public class SimpleGameEngine extends GameEngine {
    public SimpleGameEngine() {
    } // SimpleGameEngine()

    public Colour getColour(final int position) {
        return selectColour();
    } // getColour(int)

    public Colour getColour(){
        return getColour(0);
    } // getColour()

    public boolean checkColour(final Colour colour){
        return getColour().equals(colour);
    } // checkColour(Colour)
} // SimpleGameEngine
