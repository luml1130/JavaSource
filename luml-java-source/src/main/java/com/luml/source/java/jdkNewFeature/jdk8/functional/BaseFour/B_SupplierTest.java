package com.luml.source.java.jdkNewFeature.jdk8.functional.BaseFour;

import com.luml.source.domain.Person;
import com.luml.source.java.data.enumT.ScoreEnum;
import org.junit.Test;

import java.util.function.Supplier;

public class B_SupplierTest {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> "小小水";
        System.out.println(supplier.get());

        Supplier<Person> personSupplier = Person::new;
        System.out.println(personSupplier.get());


    }

    @Test
    public void testSupplier(){
        ScoreEnum scoreEnum = ScoreEnum.A;
        captureStep("Trigger"+ scoreEnum.getDesc(), () ->
                test1(scoreEnum.getDesc())
        );
        System.out.println("--------------------------");
        captureStep("Trigger"+ scoreEnum.getDesc(), () -> switch (scoreEnum) {
            case A -> 1;
            case B ->  test1(scoreEnum.getDesc());
            case C -> {
                throw  new RuntimeException("exception");
            }
            case D -> test2(scoreEnum.getDesc());
            default -> 0; //如果有没使用的枚举 比如E 此处可生效。
        });
    }
    private void captureStep(String action,Supplier<Integer> supplier){
        System.out.println("action="+action + "supplier="+supplier.get());
    }

    private Integer test1(String action){
        System.out.println("action: " + action);
        return 1;
    }
    private Integer test2(String action){
        System.out.println("action: " + action);
        return 1;
    }

}
