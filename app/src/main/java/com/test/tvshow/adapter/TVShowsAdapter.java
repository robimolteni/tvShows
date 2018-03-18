package com.test.tvshow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import  com.test.tvshow.R;
import com.test.tvshow.model.TVShow;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by roberto on 14/03/18.
 */

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.TVShowViewHolder> {

    private final Context mContext;
    private List<TVShow> tvShowsList;

    public TVShowsAdapter(Context context, List<TVShow>  tvShowsList){
        this.mContext = context;
        this.tvShowsList = tvShowsList;
    }

    @Override
    public TVShowsAdapter.TVShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.tvshow_item, parent, false);
        return new TVShowViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(TVShowsAdapter.TVShowViewHolder holder, int position) {
        TVShow singleShow = tvShowsList.get(position);
        holder.showTitleText.setText(singleShow.getName());
        holder.voteAverageText.setText(String.valueOf(singleShow.getVote_average()));
        Picasso.with(mContext).load(singleShow.getBackdropImageURL()).into(holder.showImage);
    }

    @Override
    public int getItemCount() {
        return tvShowsList.size();
    }

    public class TVShowViewHolder extends RecyclerView.ViewHolder {

        public ImageView showImage;
        public TextView showTitleText;
        public TextView voteAverageText;

        public TVShowViewHolder(final View view) {
            super(view);
            showImage = (ImageView) view.findViewById(R.id.showImage);
            showTitleText = (TextView) view.findViewById(R.id.showTitleText);
            voteAverageText = (TextView) view.findViewById(R.id.voteAverageText);
        }
    }
}
