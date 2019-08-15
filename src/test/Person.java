package test;


import java.util.List;

public class Person implements IPerson{
    private String name;
    private int age;
    private IPerson partner;
    private String[] scores;
    private boolean girl;
    private List<Person> children;

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

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    @Override
    public String sayHi() {
        return "hi,i'm "+getName();
    }

    public IPerson getPartner() {
        return partner;
    }

    public void setPartner(IPerson partner) {
        this.partner = partner;
    }
}
