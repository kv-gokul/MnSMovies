package com.mns.tmdb.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mns.tmdb.R;
import com.mns.tmdb.model.Movie;
import com.mns.tmdb.util.MnSMoviesConstants;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gokul on 26/3/18.
 */

public class MnSMovieDetailsActivity extends MnSBaseActivity {

    @InjectView(R.id.movie_title)
    TextView movieTitle;
    @InjectView(R.id.movie_poster)
    ImageView moviePoster;
    @InjectView(R.id.movie_overview)
    TextView movieOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.inject(this);
        getMovieDetails(getIntent().getIntExtra("movieId", 0));
    }


    private void getMovieDetails(int movieId) {
        if (!MnSMoviesConstants.TMDB_API_KEY.isEmpty()) {
            showDialog(this);
            Call<Movie> movieDetailsCall = getMnSMoviesAPIService().
                    getMovieDetails(movieId, MnSMoviesConstants.TMDB_API_KEY);
            movieDetailsCall.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    setMovieDetailsInView(response);
                    dismissDialog();
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    dismissDialog();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Get your API key first", Toast.LENGTH_LONG).show();
        }
    }

    private void setMovieDetailsInView(Response<Movie> response) {
        movieTitle.setText(response.body().getTitle());
        movieOverview.setText(response.body().getOverview());
        Picasso.with(this)
                .load(MnSMoviesConstants.TMDB_MOVIE_POSTER_BASE_PATH + response.body().
                        getPosterPath())
                .resize(250, 300)
                .into(moviePoster);
    }
}
