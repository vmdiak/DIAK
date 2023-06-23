package com.javarush.island.diachenko;

public class Config {
    public static class FIELD_DATA{
        public static int kids = 0;
        public static int deads = 0;
        public static int moved = 0;
    }
    public static class CLUSTER{
        public static int rows;
        public static int columns;
    }
    public static class WOLF{
        public static final int WeightNormal = 50;
        public static final int AmountMax = 30;
        public static final int SpeedMax = 3;
        public static final int SatiatedMax = 8;
    }

    public static class BOA{
        public static final int WeightNormal = 15;
        public static final int AmountMax = 30;
        public static final int SpeedMax = 1;
        public static final int SatiatedMax = 3;
    }

    public static class FOX{
        public static final int WeightNormal = 8;
        public static final int AmountMax = 30;
        public static final int SpeedMax = 2;
        public static final int SatiatedMax = 2;
    }

    public static class BEAR{
        public static final int WeightNormal = 500;
        public static final int AmountMax = 50;
        public static final int SpeedMax = 2;
        public static final int SatiatedMax = 80;
    }

    public static class EAGLE{
        public static final int WeightNormal = 6;
        public static final int AmountMax = 20;
        public static final int SpeedMax = 3;
        public static final int SatiatedMax = 1;
    }

    public static class HORSE{
        public static final int WeightNormal = 400;
        public static final int AmountMax = 20;
        public static final int SpeedMax = 4;
        public static final int SatiatedMax = 60;
    }

    public static class DEER{
        public static final int WeightNormal = 300;
        public static final int AmountMax = 20;
        public static final int SpeedMax = 4;
        public static final int SatiatedMax = 50;
    }

    public static class RABBIT{
        public static final int WeightNormal = 2;
        public static final int AmountMax = 150;
        public static final int SpeedMax = 2;
        public static final double SatiatedMax = 0.45;
    }

    public static class MOUSE{
        public static final double WeightNormal = 0.05;
        public static final int AmountMax = 500;
        public static final int SpeedMax = 1;
        public static final double SatiatedMax = 0.01;
    }

    public static class GOAT{
        public static final int WeightNormal = 60;
        public static final int AmountMax = 140;
        public static final int SpeedMax = 3;
        public static final int SatiatedMax = 10;
    }

    public static class BOAR{
        public static final int WeightNormal = 400;
        public static final int AmountMax = 50;
        public static final int SpeedMax = 2;
        public static final int SatiatedMax = 50;
    }

    public static class BUFFALO{
        public static final int WeightNormal = 700;
        public static final int AmountMax = 10;
        public static final int SpeedMax = 3;
        public static final int SatiatedMax = 100;
    }

    public static class DUCK{
        public static final int WeightNormal = 1;
        public static final int AmountMax = 200;
        public static final int SpeedMax = 4;
        public static final double SatiatedMax = 0.15;
    }

    public static class CATERPILLAR{
        public static final double WeightNormal = 0.01;
        public static final int AmountMax = 1000;
        public static final int SpeedMax = 0;
        public static final int SatiatedMax = 0;
    }

    public static class SHEEP{
        public static final double WeightNormal = 70;
        public static final int AmountMax = 140;
        public static final int SpeedMax = 3;
        public static final int SatiatedMax = 15;
    }

    public static class PLANT{
        public static final int WeightNormal = 1;
        public static final int AmountMax = 200;
    }
}
