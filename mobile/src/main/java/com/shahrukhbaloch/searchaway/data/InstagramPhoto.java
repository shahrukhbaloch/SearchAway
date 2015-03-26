package com.shahrukhbaloch.searchaway.data;

public class InstagramPhoto {

    public ImageTypes images;

    public class ImageTypes {

        public ImageAttrs getLow_resolution() {
            return low_resolution;
        }

        public void setLow_resolution(ImageAttrs low_resolution) {
            this.low_resolution = low_resolution;
        }

        public ImageAttrs getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(ImageAttrs thumbnail) {
            this.thumbnail = thumbnail;
        }

        public ImageAttrs getStandard_resolution() {
            return standard_resolution;
        }

        public void setStandard_resolution(ImageAttrs standard_resolution) {
            this.standard_resolution = standard_resolution;
        }

        public ImageAttrs low_resolution;
        public ImageAttrs thumbnail;
        public ImageAttrs standard_resolution;
    }

    public class ImageAttrs {

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String url;

    }

    public ImageTypes getImages() {
        return images;
    }

    public void setImages(ImageTypes images) {
        this.images = images;
    }
}
