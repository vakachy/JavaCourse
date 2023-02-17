package Homeworks.Homework_2;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

// Реализуйте алгоритм сортировки пузырьком числового массива,
// результат после каждой итерации запишите в лог-файл.
public class Task_2 {
    
    // метод для сортировки массива "пузырьком" с логированием событий
    // после каждой итерации
    public static int[] sortArray(int[] array) throws SecurityException, IOException {
        Logger logger = Logger.getLogger(Task_2.class.getName());
        logger.setLevel(Level.INFO);
        FileHandler fh = new FileHandler("log.txt");
        logger.addHandler(fh);
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);

        String msg;
        int temp;
        int count = 0;        
        for (int i = array.length - 1; i >= 1; i--) {
            // msg = "Тестовое логирование, проход № " + count + "\n" 
            //         + "Массив сейчас такой: " + Arrays.toString(array);
            // logger.info(msg);
            
            for (int j = 0; j < i; j++) {
                count++;
                msg = "Тестовое логирование, итерация № " + count + "\n" 
                        + "Массив сейчас такой: " + Arrays.toString(array) + "\n";
                logger.info(msg);
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        } return array;
    }

    public static void main(String[] args) throws SecurityException, IOException {
        int[] array = {-1, 2, 555, 0, 8, 17, -10, 3};
        sortArray(array);
    }
}
