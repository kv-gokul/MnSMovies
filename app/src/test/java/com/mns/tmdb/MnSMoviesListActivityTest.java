package com.mns.tmdb;

import android.widget.GridView;

import com.mns.tmdb.activity.MnSMoviesListActivity;

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
public class MnSMoviesListActivityTest {
    private MnSMoviesListActivity activity;
    private GridView moviesGridView;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MnSMoviesListActivity.class);
        findViews();
    }

    public void findViews() {
        moviesGridView = (GridView) activity.findViewById(R.id.movies_gridview);
    }

    @Test
    public void viewShouldNotBeNull() {
        assertNotNull("", moviesGridView);
    }

}
