package com.javarush.island.diachenko.animals.predators;

import com.javarush.island.diachenko.Config;
import com.javarush.island.diachenko.animals.Animal;
import com.javarush.island.diachenko.animals.Predator;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Predator {
    private boolean alive = true;
    private double weight = Config.WOLF.WeightNormal;
    private int maxAmount = Config.WOLF.AmountMax;
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
                    if (weight < (Config.WOLF.WeightNormal + Config.WOLF.SatiatedMax)) {
                        // Add weight if catch other animal
                        // Добавляем к весу, если поймаем другое животное
                        weight += switch (fullClassName.substring(fullClassName.lastIndexOf(".") + 1)) {
                            case "Horse": {
                                if (isTrue(10)) {
                                    val.setAlive(false);
                                    yield Config.HORSE.WeightNormal/10.0;
                                } else {
                                    yield -Config.HORSE.WeightNormal/30.0;
                                }
                            }
                            case "Deer": {
                                if (isTrue(15)) {
                                    val.setAlive(false);
                                    yield Config.DEER.WeightNormal/10.0;
                                } else {
                                    yield -Config.DEER.WeightNormal/30.0;
                                }
                            }
                            case "Rabbit": {
                                if (isTrue(60)) {
                                    val.setAlive(false);
                                    yield Config.RABBIT.WeightNormal;
                                } else {
                                    yield -Config.RABBIT.WeightNormal/2.0;
                                }
                            }
                            case "Mouse": {
                                if (isTrue(80)) {
                                    val.setAlive(false);
                                    yield Config.MOUSE.WeightNormal;
                                } else {
                                    yield -Config.MOUSE.WeightNormal;
                                }
                            }
                            case "Goat": {
                                if (isTrue(60)) {
                                    val.setAlive(false);
                                    yield Config.GOAT.WeightNormal/2.0;
                                } else {
                                    yield -Config.GOAT.WeightNormal/10.0;
                                }
                            }
                            case "Sheep": {
                                if (isTrue(70)) {
                                    val.setAlive(false);
                                    yield Config.SHEEP.WeightNormal/3.0;
                                } else {
                                    yield -Config.SHEEP.WeightNormal/10.0;
                                }
                            }
                            case "Boar": {
                                if (isTrue(15)) {
                                    val.setAlive(false);
                                    yield Config.BOAR.WeightNormal/10.0;
                                } else {
                                    yield -Config.BOAR.WeightNormal/40.0;
                                }
                            }
                            case "Buffalo": {
                                if (isTrue(10)) {
                                    val.setAlive(false);
                                    yield Config.BUFFALO.WeightNormal/20.0;
                                } else {
                                    yield -Config.BUFFALO.WeightNormal/70.0;
                                }
                            }
                            case "Duck": {
                                if (isTrue(40)) {
                                    val.setAlive(false);
                                    yield Config.DUCK.WeightNormal;
                                } else {
                                    yield -Config.DUCK.WeightNormal/10.0;
                                }
                            }
                            default:
                                yield -10;
                        };
                    }
                }
            }
        }

        alive = weight >= Config.WOLF.WeightNormal / 2.0;

        return 1;
    }

    @Override
    public Animal newChild() {
        return new Wolf();
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
