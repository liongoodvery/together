package org.lion.together.di.modules;

import android.app.Dialog;

import com.blankj.utilcode.utils.SPUtils;

import org.lion.together.App;
import org.lion.together.R;
import org.lion.together.dev.gist.GistC;
import org.lion.together.dev.gist.presenter.GistPresenter;
import org.lion.together.dev.gist.presenter.GistPresenterImpl;
import org.lion.together.dev.gist.ui.GistView;
import org.lion.together.widget.CustomDialog;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lion on 2016-11-17
 */
@Module
public class GistModule {
    private final GistView mGistView;

    public GistModule(GistView gistView) {
        this.mGistView = gistView;
    }

    @Provides
    public GistPresenter provideGistPresenter() {
        return new GistPresenterImpl(mGistView);
    }

    @Provides
    public SPUtils provideSputils(){
        return new SPUtils(App.sContext, GistC.GIST_SP_NAME);
    }

    @Provides
    public Dialog provideDialog(){
        return new CustomDialog(mGistView.getContext(),R.layout.dialog_gist_token);
    }

}
