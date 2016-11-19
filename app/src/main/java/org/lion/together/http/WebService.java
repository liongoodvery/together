package org.lion.together.http;

import org.lion.together.App;

/**
 * Created by lion on 11/17/16.
 */

public class WebService {
    public static GistApi gistApi() {
        return App.getAppComponent().getGistApi();
    }
}
