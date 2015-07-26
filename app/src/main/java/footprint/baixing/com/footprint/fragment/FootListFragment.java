package footprint.baixing.com.footprint.fragment;

import android.content.Intent;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;

import java.util.List;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.activity.WebViewActivity_;
import footprint.baixing.com.footprint.adapter.FootListAdapter;
import footprint.baixing.com.footprint.api.ApiFootPrint;
import footprint.baixing.com.footprint.data.FootPrint;
import footprint.baixing.com.footprint.util.SystemUtils;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EFragment(R.layout.fragment_list)
public class FootListFragment extends BaseListFragment<FootPrint, FootListAdapter> {

    @Override
    List<FootPrint> getMore(int from, int size) {
        String token = SystemUtils.getToken(getActivity());
        return ApiFootPrint.discoverFoots(getActivity(), token, from, size);
    }

    @Override
    FootListAdapter createAdapter() {
        adapter = new FootListAdapter(getActivity());
        adapter.setData(listData);
        return adapter;
    }

    @ItemClick
    void listview(FootPrint footPrint) {
        Intent intent = new Intent(getActivity(), WebViewActivity_.class);
        if(null != footPrint && null != footPrint.getFoot()) {
            intent.putExtra("title", footPrint.getFoot().getTitle());
            intent.putExtra("url", footPrint.getFoot().getUrl());
            startActivity(intent);
        }
    }
}
