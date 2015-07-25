package footprint.baixing.com.footprint.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import footprint.baixing.com.footprint.data.ApiResult;
import footprint.baixing.com.footprint.data.User;

/**
 * Created by zhangtracy on 15/7/25.
 */
public class ApiUser {

    private static final String USER_REGISTER = "/user/register.php";
    private static final String USER_LOGIN = "/user/login";

    static public User register(Context context, String nickName, String password, String bindId, String tag) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("BindUserId",bindId);
            params.put("userName", nickName);
            params.put("password",password);
            params.put("tag", tag);
            String json = BaseApi.postCommand(context, USER_REGISTER, params);
            Gson gson = new Gson();
            Type type = new TypeToken<ApiResult<User>>() {
            }.getType();
            ApiResult<User> result = gson.fromJson(json, type);
            if(null != result) {
                return result.getData();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public User login(Context context, String username, String password) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("userName", username);
            params.put("password",password);
            String json = BaseApi.postCommand(context, USER_LOGIN, params);
            Gson gson = new Gson();
            Type type = new TypeToken<ApiResult<User>>() {
            }.getType();
            ApiResult<User> result = gson.fromJson(json, type);
            if(null != result) {
                return result.getData();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
