package com.test.tvshow.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.test.tvshow.Utils;

import java.util.List;

/**
 * Created by roberto on 14/03/18.
 */

public class TVShow implements Parcelable {

    private final String name;
    private final String backdrop_path;
    private final int id;
    private final double vote_average;
    private final String overview;
    private final String poster_path;

    public TVShow(String name, String backdrop_path, int id, double vote_average, String overview, String poster_path){
        this.name = name;
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
    }

    protected TVShow(Parcel in) {
        name = in.readString();
        backdrop_path = in.readString();
        id = in.readInt();
        vote_average = in.readDouble();
        overview = in.readString();
        poster_path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(backdrop_path);
        dest.writeInt(id);
        dest.writeDouble(vote_average);
        dest.writeString(overview);
        dest.writeString(poster_path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropImageURL() {
        return Utils.IMAGE_URL+"/t/p/w500/"+backdrop_path;
    }

    public String getPosterImageURL() {
        return Utils.IMAGE_URL+"/t/p/w500/"+poster_path;
    }
}

