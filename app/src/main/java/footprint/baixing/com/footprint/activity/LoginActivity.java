package footprint.baixing.com.footprint.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.lang.reflect.Type;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.api.ApiUser;
import footprint.baixing.com.footprint.data.ApiResult;
import footprint.baixing.com.footprint.data.Constant;
import footprint.baixing.com.footprint.data.User;
import footprint.baixing.com.footprint.util.FootToast;
import footprint.baixing.com.footprint.util.MD5Util;
import footprint.baixing.com.footprint.util.SystemUtils;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewById
    EditText etPassword;

    @ViewById
    EditText etUserName;

    @Click
    void tvLogin() {
        if(TextUtils.isEmpty(etUserName.getText().toString())) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(etPassword.getText().toString())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            String password = etPassword.getText().toString();
            String md5pswd = MD5Util.getMD5(password);
            callLoginApi(etUserName.getText().toString(), md5pswd);
        }
    }

    @Click
    void tvCancel() {
        finish();
    }

    @Background
    void callLoginApi(String username, String password) {
        String json = ApiUser.login(this, username, password);
        User user = null;
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<ApiResult<User>>() {
            }.getType();
            ApiResult<User> result = gson.fromJson(json, type);
            if(null != result) {
                user = result.getData();
            }
        } catch (Exception e) {

        }
        if(null != user) {
            SystemUtils.saveToLocal(this, Constant.FILE_USER, json);
            startActivity(new Intent(this, FootListActivity_.class));
        } else {
            FootToast.makeText(this, "登录失败，请检查网络后重新尝试", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public String getActionBarTitle() {
        return "登录";
    }
}
