package com.mns.tmdb.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mns.tmdb.R;
import com.mns.tmdb.model.Movie;
import com.mns.tmdb.util.MnSMoviesConstants;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gokul on 26/3/18.
 */

public class MnSMovieDetailsActivity extends MnSBaseActivity {

    private static final String TAG = MnSMovieDetailsActivity.class.getSimpleName();

    @InjectView(R.id.movie_title)
    TextView movieTitle;
    @InjectView(R.id.movie_poster)
    ImageView moviePoster;
    @InjectView(R.id.movie_overview)
    TextView movieOverview;
    private Subscription subscription;

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
            subscription = getMnSMoviesAPIService().getMovieDetails(movieId, MnSMoviesConstants.TMDB_API_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Movie>() {
                        @Override
                        public void onCompleted() {
                            dismissDialog();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, e.getMessage());
                        }

                        @Override
                        public void onNext(Movie movie) {
                            setMovieDetailsInView(movie);
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Get your API key first", Toast.LENGTH_LONG).show();
        }
    }

    private void setMovieDetailsInView(Movie movie) {
        movieTitle.setText(movie.getTitle());
        movieOverview.setText(movie.getOverview());
        Picasso.with(this)
                .load(MnSMoviesConstants.TMDB_MOVIE_POSTER_BASE_PATH + movie.
                        getPosterPath())
                .resize(250, 300)
                .into(moviePoster);
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }
}
