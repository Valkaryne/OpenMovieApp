package com.epam.valkaryne.openmovieapp.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.domain.QueryRepository;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

public class QueryRepositoryImpl implements QueryRepository {

    private final String QUERY_PREFERENCES_FILE = "OpenMovieQuery";

    private SharedPreferences querySharedPreferences;

    public QueryRepositoryImpl(Context context) {
        querySharedPreferences = context.getSharedPreferences(QUERY_PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    @Override
    public void saveQuery(QueryModel query) {
        String jsonQuery = new Gson().toJson(query);
        querySharedPreferences.edit().putString(query.toString(), jsonQuery).apply();
    }

    @Override
    public List<QueryModel> getQueries() {
        List<QueryModel> queries = new ArrayList<>();
        for (String key : querySharedPreferences.getAll().keySet()) {
            try {
                String jsonQuery = querySharedPreferences.getString(key, null);
                if (jsonQuery != null) {
                    QueryModel query = new Gson().fromJson(jsonQuery, QueryModel.class);
                    queries.add(query);
                }
            } catch (JsonSyntaxException ex) {
                querySharedPreferences.edit().remove(key).apply();
            }
        }
        return queries;
    }

    @Override
    public void clearRepository() {
        querySharedPreferences.edit().clear().apply();
    }
}
