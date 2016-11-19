package org.lion.together.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.lion.together.R;
import org.lion.together.adapter.GistAdapter;
import org.lion.together.di.componets.DaggerGistComponent;
import org.lion.together.di.modules.GistModule;
import org.lion.together.model.Gist;
import org.lion.together.presenter.GistPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by lion on 2016-11-16
 */

public class GistFragment extends BaseFragment implements GistView {
    @BindView(R.id.rv_gist)
    RecyclerView mRvGist;
    @BindView(R.id.srl_gist)
    SwipeRefreshLayout mSrlGist;


    @Inject
    GistPresenter mGistPresenter;
    @BindView(R.id.abl_gist)
    AppBarLayout mAblGist;
    private GistAdapter mGistAdapter;

    @Override
    protected void refreshData() {
        mSrlGist.setRefreshing(true);
        mGistPresenter.fetchGists();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_gist;
    }

    @Override
    public void setContentView() {
        mSrlGist.setOnRefreshListener(this::refreshData);
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void inject() {
        super.inject();
        DaggerGistComponent.builder()
                           .gistModule(new GistModule(this))
                           .build()
                           .inject(this);
    }

    @Override
    public void onFetchSuccess(List<Gist> gists) {
        if (mSrlGist.isRefreshing()) {
            mSrlGist.setRefreshing(false);
        }
        if (null == mGistAdapter) {
            mGistAdapter = new GistAdapter(gists);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
            mRvGist.setLayoutManager(manager);
            mRvGist.setAdapter(mGistAdapter);
        } else {
            mGistAdapter.update(gists);
        }
    }

    @Override
    public void onFetchFailed() {
        if (mSrlGist.isRefreshing()) {
            mSrlGist.setRefreshing(false);
        }
    }
}
