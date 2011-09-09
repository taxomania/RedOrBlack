package taxomania.games.redorblack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class LoseGameFragment extends Fragment implements OnClickListener {
    public LoseGameFragment() {
    } // LoseGameFragment()

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        // TODO: Change drawable
        final RelativeLayout rl = new RelativeLayout(getActivity());
        rl.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        final ImageView image = new ImageView(getActivity());
        image.setImageResource(R.drawable.icon);
        image.setOnClickListener(this);
        final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);//, RelativeLayout.TRUE);
        image.setLayoutParams(lp);
        rl.addView(image);
        return rl;
    } // onCreateView(LayoutInflater, ViewGroup, Bundle)

    @Override
    public void onClick(final View v) {
        if (getActivity() instanceof RedOrBlackActivity) {
            ((RedOrBlackActivity) getActivity()).startNewGame();
        } // if
    } // onClick(View)
} // LoseGameFragment
