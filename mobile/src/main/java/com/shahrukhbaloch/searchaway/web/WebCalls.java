package com.shahrukhbaloch.searchaway.web;

import android.content.Context;
import android.util.Log;

import com.google.inject.Inject;
import com.shahrukhbaloch.searchaway.BusClass;
import com.shahrukhbaloch.searchaway.data.InstagramPhoto;
import com.shahrukhbaloch.searchaway.data.SearchQuery;
import com.shahrukhbaloch.searchaway.data.wrappers.InstagramPhotosWrapper;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shahrukh.baloch on 3/30/15.
 */
public class WebCalls extends BusClass {

    private WebService mWebService;
    private Context mContext;

    @Inject
    public WebCalls(Context context) {
        super(context);
        mWebService = WebServiceFactory.getInstanceWithBasicGsonConversion();
    }

    public void getDefaultPhotos() {
        mWebService.showPopularPhotos(photosCallback);

    }

    @Subscribe
    public void searchPhotos(SearchQuery searchQuery) {

        if (searchQuery != null) {
            String searchString = searchQuery.getSearchString();

            if (searchString.length() > 0) {
                mWebService.searchPhotos(searchString, photosCallback);
            } else {

            }

        }
    }

    private Callback<WebResponse<ArrayList<InstagramPhoto>>> photosCallback = new Callback<WebResponse<ArrayList<InstagramPhoto>>>() {

        @Override
        public void success(WebResponse<ArrayList<InstagramPhoto>> arrayListWebResponse, Response response) {
            if (arrayListWebResponse != null) {
                mBus.post(new InstagramPhotosWrapper(arrayListWebResponse.getResult()));
            }
        }

        @Override
        public void failure(RetrofitError arg0) {
            Log.d(getClass().getSimpleName(), "failure was called");
        }
    };
}
