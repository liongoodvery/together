package org.lion.together.presenter;

/**
 * Created by lion on 11/17/16.
 */

public interface GistPresenter {
    void fetchGists(String access_token);

    void verifyToken(String token);
}
