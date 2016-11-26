package org.lion.together.dev.gist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lion on 2016-11-16
 */

public class Gist implements Parcelable {

    /**
     * url : https://api.github.com/gists/8ac5b49e5714797247c74c30c2116cb5
     * forks_url : https://api.github.com/gists/8ac5b49e5714797247c74c30c2116cb5/forks
     * commits_url : https://api.github.com/gists/8ac5b49e5714797247c74c30c2116cb5/commits
     * id : 8ac5b49e5714797247c74c30c2116cb5
     * git_pull_url : https://gist.github.com/8ac5b49e5714797247c74c30c2116cb5.git
     * git_push_url : https://gist.github.com/8ac5b49e5714797247c74c30c2116cb5.git
     * html_url : https://gist.github.com/8ac5b49e5714797247c74c30c2116cb5
     * public : true
     * created_at : 2016-11-16T14:25:03Z
     * updated_at : 2016-11-16T14:25:03Z
     * description : Exported from Popcode. Click to import: https://popcode.org/?gist=8ac5b49e5714797247c74c30c2116cb5
     * comments : 0
     * user : null
     * comments_url : https://api.github.com/gists/8ac5b49e5714797247c74c30c2116cb5/comments
     * owner : {"login":"Diane26290","id":22372170,"avatar_url":"https://avatars.githubusercontent.com/u/22372170?v=3","gravatar_id":"","url":"https://api.github.com/users/Diane26290","html_url":"https://github.com/Diane26290","followers_url":"https://api.github.com/users/Diane26290/followers","following_url":"https://api.github.com/users/Diane26290/following{/other_user}","gists_url":"https://api.github.com/users/Diane26290/gists{/gist_id}","starred_url":"https://api.github.com/users/Diane26290/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/Diane26290/subscriptions","organizations_url":"https://api.github.com/users/Diane26290/orgs","repos_url":"https://api.github.com/users/Diane26290/repos","events_url":"https://api.github.com/users/Diane26290/events{/privacy}","received_events_url":"https://api.github.com/users/Diane26290/received_events","type":"User","site_admin":false}
     * truncated : false
     */

    public String url;
    public String forks_url;
    public String commits_url;
    public String id;
    public String git_pull_url;
    public String git_push_url;
    public String html_url;
    @SerializedName("public")
    public boolean publicX;
    public String created_at;
    public String updated_at;
    public String description;
    public int comments;
    public Object user;
    public String comments_url;
    public Owner owner;
    public boolean truncated;
    public Map<String,GistFile> files;

    protected Gist(Parcel in) {
        url = in.readString();
        forks_url = in.readString();
        commits_url = in.readString();
        id = in.readString();
        git_pull_url = in.readString();
        git_push_url = in.readString();
        html_url = in.readString();
        publicX = in.readByte() != 0;
        created_at = in.readString();
        updated_at = in.readString();
        description = in.readString();
        comments = in.readInt();
        comments_url = in.readString();
        owner = in.readParcelable(Owner.class.getClassLoader());
        truncated = in.readByte() != 0;
        Serializable serializable = in.readSerializable();
        if (serializable!=null && serializable instanceof Map){
            files = (Map)serializable;
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(forks_url);
        dest.writeString(commits_url);
        dest.writeString(id);
        dest.writeString(git_pull_url);
        dest.writeString(git_push_url);
        dest.writeString(html_url);
        dest.writeByte((byte) (publicX ? 1 : 0));
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(description);
        dest.writeInt(comments);
        dest.writeString(comments_url);
        dest.writeParcelable(owner, flags);
        dest.writeByte((byte) (truncated ? 1 : 0));
        if (files instanceof Serializable){
            dest.writeSerializable((Serializable)files);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Gist> CREATOR = new Creator<Gist>() {
        @Override
        public Gist createFromParcel(Parcel in) {
            return new Gist(in);
        }

        @Override
        public Gist[] newArray(int size) {
            return new Gist[size];
        }
    };
}
