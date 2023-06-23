package com.javarush.island.diachenko.animals;

import com.javarush.island.diachenko.Organism;
import com.javarush.island.diachenko.Plant;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator implements Animal {
    @Override
    public boolean canBeEatable() {
        return false;
    }

    @Override
    public int eat() {
        return 0;
    }

    public int eat(Vector<Animal> vector){
        return 1;
    }

    @Override
    public boolean isTrue(int probability) {
        return ThreadLocalRandom.current().nextInt(0, 100) <= probability;
    }
}
