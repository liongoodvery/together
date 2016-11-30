package org.lion.together.dev.gist.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.lion.together.R;
import org.lion.together.base.BaseRecyclerAdapter;
import org.lion.together.dev.gist.model.Gist;
import org.lion.together.utils.Utils;

import java.util.Collection;

import rx.Observable;

/**
 * Created by lion on 11/17/16.
 */

public class GistAdapter extends BaseRecyclerAdapter<GistHolder, Gist> {
    public GistAdapter(Collection<Gist> gists) {
        super(gists);
    }

    @Override
    public GistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GistHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gist, null));
    }

    @Override
    public void onBindViewHolder(GistHolder holder, int position) {

        Gist gist = mDatas.get(position);
        if (TextUtils.isEmpty(gist.description)) {
            holder.mTvGistDesc.setText("Default");
        } else {
            holder.mTvGistDesc.setText(gist.description);
        }
        if (null != gist.owner) {
            Utils.setImageUrl(holder.mSdvAvatar, gist.owner.avatar_url);
        } else {
            holder.mSdvAvatar.setImageResource(R.drawable.ic_highlight);
        }
        if (null != gist.files) {
            StringBuilder sb = new StringBuilder();
            Observable.from(gist.files.keySet())
                    .reduce((s, s2) -> new StringBuilder().append(s).append(" ").append(s2).toString())
                    .doOnNext(holder.mTvGistFile::setText)
                    .subscribe();
            Observable.from(gist.files.values())
                    .first()
                    .map(gistFile -> TextUtils.isEmpty(gistFile.language) ? "DEFAULT" : gistFile.language)
            .doOnNext(holder.mTagGistLang::setText)
                    .subscribe();
        }
        holder.mTvGistTime.setText(gist.created_at);
    }
}
