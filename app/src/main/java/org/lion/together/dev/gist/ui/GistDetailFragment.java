package org.lion.together.dev.gist.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.lion.together.R;
import org.lion.together.dev.gist.adapter.FileAdapter;
import org.lion.together.base.BaseFragment;
import org.lion.together.dev.gist.GistC;
import org.lion.together.dev.gist.model.Gist;
import org.lion.together.dev.gist.model.GistFile;
import org.lion.together.dev.gist.model.Owner;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lion on 11/24/16.
 */

public class GistDetailFragment extends BaseFragment implements AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.sdv_avatar)
    SimpleDraweeView mSdvAvatar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.abl_gist)
    AppBarLayout mAblGist;
    @BindView(R.id.rv_files)
    RecyclerView mRvFiles;
    @BindView(R.id.iv_back)

    ImageView mIvBack;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_gist_time)
    TextView mTvGistTime;
    private Gist mGist;
    private Owner mOwner;
    private Map<String, GistFile> mFiles;
    private boolean isHideToolbarView = false;

    @Override
    protected void refreshData() {

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_gist_detail;
    }

    @Override
    public void setContentView() {
        if (mOwner != null) {
            if (!TextUtils.isEmpty(mOwner.avatar_url)) {
                mSdvAvatar.setImageURI(mOwner.avatar_url);
            }
        }
        mAblGist.addOnOffsetChangedListener(this);
        mCollapsingToolbar.setScrimAnimationDuration(300);
        String description = mGist.description;
        if (TextUtils.isEmpty(description)) {
            mTvDescription.setText("No Description");
        } else {
            mTvDescription.setText(description);
        }
        mTvGistTime.setText(mGist.updated_at);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvFiles.setLayoutManager(layoutManager);
        mRvFiles.setAdapter(new FileAdapter( mFiles.values()));
    }

    @Override
    protected void initArguments(Bundle arguments) {
        mGist = arguments.getParcelable(GistC.GIST_SINGLE_GIST);
        if (null != mGist) {
            mOwner = mGist.owner;
            mFiles = mGist.files;
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;
        mToolbarTitle.setAlpha(percentage);
        mIvBack.setAlpha((float) (0.5 + percentage / 2));
    }

    @Override
    protected void setToolBar() {
    }


    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }


}
