package footprint.baixing.com.footprint.fragment;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.activity.LoginActivity_;
import footprint.baixing.com.footprint.api.ApiFootPrint;
import footprint.baixing.com.footprint.data.FootPrint;
import footprint.baixing.com.footprint.data.User;
import footprint.baixing.com.footprint.util.SystemUtils;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EFragment(R.layout.fragment_list)
public class MyFootListFragment extends FootListFragment {
    MyHeaderView headerView = null;

    @Override
    void initViews() {
        super.initViews();
        User user = SystemUtils.getUser(getActivity());
        if(null != user) {
            headerView.bind(user);
        }
    }

    @Override
    List<FootPrint> getMore(int from, int size) {
        token = SystemUtils.getToken(getActivity());
        if(TextUtils.isEmpty(token)) {
            startActivity(new Intent(getActivity(), LoginActivity_.class));
            getActivity().finish();
        }
        return ApiFootPrint.myFoots(getActivity(), token, from, size);
    }

    @Override
    public View getHeaderView(Context context) {
        headerView = MyFootListFragment_.MyHeaderView_.build(context);
        return headerView;
    }

    @EViewGroup(R.layout.layout_my_headerview)
    public static class MyHeaderView extends RelativeLayout {
        @ViewById
        ImageView ivhead;

        @ViewById
        TextView tvname;

        public MyHeaderView(Context context) {
            super(context);
        }

        public void bind(User user) {
            if(!TextUtils.isEmpty(user.getUsername())) {
                tvname.setText(user.getUsername());
            }
        }
    }
}
