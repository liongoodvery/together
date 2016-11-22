package org.lion.together.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by lion on 2016-11-22
 */

public abstract class BaseRecyclerVH extends RecyclerView.ViewHolder {
    public View mItemView;

    public BaseRecyclerVH(View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, itemView);
    }
}
