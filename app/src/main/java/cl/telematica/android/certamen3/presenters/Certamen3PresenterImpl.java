package cl.telematica.android.certamen3.presenters;

import android.support.v7.widget.RecyclerView;

import cl.telematica.android.certamen3.activities.Main2Activity;
import cl.telematica.android.certamen3.activities.MainActivity;
import cl.telematica.android.certamen3.models.MyAsyncTaskExecutorImpl;
import cl.telematica.android.certamen3.models.MyAsyncTaskFavImpl;
import cl.telematica.android.certamen3.presenters.contracts.Certamen3Presenter;

/**
 * Created by Patricio on 18-11-2016.
 */

public class Certamen3PresenterImpl implements Certamen3Presenter{

    public Certamen3PresenterImpl(final MainActivity activity, final RecyclerView mRecyclerView){
        int number = 1;
        MyAsyncTaskExecutorImpl.getInstance().executeMyAsynctask(activity, mRecyclerView,number);
    }

    public Certamen3PresenterImpl(final Main2Activity activity, final RecyclerView mRecyclerView){
        int number = 2;
        MyAsyncTaskFavImpl.getInstance().executeMyAsynctask(activity, mRecyclerView,number);
    }

}
