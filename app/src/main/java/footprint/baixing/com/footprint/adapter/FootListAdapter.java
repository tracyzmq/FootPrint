package footprint.baixing.com.footprint.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import footprint.baixing.com.footprint.R;
import footprint.baixing.com.footprint.data.FootPrint;
import footprint.baixing.com.footprint.util.SystemUtils;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EBean
public class FootListAdapter extends BaseListAdatper<FootPrint> {

    public FootListAdapter(Context context) {
        super(context, null);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FootPrintItemView footPrintItemView;
        if (convertView == null) {
            footPrintItemView = FootListAdapter_.FootPrintItemView_.build(context);
        } else {
            footPrintItemView = (FootPrintItemView) convertView;
        }

        footPrintItemView.bind(getItem(position));

        return footPrintItemView;
    }

    @EViewGroup(R.layout.item_footprint_list)
    public static class FootPrintItemView extends LinearLayout {
        @ColorRes(R.color.yelloe_white)
        int yelloe_white;

        @ViewById
        TextView timestamp;

        @ViewById
        ImageView avartar;

        @ViewById
        TextView title;

        @ViewById
        TextView subTitle;

        @ViewById
        ImageView ivLike;

        @ViewById
        TextView tvLike;

        public FootPrintItemView(Context context) {
            super(context);
        }

        public void bind(FootPrint foot) {
            avartar.setBackgroundColor(yelloe_white);
            if(null != foot && null != foot.getFoot()) {
                if(foot.getTimestamp() != 0) {
                    timestamp.setVisibility(View.VISIBLE);
                    timestamp.setText(SystemUtils.getFormatTime(foot.getTimestamp()));
                } else {
                    timestamp.setVisibility(View.GONE);
                }
                if(!TextUtils.isEmpty(foot.getFoot().getTitle())) {
                    title.setVisibility(View.VISIBLE);
                    title.setText(foot.getFoot().getTitle());
                } else {
                    title.setVisibility(View.GONE);
                }

                if(!TextUtils.isEmpty(foot.getFoot().getSubTitle())) {
                    subTitle.setVisibility(View.VISIBLE);
                    subTitle.setText(foot.getFoot().getSubTitle());
                } else {
                    subTitle.setVisibility(View.GONE);
                }

//                ivLike.setVisibility(View.VISIBLE);
//                tvLike.setText(foot.getFoot().getLike());
            }
        }
    }
}
