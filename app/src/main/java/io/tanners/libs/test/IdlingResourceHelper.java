package io.tanners.libs.test;

import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

/**
 * Credit goes too https://pavneetsblog.wordpress.com/2017/10/13/testing-fragments-and-network-call-using-espresso/
 */
public class IdlingResourceHelper  {

    private static final String RESOURCE = "JOKSTER";
    private static IdlingResourceHelper mInstance;
    private CountingIdlingResource mCountingIdlingResource;



//    IdlingPolicies.ssetMasterPolicyTimeout(3, MINUTES);
//    IdlingPolicies.setIdlingResourceTimeout(3, MINUTES);

    public IdlingResourceHelper() {
        this.mCountingIdlingResource = new CountingIdlingResource(RESOURCE, true);
    }

    public void increment() {
        mCountingIdlingResource.increment();
    }

    public void decrement() {
        mCountingIdlingResource.decrement();
    }

    public IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }

    public static IdlingResourceHelper getInstance()
    {
        if (mInstance == null)
            mInstance = new IdlingResourceHelper();

        return mInstance;
    }
}
