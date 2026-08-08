package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGTest {
    @Test
    public void testAddition() {
        Assert.assertEquals(2, 1 + 1, "1 + 1 should equal 2");
    }
}
