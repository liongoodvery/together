package org.lion.together.presenter;

import com.orhanobut.logger.Logger;

import org.lion.together.http.WebService;
import org.lion.together.model.Gist;
import org.lion.together.ui.GistView;
import org.lion.together.utils.CommonSubscriber;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lion on 2016-11-17
 */

public class GistPresenterImpl implements GistPresenter {
    private GistView mGistView;

    public GistPresenterImpl(GistView gistView) {
        mGistView = gistView;
    }

    @Override
    public void fetchGists() {
        WebService.gistApi()
                .getAllGists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonSubscriber<List<Gist>>() {
                    @Override
                    public void onError(Throwable e) {
                        Logger.i("onError" + "");
                        mGistView.onFetchFailed();
                    }

                    @Override
                    public void onNext(List<Gist> gists) {
                        mGistView.onFetchSuccess(gists);
                    }
                });
    }
}
