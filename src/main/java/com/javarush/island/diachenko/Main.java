package com.javarush.island.diachenko;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Cluster cluster;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Choose cluster size");
        inputClusterSize();

        System.out.print("Day speed in milliseconds: ");
        int time = scanner.nextInt();

        printStartOptions();

        boolean startFlag = true;
        boolean coordinateFlag = true;

        // Чисто технический сканер, который перехватывает пустой стринг, который непонятно откуда берется.
        scanner.nextLine();
        String input = scanner.nextLine();

        int row = 0;
        int column = 0;
        cluster = new Cluster(Config.CLUSTER.rows, Config.CLUSTER.columns, time);

        while (startFlag) {
            switch (input) {
                case "q":
                    startFlag = false;
                    cluster.stopFields();
                    break;
                case "f":
                    cluster.printFullStatistic(cluster.getStatistic());
                    Thread.sleep(time);
                    break;
                case "s":
                    cluster.printShortStatistic(cluster.getStatistic());
                    Thread.sleep(time);
                    break;
                case "c":
                    if (coordinateFlag) {
                        coordinateFlag = false;
                        boolean checkCoordinatesFlag = true;

                        System.out.print("Row: ");
                        row = scanner.nextInt();
                        while (checkCoordinatesFlag) {
                            if (row < 0 || row > Config.CLUSTER.rows) {
                                System.out.print("Wrong! Type row in limits [0" + ":" + Config.CLUSTER.rows + "]: ");
                                row = scanner.nextInt();
                            } else checkCoordinatesFlag = false;
                        }

                        checkCoordinatesFlag = true;

                        System.out.print("Column: ");
                        column = scanner.nextInt();
                        while (checkCoordinatesFlag) {
                            if (column < 0 || column > Config.CLUSTER.columns) {
                                System.out.print("Wrong! Type column in limits [0" + ":" + Config.CLUSTER.columns + "]: ");
                                column = scanner.nextInt();
                            } else checkCoordinatesFlag = false;
                        }
                    }

                    System.out.println("================================================");

                    cluster.printCoordinateStatistic(row - 1, column - 1);
                    Thread.sleep(time);
                    break;
                default:
                    System.out.println("Wrong input!\n");
                    printStartOptions();
                    input = scanner.nextLine();
            }
        }
    }

    private static void printStartOptions() {
        System.out.println(
                        "Choose statistic depth:" + "\n" +
                        "[f] - print full log" + "\n" +
                        "[s] - print short log" + "\n" +
                        "[c] - print be field coordinates" + "\n" +
                        "[q] - quit" + ""
        );
    }

    private static void inputClusterSize() {
        System.out.print("Rows: ");
        Config.CLUSTER.rows = scanner.nextInt();
        // Проверка на дурака
        if (Config.CLUSTER.rows < 0) Config.CLUSTER.rows *= -1;
        else if (Config.CLUSTER.rows == 0) Config.CLUSTER.rows = 1;

        System.out.print("Columns: ");
        Config.CLUSTER.columns = scanner.nextInt();
        if (Config.CLUSTER.columns < 0) Config.CLUSTER.columns *= -1;
        else if (Config.CLUSTER.columns == 0) Config.CLUSTER.columns = 1;

    }
}
