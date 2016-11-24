package org.lion.together.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lion on 2016-11-22
 */

public abstract class BaseRecyclerAdapter<VH extends BaseRecyclerVH, T> extends RecyclerView.Adapter<VH> {
    protected List<T> mDatas;

    public BaseRecyclerAdapter(Collection<T> datas) {
        mDatas = new ArrayList<T>();
        if (datas != null) {
            mDatas.addAll(datas);
        }
    }

    public void update(Collection<T> datas) {
        mDatas.clear();
        if (datas != null) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public T getDataAt(int position) {
        return mDatas.get(position);
    }
}
