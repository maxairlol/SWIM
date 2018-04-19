package com.example.artem.films;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    TextView filmTitle, filmCategory;
    ImageView filmImage;
    ViewPager viewPager;
    public static ArrayList<Actor> actors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        set_views();
        set_film_data();
    }

    public void set_views() {
        filmTitle = findViewById(R.id.filmTitle);
        filmCategory = findViewById(R.id.filmCategory);
        filmImage = findViewById(R.id.filmImage);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new SwipeAdapter(getSupportFragmentManager()));
    }


    public void set_film_data(){
        Intent intent = getIntent();
        Film film = intent.getParcelableExtra(FilmsActivity.LIST_FILM_ITEM);
        filmTitle.setText(film.getTitle());
        filmCategory.setText(film.getCategory());
        filmImage.setImageResource(film.getImage());
        actors = film.getActors();
    }
}
