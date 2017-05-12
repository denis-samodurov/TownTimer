package com.example.denissamodurov.towntimer.imageframes;

import android.content.Context;
import android.content.res.Resources;
import android.widget.ImageView;

import com.example.denissamodurov.towntimer.Observer;

/**
 * Created by denissamodurov on 09/05/2017.
 */

public abstract class House implements Observer {
    public enum HouseLabel{
        PENTHOUSE;
    }

    public abstract void setHouseImage(ImageView houseImage);
    public abstract void setStartTimeForTimer(long timerSecond);
}
