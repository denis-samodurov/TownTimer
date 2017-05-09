package com.example.denissamodurov.towntimer;

import android.os.CountDownTimer;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denissamodurov on 07/05/2017.
 */

public class ProjectTimer implements Observable, Timer{
    private static int MILLISECONDS_IN_SECOND = 1000;

    List<Observer> mObservers;
    long mSecondLeft;
    boolean mIsTimerEnd;

    public ProjectTimer() {
        mObservers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        mObservers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        mObservers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer currentObserver : mObservers){
            currentObserver.update(mSecondLeft, mIsTimerEnd);
        }
    }
    @Override
    public void runTimer(int secondLeft) {
        mSecondLeft = secondLeft;
        mIsTimerEnd = false;
        startTimer();
    }

    private void startTimer(){
        CountDownTimer countDownTimer = new CountDownTimer(mSecondLeft * MILLISECONDS_IN_SECOND, MILLISECONDS_IN_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                mSecondLeft = millisUntilFinished / MILLISECONDS_IN_SECOND;
                notifyObserver();
            }

            @Override
            public void onFinish() {
                mIsTimerEnd = true;
                mSecondLeft = 0;
                notifyObserver();
            }
        }.start();
    }
}
