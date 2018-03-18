package com.test.tvshow.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.test.tvshow.Utils;

import java.util.List;

/**
 * Created by roberto on 14/03/18.
 */

public class TVShow implements Parcelable {

    private final String original_name;
    private final List<Integer> genre_ids = null;
    private final String name;
    private final double popularity;
    private final List<String> origin_country;
    private final int vote_count;
    private final String first_air_date;
    private final String backdrop_path;
    private final String original_language;
    private final int id;
    private final double vote_average;
    private final String overview;
    private final String poster_path;

    public TVShow(String original_name, List<Integer> genre_ids, String name, double popularity, List<String> origin_country, int vote_count, String first_air_date, String backdrop_path, String original_language, int id, double vote_average, String overview, String poster_path){
        this.original_name = original_name;
       // this.genre_ids = genre_ids;
        this.name = name;
        this.popularity= popularity;
        this.origin_country = origin_country;
        this.vote_count = vote_count;
        this.first_air_date = first_air_date;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
    }

    protected TVShow(Parcel in) {
        original_name = in.readString();
        name = in.readString();
        popularity = in.readDouble();
        origin_country = in.createStringArrayList();
        vote_count = in.readInt();
        first_air_date = in.readString();
        backdrop_path = in.readString();
        original_language = in.readString();
        id = in.readInt();
        vote_average = in.readDouble();
        overview = in.readString();
        poster_path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original_name);
        dest.writeString(name);
        dest.writeDouble(popularity);
        dest.writeStringList(origin_country);
        dest.writeInt(vote_count);
        dest.writeString(first_air_date);
        dest.writeString(backdrop_path);
        dest.writeString(original_language);
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

    public String getOriginal_name() {
        return original_name;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getName() {
        return name;
    }

    public double getPopularity() {
        return popularity;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
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

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdropImageURL() {
        return Utils.IMAGE_URL+"/t/p/w500/"+backdrop_path;
    }

    public String getPosterImageURL() {
        return Utils.IMAGE_URL+"/t/p/w500/"+poster_path;
    }
}

