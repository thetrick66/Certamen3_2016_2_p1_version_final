package cl.telematica.android.certamen3.models;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen3.activities.MainActivity;
import cl.telematica.android.certamen3.models.contracts.MyAsyncTaskExecutor;
import cl.telematica.android.certamen3.presenters.DataAdapterImpl;

/**
 * Created by franciscocabezas on 11/18/16.
 */

public class MyAsyncTaskExecutorImpl implements MyAsyncTaskExecutor {

    private RecyclerView.Adapter mAdapter;

    private static MyAsyncTaskExecutorImpl instance;

    public static MyAsyncTaskExecutorImpl getInstance() {
        if(instance == null) {
            instance = new MyAsyncTaskExecutorImpl();
        }
        return instance;
    }

    public void executeMyAsynctask(final MainActivity activity, final RecyclerView mRecyclerView, final int number) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute(){
            }

            @Override
            protected String doInBackground(Void... params) {
                String resultado = new HttpServerConnectionImpl().connectToServer("http://www.mocky.io/v2/582eea8b2600007b0c65f068", 15000);
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {

                if(result != null){
                    System.out.println(result);

                    //Why god... why
                    mAdapter = new DataAdapterImpl(activity, getFeeds(result),number);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        };

        task.execute();
    }
    public List<FeedImpl> getFeeds(String result) {
        List<FeedImpl> feeds = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject responseData = jsonObject.getJSONObject("responseData");
            JSONObject feedObj = responseData.getJSONObject("feed");

            JSONArray entries = feedObj.getJSONArray("entries");
            int size = entries.length();
            for(int i = 0; i < size; i++){
                JSONObject entryObj = entries.getJSONObject(i);
                FeedImpl feed = new FeedImpl();

                feed.setTitle(entryObj.optString("title"));
                feed.setLink(entryObj.optString("link"));
                feed.setAuthor(entryObj.optString("author"));
                feed.setPublishedDate(entryObj.optString("publishedDate"));
                feed.setContent(entryObj.optString("content"));
                feed.setImage(entryObj.optString("image"));

                feeds.add(feed);
            }

            return feeds;
        } catch (JSONException e) {
            e.printStackTrace();
            return feeds;
        }
    }

}
