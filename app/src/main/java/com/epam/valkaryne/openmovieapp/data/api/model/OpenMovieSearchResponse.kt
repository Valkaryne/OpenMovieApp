package com.epam.valkaryne.openmovieapp.data.api.model

import com.google.gson.annotations.SerializedName

data class OpenMovieSearchResponse(
    @field:SerializedName("Response") val successful: Boolean,
    @field:SerializedName("Search") val searchResults: List<MovieInfo>?,
    @field:SerializedName("totalResults") val totalResults: Int,
    @field:SerializedName("Error") val error: String
)