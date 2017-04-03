package com.example.brewery.breweryviewer.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.brewery.breweryviewer.R;
import com.example.brewery.breweryviewer.model.Brewery;

import java.util.List;


public class BreweriesAdapter extends RecyclerView.Adapter<BreweriesAdapter.ViewHolder> {

    static final String TAG = BreweriesAdapter.class.getSimpleName();

    private List<Brewery> breweries = null;

    public BreweriesAdapter (List<Brewery> list){
        breweries = list;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView breweryImage;
        public TextView breweryName;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            breweryImage = (ImageView) itemView.findViewById(R.id.brewery_image);
            breweryName = (TextView) itemView.findViewById(R.id.brewery_name);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BreweriesAdapter() {
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BreweriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brewery_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if ( breweries != null ) {

            Brewery breweryData = breweries.get(position);
            if ( breweryData != null && breweryData.getData() != null) {
                holder.breweryName.setText(breweryData.getData().getName());
//                holder.breweryImage.setImageDrawable(breweryData.getData().getName());
            }
        }

    }

    @Override
    public int getItemCount() {
        if( breweries != null)
            return breweries.size();
        else
            return 0;
    }

    public void setData(List<Brewery> data){
        breweries = data;
        notifyDataSetChanged();
    }


}
