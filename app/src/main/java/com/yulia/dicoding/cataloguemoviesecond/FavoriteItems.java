package com.yulia.dicoding.cataloguemoviesecond;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract;

import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.getColumnInt;
import static com.yulia.dicoding.cataloguemoviesecond.db.DatabaseContract.getColumnString;

public class FavoriteItems implements Parcelable {
    private int id;
    private String posterPath;
    private String popularityMovie;
    private String overview;
    private String releaseDate;
    private String voteAveragedetail;
    private String title;
    private String language;

    public FavoriteItems(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPopularityMovie() {
        return popularityMovie;
    }

    public void setPopularityMovie(String popularityMovie) {
        this.popularityMovie = popularityMovie;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoteAveragedetail() {
        return voteAveragedetail;
    }

    public void setVoteAveragedetail(String voteAveragedetail) {
        this.voteAveragedetail = voteAveragedetail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }



    public FavoriteItems(Cursor cursor){
        this.id = getColumnInt(cursor,DatabaseContract.FavoriteColumns._ID);
        this.title = getColumnString(cursor, DatabaseContract.FavoriteColumns.TITLE);
        this.posterPath= getColumnString(cursor,DatabaseContract.FavoriteColumns.POSTERPATH);
        this.overview =getColumnString(cursor,DatabaseContract.FavoriteColumns.OVERVIEW);
       this.releaseDate =getColumnString(cursor,DatabaseContract.FavoriteColumns.RELEASE_DATE);
        this.voteAveragedetail= getColumnString(cursor,DatabaseContract.FavoriteColumns.VOTE);
        this.language = getColumnString(cursor,DatabaseContract.FavoriteColumns.LANGUAGE);
        this.popularityMovie = getColumnString(cursor,DatabaseContract.FavoriteColumns.POPULARITY);
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

    protected FavoriteItems(Parcel in) {
        this.id = in.readInt();
        this.posterPath = in.readString();
        this.popularityMovie = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.voteAveragedetail = in.readString();
        this.title = in.readString();
        this.language = in.readString();
    }

    public static final Parcelable.Creator<FavoriteItems> CREATOR = new Parcelable.Creator<FavoriteItems>() {
        @Override
        public FavoriteItems createFromParcel(Parcel in) {
            return new FavoriteItems(in);
        }

        @Override
        public FavoriteItems[] newArray(int size) {
            return new FavoriteItems[size];
        }
    };
}
