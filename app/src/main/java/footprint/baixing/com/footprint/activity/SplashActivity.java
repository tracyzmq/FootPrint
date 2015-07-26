package footprint.baixing.com.footprint.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.util.SystemUtils;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!TextUtils.isEmpty(SystemUtils.getToken(this))) {
            startActivity(new Intent(this, FootListActivity_.class));
            finish();
        }
    }

    @Click
    void tvLogin() { startActivity(new Intent(this, LoginActivity_.class));}

    @Click
    void tvRegister() { startActivity(new Intent(this, RegisterActivity_.class));}
}
