package com.luml.java.data.json.fastjson;


import com.alibaba.fastjson.JSON;
import com.luml.java.data.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2022/3/22
 */
public class JSONTest {

    private List<Person> listOfPersons = new ArrayList<Person>();

    @Before
    public void setUp() {
        listOfPersons.add(new Person(1,15, "John Doe", new Date()));
        listOfPersons.add(new Person(2,20, "Janette Doe", new Date()));
    }

    @Test
    public void whenJavaList_thanConvertToJsonCorrect() {
        String jsonOutput = JSON.toJSONString(listOfPersons);
        System.out.println(jsonOutput);
    }
}
