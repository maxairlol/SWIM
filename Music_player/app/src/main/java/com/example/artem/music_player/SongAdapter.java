package com.example.artem.music_player;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Artem on 2018-05-11.
 */

public class SongAdapter extends BaseAdapter {
    private Context context;
    private List<Song> listOfSongs;

    public SongAdapter(Context context, List<Song> listOfSongs) {
        this.context = context;
        this.listOfSongs = listOfSongs;
    }

    @Override
    public int getCount() {
        return listOfSongs.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfSongs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listOfSongs.indexOf(getItem(i));
    }

    private class ViewHolder {
        ImageView play_music;
        TextView song_title;
        TextView song_author;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.song_layout, null);
        }
        holder = new ViewHolder();
        holder.song_title = (TextView) view.findViewById(R.id.song_title);
        holder.song_author = (TextView) view.findViewById(R.id.song_author);
        holder.play_music = (ImageView) view.findViewById(R.id.play_btn);

        final Song song = listOfSongs.get(i);
        holder.song_title.setText(song.getTitle());
        holder.song_author.setText(song.getAuthor());
        holder.play_music.setImageResource(R.drawable.play_btn);
        holder.play_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).play(context,song);
            }
        });

        return view;
    }



}
