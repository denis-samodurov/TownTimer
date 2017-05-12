package com.example.denissamodurov.towntimer.imageframes;

import android.widget.ImageView;

import com.example.denissamodurov.towntimer.R;

/**
 * Created by denissamodurov on 09/05/2017.
 */

public class Penthouse extends House {
    private long firstInterval;
    private long secondInterval;
    private ImageView mHouseImage;

    public void setHouseImage(ImageView houseImage){
        mHouseImage = houseImage;
        mHouseImage.setImageResource(R.mipmap.penthouse_start);
    }

    public void setStartTimeForTimer(long timerSecond){
        firstInterval = timerSecond / 3;
        secondInterval = 2 * timerSecond / 3;
    }

    @Override
    public void update(long secondLeft, boolean isEndTimer) {
        if(isEndTimer){
            mHouseImage.setImageResource(R.mipmap.penthouse_end);
        } else {
            if(secondLeft < firstInterval){
                mHouseImage.setImageResource(R.mipmap.penthouse_early_middle);
                return;
            }
            if(secondLeft < secondInterval){
                mHouseImage.setImageResource(R.mipmap.penthouse_late_middle);
                return;
            }
        }
    }
}
