package kr.co.saweb.enhance.android.annotations.ui.list;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import kr.co.saweb.enhance.android.annotations.util.AnnotationUtils;

/**
 * Created by OKS on 2014-10-18.
 */
public abstract class BaseListAdapter<T extends BaseListItemView> extends BaseAdapter {
    public abstract Context getContext();

    private List itemList = new ArrayList();

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        T v = null;

        try {
            if (convertView == null) {
                Class<T> t = AnnotationUtils.getAnnotationClass(getClass());
                Constructor constructor = t.getConstructor(Context.class);
                T result = (T) constructor.newInstance(getContext());
                result.getClass().getMethod("onFinishInflate").invoke(result);
                v = result;
            } else {
                v = (T) convertView;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        v.bind(getItem(i));

        return v;
    }

    public void add(Object item) {
        itemList.add(item);
    }

    public void setItemList(List itemList) {
        this.itemList = itemList;
    }
}
