package com.shahrukhbaloch.searchaway.web;

import com.shahrukhbaloch.searchaway.data.InstagramPhoto;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface WebService {

    public static String CLIENT_ID = "?client_id=6c803f9d549e4804b743e4bc0ab75e81";

    //TODO Instagram returns an array instead of an array object, so wrappers dont work. Look into how that works.

    @GET("/tags/{query}/media/recent" + CLIENT_ID)
    public void searchPhotos(@Path("query") String query, Callback<WebResponse<ArrayList<InstagramPhoto>>> callback);

    @GET("/media/popular" + CLIENT_ID)
    public void showPopularPhotos(Callback<WebResponse<ArrayList<InstagramPhoto>>> callback);
}
