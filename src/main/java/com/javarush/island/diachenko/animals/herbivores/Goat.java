package com.javarush.island.diachenko.animals.herbivores;

import com.javarush.island.diachenko.Config;
import com.javarush.island.diachenko.Plant;
import com.javarush.island.diachenko.animals.Animal;
import com.javarush.island.diachenko.animals.Herbivore;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Goat  extends Herbivore {
    private boolean alive = true;
    private double weight = Config.GOAT.WeightNormal;
    private int maxAmount = Config.GOAT.AmountMax;
    private boolean gender = ThreadLocalRandom.current().nextBoolean();

    @Override
    public boolean getGender() {
        return gender;
    }

    @Override
    public int getMaxSpeed() {
        return Config.BOAR.SpeedMax;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public int eat(Vector<Plant> plants) {
        if (plants.size() > 0 && weight < (Config.GOAT.WeightNormal + Config.GOAT.SatiatedMax)) {
            plants.remove(plants.size() - 1);
            weight++;
        } else {
            weight--;
        }

        alive = weight >= Config.GOAT.WeightNormal / 10.0;

        return 1;
    }

    @Override
    public Animal newChild() {
        return new Goat();
    }

    @Override
    public Animal duplicate() {
        return null;
    }

    @Override
    public int move() {
        return 0;
    }

    @Override
    public void setAlive(boolean bool) {
        alive = bool;
    }
}
