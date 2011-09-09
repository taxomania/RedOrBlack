package taxomania.games.redorblack;

import taxomania.games.redorblack.GameEngine.Colour;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GameUiFragment extends Fragment {
    private static final String TAG = GameUiFragment.class.getSimpleName();
    private RelativeLayout mRl;
    private GameEngine mGame;
    private int mGuess = 0;

    public GameUiFragment() {
    } // GameFragment()

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGame = new GameEngine();
    } // onCreate(Bundle)

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        mRl = new RelativeLayout(getActivity());
        mRl.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
                getActivity().getResources().getDisplayMetrics());
        mRl.setPadding(padding, padding, padding, padding);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        mRl.addView(createButtons(), lp);

        lp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        mRl.addView(createAnswerBlocks(), lp);

        return mRl;
    } // onCreateView(LayoutInflater, ViewGroup, Bundle)

    private TableLayout createAnswerBlocks() {
        final TableLayout tl = new TableLayout(getActivity());
        for (int i = 0; i < 2; i++) {
            tl.addView(addAnswerRow());
        } // for
        return tl;
    } // createAnswerBlocks()

    private TableRow addAnswerRow() {
        final TableRow tr = new TableRow(getActivity());
        for (int i = 0; i < 10; i++) {
            // TODO: Change drawable
            final ImageView image = new ImageView(getActivity());
            image.setImageResource(R.drawable.icon);
            image.setTag(i);
            tr.addView(image);
        } // for
        return tr;
    } // addAnswerRow()

    private TableLayout createButtons() {
        // TODO: Change drawables
        final TableLayout tl = new TableLayout(getActivity());
        final TableRow tr = new TableRow(getActivity());
        final ImageView red = new ImageView(getActivity());
        red.setImageResource(R.drawable.icon);
        red.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                updateAnswerBlock(Colour.RED);
            } // onClick(View)
        });
        tr.addView(red);

        final ImageView black = new ImageView(getActivity());
        black.setImageResource(R.drawable.icon);
        black.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                updateAnswerBlock(Colour.BLACK);
            } // onClick(View)
        });
        tr.addView(black);
        tl.addView(tr);
        return tl;
    } // createButtons()

    private void updateAnswerBlock(final Colour colour) {
        if (mGame.getPosition(mGuess).equals(colour)) {
            // TODO: Change drawable
            ((ImageView) mRl.findViewWithTag((Integer) mGuess)).setImageResource(R.drawable.icon);
            mGuess++;
            Log.d(TAG, "Correct");
        } // if
        else {
            Log.d(TAG, "Incorrect");
            if (getActivity() instanceof RedOrBlackActivity) {
                ((RedOrBlackActivity) getActivity()).loseGame();
            } // if
        } // else
    } // updateAnswerBlock(Colours)
} // GameFragment
