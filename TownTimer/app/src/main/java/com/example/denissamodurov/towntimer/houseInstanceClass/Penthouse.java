package com.example.denissamodurov.towntimer.houseInstanceClass;

import com.example.denissamodurov.towntimer.R;

/**
 * Created by denissamodurov on 09/05/2017.
 */

public class Penthouse extends House {
    @Override
    public int getStartImage() {
        return R.mipmap.penthouse_start;
    }

    @Override
    public int getEarlyMiddleImage() {
        return R.mipmap.penthouse_early_middle;
    }

    @Override
    public int getLateMiddleImage() {
        return R.mipmap.penthouse_late_middle;
    }

    @Override
    public int getEndImage() {
        return R.mipmap.penthouse_end;
    }
}
