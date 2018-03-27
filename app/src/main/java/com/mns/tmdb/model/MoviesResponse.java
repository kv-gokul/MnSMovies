package com.mns.tmdb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gokul on 26/3/18.
 */

public class MoviesResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Movie> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoviesResponse)) return false;

        MoviesResponse that = (MoviesResponse) o;

        if (getPage() != that.getPage()) return false;
        if (getTotalResults() != that.getTotalResults()) return false;
        if (getTotalPages() != that.getTotalPages()) return false;
        return getResults().equals(that.getResults());

    }

    @Override
    public int hashCode() {
        int result = getPage();
        result = 31 * result + getResults().hashCode();
        result = 31 * result + getTotalResults();
        result = 31 * result + getTotalPages();
        return result;
    }

    @Override
    public String toString() {
        return "MoviesResponse{" +
                "page=" + page +
                ", results=" + results +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                '}';
    }
}
