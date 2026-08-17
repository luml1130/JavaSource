package com.luml.gof._1_create.builder;

/**
 * @author luml
 * @description:
 * 在Person类中定义一个内部类Builder，
 * 1、这个Builder内部类中的属性要和Person中的相同，并且必须有的属性要用final修饰，防止这些属性没有被赋值。
 * 2、然后内部类中定义了一个构造方法，传入必须有的属性。其他非必须的属性都通过方法设置，每个方法都返回Builder对象自身。
 * 3、最后定义了一个build方法，将Builder对象传入Person的私有构造方法，最终返回一个对象。重写toString
 *     返回两个必填项组成的字符串。
 * @date 2020/4/30 16:26
 */
public class Person {
    private final String name;
    private final String gender;
    private  String house;
    private  String age;
    private  String height;
    private  Integer money;

    public Person(Builder builder) {
        this.name = builder.name;
        this.gender = builder.gender;
    }

    @Override
    public String toString() {
        return "姓名："+this.name+"，性别："+this.gender;
    }

    public static class  Builder{
        private final String name;
        private final String gender;
        private  String house;
        private  String age;
        private  String height;
        private  Integer money;

        public Builder(String name, String gender) {
            this.name = name;
            this.gender = gender;
        }
        public Builder house(String house) {
            this.house = house;
            return this;
        }
        public Builder height(String height) {
            this.height = height;
            return this;
        }
        public Builder money(Integer money) {
            this.money = money;
            return this;
        }
        //重要哦
        public Person build(){
            return new Person(this);
        }
    }

}
class PersonMain{
    public static void main(String[] args) {
        Person person = new Person.Builder("lumengliang","男")
                .height("1.75")
                .house("140平")
                .money(10000).build();
        System.out.println(person.toString());
    }
}