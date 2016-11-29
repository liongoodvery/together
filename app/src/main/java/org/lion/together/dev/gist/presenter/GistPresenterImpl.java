package org.lion.together.dev.gist.presenter;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import org.lion.together.http.WebService;
import org.lion.together.dev.gist.model.Gist;
import org.lion.together.dev.gist.model.TokenVerifyResponce;
import org.lion.together.dev.gist.ui.GistView;
import org.lion.together.utils.CommonSubscriber;

import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
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
    public void fetchGists(String access_token) {
        CommonSubscriber<List<Gist>> subscriber = new CommonSubscriber<List<Gist>>() {

            @Override
            protected void doOnHttpException(HttpException httpException) {
                mGistView.onFetchFailed(httpException.code());
            }

            @Override
            public void onNext(List<Gist> gists) {
                mGistView.onFetchSuccess(gists);
            }
        };
        if (!TextUtils.isEmpty(access_token)) {
            WebService.gistApi()
                    .getGistsByToken(access_token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        } else {
            WebService.gistApi()
                    .getAllGists()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }

    @Override
    public void verifyToken(String token) {
        CommonSubscriber<TokenVerifyResponce> subscriber = new CommonSubscriber<TokenVerifyResponce>() {
            @Override
            public void onError(Throwable e) {
                Logger.i("onError " + e.getMessage());
                mGistView.onVerifyTokenResponce(false, token);
            }

            @Override
            public void onNext(TokenVerifyResponce responce) {
                if (TextUtils.isEmpty(responce.message)) {
                    mGistView.onVerifyTokenResponce(true, token);
                } else {
                    mGistView.onVerifyTokenResponce(false, token);
                }
            }
        };
        WebService.gistApi()
                .verifyToken(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
