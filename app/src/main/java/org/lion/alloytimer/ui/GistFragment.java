package org.lion.alloytimer.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;

import org.lion.alloytimer.R;
import org.lion.alloytimer.adapter.GistAdapter;
import org.lion.alloytimer.di.componets.DaggerGistComponent;
import org.lion.alloytimer.di.modules.GistModule;
import org.lion.alloytimer.model.Gist;
import org.lion.alloytimer.presenter.GistPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by lion on 2016-11-16
 */

public class GistFragment extends BaseFragment implements GistView {
    @BindView(R.id.rv_gist)
    RecyclerView mRvGist;
    @Inject
    GistPresenter mGistPresenter;
    private GistAdapter mGistAdapter;

    @Override
    protected void refreshData() {
        mGistPresenter.fetchGists();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_gist;
    }

    @Override
    public void setContentView() {
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void inject() {
        super.inject();
        DaggerGistComponent.builder()
                .gistModule(new GistModule(this)).build().inject(this);
    }

    @Override
    public void onFetchSuccess(List<Gist> gists) {
        Logger.i("onNext" + "");
        if (mGistAdapter == null) {
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
        Logger.i("onError" + "");
    }
}
