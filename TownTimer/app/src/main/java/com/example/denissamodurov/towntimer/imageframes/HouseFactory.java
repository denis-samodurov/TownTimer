package com.example.denissamodurov.towntimer.imageframes;

import android.content.Context;
import android.widget.ImageView;

import static com.example.denissamodurov.towntimer.imageframes.House.*;
import static com.example.denissamodurov.towntimer.imageframes.House.HouseLabel.*;


/**
 * Created by denissamodurov on 10/05/2017.
 */

public class HouseFactory {
    public static House createHouse(HouseLabel label){
        switch(label){
            case PENTHOUSE: return new Penthouse();

            default: return new EmptyHouse();
        }
    }
}
