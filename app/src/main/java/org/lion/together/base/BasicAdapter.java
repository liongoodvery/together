package org.lion.together.base;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView Adapter
 * @param <T> 每个条目对应的bean的类型
 */
public abstract class BasicAdapter<T> extends BaseAdapter {
    private List<T> mDatas;


    public BasicAdapter(@NonNull List<T> datas) {
        //mDatas 总会被创建，除非使用反射，否则mDatas不会为空
        mDatas = new ArrayList<>();
        if (datas == null)
            return;
        mDatas.addAll(datas);
    }

    public void setDatas(List<T> datas) {
        //如果传入的集合为null,则清空集合，ListView 将不会显示任何条目
        if (null == datas) {
            mDatas.clear();
        } else {
            mDatas.clear();
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void addData(T data) {
        if (null == data) {
            return;
        } else {
            mDatas.add(data);
        }
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BasicHolder holder = null;
        if (convertView == null) {
            //创建ViewHolder时将自动为其设置tag
            holder = createHolder(getItemViewType(position));
        } else {
            holder = (BasicHolder) convertView.getTag();
        }
        holder.bindView(getItem(position));
        return holder.getItemView();
    }

    protected abstract BasicHolder createHolder(int viewType);

}
