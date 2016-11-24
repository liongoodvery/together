package org.lion.together.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Owner implements Parcelable{
        /**
         * login : Diane26290
         * id : 22372170
         * avatar_url : https://avatars.githubusercontent.com/u/22372170?v=3
         * gravatar_id :
         * url : https://api.github.com/users/Diane26290
         * html_url : https://github.com/Diane26290
         * followers_url : https://api.github.com/users/Diane26290/followers
         * following_url : https://api.github.com/users/Diane26290/following{/other_user}
         * gists_url : https://api.github.com/users/Diane26290/gists{/gist_id}
         * starred_url : https://api.github.com/users/Diane26290/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/Diane26290/subscriptions
         * organizations_url : https://api.github.com/users/Diane26290/orgs
         * repos_url : https://api.github.com/users/Diane26290/repos
         * events_url : https://api.github.com/users/Diane26290/events{/privacy}
         * received_events_url : https://api.github.com/users/Diane26290/received_events
         * type : User
         * site_admin : false
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

        protected Owner(Parcel in) {
                login = in.readString();
                id = in.readInt();
                avatar_url = in.readString();
                gravatar_id = in.readString();
                url = in.readString();
                html_url = in.readString();
                followers_url = in.readString();
                following_url = in.readString();
                gists_url = in.readString();
                starred_url = in.readString();
                subscriptions_url = in.readString();
                organizations_url = in.readString();
                repos_url = in.readString();
                events_url = in.readString();
                received_events_url = in.readString();
                type = in.readString();
                site_admin = in.readByte() != 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(login);
                dest.writeInt(id);
                dest.writeString(avatar_url);
                dest.writeString(gravatar_id);
                dest.writeString(url);
                dest.writeString(html_url);
                dest.writeString(followers_url);
                dest.writeString(following_url);
                dest.writeString(gists_url);
                dest.writeString(starred_url);
                dest.writeString(subscriptions_url);
                dest.writeString(organizations_url);
                dest.writeString(repos_url);
                dest.writeString(events_url);
                dest.writeString(received_events_url);
                dest.writeString(type);
                dest.writeByte((byte) (site_admin ? 1 : 0));
        }

        @Override
        public int describeContents() {
                return 0;
        }

        public static final Creator<Owner> CREATOR = new Creator<Owner>() {
                @Override
                public Owner createFromParcel(Parcel in) {
                        return new Owner(in);
                }

                @Override
                public Owner[] newArray(int size) {
                        return new Owner[size];
                }
        };
}