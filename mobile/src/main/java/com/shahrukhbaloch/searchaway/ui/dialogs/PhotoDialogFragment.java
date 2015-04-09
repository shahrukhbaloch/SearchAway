package com.shahrukhbaloch.searchaway.ui.dialogs;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.shahrukhbaloch.searchaway.R;
import com.shahrukhbaloch.searchaway.ui.views.DraweeTouchImageView;
import com.shahrukhbaloch.searchaway.util.FileUtils;

import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectView;

/**
 * Created by shahrukh.baloch on 4/1/15.
 */
public class PhotoDialogFragment extends RoboDialogFragment implements View.OnClickListener {

    private static String KEY_IMAGE_URL = "image url key";
    private ImageLoader imageLoader;
    private Bitmap mPhotoBitmap;

    @InjectView(R.id.imgPhotoBig)
    private DraweeTouchImageView draweeTouchImageView;

    @InjectView(R.id.button_download)
    private Button mDownloadButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        imageLoader = ImageLoader.getInstance();
    }

    public static PhotoDialogFragment newInstance(String imageURL) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_IMAGE_URL, imageURL);
        PhotoDialogFragment photoDialogFragment = new PhotoDialogFragment();
        photoDialogFragment.setArguments(bundle);
        return photoDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_enlarged_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDownloadButton.setOnClickListener(this);
        if (getArguments() != null) {
            Bundle b = getArguments();
            String url = b.getString(KEY_IMAGE_URL);

//        Fresco Currently has a known bug where it cannot load URLs with a redirect, Temporarily going to use UIL until there's a fix
//        draweeTouchImageView.setImageURI(Uri.parse(url));
            if (url != null) {
                imageLoader.loadImage(url, imageLoadingListener);
            }
        }
    }

    ImageLoadingListener imageLoadingListener = new ImageLoadingListener() {

        @Override
        public void onLoadingStarted(String imageUri, View view) {

        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            imageLoader.displayImage(imageUri, draweeTouchImageView);
            mPhotoBitmap = loadedImage;
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {
            imageLoader.loadImage(imageUri, imageLoadingListener);
        }
    };

    @Override
    public void onClick(View v) {
        if (v == mDownloadButton) {
            if (mPhotoBitmap != null &&
                    FileUtils.saveBitmapToFile(mPhotoBitmap, getActivity())) {
                Toast.makeText(getActivity(), "Image saved to gallery", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Unable to save image", Toast.LENGTH_LONG).show();
            }
        }
    }
}
