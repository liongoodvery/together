package org.lion.together.di.modules;

import org.lion.together.presenter.GistPresenter;
import org.lion.together.presenter.GistPresenterImpl;
import org.lion.together.ui.GistView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lion on 2016-11-17
 */
@Module
public class GistModule {
    private GistView gistView;

    public GistModule(GistView gistView) {
        this.gistView = gistView;
    }

    @Provides
    public GistPresenter provideGistPresenter() {
        return new GistPresenterImpl(gistView);
    }
}
