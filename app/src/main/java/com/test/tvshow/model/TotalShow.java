package com.test.tvshow.model;

import java.util.List;

/**
 * Created by roberto on 14/03/18.
 */

public class TotalShow {

    private final int total_pages;
    private final List<TVShow> results;

    public TotalShow(int page, int total_results, int total_pages, List<TVShow> results) {
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<TVShow> getResults() {
        return results;
    }
}
