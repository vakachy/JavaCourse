package Homeworks.Homework_1;
import java.util.Scanner;
public class Task_1 {
    // метод для ввода целого числа от пользователя
    static int getInteger() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer value: ");
        int value = sc.nextInt();
        sc.close();
        return value;
    }
    // метод для вычисления треугольного числа
    static int evaluateTriangularNumber(int value) {
        int triangularNumber = (value * (value + 1)) / 2;
        return triangularNumber;
    }
    public static void main(String[] args) {
        int value = getInteger();
        int triangularNumber = evaluateTriangularNumber(value);
        System.out.println(triangularNumber);
}
}
