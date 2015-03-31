
package com.shahrukhbaloch.searchaway.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.inject.Inject;
import com.shahrukhbaloch.searchaway.R;
import com.shahrukhbaloch.searchaway.data.InstagramPhoto;
import com.shahrukhbaloch.searchaway.data.SearchQuery;
import com.shahrukhbaloch.searchaway.data.wrappers.InstagramPhotosWrapper;
import com.shahrukhbaloch.searchaway.ui.adapters.ReycleViewAdapter;
import com.shahrukhbaloch.searchaway.web.WebCalls;
import com.shahrukhbaloch.searchaway.web.WebErrorHandler;
import com.shahrukhbaloch.searchaway.web.WebResponse;
import com.shahrukhbaloch.searchaway.web.WebService;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import roboguice.event.EventManager;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

public class PhotoListFragment extends RoboFragment {

    private int PHOTOS_GRID_COLUMNS = 2;

    public static PhotoListFragment newInstance() {
        return new PhotoListFragment();
    }

    private WebService webService;

    private ArrayList<InstagramPhoto> listPhotos;

    private ReycleViewAdapter adapterPhotos;

    @Inject
    protected EventManager eventManager;

    @InjectView(R.id.gridViewPhotos)
    private RecyclerView mRecyclerView;

    private RecyclerView.AdapterDataObserver adapterDataObserver;

    @Inject
    public WebCalls webCalls;

    @Inject
    public Bus mBus;

    //@Inject protected WebCalls.WebCallsListener webCallsListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listPhotos = new ArrayList<InstagramPhoto>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_photos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), PHOTOS_GRID_COLUMNS));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapterPhotos = new ReycleViewAdapter(listPhotos);
        mRecyclerView.setAdapter(adapterPhotos);
        mBus.register(this);
        webCalls.getDefaultPhotos();

    }

    @Subscribe
    public void populateRecyclerView(InstagramPhotosWrapper instagramPhotosWrapper) {
        adapterPhotos.listPhotos.clear();
        adapterPhotos.listPhotos.addAll(instagramPhotosWrapper.getPhotos());
        adapterPhotos.notifyDataSetChanged();
    }

    @Subscribe
    public void doBusTest(SearchQuery searchQuery) {
        Log.d(getClass().getSimpleName(), searchQuery.getSearchString());
    }


//    @OnItemClick(R.id.gridViewPhotos)
//    void onItemClick(int position) {
//        Toast.makeText(getActivity(), "Clicked position " + position + "!", Toast.LENGTH_SHORT).show();
//
//    }


//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position,
//            long id) {
//
////        InstaPhoto item = (InstaPhoto) adapterPhotos.getItem(position);
////
////        String gsonPhoto = gson.toJson(item, InstaPhoto.class);
////
////        addAndShowDialogFragment(ImageDialogFragment.newInstance(gsonPhoto));
//
//    }

//    public void addAndShowDialogFragment(
//            DialogFragment dialog) {
//        FragmentTransaction transaction = getFragmentManager()
//                .beginTransaction();
//        dialog.show(transaction, "tag");
//
//    }


//    private Callback<WebResponse<ArrayList<InstagramPhoto>>> photosCallback = new Callback<WebResponse<ArrayList<InstagramPhoto>>>() {
//
//        @Override
//        public void success(WebResponse<ArrayList<InstagramPhoto>> arg0,
//                            Response arg1) {
//
//            if (arg0.getResult() != null) {
//
//                adapterPhotos.listPhotos.clear();
//                adapterPhotos.listPhotos.addAll(arg0.getResult());
//                adapterPhotos.notifyDataSetChanged();
//            }
//
//        }
//
//        @Override
//        public void failure(RetrofitError arg0) {
//
//            WebErrorHandler.handleInstagramError(getActivity(), arg0);
////            Toast.makeText(
////                    getActivity(),
////                    "Internet Connection not found, Please connect to the internet to continue",
////                    Toast.LENGTH_LONG).show();
//        }
//    };
}
