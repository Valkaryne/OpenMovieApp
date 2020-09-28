package com.epam.valkaryne.openmovieapp.core.model;

public final class QueryModel {

    private String title;
    private String year = "";
    private String type = "";

    public QueryModel(String title, String year, String type) {
        this.title = title;
        this.year = year;
        this.type = type;
    }

    public QueryModel(String title, String year) {
        this.title = title;
        this.year = year;
    }

    public QueryModel(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return title + "_" + year + "_" + type;
    }
}
