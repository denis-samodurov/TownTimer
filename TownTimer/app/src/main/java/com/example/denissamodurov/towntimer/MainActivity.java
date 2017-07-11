package com.example.denissamodurov.towntimer;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.example.denissamodurov.towntimer.houseInstanceClass.House;
import com.example.denissamodurov.towntimer.houseInstanceClass.HouseFactory;

public class MainActivity extends AppCompatActivity implements Observer {
    private static final int REQUEST_CODE_CHEAT = 0;
    private static int SECOND_IN_MINUTE = 60;

    private TextView mTimerClockFace;
    private TextView mTimeForTimer;
    private Button mStartButton;
    private SeekBar mSeekbarSetTimer;
    private ImageView mHouseImageView;
    private ProjectTimer mProjectTimer;
    private MediaPlayer mEndOfTimer;
    private House mHouse;
    private House.HouseLabel mHouseLabelToCreate;
    private int mCurrentHouseLabelPosition;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupGUI();
        setupTimer();
        setupMusic();
    }

    private void setupGUI(){
        mTimeForTimer = (TextView) findViewById(R.id.time_for_timer);
        mTimerClockFace = (TextView) findViewById(R.id.timer_clock_face);
        mStartButton = (Button) findViewById(R.id.start_button);
        mSeekbarSetTimer = (SeekBar) findViewById(R.id.seekbar_set_timer);
        setupStartButton();
        setupHouseImageView();
        setupSeekBar();
        setupDefaultSettings();
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
                int second = getSecondsFromSeekbar();
                mProjectTimer.runTimer(second);

                mHouse = HouseFactory.createHouse(mHouseLabelToCreate);
                mHouse.setHouseImage(mHouseImageView);
                mHouse.setStartTimeForTimer(second);

                mProjectTimer.registerObserver(mHouse);
                mStartButton.setEnabled(false);
            }
        });
    }

    private void setupHouseImageView(){
        mHouseImageView = (ImageView) findViewById(R.id.house_image);
        mHouseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = HouseSelectedListActivity.newIntent(MainActivity.this, mCurrentHouseLabelPosition);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
    }

    private void setupSeekBar(){
        mSeekbarSetTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getSecondsFromSeekbar();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar eekBar) {
                getSecondsFromSeekbar();
            }
        });
    }

    private void setupDefaultSettings(){
        mCurrentHouseLabelPosition = DefaultSettings.DEFAULT_START_POSITION;
        setImageResourceForHouseImage();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        mCurrentHouseLabelPosition = HouseSelectedListActivity.getCurrentPosition(data);
        setImageResourceForHouseImage();
    }

    private void setImageResourceForHouseImage() {
        mHouseLabelToCreate = House.HouseLabel.values()[mCurrentHouseLabelPosition];
        int currentHouseEndImage = HouseFactory.getHouseEndImage(mHouseLabelToCreate);
        mHouseImageView.setImageResource(currentHouseEndImage);
    }

    private int getSecondsFromSeekbar(){
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

    //TODO сделать красирое отображение таймера
    private void setupTimerClockFace(long secondLeft) {
        long minute = secondLeft / SECOND_IN_MINUTE;
        long second = secondLeft % SECOND_IN_MINUTE;
        mTimerClockFace.setText(minute + ":" + second);
    }
}
