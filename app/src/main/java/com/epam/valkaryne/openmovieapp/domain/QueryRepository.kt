package com.epam.valkaryne.openmovieapp.domain

import com.epam.valkaryne.openmovieapp.common.QueryModel

interface QueryRepository {

    fun saveQuery(query: QueryModel)

    fun getQueries(): List<QueryModel>

    fun clearRepository()
}