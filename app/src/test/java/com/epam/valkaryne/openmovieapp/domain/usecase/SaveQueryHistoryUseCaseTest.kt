package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.domain.QueryRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class SaveQueryHistoryUseCaseTest {

    private val mockQueryRepository: QueryRepository = mockk(relaxed = true)
    private val saveQueryHistoryUseCase = SaveQueryHistoryUseCase(mockQueryRepository)

    @Test
    fun `when execute usecase should call saveQuery method with argument`() {
        saveQueryHistoryUseCase.executeUseCase(queryModel)

        verify { mockQueryRepository.saveQuery(queryModel) }
    }

    @Test
    fun `when execute usecase should not call clearRepository method`() {
        saveQueryHistoryUseCase.executeUseCase(queryModel)

        verify(exactly = 0) { mockQueryRepository.clearRepository() }
    }

    @Test
    fun `when execute usecase should not call getQueries method`() {
        saveQueryHistoryUseCase.executeUseCase(queryModel)

        verify(exactly = 0) { mockQueryRepository.getQueries() }
    }

    private companion object {
        val queryModel = QueryModel("Tenet", "2020", "movie")
    }
}