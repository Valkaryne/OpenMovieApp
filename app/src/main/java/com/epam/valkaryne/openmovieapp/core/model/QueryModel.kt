package com.epam.valkaryne.openmovieapp.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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