package taxomania.games.redorblack.ui;

import taxomania.games.redorblack.R;
import taxomania.games.redorblack.TopScorePrefs;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

public class RedOrBlackActivity extends FragmentActivity {
    private static final int MAIN_LAYOUT_ID = Integer.MAX_VALUE;
    private static final int PADDING_DIP = 10;

    private PostTopScore mScorePoster = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        startNewGame();
    } // onCreate(Bundle)

    private View setLayout() {
        final LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setId(MAIN_LAYOUT_ID);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setBackgroundResource(R.drawable.bg);
        final DisplayMetrics dm = getResources().getDisplayMetrics();
        final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                PADDING_DIP, dm);
        mainLayout.setPadding(padding, padding, padding, padding);

        return mainLayout;
    } // setLayout()

    void loseGame() {
        replaceFragment(new LoseGameFragment());
    } // loseGame()

    void winGame() {
        replaceFragment(new WinGameFragment());
    } // winGame()

    private void replaceFragment(final Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(MAIN_LAYOUT_ID, fragment).commit();
    } // replaceFragment(Fragment)

    void startNewGame() {
        replaceFragment(new GameUiFragment());
    } // startNewGame()

    void postTopScore(final int score) {
        stopScorePoster();
        mScorePoster = new PostTopScore();
        mScorePoster.execute(score);
    } // postTopScore(int)

    @Override
    protected void onPause() {
        stopScorePoster();
        super.onPause();
    } // onPause()

    private void stopScorePoster() {
        if (mScorePoster != null) {
            mScorePoster.cancel(true);
            mScorePoster = null;
        } // if
    } // stopScorePoster

    private final class PostTopScore extends AsyncTask<Integer, Void, Boolean> {
        @Override
        protected Boolean doInBackground(final Integer... params) {
            return new TopScorePrefs(RedOrBlackActivity.this).edit().putScore(params[0]).commit();
        } // doInBackground(Integer)
    } // PostTopScore

} // RedOrBlackActivity