package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.domain.QueryRepository

class ClearQueryHistoryUseCase(private val queryRepository: QueryRepository) {

    fun executeUseCase() = queryRepository.clearRepository()
}