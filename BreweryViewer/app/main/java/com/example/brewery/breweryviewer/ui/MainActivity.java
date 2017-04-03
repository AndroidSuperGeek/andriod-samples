package com.example.brewery.breweryviewer.ui;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.brewery.breweryviewer.R;
import com.example.brewery.breweryviewer.model.Brewery;
import com.example.brewery.breweryviewer.model.Data;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private  BreweriesFragmentLoader mDataFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.breweries_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BreweriesAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        mDataFragment = (BreweriesFragmentLoader) fm.findFragmentByTag(BreweriesFragmentLoader.FRAGMENT_TAG);

        // create the fragment and data the first time
        if (mDataFragment == null) {
            // add the fragment
            mDataFragment = new BreweriesFragmentLoader();
            mDataFragment.setAdapter(mAdapter);
            fm.beginTransaction().add(mDataFragment, BreweriesFragmentLoader.FRAGMENT_TAG).commit();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(isFinishing()) {
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().remove(mDataFragment).commit();
        }
    }

}
