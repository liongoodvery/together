package org.lion.together.adapter;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.lion.together.R;
import org.lion.together.widget.TagView;

import butterknife.BindView;

/**
 * Created by lion on 11/17/16.
 */

public class GistHolder extends BaseRecyclerVH {

    @BindView(R.id.sdv_avatar)
    SimpleDraweeView mSdvAvatar;
    @BindView(R.id.tv_gist_desc)
    TextView mTvGistDesc;
    @BindView(R.id.tv_gist_file)
    TextView mTvGistFile;
    @BindView(R.id.tv_gist_time)
    TextView mTvGistTime;
    @BindView(R.id.tag_gist_lang)
    TagView mTagGistLang;

    public GistHolder(View itemView) {
        super(itemView);
    }
}
