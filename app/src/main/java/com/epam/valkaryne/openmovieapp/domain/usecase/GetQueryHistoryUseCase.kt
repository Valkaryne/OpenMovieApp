package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.core.model.QueryModel
import com.epam.valkaryne.openmovieapp.domain.QueryRepository

class GetQueryHistoryUseCase(private val queryRepository: QueryRepository) {

    fun executeUseCase(): List<QueryModel> = queryRepository.getQueries()
}