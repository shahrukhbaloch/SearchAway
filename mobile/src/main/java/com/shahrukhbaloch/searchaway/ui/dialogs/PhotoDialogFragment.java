package com.shahrukhbaloch.searchaway.ui.dialogs;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shahrukhbaloch.searchaway.R;
import com.shahrukhbaloch.searchaway.ui.views.DraweeTouchImageView;

import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectView;

/**
 * Created by shahrukh.baloch on 4/1/15.
 */
public class PhotoDialogFragment extends RoboDialogFragment implements View.OnClickListener {

    private static String KEY_IMAGE_URL = "image url key";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    public static PhotoDialogFragment newInstance(String imageURL) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_IMAGE_URL, imageURL);
        PhotoDialogFragment photoDialogFragment = new PhotoDialogFragment();
        photoDialogFragment.setArguments(bundle);
        return photoDialogFragment;
    }

    @InjectView(R.id.imgPhotoBig)
    private DraweeTouchImageView draweeTouchImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_enlarged_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            Bundle b = getArguments();
            String url = b.getString(KEY_IMAGE_URL);

//        Fresco Currently has a known bug where it cannot load URLs with a redirect, Temporarily going to use UIL until there's a fix
//        draweeTouchImageView.setImageURI(Uri.parse(url));
            if (url != null) {
                ImageLoader.getInstance().displayImage(url, draweeTouchImageView);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v == draweeTouchImageView) {
            showDownloadOption();
        }
    }

    private void showDownloadOption() {
        
    }
}
