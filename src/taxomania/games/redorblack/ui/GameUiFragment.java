package taxomania.games.redorblack.ui;

import java.text.NumberFormat;

import taxomania.games.redorblack.R;
import taxomania.games.redorblack.TopScorePrefs;
import taxomania.games.redorblack.engine.GameEngine;
import taxomania.games.redorblack.engine.GameEngine.Colour;
import taxomania.games.redorblack.engine.SimpleGameEngine;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameUiFragment extends Fragment {
    private static final String TAG = GameUiFragment.class.getSimpleName();
    private static final int MAX_TURNS = 20;
    private static final int FOOTER_VIEW_ID = Integer.MAX_VALUE;
    private static final int ANSWER_VIEW_ID = FOOTER_VIEW_ID - 1;
    private static final int CHANCES_VIEW_ID = ANSWER_VIEW_ID - 1;

    private static int sHighscore = 0;

    private int mGuess;
    private GameEngine mGame;
    private RedOrBlackActivity mFragActivity;
    private RelativeLayout mRl;
    private TextView mTopScoreTextView, mNumCorrectTextView, mChancesTextView;

    public GameUiFragment() {
    } // GameFragment()

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGame = new SimpleGameEngine();
        mFragActivity = (RedOrBlackActivity) getActivity();
    } // onCreate(Bundle)

    @Override
    public void onResume() {
        super.onResume();
        getTopScore();
    } // onResume()

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        mRl = new RelativeLayout(mFragActivity);
        mRl.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
                mFragActivity.getResources().getDisplayMetrics());
        mRl.setPadding(padding, padding, padding, padding);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        mChancesTextView = new TextView(mFragActivity);
        mChancesTextView.setId(CHANCES_VIEW_ID);
        mChancesTextView.setGravity(Gravity.CENTER);
        mChancesTextView.setTextColor(Color.BLACK);
        mChancesTextView.setPadding(0, padding, 0, padding * 2);
        setChancesText();
        mRl.addView(mChancesTextView, lp);

        lp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, mChancesTextView.getId());
        mRl.addView(createButtons(), lp);

        lp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        final TableLayout footerTable = createFooterView();
        mRl.addView(footerTable, lp);

        lp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ABOVE, footerTable.getId());
        final TableLayout answersTable = createAnswerBlocks();
        mRl.addView(answersTable, lp);

        lp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ABOVE, answersTable.getId());
        mNumCorrectTextView = new TextView(mFragActivity);
        mNumCorrectTextView.setGravity(Gravity.CENTER);
        mNumCorrectTextView.setTextColor(Color.BLACK);
        setCurrentTurnText();
        mRl.addView(mNumCorrectTextView, lp);

        return mRl;
    } // onCreateView(LayoutInflater, ViewGroup, Bundle)

    private TableLayout createFooterView() {
        final TableLayout tl = new TableLayout(mFragActivity);
        tl.setId(FOOTER_VIEW_ID);
        final TableRow tr = new TableRow(mFragActivity);
        mTopScoreTextView = new TextView(mFragActivity);
        setTopScoreText();
        mTopScoreTextView.setTextColor(Color.BLACK);
        tr.addView(mTopScoreTextView);
        tl.addView(tr);
        return tl;
    } // createFooterView()

    private String getChances() {
        final NumberFormat nf = NumberFormat.getInstance();
        return nf.format(mGame.getProbability(mGuess) * 100) + "%";
    } // getChances()

    private void setChancesText() {
        mChancesTextView.setText("You have a " + getChances() + " chance of being correct");
    } // setChancesText()

    private void setCurrentTurnText() {
        mNumCorrectTextView.setText("Correct: " + mGuess);
    } // setCurrentTurnText()

    // Default implementation
    private void setTopScoreText() {
        setTopScoreText(sHighscore);
    } // setTopScoreText()

    private void setTopScoreText(final int score) {
        mTopScoreTextView.setText("Best Score: " + score);
    } // setTopScoreText(int)

    private TableLayout createAnswerBlocks() {
        final TableLayout tl = new TableLayout(mFragActivity);
        tl.setId(ANSWER_VIEW_ID);
        tl.addView(addAnswerRow(0, 10));
        tl.addView(addAnswerRow(10, 20));
        return tl;
    } // createAnswerBlocks()

    private TableRow addAnswerRow(final int startTag, final int finishTag) {
        final TableRow tr = new TableRow(mFragActivity);
        tr.setGravity(Gravity.CENTER);
        for (int i = startTag; i < finishTag; i++) {
            final ImageView image = new ImageView(mFragActivity);
            image.setImageResource(R.drawable.smalldefault);
            image.setTag(i);
            tr.addView(image);
        } // for
        return tr;
    } // addAnswerRow()

    private TableLayout createButtons() {
        final TableLayout tl = new TableLayout(mFragActivity);
        final TableRow tr = new TableRow(mFragActivity);
        tr.setGravity(Gravity.CENTER);
        final ImageView red = new ImageView(mFragActivity);
        red.setImageResource(R.drawable.red);
        red.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                updateAnswerBlock(Colour.RED);
            } // onClick(View)
        });
        tr.addView(red);

        final ImageView black = new ImageView(mFragActivity);
        black.setImageResource(R.drawable.black);
        black.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                updateAnswerBlock(Colour.BLACK);
            } // onClick(View)
        });
        tr.addView(black);
        tl.addView(tr);

        final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
                mFragActivity.getResources().getDisplayMetrics());
        black.setPadding(padding, 0, 0, 0);
        red.setPadding(0, 0, padding, 0);
        return tl;
    } // createButtons()

    private void updateAnswerBlock(final Colour colour) {
        if (mGame.getColour(mGuess).equals(colour)) {
            switch (colour) {
                case BLACK:
                    ((ImageView) mRl.findViewWithTag((Integer) mGuess))
                            .setImageResource(R.drawable.smallblack);
                    break;
                case RED:
                    ((ImageView) mRl.findViewWithTag((Integer) mGuess))
                            .setImageResource(R.drawable.smallred);
                default:
                    break;
            } // switch
            mGuess++;
            setCurrentTurnText();
            setChancesText();
            if (mGuess > sHighscore) {
                setTopScoreText(mGuess);
            } // if
            if (mGuess == MAX_TURNS) {
                checkScore();
                mFragActivity.winGame();
            } // if
            Log.d(TAG, "Correct");
        } // if
        else {
            Log.d(TAG, "Incorrect");
            checkScore();
            mFragActivity.loseGame();
        } // else
    } // updateAnswerBlock(Colours)

    private void checkScore() {
        if (mGuess > sHighscore) {
            mFragActivity.postTopScore(mGuess);
        } // if
        else {
            Log.d(TAG, mGuess + " : " + sHighscore);
        } // else
    } // checkScore()

    private RetrieveTopScore mScoreGetter = null;

    private void getTopScore() {
        stopScoreTask();
        mScoreGetter = new RetrieveTopScore();
        mScoreGetter.execute();
    } // getTopScore()

    private void stopScoreTask() {
        if (mScoreGetter != null) {
            mScoreGetter.cancel(true);
            mScoreGetter = null;
        } // if
    } // stopScoreTask()

    @Override
    public void onPause() {
        stopScoreTask();
        super.onPause();
    } // onPause()

    private final class RetrieveTopScore extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(final Void... noParams) {
            return new TopScorePrefs(mFragActivity).getScore();
        } // doInBackground(Void...)

        @Override
        protected void onPostExecute(final Integer result) {
            sHighscore = result;
            setTopScoreText();
        } // onPostExecute(Integer)
    } // RetrieveTopScore

} // GameFragment