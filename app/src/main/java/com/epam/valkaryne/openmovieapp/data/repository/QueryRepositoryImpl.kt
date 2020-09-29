package com.epam.valkaryne.openmovieapp.data.repository

import android.content.Context
import androidx.core.content.edit
import com.epam.valkaryne.openmovieapp.core.model.QueryModel
import com.epam.valkaryne.openmovieapp.domain.QueryRepository
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

class QueryRepositoryImpl(context: Context) : QueryRepository {

    private val querySharedPreferences =
        context.getSharedPreferences(QUERY_PREFERENCES_FILE, Context.MODE_PRIVATE)

    override fun saveQuery(query: QueryModel) {
        querySharedPreferences.edit {
            val jsonQuery = Gson().toJson(query)
            putString(query.toString(), jsonQuery)
        }
    }

    override fun getQueries(): List<QueryModel> {
        val queries = mutableListOf<QueryModel>()
        querySharedPreferences.all.map {
            try {
                querySharedPreferences.getString(it.key, null)?.let { jsonQuery ->
                    Gson().fromJson(jsonQuery, QueryModel::class.java)?.let { query ->
                        queries.add(query)
                    }
                }
            } catch (ex: JsonSyntaxException) {
                querySharedPreferences.edit { remove(it.key) }
            }
        }
        return queries
    }

    override fun clearRepository() =
        querySharedPreferences.edit { clear() }

    private companion object {
        const val QUERY_PREFERENCES_FILE = "OpenMovieQuery"
    }
}