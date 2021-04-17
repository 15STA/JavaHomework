package MainCourse_1Level.Lesson5.Homework05;

import MainCourse_1Level.Lesson5.Homework05.models.Animal;

public class Bird extends Animal {

    public Bird (String name, float maxRun, float maxJump, float maxSwim) {
        super("Bird", name, maxRun, maxJump, maxSwim);
    }

    @Override
    public boolean swim(float length){
        System.out.print(this.type + "s " + "can't swim! ");
        return false;
    }



}
