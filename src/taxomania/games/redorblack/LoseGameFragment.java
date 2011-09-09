package taxomania.games.redorblack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class LoseGameFragment extends Fragment implements OnClickListener {
    public LoseGameFragment() {
    } // LoseGameFragment()

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        final TextView tv = new TextView(getActivity());
        tv.setText("AGAIN");
        tv.setOnClickListener(this);
        return tv;
    } // onCreateView(LayoutInflater, ViewGroup, Bundle)

    @Override
    public void onClick(final View v) {
        if (getActivity() instanceof RedOrBlackActivity) {
            ((RedOrBlackActivity) getActivity()).startNewGame();
        } // if
    } // onClick
} // LoseGameFragment
