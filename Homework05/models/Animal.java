package MainCourse_1Level.Lesson5.Homework05.models;

import java.util.Random;

public abstract class Animal {
    protected String type;
    protected String name;
    protected float maxRun;
    protected float maxJump;
    protected float maxSwim;

    public Animal (String type, String name, float maxRun, float maxJump, float maxSwim) {
        Random r = new Random();
        final float k = 0.3f; // коэффициент разброса значений (махRun, maxJump, maxSwim) относительно заданных - 30%
        this.type = type;
        this.name = name;
 //       this.maxRun = maxRun;
//        this.maxJump = maxJump;
//       this.maxSwim = maxSwim;
        this.maxRun = r.nextFloat()*((1+k)*maxRun-(1-k)*maxRun) + (1-k)*maxRun;
        this.maxJump = r.nextFloat()*((1+k)*maxJump-(1-k)*maxJump) + (1-k)*maxJump;
        this.maxSwim = r.nextFloat()*((1+k)*maxSwim-(1-k)*maxSwim) + (1-k)*maxSwim;
    }

    public boolean run (float length) {
        if (length <= maxRun) {
            return true;
        }
        else return false;
    }

    public boolean jump (float height) {
        if (height <= maxJump) {
           return true;
        }
        else return false;
    }

    public boolean swim (float length) {
        if (length <= maxSwim) {
            return true;
        }
        else return false;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public float getMaxRun () {
        return maxRun;
    }

    public float getMaxJump () {
        return maxJump;
    }

    public float getMaxSwim () {
       return maxSwim;
    }

}
