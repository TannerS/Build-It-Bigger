package io.tanners.libs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import io.tanners.libs.backend.myApi.model.JokeBean;

public class MainActivityFragment extends MainActivityFragmentRoot {

    public MainActivityFragment() {
        // Required empty public constructor
    }

    public static MainActivityFragment newInstance() {
        return new MainActivityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_main_activity, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button mJokeButton = mView.findViewById(R.id.findJokeBtn);

//        mJokeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
       mJokeButton.setOnClickListener(this);

       setUpAds();
    }

    private void loadJoke()
    {
        new EndpointsAsyncTask<JokeBean>(createEndpointCallBack()).execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void setUpAds()
    {
        AdView mAdView = (AdView) mView.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {
        loadJoke();
    }
}


