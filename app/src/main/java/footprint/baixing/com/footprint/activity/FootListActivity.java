package footprint.baixing.com.footprint.activity;

import org.androidannotations.annotations.EActivity;

import footprint.baixing.com.footprint.R;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EActivity(R.layout.activity_footlist)
public class FootListActivity extends BaseActivity {

    @Override
    public String getActionBarTitle() {
        return "足迹";
    }
}
