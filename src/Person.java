import beans.PropertyValues;

import java.util.List;

public class Person{
    private String name;
    private int age;
    private Person partner;
    private String[] scores;
    private boolean girl;
    private PropertyValues pvs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name:"+name+",age:"+age;
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    public String[] getScores() {
        return scores;
    }

    public void setScores(String[] scores) {
        this.scores = scores;
    }

    public boolean isGirl() {
        return girl;
    }

    public void setGirl(boolean girl) {
        this.girl = girl;
    }

    public PropertyValues getPvs() {
        return pvs;
    }

    public void setPvs(PropertyValues pvs) {
        this.pvs = pvs;
    }
}
