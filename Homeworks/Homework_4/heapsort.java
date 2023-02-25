
package Homeworks.Homework_4;

public class heapsort {

    // метод для построения кучи/пирамиды типа Max Heap, где значение
    // каждого "ребенка" всегда меньше,
    // чем значение их "родителя"
    public static void buildMaxHeap(int a[], int n) {
        for (int i = 1; i < n; i++) {
            // если "ребенок" больше, чем "родитель" (i - "ребенок", (i-1)/2 - "родитель")
            if (a[i] > a[(i - 1) / 2]) {
                int j = i;
                // поменять местами "детей" и "родителей"
                while (a[j] > a[(j - 1) / 2]) {
                    swap(a, j, (j - 1) / 2);
                    j = (j - 1) / 2; // следующий "родитель"
                }
            }
        }
    }

    // метод для сортировки кучи/пирамиды:
    // 1. максимальное значение, стоящее на первом месте в куче/пирамиде
    // меняется местами с последним элементом;
    // 2. после этого куча/пирамида "теряет" свою правильную структуру;
    // 3. далее упорядочивается структура кучи/пирамиды до правильной;
    // и т.д.
    public static void heapSort(int a[], int n) {
        buildMaxHeap(a, n);
        for (int i = n - 1; i > 0; i--) {
            // меняем значение первого, максимального по значению, элемента массива/кучи/пирамиды
            // с последним элементом
            swap(a, 0, i);
            // поддержание свойства кучи/пирамиды после каждого обмена
            int j = 0;
            int index;
            do {
                // выбирается "ребенок" в куче/пирамиде, значение которого выше:
                // индекс "ребенка" должен быть меньше, чем индекс неотсортированной части массива (i-1)
                // (неотсортированная часть массива начинается с индекса i)
                // и,
                // выбирается "ребенок", расположенный справа (index+1), если его значение
                // выше значения "ребенка", расположенного слева (index), иначе выбран "ребеок" слева
                index = (2 * j + 1);
                if (index < (i - 1) && a[index] < a[index + 1])
                    index++;

                // если значение "родителя" меньше значения "ребенка" (выбранного на предыдущих 3-х шагах),
                // то меняется "родитель" с "ребенком" (имеющим более высокое значение)
                // Условие index < i предусматривает ситуацию, когда "ребенок" справа уже находится
                // в отсортированной части массива, а "ребенок" слева - нет.
                if (index < i && a[j] < a[index])
                    swap(a, j, index);
                j = index;
            } while (index < i);
        }
    }

    // метод, чтобы менять местами два значения в массиве
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // метод "печати" массива
    public static void printaay(int a[]) {
        int n = a.length;
        for (int i = 0; i < n; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        int array[] = { 10, 0, 15, 35, 9, -1, 100, 1001, -5, -5 };
        int n = array.length;

        System.out.println("Заданный массив:");
        printaay(array);
        System.out.println("Массив после сортировки:");
        heapSort(array, n);
        printaay(array);
    }
}