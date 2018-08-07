package io.tanners.libs;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;

import io.tanners.libs.backend.myApi.MyApi;
import io.tanners.libs.backend.myApi.model.Joke;
//import io.tanners.libs.jokester.model.Joke;

public class MainActivityFragmentRoot extends Fragment {
//    protected MainViewModel mMainViewModel;
    protected Context mContext;
//    protected LiveData<JokeWrapper> mWrapper;
    protected MutableLiveData<Joke> mJoke;
    protected static MyApi myApiService = null;


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

        new EndpointsAsyncTask<Joke>(createEndpointCallBack()).execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private EndpointsAsyncTask.EndpointUtil<Joke> createEndpointCallBack()
    {
        return new EndpointsAsyncTask.EndpointUtil<Joke>() {
            @Override
            public void onPostDo(Joke mResult) {
                mJoke.setValue(mResult);
                Log.i("DATA", mJoke.getValue().getMJoke());
                // TODO call acivity

            }

            @Override
            public Joke onDo() {
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
                    return myApiService.sendJoke().execute();
                } catch (IOException e) {
                    return null;
                }
            }
        };
    }

//    private static class EndpointsAsyncTask extends AsyncTask<Void, Void, Joke> {
//        private static MyApi myApiService = null;
//        private EndpointUtil mCallback;
//
//        public EndpointsAsyncTask(EndpointUtil mCallback)
//        {
//            this.mCallback = mCallback;
//        }
//
//        @Override
//        protected Joke doInBackground(Void... params) {
//            if(myApiService == null) {  // Only do this once
//                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                        new AndroidJsonFactory(), null)
//                        // options for running against local devappserver
//                        // - 10.0.2.2 is localhost's IP address in Android emulator
//                        // - turn off compression when running against local devappserver
//                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                            @Override
//                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                                abstractGoogleClientRequest.setDisableGZipContent(true);
//                            }
//                        });
//                myApiService = builder.build();
//            }
//
//            try {
//                return myApiService.sendJoke().execute().getData();
//
//            } catch (IOException e) {
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(Joke mResult) {
//
//
//            mJoke.setValue(mResult);
//        }
//
//        public interface EndpointUtil {
//            public void onPostDo();
//            public void onDo();
//        }
//    }

}


