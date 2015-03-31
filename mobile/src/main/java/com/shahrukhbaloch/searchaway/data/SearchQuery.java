package com.shahrukhbaloch.searchaway.data;

public class SearchQuery {

    public SearchQuery(String mSearchString) {
        this.mSearchString = mSearchString;
    }

    String mSearchString;

    public String getSearchString() {
        return mSearchString;
    }
}