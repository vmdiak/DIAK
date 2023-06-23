package com.javarush.island.diachenko;

public class Plant implements Organism{
    private boolean alive = true;

    @Override
    public boolean isAlive() {
        return alive;
    }
}
