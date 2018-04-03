package com.mns.tmdb.api;

import com.mns.tmdb.util.MnSMoviesConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by gokul on 26/3/18.
 */

public class MnSMoviesAPIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MnSMoviesConstants.TMDB_API_BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
