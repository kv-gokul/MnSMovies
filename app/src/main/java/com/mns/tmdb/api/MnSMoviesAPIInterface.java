package com.mns.tmdb.api;

import com.mns.tmdb.model.Movie;
import com.mns.tmdb.model.MoviesResponse;
import com.mns.tmdb.util.MnSMoviesConstants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gokul on 26/3/18.
 */

public interface MnSMoviesAPIInterface {

    @GET(MnSMoviesConstants.TMDB_NOW_PLAYING_MOVIES)
    io.reactivex.Observable<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET(MnSMoviesConstants.TMDB_MOVIE_DETAILS)
    io.reactivex.Observable<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET(MnSMoviesConstants.TMDB_MOVIE_COLLECTION_DETAILS)
    io.reactivex.Observable<Movie> getMovieCollectionDetails(@Path("collectionId") int id, @Query("api_key") String apiKey);


}
