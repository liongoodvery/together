package org.lion.alloytimer.http;

import org.lion.alloytimer.App;

/**
 * Created by lion on 11/17/16.
 */

public class WebService {
    public static GistApi gistApi() {
        return App.getAppComponent().getGistApi();
    }
}
