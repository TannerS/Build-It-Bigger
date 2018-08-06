package io.tanners.libs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.tanners.libs.R;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
        // Required empty public constructor
    }

    public static MainActivityFragment newInstance() {
        return new MainActivityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_activity, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}


////        AdView mAdView = (AdView) root.findViewById(R.id.adView);
////        // Create an ad request. Check logcat output for the hashed device ID to
////        // get test ads on a physical device. e.g.
////        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
////        AdRequest adRequest = new AdRequest.Builder()
////                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
////                .build();
////        mAdView.loadAd(adRequest);