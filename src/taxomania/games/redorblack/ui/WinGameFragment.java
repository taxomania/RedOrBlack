package taxomania.games.redorblack.ui;

import taxomania.games.redorblack.R;
import android.widget.ImageView;

public class WinGameFragment extends EndGameFragment {
    public WinGameFragment() {
    } // WinGameFragment()

    @Override
    void setImage(final ImageView image) {
        image.setImageResource(R.drawable.youwin);
    } // setImage(ImageView)
} // WinGameFragment
