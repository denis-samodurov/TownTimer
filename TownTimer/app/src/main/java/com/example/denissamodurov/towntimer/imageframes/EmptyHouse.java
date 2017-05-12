package com.example.denissamodurov.towntimer.imageframes;

import android.content.res.Resources;
import android.widget.ImageView;

import com.example.denissamodurov.towntimer.Observer;

/**
 * Created by denissamodurov on 10/05/2017.
 */

public class EmptyHouse extends House implements Observer {
    @Override
    public void update(long secondLeft, boolean isEndTimer) {

    }

    @Override
    public void setHouseImage(ImageView houseImage) {

    }

    @Override
    public void setStartTimeForTimer(long timerSecond) {

    }
}
