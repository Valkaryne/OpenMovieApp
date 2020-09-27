package com.epam.valkaryne.openmovieapp.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * A class that represents a movie from OMDB API.
 */
public final class MovieDataModel {

    @SerializedName("imdbID")
    private String imdbID;

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("Poster")
    private String posterUrl;

    public MovieDataModel(String imdbID, String title, String year, String posterUrl) {
        this.imdbID = imdbID;
        this.title = title;
        this.year = year;
        this.posterUrl = posterUrl;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDataModel that = (MovieDataModel) o;
        return imdbID.equals(that.imdbID) &&
                title.equals(that.title) &&
                year.equals(that.year) &&
                posterUrl.equals(that.posterUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imdbID, title, year, posterUrl);
    }
}
