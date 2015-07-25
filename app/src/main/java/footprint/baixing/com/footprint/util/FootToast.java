package footprint.baixing.com.footprint.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by zhangtracy on 15/3/16.
 */
public class FootToast {

    public static void makeText(final Context context, final CharSequence text, final int duration) {
        if (context == null || text == null) {
            return;
        }

        Runnable task = new Runnable(){
            @Override
            public void run(){
                Toast t = Toast.makeText(context, text, duration);
                t.setGravity(Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL, 0, 0);
                t.show();
            }
        };

        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(task);
        }
    }
}
