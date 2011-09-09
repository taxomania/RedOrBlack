package taxomania.games.redorblack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class RedOrBlackActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startNewGame();
    } // onCreate(Bundle)

    void loseGame() {
        replaceFragment(new LoseGameFragment());
    } // loseGame()

    private void replaceFragment(final Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout, fragment).commit();
    } // replaceFragment(Fragment)

    void startNewGame() {
        replaceFragment(new GameUiFragment());
    } // startNewGame()

} // RedOrBlackActivity