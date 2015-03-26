package com.shahrukhbaloch.searchaway;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by shahrukh.baloch on 3/24/15.
 */
public class SearchAwayApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();

    }

    private void initImageLoader() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // .showImageForEmptyUri( R.drawable.placeholder_small )
                // .showImageOnFail(R.drawable.placeholder_small )
                .resetViewBeforeLoading(true).cacheInMemory(true)
//				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .displayer(new FadeInBitmapDisplayer(850))
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(config);
    }
}
