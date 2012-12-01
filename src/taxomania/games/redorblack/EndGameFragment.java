package taxomania.games.redorblack;

import taxomania.games.redorblack.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

abstract class EndGameFragment extends Fragment {
    interface EndGameListener {
        void startNewGame();
    } // EndGameListener

    private EndGameListener mListener;

    public EndGameFragment() {
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (EndGameListener) activity;
        } catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement EndGameListener");
        } // catch
    } // onAttach(Activity)

    static EndGameFragment newWinGameFragment() {
        return new EndGameFragment() {
            @Override
            int getImageResourceId() {
                return R.drawable.youwin;
            } // getImageResourceId()
        };
    } // newWinGameFragment()

    static EndGameFragment newLoseGameFragment() {
        return new EndGameFragment() {
            @Override
            int getImageResourceId() {
                return R.drawable.youlose;
            } // getImageResourceId()
        };
    } // newLoseGameFragment()

    @Override
    public final View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        final RelativeLayout rl = new RelativeLayout(getActivity());
        rl.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

        final ImageView image = new ImageView(getActivity());
        image.setImageResource(getImageResourceId());
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                mListener.startNewGame();
            } // onClick(View)
        });
        final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        image.setLayoutParams(lp);
        rl.addView(image);
        return rl;
    } // onCreateView(LayoutInflater, ViewGroup, Bundle)

    abstract int getImageResourceId();
} // class EndGameFragment
