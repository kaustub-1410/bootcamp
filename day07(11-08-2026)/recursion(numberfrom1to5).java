public class Main {

    static void printNumbers(int n) {
        if (n == 6) {
            return;
        }

        System.out.println(n);
        printNumbers(n + 1);
    }

    public static void main(String[] args) {
        printNumbers(1);
    }
}