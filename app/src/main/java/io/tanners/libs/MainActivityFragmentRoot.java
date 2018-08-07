package io.tanners.libs;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

import io.tanners.libs.aac.JokeRepository;
import io.tanners.libs.aac.MainViewModel;
import io.tanners.libs.aac.MainViewModelFactory;
import io.tanners.libs.backend.myApi.MyApi;
import io.tanners.libs.jokester.model.JokeWrapper;

public class MainActivityFragmentRoot extends Fragment {
    protected MainViewModel mMainViewModel;
    protected Context mContext;
    protected LiveData<JokeWrapper> mWrapper;

    public MainActivityFragmentRoot() {
        // Required empty public constructor
    }

    public static MainActivityFragmentRoot newInstance() {
        return new MainActivityFragmentRoot();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_main_activity, container, false);
//        return view;
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

//    private void setupViewModel() {
//        mMainViewModel = ViewModelProviders.of(
//                this,
//                new MainViewModelFactory(
//                        getActivity().getApplication(),
//                        JokeRepository.getInstance(mContext)
//                )).get(MainViewModel.class);
//    }

//    private void getdata()
//    {
//        try {
//            mMainViewModel.getmRecipes();
//
//            mWrapper.observe(getActivity(), new Observer<JokeWrapper>() {
//                @Override
//                public void onChanged(@Nullable JokeWrapper jokeWrapper) {
//
//
//
//
//                }
//            });
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static class EndpointsAsyncTask extends AsyncTask<Integer, Void, JokeWrapper> {
        private static MyApi myApiService = null;
        private Context context;

        @Override
        protected JokeWrapper doInBackground(Integer... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                myApiService = builder.build();
            }

            try {
                return myApiService.sendJoke(params[0]).execute().getData();

            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(JokeWrapper mResult) {
            mData.setValue(mResult);
        }
    }

}


