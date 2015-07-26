package footprint.baixing.com.footprint.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.EActivity;

import footprint.baixing.com.footprint.R;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EActivity(R.layout.activity_footlist)
public class FootListActivity extends BaseActivity {

    @Override
    public String getActionBarTitle() {
        return "推荐";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_me:
                startActivity(new Intent(this, MyFootsActivity_.class));
                break;
            case R.id.menu_logout:
                startActivity(new Intent(this, LoginActivity_.class));
                finish();
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

}
