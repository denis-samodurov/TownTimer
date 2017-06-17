package com.example.denissamodurov.towntimer.houseInstanceClass;

import android.widget.ImageView;

import com.example.denissamodurov.towntimer.Observer;

/**
 * Created by denissamodurov on 09/05/2017.
 */

public abstract class House implements Observer {
    private long firstInterval;
    private long secondInterval;
    private ImageView mHouseImage;

    public enum HouseLabel {
        PENTHOUSE,
        DUPLEX;
    }

    public void setHouseImage(ImageView houseImage) {
        mHouseImage = houseImage;
        mHouseImage.setImageResource(getStartImage());
    }

    public void setStartTimeForTimer(long timerSecond) {
        firstInterval = 2 * timerSecond / 3;
        secondInterval = timerSecond / 3;
    }

    //TODO Алгоритм работает, но он постоянно выполняет лишнюю работу
    public void update(long secondLeft, boolean isEndTimer) {
        if (isEndTimer) {
            mHouseImage.setImageResource(getEndImage());
        } else {
            if (secondLeft < secondInterval) {
                mHouseImage.setImageResource(getLateMiddleImage());
                return;
            } else if (secondLeft < firstInterval) {
                mHouseImage.setImageResource(getEarlyMiddleImage());
                return;
            }
        }
    }

    public abstract int getStartImage();

    public abstract int getEarlyMiddleImage();

    public abstract int getLateMiddleImage();

    public abstract int getEndImage();
}
