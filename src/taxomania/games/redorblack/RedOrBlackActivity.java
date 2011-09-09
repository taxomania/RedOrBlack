package taxomania.games.redorblack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

public class RedOrBlackActivity extends FragmentActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout ll = new LinearLayout(this);
        setContentView(ll);
    } // onCreate(Bundle)
} // RedOrBlackActivity