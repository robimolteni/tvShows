package com.test.tvshow.mvp.listshow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.test.tvshow.App;
import com.test.tvshow.R;
import com.test.tvshow.adapter.RecyclerItemClickListener;
import com.test.tvshow.adapter.TVShowsAdapter;
import com.test.tvshow.model.TVShow;
import com.test.tvshow.model.TotalShow;
import com.test.tvshow.mvp.detailshow.DetailActivity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TVShowMVPInteface.View {

    @BindView(R.id.tvshowrecyclerview) RecyclerView recyclerView;
    @BindView(R.id.progressBar) RelativeLayout progressBar;

    @Inject
    TVShowMVPInteface.Presenter mvpPresenter;
    private TVShowsAdapter adapter;
    private List<TVShow> tvShowList = new ArrayList<>();
    private int currentPage = 1;
    private boolean endOfList = false;
    private boolean isLoading = false;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App) getApplication()).getMVPComponent().inject(this);

        adapter = new TVShowsAdapter(this, tvShowList);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        mvpPresenter.setView(this);
        mvpPresenter.getPopularTVShows(currentPage);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !endOfList) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                        progressBar.setVisibility(View.VISIBLE);
                        isLoading = true;
                        currentPage++;
                        mvpPresenter.getPopularTVShows(currentPage);
                    }
                }
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        TVShow tvShow =  tvShowList.get(position);
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("parcel_data", tvShow);
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mvpPresenter.rxUnsubscribe();
    }

    @Override
    public void showTVShows(TotalShow tvShows) {
       if(progressBar != null)
            progressBar.setVisibility(View.GONE);

       if(currentPage == tvShows.getTotal_pages()) {
           endOfList = true;
       }
       tvShowList.addAll(tvShows.getResults());isLoading = false;
       if(adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error_msg),
                Toast.LENGTH_LONG).show();
    }
}
