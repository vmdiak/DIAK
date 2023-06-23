package com.javarush.island.diachenko.animals.herbivores;

import com.javarush.island.diachenko.Config;
import com.javarush.island.diachenko.Plant;
import com.javarush.island.diachenko.animals.Animal;
import com.javarush.island.diachenko.animals.Herbivore;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore {
    private boolean alive = true;
    private double weight = Config.DUCK.WeightNormal;
    private int maxAmount = Config.DUCK.AmountMax;
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

    public int eat(Vector<Animal> animals, Vector<Plant> plants) {
        if (plants.size() > 0 && weight < (Config.DUCK.WeightNormal + Config.DUCK.SatiatedMax)) {
            plants.remove(plants.size() - 1);
            weight += 0.05;
        } else {
            weight -= 0.05;
        }

        for (Animal val :
                animals) {
            if (val.isAlive()) {
                String fullClassName = val.getClass().getName();
                // If weight less normal weight + satiate, than it eats
                // Если вес меньше чем нормальный вес + насыщение, то кушаем
                if (weight < (Config.DUCK.WeightNormal + Config.DUCK.SatiatedMax)) {
                    // Add weight if catch other animal
                    // Добавляем к весу, если поймаем другое животное
                    weight += switch (fullClassName.substring(fullClassName.lastIndexOf(".") + 1)) {
                        case "Caterpillar": {
                            if (isTrue(90)) {
                                val.setAlive(false);
                                yield Config.CATERPILLAR.WeightNormal;
                            } else {
                                yield -Config.CATERPILLAR.WeightNormal;
                            }
                        }
                        default:
                            yield -0.005;
                    };
                }
            }
        }

        alive = weight >= Config.DUCK.WeightNormal / 10.0;

        return 1;
    }

    @Override
    public Animal newChild() {
        return new Duck();
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
