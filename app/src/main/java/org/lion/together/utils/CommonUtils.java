package org.lion.together.utils;

import android.support.annotation.DimenRes;

import org.lion.together.App;

/**
 * Created by lion on 11/20/16.
 */

public class CommonUtils {
    public static int dp2px(@DimenRes int dimen){
        return App.sContext.getResources().getDimensionPixelSize(dimen);
    }
}
