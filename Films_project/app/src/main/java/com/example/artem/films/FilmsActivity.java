package com.example.artem.films;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FilmsActivity extends AppCompatActivity {
    private List<Film> listOfFilms;
    private FIlmAdapter adapter;
    ListView listView;

    public static final String LIST_FILM_ITEM = "Film of list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        set_films();
        set_vies();
        set_listeners();
    }

    public void set_vies(){
        listView = findViewById(R.id.listView);
        adapter = new FIlmAdapter(this, listOfFilms);
        listView.setAdapter(adapter);
    }

    public void set_listeners(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),InfoActivity.class);
                intent.putExtra(LIST_FILM_ITEM,listOfFilms.get(position));
                startActivity(intent);
            }
        });

        //Swipe to delete film record
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        listView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    listOfFilms.remove(position);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        });
        listView.setOnTouchListener(touchListener);
    }

    public void set_films(){

        ArrayList<Actor> actors_group1 = new ArrayList<>();
        actors_group1.add(new Actor("Nicole Kidman",50,R.drawable.nicole_kidman));
        actors_group1.add(new Actor("Fionnula Flanagan",76,R.drawable.fionnula_flanagan));
        actors_group1.add(new Actor("Christopher Eccleston",56,R.drawable.christopher_eccleston));
        actors_group1.add(new Actor("Eric Sykes",89,R.drawable.eric_sykes));

        ArrayList<Actor> actors_group2 = new ArrayList<>();
        actors_group2.add( new Actor("Itay Tiran",38,R.drawable.itay_tiran));
        actors_group2.add(new Actor("Agnieszka Żulewska",40,R.drawable.agnieszka_zulewska));

        ArrayList<Actor> actors_group3 = new ArrayList<>();
        actors_group3.add(new Actor("Bradley Cooper",43,R.drawable.bradley_cooper));
        actors_group3.add(new Actor("Ed Helms",44,R.drawable.ed_helms));
        actors_group3.add(new Actor("Zacharius Galifianakis",48,R.drawable.zacharius_galifianakis));
        actors_group3.add( new Actor("Heather Graham",48,R.drawable.heather_graham));

        ArrayList<Actor> actors_group4 = new ArrayList<>();
        actors_group4.add(new Actor("Hugh Grant",57,R.drawable.hugh_grant));
        actors_group4.add(new Actor("Toni Collette",44,R.drawable.toni_collette));
        actors_group4.add(new Actor("Rachel Weisz",48,R.drawable.rachel_weisz));
        actors_group4.add(new Actor("Nicholas Hoult",28,R.drawable.nicholas_hoult));

        ArrayList<Actor> actors_group5 = new ArrayList<>();
        actors_group5.add(new Actor("Leonardo DiCaprio",43,R.drawable.leonardo_dicaprio));
        actors_group5.add(new Actor("Mark Ruffalo",50,R.drawable.mark_ruffalo));
        actors_group5.add(new Actor("Ben Kingsley",74,R.drawable.ben_kingsley));
        actors_group5.add(new Actor("Michelle Williams",37,R.drawable.michelle_williams));

        Film film1 = new Film("The Others","Horror", R.drawable.the_others,actors_group1);
        Film film2 = new Film("Demon","Horror",R.drawable.demon,actors_group2);
        Film film3 = new Film("Kac Vegas","Comedy",R.drawable.kav_vegas,actors_group3);
        Film film4 = new Film("About a boy","Comedy",R.drawable.about_a_boy,actors_group4);
        Film film5 = new Film("Shutter Island","Thriller",R.drawable.shutter_island,actors_group5);

        listOfFilms = new LinkedList<>(Arrays.asList(film1,film2,film3,film4,film5));
    }
}
