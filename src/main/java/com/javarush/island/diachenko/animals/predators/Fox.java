package com.javarush.island.diachenko.animals.predators;

import com.javarush.island.diachenko.Config;
import com.javarush.island.diachenko.animals.Animal;
import com.javarush.island.diachenko.animals.Predator;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator {
    private boolean alive = true;
    private double weight = Config.FOX.WeightNormal;
    private int maxAmount = Config.FOX.AmountMax;
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
    public int eat(Vector<Animal> vector) {
        for (Animal val:
                vector) {
            if (!(val instanceof Bear)) {
                if (val.isAlive()) {
                    String fullClassName = val.getClass().getName();
                    if (weight < (Config.FOX.WeightNormal + Config.FOX.SatiatedMax)) {
                        // Add weight if catch other animal
                        // Добавляем к весу, если поймаем другое животное
                        weight += switch (fullClassName.substring(fullClassName.lastIndexOf(".") + 1)) {
                            case "Rabbit": {
                                if (isTrue(80)) {
                                    val.setAlive(false);
                                    yield Config.RABBIT.WeightNormal;
                                } else {
                                    yield -Config.RABBIT.WeightNormal/2.0;
                                }
                            }
                            case "Mouse": {
                                if (isTrue(90)) {
                                    val.setAlive(false);
                                    yield Config.MOUSE.WeightNormal;
                                } else {
                                    yield -Config.MOUSE.WeightNormal;
                                }
                            }
                            case "Duck": {
                                if (isTrue(60)) {
                                    val.setAlive(false);
                                    yield Config.DUCK.WeightNormal;
                                } else {
                                    yield -Config.DUCK.WeightNormal;
                                }
                            }
                            case "Caterpillar": {
                                if (isTrue(60)) {
                                    val.setAlive(false);
                                    yield Config.CATERPILLAR.WeightNormal;
                                } else {
                                    yield -Config.CATERPILLAR.WeightNormal;
                                }
                            }
                            default:
                                yield -1;
                        };
                    }
                }
            }
        }

        alive = weight >= Config.FOX.WeightNormal / 10.0;

        return 1;
    }

    @Override
    public Animal newChild() {
        return new Fox();
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
