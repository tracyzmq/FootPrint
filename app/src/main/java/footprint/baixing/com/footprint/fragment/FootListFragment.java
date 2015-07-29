package footprint.baixing.com.footprint.fragment;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;

import java.util.List;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.activity.LoginActivity_;
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
    String token = "";

    @Override
    List<FootPrint> getMore(int from, int size) {
        token = SystemUtils.getToken(getActivity());
        if(TextUtils.isEmpty(token)) {
            startActivity(new Intent(getActivity(), LoginActivity_.class));
            getActivity().finish();
        }
        return ApiFootPrint.discoverFoots(getActivity(), token, from, size);
    }

    @Override
    FootListAdapter createAdapter() {
        adapter = new FootListAdapter(getActivity());
        adapter.setData(listData);
        return adapter;
    }

    @Override
    public View getHeaderView(Context context) {
        return null;
    }

    @ItemClick
    void listview(FootPrint footPrint) {
        Intent intent = new Intent(getActivity(), WebViewActivity_.class);
        if(null != footPrint && null != footPrint.getFoot()) {
            intent.putExtra("title", footPrint.getFoot().getTitle());
            intent.putExtra("url", footPrint.getFoot().getUrl());
            intent.putExtra("footId",footPrint.getFoot().getId());
            intent.putExtra("time",footPrint.getTime());
            startActivity(intent);
        }
    }
}
