package com.example.denissamodurov.towntimer.houseInstanceClass;

import com.example.denissamodurov.towntimer.R;

/**
 * Created by denissamodurov on 13/05/2017.
 */

public class Duplex extends House {
    @Override
    public int getStartImage() {
        return R.mipmap.duplex_start;
    }

    @Override
    public int getEarlyMiddleImage() {
        return R.mipmap.duplex_early_middle;
    }

    @Override
    public int getLateMiddleImage() {
        return R.mipmap.duplex_late_middle;
    }

    @Override
    public int getEndImage() {
        return R.mipmap.duplex_end;
    }
}
