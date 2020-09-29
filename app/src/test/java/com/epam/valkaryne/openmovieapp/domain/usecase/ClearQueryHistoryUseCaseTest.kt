package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.domain.QueryRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ClearQueryHistoryUseCaseTest {

    private val mockQueryRepository: QueryRepository = mockk(relaxed = true)
    private val clearQueryHistoryUseCase = ClearQueryHistoryUseCase(mockQueryRepository)

    @Test
    fun `when execute usecase should call clearRepository method`() {
        clearQueryHistoryUseCase.executeUseCase()

        verify { mockQueryRepository.clearRepository() }
    }

    @Test
    fun `when execute usecase should not call getQueries method`() {
        clearQueryHistoryUseCase.executeUseCase()

        verify(exactly = 0) { mockQueryRepository.getQueries() }
    }

    @Test
    fun `when execute usecase should not call saveQuery method`() {
        clearQueryHistoryUseCase.executeUseCase()

        verify(exactly = 0) { mockQueryRepository.saveQuery(any()) }
    }
}

