package com.example.denissamodurov.towntimer.houseInstanceClass;

import com.example.denissamodurov.towntimer.R;

import static com.example.denissamodurov.towntimer.houseInstanceClass.House.*;

/**
 * Created by denissamodurov on 10/05/2017.
 */

public class HouseFactory {
    public static House createHouse(HouseLabel label){
        switch(label){
            case PENTHOUSE: return new Penthouse();
            case DUPLEX: return new Duplex();
        }
        return new EmptyHouse();
    }

    public static int getHouseEndImage(HouseLabel label){
        switch(label){
            case PENTHOUSE: return R.mipmap.penthouse_end;
            case DUPLEX: return R.mipmap.duplex_end;
        }
        return 0;
    }
}
