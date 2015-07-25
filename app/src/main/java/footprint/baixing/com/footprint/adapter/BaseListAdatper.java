package footprint.baixing.com.footprint.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtracy on 15/7/25.
 */
@EBean
abstract public class BaseListAdatper<T> extends BaseAdapter {
    protected Context context;
    protected LayoutInflater layoutInflater;
    protected List<T> lists;

    public BaseListAdatper(Context context, List<T> listItem) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.lists = listItem;
    }

    public void setData(List<T> listItem) {
        this.lists = listItem;
    }

    public void addData(T item) {
        if(null == lists) {
            lists = new ArrayList<T>();
        }
        lists.add(item);
    }

    public void removeData(T item) {
        if(null == lists) {
            lists = new ArrayList<T>();
        }
        lists.remove(item);
    }

    public void addData(int position, List<T> list) {
        if(null == list) {
            return;
        }
        if(null == lists) {
            lists = new ArrayList<T>();
        }
        lists.addAll(position, list);
    }

    @Override
    public int getCount() {
        if(null != lists) {
            return lists.size();
        }
        return 0;
    }

    @Override
    public T getItem(int paramInt) {
        if(null != lists) {
            return lists.get(paramInt);
        }
        return null;
    }

    @Override
    public long getItemId(int paramInt) {
        return paramInt;
    }

}
