package com.test.tvshow.mvp.detailshow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.tvshow.App;
import com.test.tvshow.R;
import com.test.tvshow.adapter.DetailShowsAdapter;
import com.test.tvshow.adapter.RecyclerItemClickListener;
import com.test.tvshow.model.TVShow;
import com.test.tvshow.model.TotalShow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailShowsMVPInterface.View {

    @BindView(R.id.similarShowSection) RecyclerView recyclerView;
    @BindView(R.id.bigHeroImg) ImageView bigHeroImg;
    @BindView(R.id.overviewText) TextView overviewText;
    @BindView(R.id.titleText) TextView titleText;

    private DetailShowsAdapter adapter;
    private List<TVShow> tvShowList = new ArrayList<>();

    private int currentPage = 1;

    private boolean endOfList = false;
    private boolean isLoading = false;
    private LinearLayoutManager linearLayoutManager;

    @Inject
    DetailShowsMVPInterface.Presenter detailMvpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        ((App) getApplication()).getMVPComponent().injectDetail(this);

        final TVShow singleShow = (TVShow) getIntent().getParcelableExtra("parcel_data");
        titleText.setText(singleShow.getName());
        Picasso.with(this).load(singleShow.getPosterImageURL()).into(bigHeroImg);
        overviewText.setText(singleShow.getOverview());

        adapter = new DetailShowsAdapter(this, tvShowList);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        detailMvpPresenter.setView(this);
        detailMvpPresenter.getSimilarTVShows(singleShow.getId(), currentPage);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !endOfList) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                        isLoading = true;
                        currentPage++;
                        detailMvpPresenter.getSimilarTVShows(singleShow.getId(), currentPage);
                    }
                }
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        TVShow tvShow =  tvShowList.get(position);
                        Intent intent = new Intent(DetailActivity.this, DetailActivity.class);
                        intent.putExtra("parcel_data", tvShow);
                        startActivity(intent);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    @Override
    public void showSimilarTVShows(TotalShow tvShows) {
        if(currentPage == tvShows.getTotal_pages()) {
            endOfList = true;
        }
        tvShowList.addAll(tvShows.getResults());
        isLoading = false;
        if(adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        detailMvpPresenter.rxUnsubscribe();
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error_msg),
                Toast.LENGTH_LONG).show();
    }
}
