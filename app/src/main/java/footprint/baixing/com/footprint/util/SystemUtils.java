package footprint.baixing.com.footprint.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhangtracy on 15/7/25.
 */
public class SystemUtils {

    public static void saveToLocal(Context context, String filename, String json) {
        context.getSharedPreferences(filename, Context.MODE_PRIVATE).edit().putString("data", json).commit();
    }

    public static String loadFromLocal(Context context, String filename) {
        SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return preferences.getString(filename, "");
    }
}
