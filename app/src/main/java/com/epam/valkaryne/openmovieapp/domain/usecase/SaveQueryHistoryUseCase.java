package com.epam.valkaryne.openmovieapp.domain.usecase;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.domain.QueryRepository;

public class SaveQueryHistoryUseCase {

    private QueryRepository queryRepository;

    public SaveQueryHistoryUseCase(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public void executeUseCase(QueryModel query) {
        queryRepository.saveQuery(query);
    }
}
