package org.lion.together.utils;

import com.orhanobut.logger.Logger;

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
        Logger.i(e.getMessage());
    }

    @Override
    public void onNext(T t) {

    }
}
