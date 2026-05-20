package com.gof.structural.Decorator;

/**
 * @author luml
 * @description:   装饰的类 和 被装饰的类 实现相同的接口 或者 继承相同的父类.
 *                在装饰的类中获得被装饰的类的引用.
 * @date 2020/4/21 22:07
 */
public class ItCastTest {
    public static void main(String[] args) {
        final IPerson person = new Person();
        final IPerson superPerson = new PersonWrapper(person);

        person.run();
        superPerson.run();
    }
}

interface IPerson {
    public void run();
}

class Person implements IPerson {
    @Override
    public void run(){
        System.out.println("人跑100米需要14秒...");
    }
}

class PersonWrapper implements IPerson {
    /**
     * 在装饰的类中获得被装饰的类的引用.
     * 定义一个构造方法把被包装的类传进来
     * @param person
     */
    private IPerson person;
    public PersonWrapper(IPerson person){
        this.person = person;
    }
    @Override
    public void run() {
        //person.run();
        System.out.println("超人跑100米需要2秒....");
    }
}
