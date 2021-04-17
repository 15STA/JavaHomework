package MainCourse_1Level.Lesson5.Homework05;

import MainCourse_1Level.Lesson5.Homework05.models.Animal;

/**
 1. Создать классы Собака, Лошадь, Птица и Кот с наследованием от класса Животное.

 2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
 В качестве параметра каждому методу передается величина, означающая или длину
 препятствия (для бега и плавания), или высоту (для прыжков).

 3. У каждого животного есть ограничения на действия
 (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,;
 прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ;
 плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).

 4. При попытке животного выполнить одно из этих действий,
 оно должно сообщить результат.
 (Например, dog1.run(150); -> результат: 'Пёсик пробежал!')
 5. * Добавить животным разброс в ограничениях.
 То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.

 **/

public class Homework05 {
    public static void main(String[] args) {
      Cat cat1 = new Cat ("Bars", 200, 2, 1);
      Dog dog1 = new Dog ("Richard", 500, 0.5f, 10);
      Horse horse1 = new Horse ("Cherry", 1500, 3, 100);
      Bird bird1 = new Bird("Robin", 5, 0.2f, 1);


        float animalRun = 300;
        float animalJump = 2.1f;
        float animalSwim = 5;

        Animal [] animalArr = {cat1, dog1, horse1, bird1};

        for (int i=0; i<animalArr.length; i++) {
            animalInfo(animalArr[i]);
            runInfo(animalArr[i], animalRun, animalArr[i].run(animalRun));
            jumpInfo(animalArr[i], animalJump, animalArr[i].jump(animalJump));
            swimInfo(animalArr[i], animalSwim, animalArr[i].swim(animalSwim));
            System.out.println();
        }
    }

    public static void animalInfo (Animal animal){
        if (animal.getMaxSwim() ==0) {
            System.out.print(animal.getType() + "s" + " can run - " + animal.getMaxRun() + ", jump - " +
                    animal.getMaxJump() + ", can't swim. " );
        }
        else {
            System.out.print(animal.getType() + "s" + " can run - " + animal.getMaxRun() + ", jump - " +
                    animal.getMaxJump() + ", swim - " + animal.getMaxSwim() + ". ");
        }
    }

    public static void runInfo (Animal animal, float length, boolean ability) {
        if (ability) {
            System.out.print(animal.getType() + " " + animal.getName() + " has run " + length + " m! ");
        }
        else System.out.print(animal.getType() + " " + animal.getName() + " can't run " + length + " m. ");
    }

    public static void jumpInfo (Animal animal, float height, boolean ability) {
        if (ability) {
            System.out.print(animal.getName() + " has jumped " + height + " m! ");
        }
        else System.out.print(animal.getName() + " can't jump " + height + " m. " );
    }

    public static void swimInfo (Animal animal, float length, boolean ability) {
        if (ability) {
            System.out.print(animal.getName() + " has swum " + length + " m! ");
        }
        else System.out.print(animal.getName() + " can't swim " + length + " m. " );
    }
}
