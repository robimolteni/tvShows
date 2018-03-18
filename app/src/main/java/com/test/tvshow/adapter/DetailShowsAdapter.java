package com.test.tvshow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.tvshow.R;
import com.test.tvshow.model.TVShow;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by roberto on 16/03/18.
 */

public class DetailShowsAdapter extends RecyclerView.Adapter<DetailShowsAdapter.DetailShowViewHolder> {

    private final Context mContext;
    private List<TVShow> tvShowsList;

    public DetailShowsAdapter(Context context, List<TVShow>  tvShowsList){
        this.mContext = context;
        this.tvShowsList = tvShowsList;
    }

    @Override
    public DetailShowsAdapter.DetailShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.detail_item, parent, false);
        return new DetailShowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DetailShowsAdapter.DetailShowViewHolder holder, int position) {
        TVShow singleShow = tvShowsList.get(position);
        holder.showTitleText.setText(singleShow.getName());
        Picasso.with(mContext).load(singleShow.getBackdropImageURL()).into(holder.imageSimilarShow);
    }

    @Override
    public int getItemCount() {
        return tvShowsList.size();
    }

    public class DetailShowViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageSimilarShow;
        public TextView showTitleText;

        public DetailShowViewHolder(final View view) {
            super(view);
            imageSimilarShow = (ImageView) view.findViewById(R.id.imageSimilarShow);
            showTitleText = (TextView) view.findViewById(R.id.showTitleText);
        }
    }
}