package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.domain.QueryRepository

/**
 * Domain logic to save a query in history
 */
class SaveQueryHistoryUseCase(private val queryRepository: QueryRepository) {

    /**
     * Executes domain logic
     *
     * @param query a query to save in history
     */
    fun executeUseCase(query: QueryModel) = queryRepository.saveQuery(query)
}