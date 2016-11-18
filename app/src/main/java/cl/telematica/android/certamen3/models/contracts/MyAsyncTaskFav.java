package cl.telematica.android.certamen3.models.contracts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cl.telematica.android.certamen3.activities.Main2Activity;
import cl.telematica.android.certamen3.models.FeedImpl;

/**
 * Created by Patricio on 18-11-2016.
 */

public interface MyAsyncTaskFav {
    List<FeedImpl> getFeeds(Context activity);
    void executeMyAsynctask(final Main2Activity activity, final RecyclerView mRecyclerView, int number);
}
