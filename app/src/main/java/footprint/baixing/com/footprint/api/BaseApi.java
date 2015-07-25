package footprint.baixing.com.footprint.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class BaseApi {
    public static String hosturl = "http://120.25.151.196/footprint";
    public static String COOKIES = "cookies";
    public static boolean bShowlog = true;

    public static String getHost() {return hosturl;}

    public static void setHost(String url) {hosturl = url;}

    private static String json;


    public static String postCommand(final Context context, String apiname, final Map<String, String> params) {
        RequestQueue mQueue = Volley.newRequestQueue(context);
        RequestFuture<String> future = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getHost() + apiname,  future, future) {
            @Override
            protected Map<String, String> getParams() {
                if(bShowlog) {
                    Log.e("footprint",params.toString());
                }
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                try {

                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    if(!TextUtils.isEmpty(rawCookies)) {
                        SharedPreferences sp = context.getSharedPreferences(COOKIES, 0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(COOKIES, rawCookies);
                        editor.commit();
                    }
                    String dataString = new String(response.data, "UTF-8");
                    if(bShowlog) {
                        Log.e("footprint",(rawCookies==null)?"":rawCookies);
                        Log.e("footprint",(dataString==null)?"":dataString);
                    }

                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }

            public Map<String, String> getHeaders() {
                HashMap localHashMap = new HashMap();
                String cookies = context.getSharedPreferences(COOKIES, 0).getString(COOKIES,"");
                localHashMap.put("Cookie", cookies);
                if(bShowlog) {
                    Log.e("footprint", localHashMap.toString());
                }
                return localHashMap;
            }
        };
        String result = "";
        if(bShowlog) {
            Log.e("footprint",stringRequest.toString());
        }
        mQueue.add(stringRequest);
        try {
            result = future.get(); // this will block
        } catch (InterruptedException e) {
            e.printStackTrace();
            // exception handling

        } catch (ExecutionException e) {
            e.printStackTrace();
            // exception handling
        }
        return result;
    }

    public static String getCommand(final Context context, String apiname, final Map<String,String> params) {

        RequestQueue mQueue = Volley.newRequestQueue(context);
        RequestFuture<String> future = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getHost() + apiname + "?" + getFormatParams(params),  future, future) {


            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    if(!TextUtils.isEmpty(rawCookies)) {
                        SharedPreferences sp = context.getSharedPreferences(COOKIES, 0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(COOKIES, rawCookies);
                        editor.commit();
                    }
                    String dataString = new String(response.data, "UTF-8");
                    if(bShowlog) {
                        Log.e("footprint",rawCookies==null?"":rawCookies);
                        Log.e("footprint",dataString==null?"":dataString);
                    }
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }

            public Map<String, String> getHeaders() {
                HashMap localHashMap = new HashMap();
                String cookies = context.getSharedPreferences(COOKIES, 0).getString(COOKIES,"");
                localHashMap.put("Cookie", cookies);
                if(bShowlog) {
                    Log.e("footprint", localHashMap.toString());
                }
                return localHashMap;
            }


        };
        String result = "";
        if(bShowlog) {
            Log.e("footprint",stringRequest.toString());
        }
        mQueue.add(stringRequest);
        try {
            result = future.get(); // this will block
        } catch (InterruptedException e) {
            // exception handling
        } catch (ExecutionException e) {
            // exception handling
        }
        return result;
    }

    public static String getFormatParams(Map<String,String> parameters) {

        Map<String, String> params = parameters;//.getParams();

        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        boolean hasParam = false;
        List<String> keyList = new ArrayList<String>(params.keySet());
        Collections.sort(keyList);

        for (String key : keyList) {
            String name = key;
            String value = params.get(key);
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(value)) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                String encodedName = "";
                String encodedValue = "";//URLEncoder.encode(value);
                try {
                    encodedName = URLEncoder.encode(name);
                    encodedValue = URLEncoder.encode(value);//URLEncoder.encode(value, charset);//
                } catch (Throwable e) {
                }
                query.append(encodedName).append("=").append(encodedValue);
            }
        }

        return query.toString();
    }

}
