package org.lion.alloytimer.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;

import org.lion.alloytimer.R;
import org.lion.alloytimer.adapter.GistAdapter;
import org.lion.alloytimer.http.WebService;
import org.lion.alloytimer.model.Gist;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lion on 2016-11-16
 */

public class GistFragment extends BaseFragment {
    @BindView(R.id.rv_gist)
    RecyclerView mRvGist;
    private GistAdapter mGistAdapter;
    @Override
    protected void refreshData() {
        WebService.gistApi()
                .getAllGists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Gist>>() {
                    @Override
                    public void onCompleted() {
                        Logger.i("onCompleted" + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i("onError" + "");
                    }

                    @Override
                    public void onNext(List<Gist> gists) {
                        Logger.i("onNext" + "");
                        if (mGistAdapter == null){
                            mGistAdapter = new GistAdapter(gists);
                            RecyclerView.LayoutManager manager =new LinearLayoutManager(getActivity());
                            mRvGist.setLayoutManager(manager);
                            mRvGist.setAdapter(mGistAdapter);
                        }else {
                            mGistAdapter.update(gists);
                        }

                    }
                });
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
}
