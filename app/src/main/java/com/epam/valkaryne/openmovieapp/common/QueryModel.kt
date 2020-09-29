package com.epam.valkaryne.openmovieapp.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Data class that represents a query that user sends to API
 */
@Parcelize
data class QueryModel(
    val title: String,
    val year: String = "",
    val type: String = ""
) : Parcelable {

    override fun toString(): String {
        return "${title}_${year}_$type"
    }
}