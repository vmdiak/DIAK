package com.javarush.island.diachenko.animals;

import com.javarush.island.diachenko.Plant;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivore implements Animal {

    @Override
    public boolean canBeEatable() {
        return true;
    }

    @Override
    public int eat() {
        return 0;
    }

    public int eat(Vector<Plant> plants){
        return 1;
    }

    @Override
    public boolean isTrue(int probability) {
        return ThreadLocalRandom.current().nextInt(0, 100) <= probability;
    }
}
