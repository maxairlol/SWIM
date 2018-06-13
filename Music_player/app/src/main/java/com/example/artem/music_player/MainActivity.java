package com.example.artem.music_player;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private List<Song> listOfSongs;
    private SongAdapter adapter;
    private MediaPlayer mediaPlayer;
    private ListView listView;
    private TextView bottom_title;
    private TextView currentPosition;
    private TextView endPosition;
    private Handler myHandler = new Handler();
    private ImageButton play_stop;
    private SeekBar seekBar;
    private double startTime = 0;
    private double finalTime = 0;
    private MusicService musicService;
    private Intent playIntent;
    private Boolean musicBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list_layout);

        initial_data();
        set_vies();
        set_listeners();
    }

    public void set_vies(){
        listView = findViewById(R.id.listViewSongs);
        play_stop = findViewById(R.id.bottom_play_stop);
        bottom_title = findViewById(R.id.bar_title);
        currentPosition = findViewById(R.id.current_possition);
        endPosition = findViewById(R.id.end_possition);
        seekBar = findViewById(R.id.seekBar);
        adapter = new SongAdapter(this, listOfSongs);
        listView.setAdapter(adapter);
    }

    public void set_listeners(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mediaPlayer != null && b)
                    mediaPlayer.seekTo(i);
          }});
        }

    @SuppressLint("DefaultLocale")
    public void play(Context context, Song song){
        releaseMP();
        mediaPlayer = MediaPlayer.create(context,song.getSong());
        bottom_title.setText(song.getTitle());
        play_stop.setImageDrawable(Resources.getSystem().getDrawable(android.R.drawable.ic_media_pause));
        mediaPlayer.start();
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        seekBar.setMax((int) finalTime);

        endPosition.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                finalTime)))
        );
        seekBar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateSongTime,100);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                play_stop.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
            }
        });
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Runnable UpdateSongTime = new Runnable() {
        @SuppressLint("DefaultLocale")
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            currentPosition.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    public void bottom_navigation_click(View view) {
        if (mediaPlayer == null)
            return;
        switch (view.getId()) {
            case R.id.bottom_play_stop:
                Resources res = getResources();
                Drawable img;
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    img = res.getDrawable(android.R.drawable.ic_media_play);
                } else{
                    img = res.getDrawable(android.R.drawable.ic_media_pause);
                    mediaPlayer.start();
                }
                play_stop.setImageDrawable(img);
                break;
            case R.id.remote_back:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000);
                break;
            case R.id.remote_forward:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 10000);
                break;
        }
    }

    public void initial_data(){
        listOfSongs = new ArrayList<>();
        listOfSongs.add(new Song("Afraid","The Neighbourhood",R.raw.the_neighbourhood_afraid));
        listOfSongs.add(new Song("Daddy issues","The Neighbourhood",R.raw.the_neighbourhood_daddy_issues));
        listOfSongs.add(new Song("Paris","Suicideboys",R.raw.suicideboys_paris));
        listOfSongs.add(new Song("Dead","Lil Peep",R.raw.lil_peep_dead));
        listOfSongs.add(new Song("Awful things","Lil Peep",R.raw.lil_peep_awful_things));
        listOfSongs.add(new Song("Sweater weather","The Neighbourhood",R.raw.the_neighbourhood_sweater_weather));
    }
}
