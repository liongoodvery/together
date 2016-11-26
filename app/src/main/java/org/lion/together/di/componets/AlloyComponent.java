package org.lion.together.di.componets;

import org.lion.together.di.modules.AlloyModule;
import org.lion.together.dev.alloy.ui.AlloyFragment;

import dagger.Component;

/**
 * Created by lion on 11/11/16.
 */
@Component(modules = {AlloyModule.class})
public interface AlloyComponent {
    void inject(AlloyFragment fragment);
}
