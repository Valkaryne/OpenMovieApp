package com.epam.valkaryne.openmovieapp.domain.usecase;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.domain.QueryRepository;

import java.util.List;

public class GetQueryHistoryUseCase {

    private QueryRepository queryRepository;

    public GetQueryHistoryUseCase(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public List<QueryModel> executeUseCase() {
        return queryRepository.getQueries();
    }
}
