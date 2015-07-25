package footprint.baixing.com.footprint.activity;

import android.app.Activity;
import android.content.Intent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import footprint.baixing.com.footprint.R;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

    @Click
    void tvLogin() { startActivity(new Intent(this, LoginActivity_.class));}

    @Click
    void tvRegister() { startActivity(new Intent(this, RegisterActivity_.class));}
}
