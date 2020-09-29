package com.epam.valkaryne.openmovieapp.domain

import com.epam.valkaryne.openmovieapp.core.model.QueryModel

interface QueryRepository {

    fun saveQuery(query: QueryModel)

    fun getQueries(): List<QueryModel>

    fun clearRepository()
}