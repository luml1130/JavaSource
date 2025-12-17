package com.luml.java.collection.operate.sort;

import com.luml.domain.sort.Book;
import com.luml.domain.sort.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author luml
 * @description
 * Collections.sort() 有两种主要的调用方式：
 * ‌自然排序（Natural Ordering）‌
 *      ：如果对象的类实现了 Comparable 接口，可以直接调用 Collections.sort(list)。
 *      排序时会自动使用对象的 compareTo() 方法定义的规则。‌
 * ‌自定义排序（Custom Ordering）‌：通过传入一个 Comparator 实例来指定排序逻辑，
 *      例如按对象的某个属性升序或降序排列。‌
 *
 * 如需更深入的排序控制（如降序或复杂对象排序），建议结合 Comparator 使用。‌
 * @date 2025/12/17
 */
public class CollectionsSortTest {

    @Test
    public void testSort(){
        //自然排序‌（默认升序）：要求集合元素实现 Comparable 接口（如 String、Integer 等内置类型已实现）。
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5);
        Collections.sort(numbers); // 排序后: [1, 1, 3, 4, 5]


        //自定义排序‌：通过 Comparator 指定排序规则，适用于未实现 Comparable 的类或需要特殊排序逻辑时。
        List<String> words = Arrays.asList("banana", "apple", "cherry");
        Collections.sort(words, (s1, s2) -> s1.length() - s2.length());
        // 按长度排序: ["apple", "banana", "cherry"]

        //自定义排序示例（降序）
        List<String> words2 = Arrays.asList("banana", "apple", "cherry");
        Collections.sort(words2, (s1, s2) -> s2.compareTo(s1));
        System.out.println(words2); // 输出: ["cherry", "banana", "apple"]
    }

    @Test
    public void testObjectSort(){
        // 使用 Comparator 排序
        List<Book> books = Arrays.asList(
                new Book(1, "Java", 50.0, Calendar.getInstance()),
                new Book(2, "Python", 30.0, Calendar.getInstance())
        );
        List<Student> students = Arrays.asList(
                new Student("Alice", 20), new Student("Bob", 18));

        //1、使用 Comparable 接口（实现自然排序
        //让类实现 Comparable<T> 接口，并重写 compareTo() 方法。
        Collections.sort(students); // 自动按年龄升序排序

        //2、使用 Comparator 接口（自定义比较器）
        //通过传入 Comparator 实例来定义排序规则，这种方式更灵活，尤其适合按不同属性排序。例如，按姓名排序：
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName()); // 按姓名字典序排序
            }
        });

        //3、使用 Lambda 表达式可以更简洁：
        Collections.sort(students, (s1, s2) -> s1.getName().compareTo(s2.getName()));
        // 按价格升序
        Collections.sort(books, (b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice()));

        // 按出版时间降序
        Collections.sort(books, (b1, b2) -> b2.getPublishDate().compareTo(b1.getPublishDate()));
        //如果需要降序，可以使用 Comparator.reverseOrder()
        Collections.sort(students, Comparator.reverseOrder());
        // 如果需要降序，也调整 Lambda 表达式：‌
        Collections.sort(students, (s1, s2) -> s2.getAge() - s1.getAge()); // 按年龄降序








    }

}
