package one.saver.devautoadv;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Doron Yechezkel on 12/2/2017.
 */

public class Helper extends AsyncTask<Void, Void, Void>{

    @Override
    protected Void doInBackground(Void... voids) {
        Log.e("HTTP", "HTTP is alive");
        return null;
    }
}
