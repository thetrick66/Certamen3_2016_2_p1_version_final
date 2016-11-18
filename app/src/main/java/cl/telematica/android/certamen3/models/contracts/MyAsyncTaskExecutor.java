package cl.telematica.android.certamen3.models.contracts;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import cl.telematica.android.certamen3.activities.MainActivity;
import cl.telematica.android.certamen3.models.FeedImpl;

/**
 * Created by Patricio on 18-11-2016.
 */

public interface MyAsyncTaskExecutor {
    List<FeedImpl> getFeeds(String result);
    void executeMyAsynctask(final MainActivity activity, final RecyclerView mRecyclerView, int number);
}
