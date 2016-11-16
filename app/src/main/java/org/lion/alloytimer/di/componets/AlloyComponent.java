package org.lion.alloytimer.di.componets;

import org.lion.alloytimer.di.modules.AlloyModule;
import org.lion.alloytimer.ui.AlloyFragment;

import dagger.Component;

/**
 * Created by lion on 11/11/16.
 */
@Component(modules = {AlloyModule.class})
public interface AlloyComponent {
    void inject(AlloyFragment activity);
}
