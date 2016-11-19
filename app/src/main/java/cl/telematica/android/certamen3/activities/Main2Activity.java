package cl.telematica.android.certamen3.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cl.telematica.android.certamen3.R;
import cl.telematica.android.certamen3.presenters.Certamen3PresenterImpl;
import cl.telematica.android.certamen3.presenters.contracts.Certamen3Presenter;

/**
 * Created by Patricio on 18-11-2016..
 */
public class Main2Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Certamen3Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        createMyRecyclerView();
        presenter = new Certamen3PresenterImpl(this, mRecyclerView);
    }
    public void createMyRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

}
