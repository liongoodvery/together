package org.lion.alloytimer.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.lion.alloytimer.R;
import org.lion.alloytimer.model.Gist;
import org.lion.alloytimer.utils.AlloyUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lion on 11/17/16.
 */

public class GistAdapter extends RecyclerView.Adapter<GistHolder> {

    private final List<Gist> mGists;


    public GistAdapter(Collection<Gist> gists) {
        super();
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
        if (TextUtils.isEmpty(gist.description)) {
            holder.mTvGistDesc.setText("Default");
        } else {
            holder.mTvGistDesc.setText(gist.description);
        }
        if (null != gist.owner) {
            AlloyUtils.setImageUrl(holder.mSdvAvatar, gist.owner.avatar_url);
        }
        holder.mTvGistTime.setText(gist.created_at);
    }

    @Override
    public int getItemCount() {
        return mGists.size();
    }

    public void update(Collection<Gist> gists) {
        mGists.clear();
        mGists.addAll(gists);
        notifyDataSetChanged();
    }
}
