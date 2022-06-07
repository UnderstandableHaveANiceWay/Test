import java.util.Scanner;

public class Task1 {
    public static int birthdayCakeCandles(int[] candles) {
        int highest = candles[0], count = 0;
        for (int candle:candles) {
            if (candle > highest) {
                highest = candle;
                count = 1;
            } else if (candle == highest) {
                ++count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] candles = new int[n];
        for (int i = 0; i < n; ++i) {
            candles[i] = scanner.nextInt();
        }
        System.out.println(birthdayCakeCandles(candles));
    }
}
