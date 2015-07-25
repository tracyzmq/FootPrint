package footprint.baixing.com.footprint.activity;

import android.support.v7.app.ActionBar;
import android.webkit.WebView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import footprint.baixing.com.footprint.R;

/**
 * Created by zhangtracy on 15/7/26.
 */
@EActivity(R.layout.activity_webview)
public class WebViewActivity extends BaseActivity {
    @Extra
    String title;

    @Extra
    String url;

    @ViewById
    WebView wv;

    @Override
    public String getActionBarTitle() {
        return title;
    }

    @AfterViews
    void initViews() {
        wv.loadUrl(url);
    }

    @Override
    public void initActionBar(ActionBar actionBar) {
        //TODO:
        actionBar.getCustomView();
    }
}
