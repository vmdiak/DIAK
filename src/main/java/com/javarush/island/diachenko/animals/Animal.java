package com.javarush.island.diachenko.animals;

public interface Animal {
    int getMaxAmount();

    boolean isAlive();

    boolean canBeEatable();

    int eat();

    Animal duplicate();

    int move();

    boolean isTrue(int probability);

    void setAlive(boolean bool);

    Animal newChild();

    boolean getGender();

    int getMaxSpeed();
}
