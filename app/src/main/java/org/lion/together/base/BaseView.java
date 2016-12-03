package org.lion.together.base;

import android.content.Context;

/**
 * Created by lion on 2016-12-03
 */

public interface BaseView<T> {
    T getComponent();
    Context getViewContext();
}
