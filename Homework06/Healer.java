package MainCourse_1Level.Lesson6.Homework;

import java.util.Random;

public class Healer {

    protected String name;
    protected float health;
    protected int defence;

    public Healer(String name, float health, int defence) {
        this.name = name;
        this.health = health;
        this.defence = defence;
    }

    public static void heal(SuperHero hero) {
        Random lucky = new Random();
        float treat = hero.defence*0.3f;
        if (lucky.nextBoolean()) {
            hero.health += treat;
            System.out.println(hero.name + " is lucky! HP increase by " + treat + ". " + hero.name + " HP = " + hero.health);
        }
        else {
            System.out.println(hero.name + " isn't lucky. Maybe next time..." );
        }
    }


}

