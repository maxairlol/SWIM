package com.example.artem.films;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;



public class GalleryFragment extends Fragment{
    private int[] images;
    public GalleryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        GridView gridView = view.findViewById(R.id.gridView);
        set_images();
        gridView.setAdapter(new ImageAdapter(getContext(),images));
        return view;
    }

    public void set_images(){
        images = new int[]{
                R.drawable.batman, R.drawable.captain_america, R.drawable.darth_vader,
                R.drawable.iron_man, R.drawable.pokemon, R.drawable.spiderman
        };
    }
}
