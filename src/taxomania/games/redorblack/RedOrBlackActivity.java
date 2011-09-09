package taxomania.games.redorblack;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RedOrBlackActivity extends FragmentActivity {
    private static final int MAIN_LAYOUT_ID = Integer.MAX_VALUE;
    private static final int TEXT_SIZE = 30;
    private static final int PADDING = 5;

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
        mainLayout.setBackgroundColor(Color.GRAY);
        final DisplayMetrics dm = getResources().getDisplayMetrics();
        final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PADDING,
                dm);
        mainLayout.setPadding(padding, padding, padding, padding);

        final LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT);
        final float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE, dm);
        TextView tv = new TextView(this);
        tv.setText("RED");
        tv.setTextColor(Color.RED);
        tv.setTextSize(textSize);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.LEFT);
        mainLayout.addView(tv);

        tv = new TextView(this);
        tv.setText("OR");
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(textSize);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        mainLayout.addView(tv);

        tv = new TextView(this);
        tv.setText("BLACK");
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(textSize);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.RIGHT);
        mainLayout.addView(tv);

        return mainLayout;
    } // setLayout()

    void loseGame() {
        replaceFragment(new LoseGameFragment());
    } // loseGame()

    private void replaceFragment(final Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(MAIN_LAYOUT_ID, fragment).commit();
    } // replaceFragment(Fragment)

    void startNewGame() {
        replaceFragment(new GameUiFragment());
    } // startNewGame()

} // RedOrBlackActivity