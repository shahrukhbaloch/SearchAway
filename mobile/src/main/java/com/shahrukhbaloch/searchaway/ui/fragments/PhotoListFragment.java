
package com.shahrukhbaloch.searchaway.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shahrukhbaloch.searchaway.R;
import com.shahrukhbaloch.searchaway.data.InstagramPhoto;
import com.shahrukhbaloch.searchaway.ui.adapters.ReycleViewAdapter;
import com.shahrukhbaloch.searchaway.web.WebResponse;
import com.shahrukhbaloch.searchaway.web.WebService;
import com.shahrukhbaloch.searchaway.web.WebServiceFactory;

import java.util.ArrayList;

import butterknife.OnItemClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PhotoListFragment extends Fragment {

    public static PhotoListFragment newInstance() {
        return new PhotoListFragment();
    }

    private WebService webService;

    private ArrayList<InstagramPhoto> listPhotos;
 
    private ReycleViewAdapter adapterPhotos;

//    @InjectView(R.id.gridViewPhotos)
    private RecyclerView mRecyclerView;

    private RecyclerView.AdapterDataObserver adapterDataObserver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listPhotos = new ArrayList<InstagramPhoto>();

        webService = WebServiceFactory.getInstanceWithBasicGsonConversion();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_photos, container, false);
//        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.gridViewPhotos);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapterPhotos = new ReycleViewAdapter(listPhotos);
        mRecyclerView.setAdapter(adapterPhotos);

        webService.getSelfies(new Callback<WebResponse<ArrayList<InstagramPhoto>>>() {

            @Override
            public void success(WebResponse<ArrayList<InstagramPhoto>> arg0,
                    Response arg1) {

                if (arg0.getResult() != null) {

                    adapterPhotos.listPhotos.addAll(arg0.getResult());
                    adapterPhotos.notifyDataSetChanged();
                }

            }

            @Override
            public void failure(RetrofitError arg0) {

                Toast.makeText(
                        getActivity(),
                        "Internet Connection not found, Please connect to the internet to continue",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @OnItemClick(R.id.gridViewPhotos)
    void onItemClick(int position){
        Toast.makeText(getActivity(), "Clicked position " + position + "!", Toast.LENGTH_SHORT).show();

}

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
}
