package com.example.denissamodurov.towntimer;

/**
 * Created by denissamodurov on 07/05/2017.
 */

public interface Observable {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();
}
