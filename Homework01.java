package MainCourse_1Level.Lesson1.Homework01;

    // Домашняя работа к уроку 1 Самусевой Татьяны
    /*
1) Написать метод вычисляющий выражение a * (b + (c / d)) и
возвращающий результат с плавающей точкой, где a, b, c, d – целочисленные входные параметры этого метода;

2) Написать метод, принимающий на вход два целых числа,
и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
если да – вернуть true, в противном случае – false;

3) Написать метод, которому в качестве параметра передается целое число,
метод должен проверить положительное ли число передали, или отрицательное.
Замечание: ноль считаем положительным числом. Результат работы метода вывести в консоль

4) Написать метод, которому в качестве параметра передается строка, обозначающая имя,
метод должен вернуть приветственное сообщение «Привет, переданное_имя!»; Вывести приветствие в консоль.

5)** Написать метод, который определяет является ли год високосным.
Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
Для проверки работы вывести результаты работы метода в консоль

*/

public class Homework01 {
    public static void main(String[] args) {
      calcExpression(100, -3, 15, -4);
      sumInRange(30, -10);
      signValue(10000000000000054L);
      helloName("Александр");
      leapYear(1990);
    }

    // Задание 1
    public static float calcExpression (int a, int b, int c, int d) {
        float result = 0f;
        if (d==0) {
          System.out.println("Деление на ноль! Значение переменной d не может равняться 0");
                 }
        else{
            result = (float) a * ((float) b + ((float) c / (float) d));
            System.out.println("a * (b + (c / d)) = " + result);
        }
       return result;
    }

    // Задание 2
    public static boolean sumInRange (int a, int b){
        boolean result;
        if ((a+b)>=10 && (a+b)<=20) {
            result = true;
        }
        else {
            result = false;
        }
        System.out.println(result);
        return result;
    }

    //Задание 3
    public static void signValue (long number){
        if (number>=0) {
            System.out.println(number +" - Положительное число");
        }
        else{
            System.out.println(number +" - Отрицательное число");
        }
    }

    //Задание 4
    public static void helloName (String name){
        System.out.println("Привет, " + name + "!");
    }

    //Задание 5
    public static void leapYear (int year){
        if (year%400 ==0) {
            System.out.println(year + " - високосный год");
        }
        else {
            if ((year%4 == 0) && (!(year%100 == 0))) {
                System.out.println(year + " - високосный год");
            }
            else{
                System.out.println(year + " - НЕвисокосный год");
            }
        }
    }
}
