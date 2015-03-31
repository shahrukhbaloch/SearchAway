package com.shahrukhbaloch.searchaway.ui.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.google.inject.Inject;
import com.shahrukhbaloch.searchaway.R;
import com.squareup.otto.Bus;

import roboguice.activity.RoboActionBarActivity;

public abstract class BaseActivity extends RoboActionBarActivity {

    @Inject
    protected Bus mBus;

    protected void addFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

    }

    protected void addAndShowDialogFragment(
            DialogFragment dialog, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        dialog.show(transaction, tag);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(),"Hashcode is "  +mBus.hashCode());

        mBus.register(this);
    }
}

