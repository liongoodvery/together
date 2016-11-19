package org.lion.together.di.componets;

import org.lion.together.di.modules.GistModule;
import org.lion.together.ui.GistFragment;

import dagger.Component;

/**
 * Created by lion on 2016-11-17
 */
@Component(dependencies = {GistModule.class})
public interface GistComponent  {
    void inject(GistFragment fragment);
}
