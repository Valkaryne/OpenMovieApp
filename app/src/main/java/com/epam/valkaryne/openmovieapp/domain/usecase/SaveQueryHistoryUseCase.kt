package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.core.model.QueryModel
import com.epam.valkaryne.openmovieapp.domain.QueryRepository

class SaveQueryHistoryUseCase(private val queryRepository: QueryRepository) {

    fun executeUseCase(query: QueryModel) = queryRepository.saveQuery(query)
}