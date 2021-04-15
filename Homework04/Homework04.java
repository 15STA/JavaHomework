package MainCourse_1Level.Lesson4.Homework04;


//1 Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;
//2 Конструктор класса должен заполнять эти поля при создании объекта;
//3 Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
//4 Вывести при помощи методов из пункта 3 ФИО и должность.
//5 Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
//6* Создать метод, повышающий зарплату всем сотрудникам старше 35 лет на 10000;
//7** При создании экземпляра класса Сотрудник присваивать ему уникальный порядковый номер.

public class Homework04 {
    public static void main(String[] args) {
        Worker worker1 = new Worker("Иванов И.И.", "программист", "+79998887766", 100000, 30);
        System.out.println(worker1.getFullName() + " - " + worker1.getAge() + " лет");

        Worker[] workerS = {new Worker("Тимофеев П.С", "менеджер", "+79991234567", 80000, 28),
                new Worker("Петров Н.В.", "начальник отдела разработки ПО", "+79991111111", 200000, 42),
                new Worker("Сидоров А.А.", "программист", "+79992222222", 150000, 40),
                new Worker("Егоров В.С.", "бухгалтер", "+79999999999", 100000, 45),
                new Worker("Синицин К.Е", "водитель", "+79991002030", 50000, 25)
        };

        System.out.println("--------------------");
        System.out.println("Все сотрудники:");
        for (int i=0; i < workerS.length; i++) {
               System.out.println(workerS[i].getFullInfo());
        }

        System.out.println("--------------------");
        System.out.println("Сотрудники старше 40 лет:");
        for (int i=0; i < workerS.length; i++) {
            if (workerS[i].getAge() > 40) {
                System.out.println(workerS[i].getFullInfo());
            }
        }

        System.out.println("--------------------");
        int ageForChangeSalary = 35;
        int deltaSalary = 10000;
        System.out.println("Сотрудникам старше " + ageForChangeSalary + " лет увеличили зарплату на " + deltaSalary + ":");
        increaseSalary(workerS, 35, 10000);
        for (int i=0; i < workerS.length; i++) {
            if (workerS[i].getAge() > ageForChangeSalary) {
                System.out.println(workerS[i].getFullInfo());
            }
        }


    }

        private static void increaseSalary (Worker[] people, int age, int deltaSalary) {
        for (int i=0; i < people.length; i++) {
            if (people[i].getAge() > age) {
                people[i].setSalary(people[i].getSalary() + deltaSalary);
            }
        }
    }
}
