package org.lion.alloytimer.di.componets;

import org.lion.alloytimer.di.modules.GistModule;
import org.lion.alloytimer.ui.GistFragment;

import dagger.Component;

/**
 * Created by lion on 2016-11-17
 */
@Component(dependencies = {GistModule.class})
public interface GistComponent  {
    void inject(GistFragment fragment);
}
