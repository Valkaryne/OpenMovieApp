package com.epam.valkaryne.openmovieapp.domain.usecase;

import com.epam.valkaryne.openmovieapp.domain.QueryRepository;

public class ClearQueryHistoryUseCase {

    private QueryRepository queryRepository;

    public ClearQueryHistoryUseCase(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public void executeUseCase() {
        queryRepository.clearRepository();
    }
}
