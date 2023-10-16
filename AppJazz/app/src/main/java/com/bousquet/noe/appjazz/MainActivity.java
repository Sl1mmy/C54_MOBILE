package com.bousquet.noe.appjazz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;

import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ImageView playButton, nextButton, previousButton, shuffleButton, repeatButton;
    private TextView nameText, artistText;
    private ImageView coverImage;

    private TextView timeText;
    private Chronometer timer;
    private SeekBar timeSeekBar;

    private ImageView searchButton;
    private TextView linkText;

    private static SpotifyDiffuseur instance;

    private long pauseTime = 0;
    private boolean started = false; //if spotify already playing


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //play actions
        playButton = findViewById(R.id.playPauseButton);
        nextButton = findViewById(R.id.nextButton);
        previousButton = findViewById(R.id.previousButton);
        shuffleButton = findViewById(R.id.shuffleButton);
        repeatButton = findViewById(R.id.repeatButton);

        //infos
        nameText = findViewById(R.id.textTitle);
        artistText = findViewById(R.id.textArtist);
        coverImage = findViewById(R.id.album_cover);

        //time
        timeText = findViewById(R.id.textTimeTotal);
        timer = findViewById(R.id.textTime);
        timeSeekBar = findViewById(R.id.slider);

        //header
        searchButton = findViewById(R.id.searchButton);
        linkText = findViewById(R.id.linkText);

        instance = SpotifyDiffuseur.getInstance(this);


        timer.start();

        //LISTENERS ---------------------------------------------------------

        //PLAY ACTIONS:
        playButton.setOnClickListener(v -> {
            if(instance.isPlaying()) {
                instance.pause();
                timer.stop();
                pauseTime = timer.getBase() - SystemClock.elapsedRealtime();
                playButton.setImageResource(R.drawable.ic_play);
            } else {
                instance.play();
                timer.setBase(SystemClock.elapsedRealtime() + pauseTime);
                timer.start();
                playButton.setImageResource(R.drawable.ic_pause);
            }
        });

        nextButton.setOnClickListener(v -> {
            instance.next();
            timer.setBase(SystemClock.elapsedRealtime());
            playButton.setImageResource(R.drawable.ic_pause);
        });

        previousButton.setOnClickListener(v -> {
            instance.previous();
            timer.setBase(SystemClock.elapsedRealtime());
            playButton.setImageResource(R.drawable.ic_pause);
        });

        shuffleButton.setOnClickListener(v -> instance.toggleShuffle());

        repeatButton.setOnClickListener(v -> instance.toggleRepeat());

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                instance.slide(seekBar.getProgress());
                timer.setBase(SystemClock.elapsedRealtime() - instance.getProgress());
            }
            //useless:
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
        });

        linkText.setOnClickListener(v -> {
            String url = "https://www.thejazzresource.com/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        searchButton.setOnClickListener(v -> {
            //TODO: launch Intent for result
        });

        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                chronometer.setOnChronometerTickListener(null);
                update();
                chronometer.setOnChronometerTickListener(this);
            }
        });
    }

    private void update() {
        if(instance.isConnected()) {
            instance.update();
            if(started) {
                if(instance.isSongChanged()) {
                    playNew();
                    instance.resetSongChanged();
                }

                timeSeekBar.setProgress((int) instance.getProgress());
            } else {
                startup();
            }
        }
    }

    private void playNew() {
        displayInfos();
        timeSeekBar.setMax((int) instance.getLenght());
        timer.setBase(SystemClock.elapsedRealtime()); //reset timer
    }

    private void displayInfos() {
        nameText.setText(instance.getName());
        artistText.setText(instance.getArtist());
        coverImage.setImageBitmap(instance.getCover());
        timeText.setText(millisToTime(instance.getLenght()));
    }

    @SuppressLint("DefaultLocale")
    private String millisToTime(long millis) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }

    private void startup() {
        displayInfos();
        timeSeekBar.setMax((int) instance.getLenght());
        timer.setBase(SystemClock.elapsedRealtime() - instance.getProgress());
        timeSeekBar.setProgress((int) instance.getProgress());

        if(instance.isPlaying()) {
            playButton.setImageResource(R.drawable.ic_pause);

        } else { timer.stop(); }
        started = true;
    }
}