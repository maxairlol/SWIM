package com.example.artem.films;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class FIlmAdapter extends BaseAdapter {
        private Context context;
        private List<Film> listOfFilms;
        public FIlmAdapter(Context context, List<Film> listOfUsers) {
            this.context = context;
            this.listOfFilms = listOfUsers;
        }

        @Override
        public int getCount() {
            return listOfFilms.size();
        }

        @Override
        public Object getItem(int position) {
            return listOfFilms.get(position);
        }

        @Override
        public long getItemId(int position) {
            return listOfFilms.indexOf(getItem(position));
        }

        /* private view holder class */
        private class ViewHolder {
            ImageView film_image;
            TextView film_title;
            TextView film_category;
        }

        @Override
        public View getView(final int position, View cView, ViewGroup parent) {
            ViewHolder holder = null;
            if (cView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                cView = inflater.inflate(R.layout.row_layout, null);
            }
            holder = new ViewHolder();
            holder.film_title = (TextView) cView.findViewById(R.id.filmTitle);
            holder.film_category = (TextView) cView.findViewById(R.id.filmCategory);
            holder.film_image = (ImageView) cView.findViewById(R.id.filmImage);

            Film film =listOfFilms.get(position);
            holder.film_title.setText(film.getTitle());
            holder.film_category.setText(film.getCategory());
            holder.film_image.setImageResource(film.getImage());

            return cView;
        }
    }
