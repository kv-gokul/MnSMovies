package com.mns.tmdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.mns.tmdb.R;
import com.mns.tmdb.adapter.MnSMoviesAdapter;
import com.mns.tmdb.model.Movie;
import com.mns.tmdb.model.MoviesResponse;
import com.mns.tmdb.util.MnSMoviesConstants;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gokul on 26/3/18.
 */

public class MnSMoviesListActivity extends MnSBaseActivity {

    @InjectView(R.id.movies_gridview)
    GridView moviesGrid;
    private List<Movie> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.inject(this);
        getNowPlayingMovies();
    }

    private void getNowPlayingMovies() {
        if (!MnSMoviesConstants.TMDB_API_KEY.isEmpty()) {
            showDialog(this);
            Call<MoviesResponse> moviesCall = getMnSMoviesAPIService().
                    getNowPlayingMovies(MnSMoviesConstants.TMDB_API_KEY);
            moviesCall.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    moviesList = response.body().getResults();
                    loadMoviesGrid();
                    dismissDialog();
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    dismissDialog();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Get your API key first", Toast.LENGTH_LONG).show();
        }
    }

    private void loadMoviesGrid() {
        if (moviesList != null && moviesList.size() > 0) {
            moviesGrid.setAdapter(new MnSMoviesAdapter(moviesList, getBaseContext()));
            moviesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Intent movieDetailsIntent = new Intent(MnSMoviesListActivity.this, MnSMovieDetailsActivity.class);
                    movieDetailsIntent.putExtra("movieId", moviesList.get(position).getId());
                    startActivity(movieDetailsIntent);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moviesList = null;
    }
}
