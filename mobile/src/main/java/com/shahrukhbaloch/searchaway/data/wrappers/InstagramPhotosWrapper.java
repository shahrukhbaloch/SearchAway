package com.shahrukhbaloch.searchaway.data.wrappers;

import com.shahrukhbaloch.searchaway.data.InstagramPhoto;

import java.util.ArrayList;

/**
 * Created by shahrukh.baloch on 3/31/15.
 */
public class InstagramPhotosWrapper {

    private ArrayList<InstagramPhoto> photos;

    public InstagramPhotosWrapper(ArrayList<InstagramPhoto> photos) {
        this.photos = photos;
    }

    public ArrayList<InstagramPhoto> getPhotos() {
        return photos == null ? new ArrayList<InstagramPhoto>() : photos;
    }

    public void setPhotos(ArrayList<InstagramPhoto> photos) {
        this.photos = photos;
    }

}
