package footprint.baixing.com.footprint.fragment;

import android.content.Intent;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;

import java.util.ArrayList;
import java.util.List;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.activity.WebViewActivity_;
import footprint.baixing.com.footprint.adapter.FootListAdapter;
import footprint.baixing.com.footprint.data.Foot;
import footprint.baixing.com.footprint.data.FootPrint;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EFragment(R.layout.fragment_list)
public class FootListFragment extends BaseListFragment<FootPrint, FootListAdapter> {

    @Override
    List<FootPrint> getMore(int from, int size) {
//        String json = SystemUtils.loadFromLocal(getActivity(), Constant.FILE_USER);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ApiResult<User>>() {
//        }.getType();
//        User user = gson.fromJson(json, type);
//        return ApiFootPrint.discoverFoots(getActivity(), user.getToken(), from, size);
        List<FootPrint> footPrints = new ArrayList<>();
        for(int i=0; i<size; i++) {
            FootPrint footPrint = new FootPrint();
            Foot foot = new Foot();
            foot.setTitle("足迹" + from + i);
            if(i%2 == 0) {
                foot.setUrl("http://www.baixing.com");
            } else {
                foot.setUrl("http://www.baidu.com");
            }
            footPrint.setFoot(foot);
            footPrint.setId(i);
            footPrint.setFootId(i);
            footPrint.setInitiative(1);
            footPrints.add(footPrint);
        }
        return footPrints;
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
