package lab.counter;

public class RequestCounter {
    private static int counter;

    public static void increment() {
        ++counter;
    }

    public static Integer getCounter() {
        return counter;
    }
}
