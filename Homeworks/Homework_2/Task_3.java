package Homeworks.Homework_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
//      {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
//              {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]

// Пример вывода:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.
public class Task_3 {
    // метод для подсчета количества кавычек в заданной json-строке
    public static int quotesCount(String string) {
        int count = 0;
        char quotes = '"';
        char ch;
        for (int i = 0; i < string.length(); i++) {
            if ((ch = string.charAt(i)) == quotes) {
                count++;
            }
        }
        return count;
    }

    // метод для определения индексов всех кавычек в заданной json-строке
    public static int[] getQuotesIndexes(String string, int count) {
        char quotes = '"';
        int[] quotesIndexes = new int[count];
        char ch;
        int k = 0;
        for (int j = 0; j < string.length(); j++) {
            if ((ch = string.charAt(j)) == quotes) {
                quotesIndexes[k] = j;
                k++;
            }
        }
        return quotesIndexes;
    }

    // для выборки подстрок из заданной json-строки, находящихся между парами
    // кавычек
    // результат: массив keyValue, содержащий выбранные подстроки
    public static String[] getKeyValue(int[] quotesIndexes, String string) {
        String[] keyValue = new String[quotesIndexes.length / 2];
        for (int j = 0; j < quotesIndexes.length / 2; j++) {
            String word = string.substring(quotesIndexes[2 * j] + 1, quotesIndexes[2 * j + 1]);
            keyValue[j] = word;
        }
        return keyValue;
    }

    // метод для формирования строк
    // Студент Иванов получил 5 по предмету Математика.
    public static String createOutput(String[] keyValue) {
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < keyValue.length / 6; j++) {
            sb.append("Студент "); // Студент
            sb.append("'" + keyValue[6 * j + 1] + "'" + " "); // 'Иванов '
            sb.append("получил "); // получил
            sb.append("'" + keyValue[6 * j + 3] + "'" + " "); // '5 '
            sb.append("по предмету "); // по предмету
            sb.append("'" + keyValue[6 * j + 5] + "'" + ".\n"); // 'Математика'
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String line;

        BufferedReader br = new BufferedReader(new FileReader("fileA.txt"));
        line = br.readLine();
        br.close();

        int count = quotesCount(line);
        int quotesIndexes[] = Arrays.copyOf(getQuotesIndexes(line, count), count);

        String keyValue[] = Arrays.copyOf(getKeyValue(quotesIndexes, line), getKeyValue(quotesIndexes, line).length);

        System.out.println(createOutput(keyValue));
    }
}
