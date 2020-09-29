package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.domain.QueryRepository

/**
 * Domain logic to clear queries' history
 */
class ClearQueryHistoryUseCase(private val queryRepository: QueryRepository) {

    /**
     * Executes domain logic
     */
    fun executeUseCase() = queryRepository.clearRepository()
}