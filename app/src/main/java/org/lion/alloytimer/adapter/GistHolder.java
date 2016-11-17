package org.lion.alloytimer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.lion.alloytimer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lion on 11/17/16.
 */

public class GistHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_gist_desc)
    TextView mTvGistDesc;

    public GistHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
