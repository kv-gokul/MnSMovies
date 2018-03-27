package com.mns.tmdb;

import android.widget.ImageView;
import android.widget.TextView;

import com.mns.tmdb.activity.MnSMovieDetailsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by gokul on 26/3/18.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MnSMovieDetailsActivityTest {
    private MnSMovieDetailsActivity activity;
    private TextView movieTitle;
    private TextView movieOverview;
    private ImageView moviePoster;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MnSMovieDetailsActivity.class);
        findViews();
    }

    public void findViews() {
        movieTitle = (TextView) activity.findViewById(R.id.movie_title);
        movieOverview = (TextView) activity.findViewById(R.id.movie_overview);
        moviePoster = (ImageView) activity.findViewById(R.id.movie_poster);
    }

    @Test
    public void viewShouldNotBeNull() {
        assertNotNull("", movieTitle);
        assertNotNull("", movieOverview);
        assertNotNull("", moviePoster);
    }

}
