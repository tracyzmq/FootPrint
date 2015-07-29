package footprint.baixing.com.footprint.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import org.androidannotations.annotations.EActivity;

import footprint.baixing.com.footprint.R;

/**
 * Created by zhangtracy on 15/7/26.
 */
@EActivity(R.layout.activity_my_footlist)
public class MyFootsActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.blue));
        }
    }

    @Override
    public String getActionBarTitle() {
        return "我的足迹";
    }
}
