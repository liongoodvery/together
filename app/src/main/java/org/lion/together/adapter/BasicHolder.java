package org.lion.together.adapter;

import android.view.View;

import butterknife.ButterKnife;


public abstract class BasicHolder<T> {
    protected View itemView;

    public BasicHolder() {
        itemView = createView();
        ButterKnife.bind(this, itemView);
        itemView.setTag(this);
    }

    public View getItemView() {
        return itemView;
    }

    public abstract void bindView(T t);

    public abstract View createView();
}