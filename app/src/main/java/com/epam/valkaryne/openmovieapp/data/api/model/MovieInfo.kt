package com.epam.valkaryne.openmovieapp.data.api.model

import com.google.gson.annotations.SerializedName

data class MovieInfo(
    @field:SerializedName("imdbID") val imdbID: String,
    @field:SerializedName("Title") val title: String,
    @field:SerializedName("Year") val year: String,
    @field:SerializedName("Poster") val posterUrl: String
)