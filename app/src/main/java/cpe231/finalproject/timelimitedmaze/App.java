package cpe231.finalproject.timelimitedmaze;

public final class App {
    private static final String GREETING = "Hello World";

    private App() {
    }

    public static String getGreeting() {
        return GREETING;
    }

    public static void main(String[] args) {
        System.out.println(GREETING);
    }
}
