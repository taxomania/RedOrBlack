package taxomania.games.redorblack;

import android.content.Context;
import android.content.SharedPreferences;

public class TopScorePrefs {
    private static final String PREFERENCES = "RedOrBlack_Prefs";
    private static final String KEY_SCORE = "score";
    private static final int DEFAULT = 0;

    private final SharedPreferences mPrefs;

    public TopScorePrefs(final Context context) {
        mPrefs = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    } // TopScorePrefs(Context)

    public int getScore() {
        return mPrefs.getInt(KEY_SCORE, DEFAULT);
    } // getScore()

    public Editor edit() {
        return new Editor();
    } // edit()

    public class Editor {
        private final SharedPreferences.Editor mEdit;

        public Editor() {
            mEdit = mPrefs.edit();
        } // Editor()

        public Editor putScore(final int score) {
            mEdit.putInt(KEY_SCORE, score);
            return this;
        } // putScore(int)

        public boolean commit() {
            return mEdit.commit();
        } // commit()

        public boolean reset() {
            return putScore(DEFAULT).commit();
        } // reset()
    } // TopScorePrefs.Editor

} // TopScorePrefs