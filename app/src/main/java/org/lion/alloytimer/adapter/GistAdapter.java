package org.lion.alloytimer.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.lion.alloytimer.R;
import org.lion.alloytimer.model.Gist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lion on 11/17/16.
 */

public class GistAdapter extends RecyclerView.Adapter<GistHolder> {

    private List<Gist> mGists;

    public GistAdapter(List<Gist> gists) {
        mGists = new ArrayList<>();
        mGists.addAll(gists);
    }

    @Override
    public GistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GistHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gist, null));
    }

    @Override
    public void onBindViewHolder(GistHolder holder, int position) {

        Gist gist = mGists.get(position);
        if (TextUtils.isEmpty(gist.description)){
            holder.mTvGistDesc.setText("Default");
        }else {
            holder.mTvGistDesc.setText(gist.description);
        }
    }

    @Override
    public int getItemCount() {
        return mGists.size();
    }

    public void update(List<Gist> gists) {
        mGists.clear();
        mGists.addAll(gists);
        notifyDataSetChanged();
    }
}
