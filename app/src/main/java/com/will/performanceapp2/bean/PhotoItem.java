package com.will.performanceapp2.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PhotoItem implements Parcelable {
    private String webformatURL;
    private int id;
    private String largeImageURL;
    private int webformatHeight;
    private String user;
    private int likes;
    private int favorites;

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public int getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(int webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    protected PhotoItem(Parcel in) {
        webformatURL = in.readString();
        id = in.readInt();
        largeImageURL = in.readString();
        webformatHeight = in.readInt();
        user = in.readString();
        likes = in.readInt();
        favorites = in.readInt();
    }

    public static final Creator<PhotoItem> CREATOR = new Creator<PhotoItem>() {
        @Override
        public PhotoItem createFromParcel(Parcel in) {
            return new PhotoItem(in);
        }

        @Override
        public PhotoItem[] newArray(int size) {
            return new PhotoItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(webformatURL);
        dest.writeInt(id);
        dest.writeString(largeImageURL);
        dest.writeInt(webformatHeight);
        dest.writeString(user);
        dest.writeInt(likes);
        dest.writeInt(favorites);
    }
} 