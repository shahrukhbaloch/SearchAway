
package com.shahrukhbaloch.searchaway.ui.adapters;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.DraweeView;
import com.shahrukhbaloch.searchaway.R;
import com.shahrukhbaloch.searchaway.data.InstagramPhoto;
import com.shahrukhbaloch.searchaway.ui.activities.MainActivity;
import com.shahrukhbaloch.searchaway.ui.dialogs.PhotoDialogFragment;
import com.shahrukhbaloch.searchaway.ui.views.SquareImageView;

import java.util.ArrayList;

public class ReycleViewAdapter extends RecyclerView.Adapter<ReycleViewAdapter.ViewHolder> {

    private ArrayList<InstagramPhoto> listPhotos;
    private Activity mActivity;

    public ReycleViewAdapter(ArrayList<InstagramPhoto> photos, Activity activity) {
        this.listPhotos = photos;
        this.mActivity = activity;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SquareImageView imgPhoto;
        private DraweeView imgPhoto2;

        public ViewHolder(View itemLayoutView, int position) {
            super(itemLayoutView);
            imgPhoto2 = (DraweeView) itemLayoutView.findViewById(R.id.imgPhoto);
            imgPhoto2.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MainActivity activity = (MainActivity) ReycleViewAdapter.this.mActivity;
            activity.addAndShowDialogFragment(PhotoDialogFragment.newInstance(listPhotos.get(getPosition()).getImages().getLow_resolution().getUrl()), "dummmy tag");
        }

    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listPhotos.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder arg0, int arg1) {

        ViewHolder viewHolder = (ViewHolder) arg0;

        try {
//
            viewHolder.imgPhoto2.setImageURI(Uri.parse(listPhotos.get(arg1).getImages().getStandard_resolution().getUrl()));

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View itemLayoutView = LayoutInflater.from(arg0.getContext())
                .inflate(R.layout.list_item_photo, arg0, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView, arg1);
        return viewHolder;
    }

}
