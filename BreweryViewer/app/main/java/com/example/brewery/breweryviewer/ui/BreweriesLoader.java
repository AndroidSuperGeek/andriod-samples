package com.example.brewery.breweryviewer.ui;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.brewery.breweryviewer.model.Brewery;

import java.util.List;


public class BreweriesLoader extends AsyncTaskLoader<List<Brewery>>{

    public BreweriesLoader(Context context) {
        super(context);
    }

    @Override
    public List<Brewery> loadInBackground() {
        BreweryController.getInstance().loadAllBreweries(new BreweriesCallback() {
            @Override
            public List<Brewery> breweriesLoaded(String status,List<Brewery> list) {
                return list;
            }
        });
        return null;
    }

    public interface BreweriesCallback {
        List<Brewery> breweriesLoaded(String status, List<Brewery> list);
    }

}



