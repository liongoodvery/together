package org.lion.together.di.componets;

import org.lion.together.di.modules.GistModule;
import org.lion.together.dev.gist.ui.GistFragment;

import dagger.Component;

/**
 * Created by lion on 2016-11-17
 */
@Component(modules = GistModule.class)
public interface GistComponent  {
    void inject(GistFragment fragment);
}
