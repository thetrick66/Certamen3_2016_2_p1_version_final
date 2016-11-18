package cl.telematica.android.certamen3.models;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen3.activities.Main2Activity;
import cl.telematica.android.certamen3.models.contracts.MyAsyncTaskFav;
import cl.telematica.android.certamen3.presenters.DataAdapterImpl;

/**
 * Created by Patricio on 18-11-2016.
 */

public class MyAsyncTaskFavImpl implements MyAsyncTaskFav{

    private RecyclerView.Adapter mAdapter;

    private static MyAsyncTaskFavImpl instance;

    public static MyAsyncTaskFavImpl getInstance() {
        if(instance == null) {
            instance = new MyAsyncTaskFavImpl();
        }
        return instance;
    }

    public void executeMyAsynctask(final Main2Activity activity, final RecyclerView mRecyclerView, final int number) {
        AsyncTask<Void, Void, List<FeedImpl>> task = new AsyncTask<Void, Void, List<FeedImpl>>() {

            @Override
            protected void onPreExecute(){
            }

            @Override
            protected List<FeedImpl> doInBackground(Void... params) {
                List<FeedImpl> resultado = getFeeds(activity);
                return resultado;
            }

            @Override
            protected void onPostExecute(List<FeedImpl> result) {

                if(result != null){
                    System.out.println(result);

                    //Why god... why
                    mAdapter = new DataAdapterImpl(activity, result,number);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        };

        task.execute();
    }
    public List<FeedImpl> getFeeds(Context activity) {
        List<FeedImpl> feeds = new ArrayList<>();
        DataBaseClass myDB = new DataBaseClass(activity);

        Cursor res = myDB.getAllData();
        if(res.getCount() == 0) {
        }
        else {
            while (res.moveToNext()) {
                FeedImpl feed = new FeedImpl();
                feed.setTitle(res.getString(1));
                feed.setLink(res.getString(2));
                feed.setAuthor(res.getString(3));
                feed.setPublishedDate(res.getString(4));
                feed.setContent(res.getString(5));
                feed.setImage(res.getString(6));

                feeds.add(feed);
            }
        }
        return feeds;
    }

}
