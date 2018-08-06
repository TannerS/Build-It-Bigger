package io.tanners.libs.aac;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;
import io.tanners.libs.jokester.model.JokeWrapper;

public class JokeRepository {
    private static JokeRepository sInstance;
    private static MutableLiveData<JokeWrapper> mData;
    private Context mContext;

    /**
     * @param mContext
     * @throws IOException
     */
    public JokeRepository(Context mContext) throws IOException {
        this.mContext = mContext;
    }

    public static JokeRepository getInstance(Context mContext) {
        if (sInstance == null) {
            try {
                sInstance = new JokeRepository(mContext);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sInstance;
    }

    public LiveData<JokeWrapper> getData()
    {
        return mData;
    }

    private static class EndpointsAsyncTask extends AsyncTask<Void, Void, JokeWrapper> {
        private static MyApi myApiService = null;
        private Context context;

        @Override
        protected JokeWrapper doInBackground(Void... params) {
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
                return myApiService.sendJoke().execute().getData();

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






