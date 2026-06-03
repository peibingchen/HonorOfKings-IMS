package util;

import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt, int defaultValue) {
        String value = readLine(prompt);
        if (value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number. Using default: " + defaultValue);
            return defaultValue;
        }
    }
}
