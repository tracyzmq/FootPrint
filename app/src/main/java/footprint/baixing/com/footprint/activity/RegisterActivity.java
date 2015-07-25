package footprint.baixing.com.footprint.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.api.ApiUser;
import footprint.baixing.com.footprint.data.Constant;
import footprint.baixing.com.footprint.data.User;
import footprint.baixing.com.footprint.util.FootToast;
import footprint.baixing.com.footprint.util.MD5Util;
import footprint.baixing.com.footprint.util.SystemUtils;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

    @ViewById
    EditText etUsername;

    @ViewById
    EditText etConfirmPassword;

    @ViewById
    EditText etPassword;

    @ViewById
    EditText etBindId;

    @Click
    void tvRegister() {
        if(TextUtils.isEmpty(etUsername.getText().toString())) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(etPassword.getText().toString())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        } else if(!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            Toast.makeText(this, "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
        } else {
            callRegisterApi();
        }
    }

    @Click
    void tvCancel() {
        finish();
    }

    @Background
    void callRegisterApi() {
        String password = etPassword.getText().toString();
        String md5pswd = MD5Util.getMD5(password);
        User result = ApiUser.register(this, etUsername.getText().toString(), md5pswd, etBindId.getText().toString(), "");
        if(null != result) {
            SystemUtils.saveToLocal(this, Constant.FILE_USER, result.toString());
            startActivity(new Intent(this, FootListActivity_.class));
        } else {
            FootToast.makeText(this, "注册失败，请检查网络后重新尝试", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public String getActionBarTitle() {
        return "注册";
    }
}
