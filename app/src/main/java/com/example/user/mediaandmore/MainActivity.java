package com.example.user.mediaandmore;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> SongIdList = new ArrayList<>();
    MediaPlayer mp = new MediaPlayer();
    int nSongs = 2;
    int currentSong = 0;// to set song index to play

    boolean flag = false; // For "play" "pause"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SongIdList.add(R.raw.leanon);
        SongIdList.add(R.raw.summer);

        mp = MediaPlayer.create(this, SongIdList.get(currentSong));

        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx not work for 2nd song
        mp.setOnCompletionListener(listener);

    }

    public MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Bt_Next_CLicked(null);
        }
    };

    public void Bt_Play_Clicked(View view) {

        Button bt_Play = (Button) findViewById(R.id.bt_Play);

        if (flag == false) {
            mp.start();
            bt_Play.setText("Pause");

            flag = true;

        } else {
            mp.pause();
            bt_Play.setText("Start");

            flag = false;
        }
    }


    public void Bt_Next_CLicked(View view) {
        // To play song continuosly in a row
        if (currentSong < nSongs - 1) currentSong++;
        else
        {
            currentSong = 0;
        }

        // DO THIS OR....
        mp.release();
        mp = MediaPlayer.create(this, SongIdList.get(currentSong));

        flag = false;
        Bt_Play_Clicked(view);
    }


    @Override
    public void finish() {
        mp.release();
        super.finish();
    }

    public void Bt_Prev_Clicked(View view) {
        if (currentSong > 0) currentSong--;
        else
        {
            currentSong = nSongs-1;
        }


        mp.release();
        mp = MediaPlayer.create(this, SongIdList.get(currentSong));

        flag = false;
        Bt_Play_Clicked(view);
    }

    public void Bt_Seek_Clicked(View view) {
        //int l = mp.getDuration();
        //Toast.makeText(MainActivity.this, l, Toast.LENGTH_SHORT).show();
        mp.seekTo(210000);// test
    }
}


