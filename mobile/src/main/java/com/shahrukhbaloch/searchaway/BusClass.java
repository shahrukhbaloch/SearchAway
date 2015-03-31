package com.shahrukhbaloch.searchaway;

import android.content.Context;
import android.util.Log;

import com.google.inject.Inject;
import com.shahrukhbaloch.searchaway.data.SearchQuery;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import roboguice.RoboGuice;

/**
 * Created by shahrukh.baloch on 3/31/15.
 */
public class BusClass {

    @Inject
    protected Bus mBus;

    public BusClass(Context context) {
        RoboGuice.getInjector(context).injectMembers(this);
        mBus.register(this);
    }

}
