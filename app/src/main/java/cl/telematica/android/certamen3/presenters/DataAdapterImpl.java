package cl.telematica.android.certamen3.presenters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import cl.telematica.android.certamen3.R;
import cl.telematica.android.certamen3.models.DataBaseClass;
import cl.telematica.android.certamen3.models.FeedImpl;
import cl.telematica.android.certamen3.presenters.contracts.DataAdapter;

/**
 * Created by franciscocabezas on 11/18/16.
 */

public class DataAdapterImpl extends RecyclerView.Adapter<DataAdapterImpl.ViewHolder> implements DataAdapter{
    private int numero;
    private List<FeedImpl> mDataset;
    private Context mContext;
    private DataBaseClass myDB;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public Button mAddBtn;

        public ViewHolder(View v, int numero) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textTitle);
            mImageView = (ImageView) v.findViewById(R.id.imgBackground);
            mAddBtn = (Button) v.findViewById(R.id.add_btn);
            if (numero==2){
                mAddBtn.setVisibility(View.GONE);
            }
        }
    }

    public DataAdapterImpl(Context mContext, List<FeedImpl> myDataset , int number) {
        this.mContext = mContext;
        this.mDataset = myDataset;
        myDB = new DataBaseClass(mContext);
        this.numero = number;
    }

    @Override
    public DataAdapterImpl.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v, numero);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final FeedImpl feed = mDataset.get(position);

        holder.mTextView.setText(feed.getTitle());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = feed.getLink();
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mContext.startActivity(browserIntent);
            }
        });

        Glide.with(mContext).load(feed.getImage()).into(holder.mImageView);
        Cursor total = myDB.getAllData();
        if(total.getCount() == 0 && numero == 2) {
            Toast.makeText(mContext, "USTED NO POSEE FAVORITOS", Toast.LENGTH_LONG).show();
        }
        Cursor res = myDB.getThatData(feed.getLink());
        if(res.getCount() == 0) {feed.setFavorite(false);}
        else{ feed.setFavorite(true);}

        if(feed.isFavorite()) {
            holder.mAddBtn.setText(mContext.getString(R.string.added));
        } else {
            holder.mAddBtn.setText(mContext.getString(R.string.like));
        }
        holder.mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * In this section, you have to manage the add behavior on local database
                 */
                feed.setFavorite(!feed.isFavorite());
                if(feed.isFavorite()) {
                    myDB.insertData(feed.getTitle(),
                            feed.getLink(),
                            feed.getAuthor(),
                            feed.getPublishedDate(),
                            feed.getContent(),
                            feed.getImage());
                    holder.mAddBtn.setText(mContext.getString(R.string.added));
                } else {
                    myDB.deleteData(feed.getLink());
                    holder.mAddBtn.setText(mContext.getString(R.string.like));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
