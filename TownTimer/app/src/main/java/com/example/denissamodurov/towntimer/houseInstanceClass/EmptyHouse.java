package com.example.denissamodurov.towntimer.houseInstanceClass;

import com.example.denissamodurov.towntimer.Observer;

/**
 * Created by denissamodurov on 10/05/2017.
 */

public class EmptyHouse extends House implements Observer {
    //TODO место потенциальной ошибки из-за того, чтобы эти методы перевают номера ресурсов
    @Override
    public int getStartImage() {
        return 0;
    }

    @Override
    public int getEarlyMiddleImage() {
        return 0;
    }

    @Override
    public int getLateMiddleImage() {
        return 0;
    }

    @Override
    public int getEndImage() {
        return 0;
    }
}
