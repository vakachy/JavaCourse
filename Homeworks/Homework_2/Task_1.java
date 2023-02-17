package Homeworks.Homework_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// Дана строка sql-запроса "select * from students where ".
// Сформируйте часть WHERE этого запроса, используя StringBuilder.
// Данные для фильтрации приведены ниже в виде json строки.
// Если значение null, то параметр не должен попадать в запрос.
// Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
// select * from students where name='Ivanov',country='Russia',city='Moscow'

public class Task_1 {

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

    // для выборки подстрок из заданной json-строки, находящихся между парами кавычек
    // результат: массив keyValue, содержащий выбранные подстроки
    public static String[] getKeyValue(int[] quotesIndexes, String string) {
        String[] keyValue = new String[quotesIndexes.length / 2];
        for (int j = 0; j < quotesIndexes.length / 2; j++) {
            String word = string.substring(quotesIndexes[2 * j] + 1, quotesIndexes[2 * j + 1]);
            keyValue[j] = word;
        }
        return keyValue;
    }

    // метод для формирования sql-запроса из массива keyValue с проверкой на null
    public static String createSQLRequest(String[] keyValue, String partialRequest, String eliminate) {
        StringBuilder sb = new StringBuilder();
        String excludeValue = new String(eliminate);
        String value;
        sb.append(partialRequest);
        for (int j = 0; j < keyValue.length/2; j++) {
            value = keyValue[2*j+1];
            if (!value.equalsIgnoreCase(excludeValue)) {
                sb.append(keyValue[2*j] + "=");
                sb.append("'" + keyValue[2*j+1] + "'" + " ");
            }
        } return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String partialRequest = "select * from students where ";
        String eliminate = "null";
        String line;

        BufferedReader br = new BufferedReader(new FileReader("file.txt"));
        line = br.readLine();
        br.close();

        int count = quotesCount(line);
        int quotesIndexes[] = Arrays.copyOf(getQuotesIndexes(line, count), count);
        
        String keyValue[] = Arrays.copyOf(getKeyValue(quotesIndexes, line), getKeyValue(quotesIndexes, line).length);

        System.out.println(createSQLRequest(keyValue, partialRequest, eliminate));
    }
}
