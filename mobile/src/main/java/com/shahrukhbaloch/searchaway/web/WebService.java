package com.shahrukhbaloch.searchaway.web;

import com.shahrukhbaloch.searchaway.data.InstagramPhoto;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by shahrukh.baloch on 3/24/15.
 */
public interface WebService {

    public static String SELFIE_ADDRESS = "/tags/mutualmobile/media/recent";
    public static String CLIENT_ID = "?client_id=6c803f9d549e4804b743e4bc0ab75e81";

    @GET(SELFIE_ADDRESS + CLIENT_ID)
    public void getSelfies(Callback<WebResponse<ArrayList<InstagramPhoto>>> callback);

}
