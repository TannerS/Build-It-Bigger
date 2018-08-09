package io.tanners.libs.network;

import android.os.AsyncTask;

public class EndpointsAsyncTask<T> extends AsyncTask<Void, Void, T> {
    private EndpointsAsyncTask.EndpointUtil mCallback;

    public EndpointsAsyncTask(EndpointsAsyncTask.EndpointUtil mCallback)
    {
        this.mCallback = mCallback;
    }

    @Override
    protected T doInBackground(Void... params) {
        return (T) this.mCallback.onDo();
    }

    @Override
    protected void onPostExecute(T mResult) {
        mCallback.onPostDo(mResult);
    }

    public interface EndpointUtil<T> {
        public void onPostDo(T mData);
        public T onDo();
    }
}