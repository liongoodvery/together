package org.lion.together.utils;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by lion on 11/17/16.
 */

public class CommonSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException){
            doOnHttpException((HttpException)e);
        }else {
            throw new RuntimeException(e);
        }
    }

    protected void doOnHttpException(HttpException httpException) {

    }

    @Override
    public void onNext(T t) {

    }
}
