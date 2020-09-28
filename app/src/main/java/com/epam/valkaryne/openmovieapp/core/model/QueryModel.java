package com.epam.valkaryne.openmovieapp.core.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class QueryModel implements Parcelable {

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

    protected QueryModel(Parcel in) {
        title = in.readString();
        year = in.readString();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QueryModel> CREATOR = new Creator<QueryModel>() {
        @Override
        public QueryModel createFromParcel(Parcel in) {
            return new QueryModel(in);
        }

        @Override
        public QueryModel[] newArray(int size) {
            return new QueryModel[size];
        }
    };

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
