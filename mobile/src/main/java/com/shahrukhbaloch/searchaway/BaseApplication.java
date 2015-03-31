package com.shahrukhbaloch.searchaway;

import android.app.Application;
import android.graphics.Bitmap;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.squareup.otto.Bus;

import dagger.ObjectGraph;
import roboguice.RoboGuice;

/**
 * Created by shahrukh.baloch on 3/24/15.
 */
public class BaseApplication extends Application {
    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
//        initImageLoader();
        Fresco.initialize(getApplicationContext());

//        BaseModule baseInjectionModule = new BaseModule(getApplicationContext());
//        RoboGuice.getInjector(this).injectMembers(baseInjectionModule);

//        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
//                RoboGuice.newDefaultRoboModule(this), new BaseModule());

        RoboGuice.getOrCreateBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), new BaseModule());
        //sad



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
