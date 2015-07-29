package footprint.baixing.com.footprint.api;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangtracy on 15/7/25.
 */
public class ApiUser {

    private static final String USER_REGISTER = "/user/register.php";
    private static final String USER_LOGIN = "/user/login.php";

    static public String register(Context context, String nickName, String password, String bindId, String tag) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("BindUserId",bindId);
        params.put("userName", nickName);
        params.put("password",password);
        params.put("tag", tag);
        String json = BaseApi.postCommand(context, USER_REGISTER, params);
        return json;
    }

    static public String login(Context context, String username, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userName", username);
        params.put("password",password);
        String json = BaseApi.postCommand(context, USER_LOGIN, params);
        return json;
    }
}
