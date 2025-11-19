package com.luml.java.tools.validation;

import com.luml.domain.City;

import java.util.Objects;

/**
 * @author luml
 * @description
 * @date 2025/11/17
 */
public class ObjectsTest {

    public static void main(String[] args) {
        City city = new City();
        city.setCity("西安");
        System.out.println(Objects.isNull(city.getProvince()));

    }
}
