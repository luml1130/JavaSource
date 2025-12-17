package com.luml.domain.sort;

import lombok.Data;

/**
 * @author luml
 * @description
 * @date 2025/12/6
 */
@Data
public class PersonSort implements Comparable<PersonSort> {

    String name;
    int age;

    public PersonSort() {
    }

    public PersonSort(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 返回值小于0‌：表示当前对象小于传入的参数对象。
     * ‌返回值等于0‌：表示当前对象等于传入的参数对象。
     * ‌返回值大于0‌：表示当前对象大于传入的参数对象。
     * @param other
     * @return
     */
    @Override
    public int compareTo(PersonSort other) {

        //return this.name.compareTo(other.getName()); // 根据名字的自然顺序排序 输出: Alice - 13   Bob - 12
        return Integer.compare(this.age, other.age); //输出：Bob - 12 Alice - 13
        //按年龄小的在前 输出：Bob - 12 Alice - 13
        /*if(this.getAge() > other.getAge()){
            return 1;
        }else if(this.getAge() < other.getAge()){
            return -1;
        }*/

        //按年龄大的在前 输出： Alice - 13 Bob - 12
       /* if(this.getAge() > other.getAge()){
            return -1;
        }else if(this.getAge() < other.getAge()){
            return 1;
        }
        return 0;*/

    }

    /*public int compareTo(@NotNull SubDimensionDetailVO o) {
        if (this.getDimensionId() > o.getDimensionId()) {
            return 1;
        }else if (this.getDimensionId() < o.getDimensionId()) {
            return -1;
        }
        return 0;
    }*/
}
