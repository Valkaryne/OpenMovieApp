package com.epam.valkaryne.openmovieapp.domain;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;

import java.util.List;

public interface QueryRepository {

    void saveQuery(QueryModel query);

    List<QueryModel> getQueries();

    void clearRepository();
}
