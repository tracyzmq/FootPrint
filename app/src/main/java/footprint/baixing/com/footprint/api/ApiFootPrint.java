package footprint.baixing.com.footprint.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import footprint.baixing.com.footprint.data.ApiResult;
import footprint.baixing.com.footprint.data.FootPrint;

/**
 * Created by zhangtracy on 15/7/25.
 */
public class ApiFootPrint {

    private static final String FOOT_DISCOVER_LIST = "/footprint/discover.php";
    private static final String FOOT_LOGGER = "/footprint/logger.php";
    private static final String FOOT_MY_LIST = "/user/myfootprint.php";

    static public List<FootPrint> discoverFoots(Context context, String token, int from, int size) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("token",token);
            params.put("from",from+"");
            params.put("size",size+"");
            String json = BaseApi.postCommand(context, FOOT_DISCOVER_LIST, params);
            Gson gson = new Gson();
            Type type = new TypeToken<ApiResult<List<FootPrint>>>() {
            }.getType();
            ApiResult<List<FootPrint>> result = gson.fromJson(json, type);
            if(null != result) {
                return result.getData();
            }
        }catch (Exception e) {

        }
        return null;
    }

    static public boolean logFoot(Context context, int footId, String token, boolean initiative, int time, String tag) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("footId",footId+"");
            params.put("token",token);
            params.put("initiative",initiative+"");
            params.put("time",time+"");
            params.put("tag",tag);
            String json = BaseApi.postCommand(context, FOOT_LOGGER, params);
            Gson gson = new Gson();
            Type type = new TypeToken<ApiResult<Object>>() {
            }.getType();
            ApiResult<Object> result = gson.fromJson(json, type);
            if(null != result) {
                return result.getOk() == 1;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static public List<FootPrint> myFoots(Context context, String token, int from, int size) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("token",token);
            params.put("from",from+"");
            params.put("size",size+"");
            String json = BaseApi.postCommand(context, FOOT_MY_LIST, params);
            Gson gson = new Gson();
            Type type = new TypeToken<ApiResult<List<FootPrint>>>() {
            }.getType();
            ApiResult<List<FootPrint>> result = gson.fromJson(json, type);
            if(null != result) {
                return result.getData();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
