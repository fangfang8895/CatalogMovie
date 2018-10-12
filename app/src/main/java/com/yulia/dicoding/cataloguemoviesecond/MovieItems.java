package com.yulia.dicoding.cataloguemoviesecond;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.util.ArrayList;

public class MovieItems extends ArrayList implements Parcelable {
    private int id;
    private String posterPath;
    private String popularityMovie;
    private String overview;
    private String releaseDate;
    private String voteAveragedetail;
    private String title;
    private String language;


    public MovieItems(JSONObject object){
        try {
            int id = object.getInt("id");
            String posterPath = object.getString("poster_path");
            String overview = object.getString("overview");
            String releaseDate = object.getString("release_date");
            double voteAverage = object.getDouble("vote_average");
            String title = object.getString("title");
            String language = object.getString("original_language");
            double popularity = object.getDouble("popularity");
            String voteAveragedetail = Double.toString(voteAverage);
            String popularityMovie = Double.toString(popularity);
            this.id = id;
            this.posterPath = posterPath;
            this.popularityMovie = popularityMovie;
            this.overview = overview;
            this.releaseDate = releaseDate;
            this.voteAveragedetail = voteAveragedetail;
            this.title = title;
            this.language = language;

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getPosterPath(){
        return posterPath;
    }
    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }

    public String getPopularityMovie(){
        return popularityMovie;
    }
    public void setPopularityMovie(String popularityMovie){
        this.popularityMovie = popularityMovie;
    }

    public String getOverview(){
        return  overview;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public String getReleaseDate(){
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getVoteAveragedetail(){
        return voteAveragedetail;
    }
    public void setVoteAveragedetail(String voteAveragedetail){
        this.voteAveragedetail = voteAveragedetail;
    }
    public String getLanguage(){
        return language;
    }
    public void setLanguage(String language){
        this.language = language;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.posterPath);
        dest.writeString(this.popularityMovie);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.voteAveragedetail);
        dest.writeString(this.title);
        dest.writeString(this.language);
    }

    protected MovieItems(Parcel in) {
        this.id = in.readInt();
        this.posterPath = in.readString();
        this.popularityMovie = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.voteAveragedetail = in.readString();
        this.title = in.readString();
        this.language = in.readString();
    }

    public static final Parcelable.Creator<MovieItems> CREATOR = new Parcelable.Creator<MovieItems>() {
        @Override
        public MovieItems createFromParcel(Parcel source) {
            return new MovieItems(source);
        }

        @Override
        public MovieItems[] newArray(int size) {
            return new MovieItems[size];
        }
    };
}

