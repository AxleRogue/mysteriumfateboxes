package me.axlerogue.mysteriumfateboxes.handlers;

import java.util.Random;

public class LuckDecisionHandler {
    private static final Random RANDOM = new Random();

    public static boolean determineLuck() {
        return RANDOM.nextBoolean();
    }
}
