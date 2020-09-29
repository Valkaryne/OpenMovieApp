package com.epam.valkaryne.openmovieapp.domain

import com.epam.valkaryne.openmovieapp.common.QueryModel

/**
 * Repository that works with history of user's queries
 */
interface QueryRepository {

    /**
     * Saves a query in history
     *
     * @param query a query to save
     */
    fun saveQuery(query: QueryModel)

    /**
     * Gets the full history of queries
     *
     * @return list of saved queries
     */
    fun getQueries(): List<QueryModel>

    /**
     * Clears the history of queries
     */
    fun clearRepository()
}