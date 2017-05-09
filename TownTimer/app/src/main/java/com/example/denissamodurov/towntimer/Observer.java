package com.example.denissamodurov.towntimer;

import java.sql.Time;

/**
 * Created by denissamodurov on 07/05/2017.
 */

public interface Observer {
    public void update(long secondLeft, boolean isEndTimer);
}
