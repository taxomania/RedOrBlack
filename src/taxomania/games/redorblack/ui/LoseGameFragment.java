package taxomania.games.redorblack.ui;

import taxomania.games.redorblack.R;
import android.widget.ImageView;

public class LoseGameFragment extends EndGameFragment {
    public LoseGameFragment() {
    } // LoseGameFragment()

    @Override
    void setImage(final ImageView image) {
        image.setImageResource(R.drawable.youlose);
    } // setImage(ImageView)
} // LoseGameFragment
