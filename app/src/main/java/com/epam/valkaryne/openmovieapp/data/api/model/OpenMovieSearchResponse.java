package com.epam.valkaryne.openmovieapp.data.api.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class OpenMovieSearchResponse {

    @SerializedName("Response")
    private boolean successful;

    @Nullable
    @SerializedName("Search")
    private List<MovieDataModel> searchResults;

    @SerializedName("totalResults")
    private int totalResults;

    @Nullable
    @SerializedName("Error")
    private String error;

    public OpenMovieSearchResponse(boolean successful, @Nullable List<MovieDataModel> searchResults, @Nullable String error) {
        this.successful = successful;
        this.searchResults = searchResults;
        this.error = error;
    }

    public boolean isSuccessful() {
        return successful;
    }

    @Nullable
    public List<MovieDataModel> getSearchResults() {
        return searchResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    @Nullable
    public String getError() {
        return error;
    }
}
