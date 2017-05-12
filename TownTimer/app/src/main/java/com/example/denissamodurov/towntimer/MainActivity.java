package com.example.denissamodurov.towntimer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.example.denissamodurov.towntimer.imageframes.House;
import com.example.denissamodurov.towntimer.imageframes.HouseFactory;

public class MainActivity extends AppCompatActivity implements Observer {

    private static int SECOND_IN_MINUTE = 60;

    private TextView mTimerClockFace;
    private TextView mTimeForTimer;
    private Button mStartButton;
    private SeekBar mSeekbarSetTimer;
    private ProjectTimer mProjectTimer;
    private MediaPlayer mEndOfTimer;
    private House mHouse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupGUI();
        setupTimer();
        setupMusic();
        setupStartButton();
        setupSeekBar();
    }

    private void setupGUI(){
        mTimeForTimer = (TextView) findViewById(R.id.time_for_timer);
        mTimerClockFace = (TextView) findViewById(R.id.timer_clock_face);
        mStartButton = (Button) findViewById(R.id.start_button);
        mSeekbarSetTimer = (SeekBar) findViewById(R.id.seekbar_set_timer);
    }

    private void setupTimer(){
        mProjectTimer = new ProjectTimer();
        mProjectTimer.registerObserver(this);
    }

    private void setupMusic(){
        mEndOfTimer = MediaPlayer.create(this, R.raw.end_of_timer);
    }

    private void setupStartButton() {
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int second = getSecondFromSeekbar();
                mProjectTimer.runTimer(second);

                mHouse = HouseFactory.createHouse(House.HouseLabel.PENTHOUSE);
                mHouse.setHouseImage((ImageView) findViewById(R.id.house_image));
                mHouse.setStartTimeForTimer(second);

                mProjectTimer.registerObserver(mHouse);
                mStartButton.setEnabled(false);
            }
        });
    }

    private void setupSeekBar(){
        mSeekbarSetTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getSecondFromSeekbar();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar eekBar) {
                getSecondFromSeekbar();
            }
        });
    }

    private int getSecondFromSeekbar(){
        int minuteAlter = 1;
        String dataFromSeekbar = String.valueOf(mSeekbarSetTimer.getProgress());
        int minuteForTimer = Integer.parseInt(dataFromSeekbar) + minuteAlter;

        mTimeForTimer.setText(minuteForTimer + " minutes");

        int secondForTimer = minuteForTimer * SECOND_IN_MINUTE;
        return secondForTimer;
    }

    @Override
    public void update(long secondLeft, boolean isEndTimer) {
        makeToastIfEnd(isEndTimer);
        setupTimerClockFace(secondLeft);
    }

    private void makeToastIfEnd(boolean isEndTimer) {
        if(isEndTimer){
            mEndOfTimer.start();

            Toast toast = Toast.makeText(getApplicationContext(), "Timer is finished!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();

            mStartButton.setEnabled(true);
        }
    }

    private void setupTimerClockFace(long secondLeft) {
        long minute = secondLeft / SECOND_IN_MINUTE;
        long second = secondLeft % SECOND_IN_MINUTE;
        mTimerClockFace.setText(minute + ":" + second);
    }
}
