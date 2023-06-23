package com.javarush.island.diachenko;

import com.javarush.island.diachenko.animals.Animal;
import com.javarush.island.diachenko.animals.Herbivore;
import com.javarush.island.diachenko.animals.Predator;
import com.javarush.island.diachenko.animals.herbivores.*;
import com.javarush.island.diachenko.animals.predators.*;

import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Service{
    private static int randomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    private static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    // Говнокод, который нужно было сделать например через дженерики
    public static void fillVectorWithAnimals (Vector<Animal> vector, HashMap<String, Integer> map) {
        boolean flag = true;

        for (int i = randomInt(1, Config.WOLF.AmountMax); i > 0; i--) {
            vector.add(new Wolf());

            if (flag) {
                map.put("Wolf", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.FOX.AmountMax); i > 0; i--) {
            vector.add(new Fox());

            if (flag) {
                map.put("Fox", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.EAGLE.AmountMax); i > 0; i--) {
            vector.add(new Eagle());

            if (flag) {
                map.put("Eagle", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.BOA.AmountMax); i > 0; i--) {
            vector.add(new Boa());

            if (flag) {
                map.put("Boa", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.BEAR.AmountMax); i > 0; i--) {
            vector.add(new Bear());

            if (flag) {
                map.put("Bear", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.SHEEP.AmountMax); i > 0; i--) {
            vector.add(new Sheep());

            if (flag) {
                map.put("Sheep", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.RABBIT.AmountMax); i > 0; i--) {
            vector.add(new Rabbit());

            if (flag) {
                map.put("Rabbit", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.MOUSE.AmountMax); i > 0; i--) {
            vector.add(new Mouse());

            if (flag) {
                map.put("Mouse", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.HORSE.AmountMax); i > 0; i--) {
            vector.add(new Horse());

            if (flag) {
                map.put("Horse", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.GOAT.AmountMax); i > 0; i--) {
            vector.add(new Goat());

            if (flag) {
                map.put("Goat", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.DUCK.AmountMax); i > 0; i--) {
            vector.add(new Duck());

            if (flag) {
                map.put("Duck", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.DEER.AmountMax); i > 0; i--) {
            vector.add(new Deer());

            if (flag) {
                map.put("Deer", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.CATERPILLAR.AmountMax); i > 0; i--) {
            vector.add(new Caterpillar());

            if (flag) {
                map.put("Caterpillar", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.BUFFALO.AmountMax); i > 0; i--) {
            vector.add(new Buffalo());

            if (flag) {
                map.put("Buffalo", i);
                flag = false;
            }
        }

        flag = true;
        for (int i = randomInt(1, Config.BOAR.AmountMax); i > 0; i--) {
            vector.add(new Boar());

            if (flag) {
                map.put("Boar", i);
                flag = false;
            }
        }
    }

    public static void fillVectorWithPlants(Vector<Plant> vector) {
        for (int i = 0; i < randomInt(0, Config.PLANT.AmountMax); i++) {
            // Add plant if field contains !maximum plants
            // Добавляем растение, если на их меньше чем максимум
            if (vector.size() >= Config.PLANT.AmountMax) break;

            vector.add(new Plant());
        }
    }

    public static void letsEat(Vector<Animal> animals, Vector<Plant> plants) {
        for (Animal val:
             animals) {
            if (val.isAlive()) {
                if (val instanceof Herbivore) {
                    // If animal is duck, mouse or boar it can eat several kind of animals
                    // Если животное утка, мышь или кабан, оно может есть несколько видов животных
                    // Снова говнокод, который нужно было сделать по другому
                    if (val instanceof Duck) {
                        ((Duck) val).eat(animals, plants);
                    } else if (val instanceof Boar) {
                        ((Boar) val).eat(animals, plants);
                    } else if (val instanceof Mouse) {
                        ((Mouse) val).eat(animals, plants);
                    } else {
                        ((Herbivore) val).eat(plants);
                    }
                } else if (val instanceof Predator) {
                    ((Predator) val).eat(animals);
                }
            }
        }
    }

    public static void removeDeadAnimals(Vector<Animal> animals, HashMap<String, Integer> animalsCount) {
        for (Animal val:
             animals) {
            String str = val.getClass().getName();
            String className = str.substring(str.lastIndexOf(".") + 1);

            if (!val.isAlive()) {
                animalsCount.put(className, animalsCount.get(className) - 1);
                Config.FIELD_DATA.deads++;
            }
        }

        animals.removeIf(animal -> !animal.isAlive());
    }

    public static void duplicateAnimals(Vector<Animal> animals, HashMap<String, Integer> animalsCount) {
        Vector<Animal> result = new Vector<>();

        for (Animal val:
             animals) {
            // Finding class name
            // Находим имя класса
            String str = val.getClass().getName();
            String className = str.substring(str.lastIndexOf(".") + 1);
            int valCount = animalsCount.get(className);

            // Duplicate animal if exist animals number less than max
            // Дублируем животного если количество животных меньше чем максимально возможное
            if (valCount < val.getMaxAmount()) {
                // 50% luck imitation
                // 50% имитация удачи
                if (randomBoolean()) {
                    result.add(val.newChild());
                    animalsCount.put(className, valCount + 1);
                    Config.FIELD_DATA.kids++;
                }
            }
        }

        animals.addAll(result);
    }

    public static void moveAnimals(Vector<Animal> animals, Field field) {
        // Animals list to move to other field
        // Список животных на перемещение на другое поле
        Vector<Animal> movingAnimals = new Vector<>();

        for (Animal val:
             animals) {
            // 50 percent chance animal decide to move to other field
            // 50 процентная вероятность того что животное решит передвинуться на другую клетку
            if (randomBoolean()) {
                // It's range in sense, not speed
                // По сути это дистанция передвижения, а не скорость
                int speed = val.getMaxSpeed();
                int newRow = field.getRow();
                int newColumn = field.getColumn();

                for (int i = 0; i < randomInt(0, speed); i++) {
                    // Every step can be in any directions by changing recent row and column (coordinates)
                    // Каждый шаг может быть в любом направлении, изменяя текущую строку и колонку (координаты)
                    newRow += randomInt(-1, 1);
                    if (newRow < 0) newRow = Config.CLUSTER.rows - 1;
                    else if (newRow == Config.CLUSTER.rows) newRow = 0;

                    newColumn += randomInt(-1, 1);
                    if (newColumn < 0) newColumn = Config.CLUSTER.columns - 1;
                    else if (newColumn == Config.CLUSTER.columns) newRow = 0;
                }

                movingAnimals.add(val);

                // Getting val (animal object) class name
                // Получаем имя класса val (объект животное)
                // Говнокод, который нужно было сделать по другому. Например, захардкодить в самом классе животного или в конфиге
                String str = val.getClass().getName();
                String className = str.substring(str.lastIndexOf(".") + 1);

                // Decrement the animals number on field by its kind
                // Уменьшить количество животных этого вида
                int num0 = field.getCountAnimals().get(className) - 1;
                field.getCountAnimals().put(className, num0);
                // Passing the animal's link to another field
                // Передаем ссылку на животное в другое поле
                field.getCluster().getField(newRow, newColumn).setCandidate(val);
                Config.FIELD_DATA.moved++;
            }
        }

        animals.removeAll(movingAnimals);
    }
}