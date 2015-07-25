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

    private static final String FOOTERLIST = "/footprint/discover";

    static public List<FootPrint> discoverFoots(Context context, String token, int from, int size) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("token",token);
            params.put("from",from+"");
            params.put("size",size+"");
            String json = BaseApi.postCommand(context, FOOTERLIST, params);
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
}
