package com.example.artem.music_player;

import android.arch.lifecycle.ViewModel;
import android.media.MediaPlayer;
import android.widget.SeekBar;

import android.os.Handler;

/**
 * Created by Artem on 2018-05-13.
 */

public class PlayerViewModel extends ViewModel{
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler handler;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
