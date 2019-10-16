package com.rhmt.testandroid.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


import org.json.JSONObject;

public class ListUser implements Parcelable {
    private int user_id;
    private int id;
    private String title;
    private Boolean completed;


    public ListUser(int user_id, int id, String title, Boolean completed) {
        this.user_id = user_id;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public ListUser() {
    }


    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.user_id);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeValue(this.completed);
    }

    protected ListUser(Parcel in) {
        this.user_id = in.readInt();
        this.id = in.readInt();
        this.title = in.readString();
        this.completed = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<ListUser> CREATOR = new Creator<ListUser>() {
        @Override
        public ListUser createFromParcel(Parcel source) {
            return new ListUser(source);
        }

        @Override
        public ListUser[] newArray(int size) {
            return new ListUser[size];
        }
    };
}
