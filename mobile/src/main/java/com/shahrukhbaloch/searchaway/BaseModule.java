package com.shahrukhbaloch.searchaway;

import android.content.Context;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.squareup.otto.Bus;

/**
 * Created by shahrukh.baloch on 3/31/15.
 */

public class BaseModule extends AbstractModule {


//    private Context mContext;
//
//    public BaseModule(Context context) {
//        mContext = context;
//    }

    @Override
    protected void configure() {
        bind(Bus.class).in(Singleton.class);
    }

}