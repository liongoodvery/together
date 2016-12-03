package org.lion.together.dev.gist.ui;

import android.content.Context;

import org.lion.together.base.BaseView;
import org.lion.together.dev.gist.model.Gist;
import org.lion.together.di.componets.GistComponent;

import java.util.List;

/**
 * Created by lion on 2016-11-17
 */

public interface GistView extends BaseView<GistComponent>{
    void onFetchSuccess(List<Gist> gists);

    void onFetchFailed(int code);

    Context getContext();

    void onVerifyTokenResponce(boolean success,String token);
}
