package footprint.baixing.com.footprint.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.data.Constant;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EFragment(R.layout.fragment_list)
public abstract class BaseListFragment<T extends Serializable, S extends BaseAdapter> extends Fragment{

    @ViewById
    PullToRefreshLayout ptr_layout;

    @ViewById
    ListView listview;

    @ColorRes(R.color.highlighted_text_material_light)
    int highlighted_text_material_light;

    List<T> listData = new ArrayList<T>();

    abstract List<T> getMore(int from, int size);

    S adapter;

    private boolean isCompleted = false;
    private int first;
    private int visCount;
    private int total;
    private int current = 0;


    private View mFooterView;
    private TextView tvFooter;
    private ProgressBar pbFooter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void initViews() {
        initFooterView(getActivity());
        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(new OnRefreshListener() {
                    @Override
                    public void onRefreshStarted(View view) {
                        processRefresh();
                    }
                })
                .setup(ptr_layout);

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE && first + visCount >= total && total != 0) {
                    if (!isCompleted) {
                        showGetMoreRefreshing();
                        processGetMore();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                first = firstVisibleItem;
                visCount = visibleItemCount;
                total = totalItemCount;
            }
        });
        ptr_layout.setRefreshing(true);
    }

    @Background
    public void processGetMore() {
        if (isCompleted) {
            return;
        }

        List<T> list = getMore(current, Constant.PER_PAGE);
        if(null != list && list.size() > 0 && null != adapter) {
            listData.addAll(list);
            if (list.size() == Constant.PER_PAGE) {
                current += Constant.PER_PAGE;
            } else {
                showFooterOnComplete();
            }
            adapter.notifyDataSetChanged();
        } else if(current == 0 && list.size() == 0) {
            View view = new View(getActivity());
            view.setBackgroundColor(highlighted_text_material_light);
            listview.setEmptyView(view);
        }
        else {
            showFooterOnComplete();
        }
        ptr_layout.setRefreshing(false);
    }

    public void processRefresh() {
        listData.clear();
        if(adapter != null) {//做个防护，以防outofindex的crash
            adapter.notifyDataSetChanged();
        }
        current = 0;
        isCompleted = false;
        pbFooter.setVisibility(View.GONE);
        processGetMore();
    }

    public void initFooterView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mFooterView = inflater.inflate(R.layout.pull_to_refresh_footer, listview, false);
        tvFooter = (TextView)mFooterView.findViewById(R.id.pulldown_to_getmore);
        pbFooter = (ProgressBar)mFooterView.findViewById(R.id.loadingProgress);
        listview.addFooterView(mFooterView);
        mFooterView.setVisibility(View.GONE);
    }

    public void showFooterOnComplete () {
        isCompleted = true;
        if(null != mFooterView && null != tvFooter && null !=  pbFooter) {
            mFooterView.setVisibility(View.VISIBLE);
            tvFooter.setText("已经到底了哦～～");
            pbFooter.setVisibility(View.INVISIBLE);
        }
    }

    public void showGetMoreRefreshing() {
        if(null != mFooterView && null != tvFooter && null !=  pbFooter) {
            mFooterView.setVisibility(View.VISIBLE);
            tvFooter.setText("正在加载中，请稍后...");
            pbFooter.setVisibility(View.VISIBLE);
        }
    }
}
