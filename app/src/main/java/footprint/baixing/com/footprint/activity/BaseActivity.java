package footprint.baixing.com.footprint.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import footprint.baixing.com.footprint.R;

/**
 * Created by zhangtracy on 15/7/26.
 */
public abstract class BaseActivity extends AppCompatActivity {

    abstract public String getActionBarTitle();

    public void initActionBar(ActionBar actionBar) {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_dark_green));
            actionBar.setTitle(getActionBarTitle());
            initActionBar(actionBar);
        }
    }
}
