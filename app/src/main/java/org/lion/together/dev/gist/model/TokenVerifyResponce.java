package org.lion.together.dev.gist.model;

import org.lion.together.base.BaseResponse;

/**
 * Created by lion on 11/19/16.
 */

public class TokenVerifyResponce extends BaseResponse {


    /**
     * login : liongoodvery
     * id : 8407068
     * avatar_url : https://avatars.githubusercontent.com/u/8407068?v=3
     * gravatar_id :
     * url : https://api.github.com/users/liongoodvery
     * html_url : https://github.com/liongoodvery
     * followers_url : https://api.github.com/users/liongoodvery/followers
     * following_url : https://api.github.com/users/liongoodvery/following{/other_user}
     * gists_url : https://api.github.com/users/liongoodvery/gists{/gist_id}
     * starred_url : https://api.github.com/users/liongoodvery/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/liongoodvery/subscriptions
     * organizations_url : https://api.github.com/users/liongoodvery/orgs
     * repos_url : https://api.github.com/users/liongoodvery/repos
     * events_url : https://api.github.com/users/liongoodvery/events{/privacy}
     * received_events_url : https://api.github.com/users/liongoodvery/received_events
     * type : User
     * site_admin : false
     * name : null
     * company : null
     * blog : null
     * location : 杭州 浙江
     * email : lion_good_very@hotmail.com
     * hireable : null
     * bio : Android Programmer
     * public_repos : 34
     * public_gists : 3
     * followers : 0
     * following : 7
     * created_at : 2014-08-10T07:50:59Z
     * updated_at : 2016-11-20T07:36:49Z
     * private_gists : 34
     * total_private_repos : 0
     * owned_private_repos : 0
     * disk_usage : 5661
     * collaborators : 0
     * plan : {"name":"free","space":976562499,"collaborators":0,"private_repos":0}
     */

    public String login;
    public int id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public boolean site_admin;
    public Object name;
    public Object company;
    public Object blog;
    public String location;
    public String email;
    public Object hireable;
    public String bio;
    public int public_repos;
    public int public_gists;
    public int followers;
    public int following;
    public String created_at;
    public String updated_at;
    public int private_gists;
    public int total_private_repos;
    public int owned_private_repos;
    public int disk_usage;
    public int collaborators;
    /**
     * name : free
     * space : 976562499
     * collaborators : 0
     * private_repos : 0
     */

    public PlanBean plan;
    public static class PlanBean {
        public String name;
        public int space;
        public int collaborators;
        public int private_repos;
    }
}
