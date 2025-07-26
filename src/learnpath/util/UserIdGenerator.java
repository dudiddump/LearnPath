package learnpath.util;

import java.util.Random;

public class UserIdGenerator {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int letterCount = 3;
    private static final int digitCount = 5;

    public static String generateUserId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < letterCount; i++) {
            int index = random.nextInt(LETTERS.length());
            sb.append(LETTERS.charAt(index));
        }
        for (int i = 0; i < digitCount; i++) {
            int digit = random.nextInt(10); //
            sb.append(digit);
        }
        return sb.toString();
    }
}
