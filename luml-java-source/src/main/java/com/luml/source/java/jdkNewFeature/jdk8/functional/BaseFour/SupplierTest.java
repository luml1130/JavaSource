package com.luml.source.java.jdkNewFeature.jdk8.functional.BaseFour;

import com.luml.source.domain.Person;

import java.util.function.Supplier;

public class SupplierTest {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> "小小水";
        System.out.println(supplier.get());

        Supplier<Person> personSupplier = Person::new;
        System.out.println(personSupplier.get());
    }
}
