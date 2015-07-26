package footprint.baixing.com.footprint.activity;

import org.androidannotations.annotations.EActivity;

import footprint.baixing.com.footprint.R;

/**
 * Created by zhangtracy on 15/7/26.
 */
@EActivity(R.layout.activity_my_footlist)
public class MyFootsActivity extends BaseActivity{
    @Override
    public String getActionBarTitle() {
        return "我的足迹";
    }
}
