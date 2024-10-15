package utils;

public class BetterPrint {

    public static void printWithBorder(String arg, String border) {
        printBorder(border,arg.length()+5);
        printCentered(arg, arg.length()+5);
        printBorder(border,arg.length()+5);
    }

    private static void printBorder(String border, int size) {
        System.out.println(border.repeat(size/border.length()));
    }

    public static void printCentered(String arg, int margin) {
        StringBuilder builder = new StringBuilder();

        if (margin < arg.length()) {
            System.out.println(arg);
            return;
        }

        int left = margin - arg.length();

        System.out.println(builder.append(" ".repeat(left/2)).append(arg).append(" ".repeat(left/2)));
    }



}
