package MainCourse_1Level.Lesson5.Homework05;

import MainCourse_1Level.Lesson5.Homework05.models.Animal;

public class Cat extends Animal {

    public Cat (String name, float maxRun, float maxJump, float maxSwim) {
        super("Cat", name, maxRun, maxJump, maxSwim);
    }


    @Override
    public boolean swim(float length){
        System.out.print(this.type + "s " + "can't swim! ");
        return false;
    }

    @Override
    public float getMaxSwim () {
        maxSwim = 0;
        return maxSwim;
    }

}
