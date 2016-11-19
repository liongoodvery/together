package org.lion.together.ui;

import org.lion.together.model.Gist;

import java.util.List;

/**
 * Created by lion on 2016-11-17
 */

public interface GistView {
    void onFetchSuccess(List<Gist> gists);

    void onFetchFailed();
}
