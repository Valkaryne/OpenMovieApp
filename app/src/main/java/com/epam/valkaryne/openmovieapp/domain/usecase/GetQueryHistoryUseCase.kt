package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.domain.QueryRepository

/**
 * Domain logic to get the full queries' history
 */
class GetQueryHistoryUseCase(private val queryRepository: QueryRepository) {

    /**
     * Executes domain logic
     *
     * @return list of previous queries
     */
    fun executeUseCase(): List<QueryModel> = queryRepository.getQueries()
}