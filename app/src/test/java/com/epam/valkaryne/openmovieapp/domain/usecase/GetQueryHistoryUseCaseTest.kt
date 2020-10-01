package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.domain.QueryRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetQueryHistoryUseCaseTest {

    private val mockQueryRepository: QueryRepository = mockk()
    private val getQueryHistoryUseCase = GetQueryHistoryUseCase(mockQueryRepository)

    @Before
    fun `set up`() {
        every { mockQueryRepository.getQueries() } returns listOf(queryModel)
    }

    @Test
    fun `when execute usecase should call getQueries method`() {
        getQueryHistoryUseCase.executeUseCase()

        verify { mockQueryRepository.getQueries() }
    }

    @Test
    fun `when execute usecase should return list of queryModels`() {
        val actualQueryHistory = getQueryHistoryUseCase.executeUseCase()
        val expectedQueryHistory = listOf(queryModel)

        assertEquals(expectedQueryHistory, actualQueryHistory)
    }

    private companion object {
        val queryModel = QueryModel("Tenet", "2020", "movie")
    }
}