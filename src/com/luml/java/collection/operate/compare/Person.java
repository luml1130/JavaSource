package com.luml.java.collection.operate.compare;

/**
 * @author luml
 * @description
 * @date 2020/4/28 16:18
 */
public class Person implements Comparable  {
    private String name;

    private Integer age;

    private String mind;

    public Person(String name, Integer age, String mind) {
        this.name = name;
        this.age = age;
        this.mind = mind;
    }

    /**
     * 比较方法在类中声明,一般是传入一个类和当前类比较
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        //return 0;
        Person person = (Person) o;
        int ret = this.name.compareTo(person.getName());
        if (ret == 0) {
            ret = Integer.compare(this.age, person.getAge());
        }
        if (ret == 0) {
            return this.getMind().compareTo(person.getMind());
        }
        return ret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMind() {
        return mind;
    }

    public void setMind(String mind) {
        this.mind = mind;
    }
}
