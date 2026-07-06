package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;

public class FateDecisionHandler {
    private static final Random RANDOM = new Random();

    public static boolean determineFate() {
        return RANDOM.nextBoolean();
    }
}
