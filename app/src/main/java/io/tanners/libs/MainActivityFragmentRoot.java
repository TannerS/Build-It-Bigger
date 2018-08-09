package io.tanners.libs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;
import io.tanners.libs.backend.myApi.MyApi;
import io.tanners.libs.backend.myApi.model.JokeBean;
import io.tanners.libs.display.JokeDisplayActivity;
import io.tanners.libs.network.EndpointsAsyncTask;

public class MainActivityFragmentRoot extends Fragment implements View.OnClickListener  {
    protected Context mContext;
    protected static MyApi myApiService = null;
    protected View mView;
    protected AsyncFunctionTester mTestCallBack;

    public MainActivityFragmentRoot() {
        // Required empty public constructor
    }

    public static MainActivityFragmentRoot newInstance() {
        return new MainActivityFragmentRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AsyncFunctionTester) {
            mTestCallBack = (AsyncFunctionTester) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AsyncFunctionTester");
        }

        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main_activity, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button mJokeButton = mView.findViewById(R.id.findJokeBtn);
        mJokeButton.setOnClickListener(this);
    }

    protected EndpointsAsyncTask.EndpointUtil<JokeBean> createEndpointCallBack()
    {
        return new EndpointsAsyncTask.EndpointUtil<JokeBean>() {
            @Override
            public void onPostDo(JokeBean mResult) {

                if(mResult != null) {
                    Intent mJokeAndLib = new Intent(mContext, JokeDisplayActivity.class);
                    mJokeAndLib.putExtra(JokeDisplayActivity.JOKSTER_DISPLAY_DATA_KEY, mResult.getMJoke());
                    startActivity(mJokeAndLib);
                }

                mTestCallBack.stopIdlingResource();
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

    private void loadJoke()
    {
        new EndpointsAsyncTask<JokeBean>(createEndpointCallBack()).execute();
    }

    @Override
    public void onClick(View v) {
        mTestCallBack.startIdlingResource();
        loadJoke();
    }

    public interface AsyncFunctionTester
    {
        public void startIdlingResource();
        public void stopIdlingResource();
    }
}


