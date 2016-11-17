package org.lion.alloytimer.ui;

import org.lion.alloytimer.model.Gist;

import java.util.List;

/**
 * Created by lion on 2016-11-17
 */

public interface GistView {
    void onFetchSuccess(List<Gist> gists);

    void onFetchFailed();
}
