package io.tanners.libs;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;
import io.tanners.libs.backend.myApi.MyApi;
import io.tanners.libs.backend.myApi.model.JokeBean;

public class MainActivityFragmentRoot extends Fragment implements View.OnClickListener  {
    protected Context mContext;
    protected MutableLiveData<JokeBean> mJoke;
    protected static MyApi myApiService = null;
    protected View mView;

    public MainActivityFragmentRoot() {
        // Required empty public constructor
    }

    public static MainActivityFragmentRoot newInstance() {
        return new MainActivityFragmentRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected EndpointsAsyncTask.EndpointUtil<JokeBean> createEndpointCallBack()
    {
        return new EndpointsAsyncTask.EndpointUtil<JokeBean>() {
            @Override
            public void onPostDo(JokeBean mResult) {
                mJoke = new MutableLiveData<JokeBean>();
                mJoke.setValue(mResult);




                Log.i("JOKE", mResult.getMJoke());
                // TODO call acivity

            }

            @Override
            public JokeBean onDo() {
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

    @Override
    public void onClick(View v) {
        throw new IllegalStateException("Need to override in child class");
    }
}


