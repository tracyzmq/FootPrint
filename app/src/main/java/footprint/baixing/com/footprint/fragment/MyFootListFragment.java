package footprint.baixing.com.footprint.fragment;

import android.text.TextUtils;

import org.androidannotations.annotations.EFragment;

import java.util.List;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.api.ApiFootPrint;
import footprint.baixing.com.footprint.data.FootPrint;
import footprint.baixing.com.footprint.util.SystemUtils;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EFragment(R.layout.fragment_list)
public class MyFootListFragment extends FootListFragment {

    @Override
    List<FootPrint> getMore(int from, int size) {
        if(TextUtils.isEmpty(token)) {
            token = SystemUtils.getToken(getActivity());
        }
        return ApiFootPrint.myFoots(getActivity(), token, from, size);
    }
}
