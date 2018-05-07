package com.jayhixson.soundswarm;

import android.media.MediaPlayer;
import android.widget.ToggleButton;

import com.jayhixson.soundswarm.SwarmNode;

public class MediaPlayerRunnable implements Runnable {


    private final SwarmNode mSwarmNode;
    private MediaPlayer mp;
    private ToggleButton playbutton;

    public MediaPlayer getMp() {
        return mp;
    }

    public void setMp(MediaPlayer mp) {
        this.mp = mp;
    }

    public MediaPlayerRunnable(SwarmNode node, MediaPlayer mp, ToggleButton button) {
        this.mSwarmNode = node;
        this.mp = mp;
        this.playbutton = button;

    }

    @Override
    public void run() {

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();

            }
        });


        mp.start();

    }
}
