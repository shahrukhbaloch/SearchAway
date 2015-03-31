
package com.shahrukhbaloch.searchaway.ui.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.DraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shahrukhbaloch.searchaway.R;
import com.shahrukhbaloch.searchaway.data.InstagramPhoto;
import com.shahrukhbaloch.searchaway.ui.views.SquareImageView;

import java.util.ArrayList;

public class ReycleViewAdapter extends RecyclerView.Adapter<ReycleViewAdapter.ViewHolder> {

    private ImageLoader imgLoader;
    public ArrayList<InstagramPhoto> listPhotos;
//    private HomeFragment fragment;

    public ReycleViewAdapter(ArrayList<InstagramPhoto> photos) {
        this.listPhotos = photos;
//        this.fragment = fragment;
        this.imgLoader = ImageLoader.getInstance();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SquareImageView imgPhoto;
        DraweeView imgPhoto2;

        public ViewHolder(View itemLayoutView, int position) {
            super(itemLayoutView);
            imgPhoto = (SquareImageView) itemLayoutView.findViewById(R.id.imgPhoto);
            imgPhoto2 = (DraweeView)itemLayoutView.findViewById(R.id.imgPhoto2);
//            imgPhoto.setOnClickListener(this);
        }


//        @Override
//        public void onClick(View v) {
//            // TODO Auto-generated method stub
//
//            String gsonPhoto = new Gson().toJson(listPhotos.get(getPosition()), InstagramPhoto.class);
////            fragment.addAndShowDialogFragment(ImageDialogFragment.newInstance(gsonPhoto));
//
//        }
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
//            imgLoader.displayImage(
//                    listPhotos.get(arg1).getImages().getStandard_resolution().getUrl(),
//                    viewHolder.imgPhoto);
            viewHolder.imgPhoto2.setImageURI(Uri.parse(listPhotos.get(arg1).getImages().getLow_resolution().getUrl()));

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
