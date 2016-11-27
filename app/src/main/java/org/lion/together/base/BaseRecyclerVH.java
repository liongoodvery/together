package org.lion.together.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public BaseRecyclerVH(Context context,int layoutRes) {
        this(LayoutInflater.from(context).inflate(layoutRes,null));
    }

    public BaseRecyclerVH(ViewGroup parent, int layoutRes) {
        this(LayoutInflater.from(parent.getContext()).inflate(layoutRes,parent,false));
    }

}
