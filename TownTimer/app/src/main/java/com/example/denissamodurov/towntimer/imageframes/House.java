package com.example.denissamodurov.towntimer.imageframes;

import android.content.Context;
import android.content.res.Resources;
import android.widget.ImageView;

import com.example.denissamodurov.towntimer.Observer;
import com.example.denissamodurov.towntimer.R;

/**
 * Created by denissamodurov on 09/05/2017.
 */

public abstract class House implements Observer {
    private long firstInterval;
    private long secondInterval;
    private ImageView mHouseImage;

    public enum HouseLabel{
        PENTHOUSE;
    }

    public void setHouseImage(ImageView houseImage){
        mHouseImage = houseImage;
        mHouseImage.setImageResource(getStartImage());
    }

    public void setStartTimeForTimer(long timerSecond){
        firstInterval = timerSecond / 3;
        secondInterval = 2 * timerSecond / 3;
    }

    //TODO Алгоритм работает, но он постоянно выполняет лишнюю работу
    public void update(long secondLeft, boolean isEndTimer) {
        if(isEndTimer){
            mHouseImage.setImageResource(getEndImage());
        } else {
            if(secondLeft < firstInterval){
                mHouseImage.setImageResource(getEarlyMiddleImage());
                return;
            }
            if(secondLeft < secondInterval){
                mHouseImage.setImageResource(getLateMiddleImage());
                return;
            }
        }
    }

    public abstract int getStartImage();
    public abstract int getEarlyMiddleImage();
    public abstract int getLateMiddleImage();
    public abstract int getEndImage();
}
