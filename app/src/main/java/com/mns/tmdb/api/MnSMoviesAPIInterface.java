package com.mns.tmdb.api;

import com.mns.tmdb.model.Movie;
import com.mns.tmdb.model.MoviesResponse;
import com.mns.tmdb.util.MnSMoviesConstants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gokul on 26/3/18.
 */

public interface MnSMoviesAPIInterface {

    @GET(MnSMoviesConstants.TMDB_NOW_PLAYING_MOVIES)
    Observable<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET(MnSMoviesConstants.TMDB_MOVIE_DETAILS)
    Observable<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET(MnSMoviesConstants.TMDB_MOVIE_COLLECTION_DETAILS)
    Observable<Movie> getMovieCollectionDetails(@Path("collectionId") int id, @Query("api_key") String apiKey);


}
