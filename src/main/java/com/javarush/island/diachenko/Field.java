package com.javarush.island.diachenko;

import com.javarush.island.diachenko.animals.Animal;

import java.util.HashMap;
import java.util.Vector;

public class Field implements Runnable{
    private Vector<Animal> animals = new Vector<>();
    private Vector<Plant> plants = new Vector<>();
    private HashMap<String, Integer> countAnimals = new HashMap<>();
    private int row = 0;
    private int column = 0;
    private Cluster cluster;
    private Vector<Animal> candidates = new Vector<>();

    public Field(int row, int column, Cluster cluster) {
        this.row = row;
        this.column = column;
        this.cluster = cluster;

        Service.fillVectorWithAnimals(animals, countAnimals);
        Service.fillVectorWithPlants(plants);
    }

    @Override
    public void run() {
        //if (row == 1 && column == 1) showStatistic();
        //showStatistic();

        animals.addAll(candidates);
        candidates.clear();

        Service.fillVectorWithPlants(plants);
        Service.removeDeadAnimals(animals, countAnimals);
        Service.letsEat(animals, plants);
        Service.duplicateAnimals(animals, countAnimals);
        Service.moveAnimals(animals, this);
    }

    private void showStatistic() {
        System.out.println("[Field " + row + " : " + column + "]" + "\n" +
                            "Animals on field: " + animals.size() + "\n" +
                            "Plants on field: " + plants.size() + "\n");
        System.out.println("======================================================\n");
    }

    public Cluster getCluster(){
        return cluster;
    }

    public synchronized HashMap<String, Integer> getCountAnimals() {
        return countAnimals;
    }

    public Vector<Plant> getPlants() {
        return plants;
    }

    public Vector<Animal> getAnimals() {
        return animals;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setCandidate(Animal animal) {
        candidates.add(animal);

        String str = animal.getClass().getName();
        String className = str.substring(str.lastIndexOf(".") + 1);
        int num0 = getCountAnimals().get(className) + 1;
        getCountAnimals().put(className, num0);
    }
}
