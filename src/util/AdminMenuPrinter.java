package util;

public class AdminMenuPrinter {
    public void printMenu() {
        System.out.println("Admin data management:");
        for (AdminMenuOption option : AdminMenuOption.values()) {
            System.out.println(option.getCode() + ". " + option.getLabel());
        }
    }
}
