package kr.co.saweb.enhance.android.ui.list;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by OKS on 2014-10-18.
 */
public abstract class BaseListItemView<T> extends LinearLayout {
    public BaseListItemView(Context context) {
        super(context);
    }
    public abstract void bind(T o);
}