package footprint.baixing.com.footprint.activity;

import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.api.ApiFootPrint;
import footprint.baixing.com.footprint.util.SystemUtils;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by zhangtracy on 15/7/26.
 */
@EActivity(R.layout.activity_webview)
public class WebViewActivity extends BaseActivity implements OnRefreshListener{
    @Extra
    int footId;

    @Extra
    String title;

    @Extra
    String url;

    @ViewById
    WebView wv;

    @ViewById
    PullToRefreshLayout ptr_layout;

    private long enterTime = 0;

    @Override
    public String getActionBarTitle() {
        return title;
    }

    @AfterViews
    public void initView() {
        ActionBarPullToRefresh.from(this)
                .allChildrenArePullable()
                .listener(this)
                .setup(ptr_layout);

        ptr_layout.setRefreshing(true);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {  //重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) { // 重写此方法可以让webview处理https请求
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ptr_layout.setRefreshing(false);
            }
        });
        wv.loadUrl(url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        enterTime = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停留超过1分钟
        long interval = (System.currentTimeMillis() - enterTime) / 1000;
        if(interval >= 60L) {
            callLogApi(interval);
        }
    }

    @Override
    public void onRefreshStarted(View view) {
        wv.loadUrl(url);
    }

    @Background
    void callLogApi(long interval) {
        String token = SystemUtils.getToken(this);
        ApiFootPrint.logFoot(this, footId, token, false, interval, "");
    }
}
