package MainCourse_1Level.Lesson6.Homework;

/**
 * 1. Посмотреть все предыдущие уроки в части кода: 1-6 уроки
 * 2. Вопросы в комментарии к домашней работе
 * 3. Модифицировать код в части работы с объектами.
 * 4. Тренировка по тестам Java
 */

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Homework06 extends Object {

//    private static Scanner scanner = new Scanner(System.in);
    private static int round = 1;

    public static void main(String[] args) throws InterruptedException {
        Random successRound = new Random(); // во время раундов кратному successRound Healer лечит героя, которому сопутствует удача в данном бою

        LightHero lightHero = new LightHero("LightHero", 5, 60, 40);
        DarkHero darkHero = new DarkHero("DarkHero", 8, 60,  10);

        while (!lightHero.isDead() || !darkHero.isDead()) {
            System.out.println("**** Round " + round + " ****");

            System.out.println(lightHero.getHeroInfo());
            System.out.println(darkHero.getHeroInfo());

            lightHero.attack(darkHero);

            if (darkHero.isDead()) {
                System.out.println("lightHero win");
                break;
            }

            darkHero.attack(lightHero);

            if (lightHero.isDead()) {
                System.out.println("DarkHero win");
                break;
            }


            if (round % (successRound.nextInt(3) +2) == 0) {
                System.out.println("This round may be lucky for heroes... Try!");
                Healer.heal(lightHero);
                Healer.heal(darkHero);
            }
            else {
                lightHero.healSelf();
                darkHero.healSelf();
            }

            System.out.println(lightHero.getHeroInfo());
            System.out.println(darkHero.getHeroInfo());
            round++;

            Thread.sleep(3000);
        }






    }

}
