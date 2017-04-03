package com.example.brewery.breweryviewer.ui;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;

import com.example.brewery.breweryviewer.model.Brewery;

import java.util.List;

public class BreweriesFragmentLoader extends Fragment implements LoaderManager.LoaderCallbacks<List<Brewery>> {
    RecyclerView.Adapter adapter;
    private int LOADER_ID = 1;

    public static final String FRAGMENT_TAG = "BreweriesFragmentLoader";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // retain this fragment
        getLoaderManager().initLoader(LOADER_ID,null,this).forceLoad();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override public Loader<List<Brewery>> onCreateLoader(int id, Bundle args) {
        return new BreweriesLoader(getActivity());
    }

    @Override public void onLoadFinished(Loader<List<Brewery>> loader, List<Brewery> data) {
        ((BreweriesAdapter)adapter).setData(data);
    }

    @Override public void onLoaderReset(Loader<List<Brewery>> loader) {
        ((BreweriesAdapter)adapter).setData(null);
    }

}