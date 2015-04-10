package com.shahrukhbaloch.searchaway.ui.dialogs;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.shahrukhbaloch.searchaway.R;
import com.shahrukhbaloch.searchaway.ui.views.DraweeTouchImageView;
import com.shahrukhbaloch.searchaway.util.FileUtils;
import com.software.shell.fab.ActionButton;

import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectView;

/**
 * Created by shahrukh.baloch on 4/1/15.
 */
public class PhotoDialogFragment extends RoboDialogFragment {

    private static String KEY_IMAGE_URL = "image url key";
    private ImageLoader imageLoader;
    private Bitmap mPhotoBitmap;

    @InjectView(R.id.img_photo_big)
    private DraweeTouchImageView mDraweeTouchImageView;

    @InjectView(R.id.btn_download)
    private ActionButton mDownloadButton;

    @InjectView(R.id.progress_view)
    private CircularProgressView mProgressView;


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
        mDownloadButton.hide();
        mDownloadButton.setShowAnimation(ActionButton.Animations.JUMP_FROM_RIGHT);
        mDownloadButton.setHideAnimation(ActionButton.Animations.JUMP_TO_RIGHT);
        mDraweeTouchImageView.setOnClickListener(draweeTouchImageViewListener);
        mDownloadButton.setOnClickListener(downloadButtonListener);
        if (getArguments() != null) {
            Bundle b = getArguments();
            String url = b.getString(KEY_IMAGE_URL);

//        Fresco Currently has a known bug where it cannot load URLs with a redirect, Going to use UIL until there's a fix
//        mDraweeTouchImageView.setImageURI(Uri.parse(url));
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
            mProgressView.setVisibility(View.INVISIBLE);
            imageLoader.displayImage(imageUri, mDraweeTouchImageView);
            mDraweeTouchImageView.setZoom(1);
            mPhotoBitmap = loadedImage;
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {
            imageLoader.loadImage(imageUri, imageLoadingListener);
        }
    };

    View.OnClickListener draweeTouchImageViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mDownloadButton.isHidden()) {
                mDownloadButton.playShowAnimation();
                mDownloadButton.show();
            } else {
                mDownloadButton.playHideAnimation();
                mDownloadButton.hide();
            }
        }
    };

    View.OnClickListener downloadButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            if (mPhotoBitmap != null &&
//                    FileUtils.saveBitmapToFile(mPhotoBitmap, getActivity())) {
//                Toast.makeText(getActivity(), "Image saved to gallery", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getActivity(), "Unable to save image", Toast.LENGTH_LONG).show();
//            }
            mProgressView.setVisibility(View.VISIBLE);
            new PhotoSaverTask().execute();
        }
    };


    private class PhotoSaverTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            return (mPhotoBitmap != null &&
                    FileUtils.saveBitmapToFile(mPhotoBitmap, getActivity()));
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (bool) {
                Toast.makeText(getActivity(), "Image saved to gallery", Toast.LENGTH_SHORT).show();
                mDownloadButton.setOnClickListener(null);
            } else {
                Toast.makeText(getActivity(), "Unable to save image", Toast.LENGTH_SHORT).show();
            }
            mProgressView.setVisibility(View.INVISIBLE);
        }
    }

//    @Override
//    public void onClick(View v) {
//        if (v == mDraweeTouchImageView) {
//            if (mDownloadButton.isHidden()) {
//                mDownloadButton.playShowAnimation();
//                mDownloadButton.show();
//            } else {
//                mDownloadButton.playHideAnimation();
//                mDownloadButton.hide();
//            }
//        } else if (v == mDownloadButton) {
//            if (mPhotoBitmap != null &&
//                    FileUtils.saveBitmapToFile(mPhotoBitmap, getActivity())) {
//                Toast.makeText(getActivity(), "Image saved to gallery", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getActivity(), "Unable to save image", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
}
