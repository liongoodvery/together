package org.lion.alloytimer.di.modules;

import org.lion.alloytimer.presenter.GistPresenter;
import org.lion.alloytimer.presenter.GistPresenterImpl;
import org.lion.alloytimer.ui.GistView;

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
