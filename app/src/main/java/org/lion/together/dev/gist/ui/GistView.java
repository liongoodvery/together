package org.lion.together.dev.gist.ui;

import android.content.Context;

import org.lion.together.dev.gist.model.Gist;

import java.util.List;

/**
 * Created by lion on 2016-11-17
 */

public interface GistView {
    void onFetchSuccess(List<Gist> gists);

    void onFetchFailed();

    Context getContext();

    void onVerifyTokenResponce(boolean success,String token);
}
