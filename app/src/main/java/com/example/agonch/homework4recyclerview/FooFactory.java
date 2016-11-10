package com.example.agonch.homework4recyclerview;

import java.util.Random;

public class FooFactory {

    private static final Random RANDOM = new Random();

    public static FooObject createFoo() {
        int semiRandomInt = RANDOM.nextInt(500);
        FooObject result = new FooObject();
        result.name = "I am semi-random Foo #" + semiRandomInt;
        return result;
    }
}
