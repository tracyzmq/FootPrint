package footprint.baixing.com.footprint.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Locale;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.data.ApiResult;
import footprint.baixing.com.footprint.data.Constant;
import footprint.baixing.com.footprint.data.User;

/**
 * Created by zhangtracy on 15/7/25.
 */
public class SystemUtils {

    public static void saveToLocal(Context context, String filename, String json) {
        context.getSharedPreferences(filename, Context.MODE_PRIVATE).edit().putString("data", json).commit();
    }

    public static String loadFromLocal(Context context, String filename) {
        SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return preferences.getString("data", "");
    }

    public static String getToken(Context context) {
        String json = SystemUtils.loadFromLocal(context, Constant.FILE_USER);
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<ApiResult<User>>() {
            }.getType();
            ApiResult<User> user = gson.fromJson(json, type);
            if(null != user && null != user.getData() && !TextUtils.isEmpty(user.getData().getToken())) {
                return user.getData().getToken();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static final long FULL_MINITE = 60L;

    public static final long FULL_HOUR = 60L * 60L;

    public static final long FULL_DAY = 60L * 60L * 24L;

    public static final long FULL_MONTH = 60L * 60L * 24L * 30L;

    public static final long FULL_YEAR = 60L * 60L * 24L * 30L * 12L;

    public static final String timeTillNow(long time, Context context){
        long now = System.currentTimeMillis() / 1000;
        long timeInterval = now - time;
        Resources res = context.getResources();
        String agoLabel = res.getString(R.string.common_time_ago);
        if (timeInterval <= 10)
        {
            return res.getString(R.string.common_time_justnow);
        }
        else if (timeInterval > 10 && timeInterval < FULL_MINITE)
        {
            return timeInterval + res.getString(R.string.common_time_second) + agoLabel;
        }
        else if (timeInterval > FULL_MINITE && timeInterval < FULL_HOUR)
        {
            return (timeInterval / FULL_MINITE) + res.getString(R.string.common_time_minute) + agoLabel;
        }
        else if (timeInterval >= FULL_HOUR && timeInterval < FULL_DAY)
        {
            return (timeInterval / FULL_HOUR) + res.getString(R.string.common_time_hour) + agoLabel;
        }
        else if (timeInterval >= FULL_DAY && timeInterval < FULL_MONTH)
        {
            return (timeInterval / FULL_DAY) + res.getString(R.string.common_time_day) + agoLabel;
        }
        else if (timeInterval >= FULL_MONTH && timeInterval < FULL_YEAR)
        {
            return (timeInterval / FULL_MONTH) + res.getString(R.string.common_time_month) + agoLabel;
        }
        else if (timeInterval >= FULL_YEAR && timeInterval < FULL_YEAR * 10)
        {
            return (timeInterval / FULL_YEAR) + res.getString(R.string.common_time_one_year_ago) + agoLabel;
        }
        else
        {
            return res.getString(R.string.common_time_unknow);
        }
    }

    public static final String getFormatTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
        sdf.applyPattern("MM月dd日 HH:MM");
        return sdf.format(timestamp * 1000);
    }
}
