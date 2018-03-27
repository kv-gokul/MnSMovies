package com.mns.tmdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mns.tmdb.R;
import com.mns.tmdb.model.Movie;
import com.mns.tmdb.util.MnSMoviesConstants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gokul on 26/3/18.
 */

public class MnSMoviesAdapter extends BaseAdapter {

    private List<Movie> movies;
    private Context context;

    public MnSMoviesAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.movie_display, null);
            TextView movieTitle = (TextView) gridView
                    .findViewById(R.id.movie_title);
            movieTitle.setText(movies.get(position).getTitle());
            ImageView movieThumbnail = (ImageView) gridView
                    .findViewById(R.id.movie_thumbnail);
            Picasso.with(context)
                    .load(MnSMoviesConstants.TMDB_MOVIE_POSTER_BASE_PATH + movies.get(position).
                            getPosterPath())
                    .resize(150, 200)
                    .into(movieThumbnail);
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
