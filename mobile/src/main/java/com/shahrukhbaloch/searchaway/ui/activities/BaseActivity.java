package com.shahrukhbaloch.searchaway.ui.activities;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.shahrukhbaloch.searchaway.R;

public abstract class BaseActivity extends ActionBarActivity {

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


}

