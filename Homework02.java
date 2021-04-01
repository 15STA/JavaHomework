package MainCourse_1Level.Lesson2.Homework02;

/*
    1 Задать целочисленный массив, состоящий из элементов 0 и 1.
    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. Написать метод, заменяющий в
    принятом массиве 0 на 1, 1 на 0;

	2 Задать пустой целочисленный массив размером 8. Написать метод,
	который c помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;

	3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], написать метод,
	принимающий на вход массив и умножающий числа меньше 6 на 2;

	4 Задать одномерный массив. Написать методы поиска в нём
	минимального и максимального элемента;

	5* Создать квадратный целочисленный массив
	(количество строк и столбцов одинаковое), заполнить его диагональные
	элементы единицами, используя цикл(ы);

	6** Написать метод, которому на вход подаётся одномерный массив и
	число n (может быть положительным, или отрицательным), при этом метод
	должен циклически сместить все элементы массива на n позиций.
	[1,2,3,4,5], -2 => [3,4,5,1,2]
	[1,2,3,4,5], 2 => [4,5,1,2,3]

	7 *** Не пользоваться вспомогательным массивом при решении задачи 6.

**/

public class Homework02 {

    public static void main(String[] args) {
        // Задача 1
        int [] array1 = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 }; // массив в котором нужно поменять 0 на 1 и 1 на 0
        System.out.println("Задача 1 (замена 0 на 1 и 1 на 0):");
        System.out.print("Исходный массив: ");
        printArray(array1);
        System.out.print("Результат:       ");
        printArray(arrChange01(array1));
        System.out.println();

        //Задача 2
        int sizeArray2 = 8; // количество элементов массива
        int firstItemArray2 = 1;  // первый элемент массива
        int stepItemArray2 = 3;    // шаг арифметической прогрессии
        int[] array2 = arProg(sizeArray2, firstItemArray2, stepItemArray2);
        System.out.println("Задача 2 (заполнить массив значениями 1 4 7 10 13 16 19 22):");
        System.out.print("Результат:       ");
        printArray(array2);
        System.out.println();

        //Задача 3
        int [] array3 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        int limit3 = 6; // число с которым сравниваем элементы массива
        int coeff3 = 2; // число на которое умножаем
        System.out.println("Задача 3 (Элементы массива, меньшие " + limit3 + ", умножить на " + coeff3 +"):");
        System.out.print("Исходный массив: ");
        printArray(array3);
        System.out.print("Результат:       ");
        printArray(arrUpLimitToCoeff(array3, limit3, coeff3));
        System.out.println();

        //Задача 4
        float [] array4 = {100, 5.2f, 36, 20, 11, 42, 5.01f, 21, 41, 8, 9, 1000.0001f};
        System.out.println("Задача 4 (Найти минимальный и максимальный элементы массива):");
        System.out.print("Исходный массив: ");
        for (int i = 0; i < array4.length; i++) {
            System.out.print(array4[i] + "  ");
        }
        System.out.println();
        System.out.println("Минимальный элемент массива: " +  minArrItem(array4));
        System.out.println("Максимальный элемент массива: " + maxArrItem(array4));
        System.out.println();

        //Задача 5
        int sizeArray5 = 7;
        System.out.println("Задача 5 (Заполнить диагональные элементы квадратного целочисленного массива единицами):");
        print2Arr(sqArrayDiagonals(sizeArray5));
        System.out.println();

        //Задачи 6,7
        int [] array6 = { 1, 2, 3, 4, 5, 6 };
        int shift = -2;   //количество позиций на которое надо сместить элементы массива
        System.out.println("Задачи 6,7 (Сместить элементы массива на " + shift + " позиций" + "):");
        System.out.print("Исходный массив: ");
        printArray(array6);
        System.out.print("Результат:       ");
        printArray(shiftArray(array6, shift));



    }

    // Задание 1. В массиве состоящем из 0 и 1 метод заменяет  0 на 1 и 1 на 0.
    public static int[] arrChange01(int[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = (inputArray[i]==0) ? 1 : 0;
        }
        return inputArray;
    }

    // Задание 2. Метод заполняет пустой массив из sizeArray элементов членами арифметической прогрессии, где первый член firstItem=1, шаг step=3
    public static int[] arProg(int sizeArray, int firstItem, int step) {
        int[] array = new int[sizeArray];
        array[0] = firstItem;
        for (int i = 1; i < sizeArray; i++) {
            array[i] = array[i-1] + step;
        }
        return array;
    }

    // Задание 3. Метод умножает меньшие шести числа в целочисленном массиве  на два.
    public static int[] arrUpLimitToCoeff(int[] inputArray, int limit, int koeff) {
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = (inputArray[i] < limit) ? (inputArray[i]*koeff) : inputArray[i];
        }
        return inputArray;
    }

    // Задание 4. Поиск минимального и максимального элемента массива.
    public static float minArrItem(float[] inputArray) {  // поиск минимального элемента массива
        float min = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i]<min) {
                min = inputArray[i];
            }
        }
        return min;
    }

    public static float maxArrItem(float[] inputArray) {  // поиск максимального элемента массива
        float max = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i]>max) {
                max = inputArray[i];
            }
        }
        return max;
    }

    // Задание 5. Заполнение диагональных элементов квадратного целочисленного массива единицами
    public static int[][] sqArrayDiagonals (int sizeArray) {
        int [][] sqArray = new int[sizeArray][sizeArray];
        for (int i=0; i<sizeArray; i++) {
            for (int j=0; j<sizeArray; j++) {
              sqArray[i][j] = ( (i==j) || (i+j)==sizeArray-1 ) ? 1 : 0;
            }
        }
        return sqArray;
    }

    // Задание 6,7. Метод циклически смещает элементы массива на shift позиций (влево или вправо в зависисмости от знака shift)
    // Без использования вспомогательного массива


    public static int[] shiftArray(int[] array, int shift){
        shift=(shift>array.length) ? (shift%array.length) : (shift);
            if (shift>0) {
                for (int n = 0; n < shift; n++) {
                    int temp = array[array.length - 1];   // последний элемент массива записываю в переменную temp
                    for (int i = array.length - 1; i > 0; i--) {    // сдвиг элементов массива на одну позицию вправо
                        array[i] = array[i - 1];
                    }
                    array[0] = temp;
                }

            }
            else {
                for (int n=0; n < (-1)*shift; n++ ) {
                    int temp = array[0];
                    for (int i = 0; i < array.length - 1; i++) {   // сдвиг элементов массива на одну позицию влево
                        array[i] = array[i + 1];
                    }
                    array[array.length-1] = temp;
                }

            }
        return (array);
    }

    public static void printArray (int[] inputArray){  // вывод одномерного массива в консоль
            for (int i = 0; i < inputArray.length; i++) {
                System.out.print(inputArray[i] + "\t ");
            }
            System.out.println();
    }

    public static void print2Arr(int[][] inputArray) { // вывод 2-мерного массива в консоль
        for (int i=0; i<inputArray.length; i++){
            for (int j=0; j<inputArray[i].length; j++) {
                System.out.print(inputArray[i][j]+"\t");
            }
            System.out.println();
        }
    }

}
