package com.jayhixson.soundswarm;

public class Assertion {
    public static final boolean PRE = false;
    public static final boolean POST = true;
    public static final boolean INV = true;

    // This class offers only static methods and data.
    // There is no need to create an instance of this class.
    private Assertion() { }

    public static void isTrue(boolean cond) {
        if (!cond) {
            System.out.println("Assertion failed");
            new Throwable().printStackTrace();
            System.exit(0);
        }
    }
}