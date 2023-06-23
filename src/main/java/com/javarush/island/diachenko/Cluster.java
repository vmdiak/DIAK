package com.javarush.island.diachenko;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Cluster {
    private final int rows;
    private final int columns;
    private final int time;
    private Field[][] arrayFields;
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(12);
    public Cluster(int rows, int columns, int time) {
        this.rows = rows;
        this.columns = columns;
        this.time = time;

        arrayFields = new Field[rows][columns];
        fillArrayFields();
    }

    private void fillArrayFields() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arrayFields[i][j] = new Field(i, j, this);
                executor.scheduleWithFixedDelay(arrayFields[i][j], 1, time, TimeUnit.SECONDS);
            }
        }
    }

    public void stopFields() {
        executor.shutdown();
    }

    // Говнокод. Нужно что-бы статистика где-то хранилась и обновлялась,
    // но здесь она каждый раз пересчитывается заново. Подозреваю что жутко не оптимально.
    // Возможно оптимальнее было бы сделать с помощью Stream API.

    // Collect statistic about animals and plants amount from cluster
    // Собираем статистику о количестве животных и растений с кластера
    public HashMap<String, Integer> getStatistic() {
        HashMap<String, Integer> map = new HashMap<>();

        for (Field[] row:
             arrayFields) {
            for (Field val:
                 row) {
                if (map.size() == 0) {
                    map.putAll(val.getCountAnimals());
                    map.put("Plant", val.getPlants().size());
                }

                for (Map.Entry<String, Integer> entry:
                        val.getCountAnimals().entrySet()) {
                    int recentNumber = map.get(entry.getKey());
                    map.put(entry.getKey(), recentNumber + entry.getValue());
                }

                int recentPlants = map.get("Plant");
                map.put("Plant", recentPlants + val.getPlants().size());
            }
        }

        return map;
    }

    public void printFullStatistic(HashMap<String, Integer> map) {
        System.out.println("Moved : " + Config.FIELD_DATA.moved + "\n" +
                           "Died : " + Config.FIELD_DATA.deads + "\n" +
                            "Kids : " + Config.FIELD_DATA.kids + "\n"
        );

        for (Map.Entry<String, Integer> entry:
             map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("==========================================================\n");
    }

    public void printShortStatistic(HashMap<String, Integer> map) {
        int allAnimals = 0;
        for (Map.Entry<String, Integer> entry:
                map.entrySet()) {
            allAnimals += entry.getKey().equals("Plant") ? 0 : entry.getValue();
        }
        System.out.println("Animals: " + allAnimals);
        System.out.println("Plants: " + map.get("Plant"));
        System.out.println("==========================================================\n");
    }

    public void printCoordinateStatistic(int row, int column) {
        HashMap<String, Integer> map = getField(row,column).getCountAnimals();

        System.out.println("Field [" + (row + 1) + " : " + (column + 1) + "]");

        for (Map.Entry<String, Integer> entry:
             map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Plants: " + getField(row, column).getPlants().size());
    }

    public Field getField(int row, int column) {
        return arrayFields[row][column];
    }

    public int getRowsNumber() {
        return rows;
    }

    public int getColumnsNumber() {
        return columns;
    }
}
