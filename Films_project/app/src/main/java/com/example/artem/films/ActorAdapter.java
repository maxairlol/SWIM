package com.example.artem.films;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Artem on 2018-04-12.
 */

public class ActorAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Actor> listOfActors;
    public ActorAdapter(Context context, ArrayList<Actor> listOfActors) {
        this.context = context;
        this.listOfActors = listOfActors;
    }

    @Override
    public int getCount() {
        return listOfActors.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfActors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listOfActors.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        TextView actor_name;
        TextView actor_year;
        ImageView actor_icon;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View cView, ViewGroup parent) {
        ViewHolder holder = null;
        if (cView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            cView = inflater.inflate(R.layout.actor_layout, null);
        }
        holder = new ViewHolder();
        holder.actor_name = (TextView) cView.findViewById(R.id.actorName);
        holder.actor_year = (TextView) cView.findViewById(R.id.actorYear);
        holder.actor_icon = (ImageView) cView.findViewById(R.id.actorIcon);

        Actor actor = listOfActors.get(position);
        holder.actor_name.setText(actor.getName());
        holder.actor_year.setText(String.valueOf(actor.getYear()));
        holder.actor_icon.setImageResource(actor.getIcon());

        return cView;
    }
}
