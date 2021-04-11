package MainCourse_1Level.Lesson4.Homework04;

//1 Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;
//2 Конструктор класса должен заполнять эти поля при создании объекта;
//3 Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
//4 Вывести при помощи методов из пункта 3 ФИО и должность.
//5 Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
//6* Создать метод, повышающий зарплату всем сотрудникам старше 35 лет на 10000;
//7** При создании экземпляра класса Сотрудник присваивать ему уникальный порядковый номер.

public class Worker {
    private int id;
    private String fullName;
    private String position;
    private String phone;
    private int salary;
    private int age;
    private static int number = 0;

    public Worker(String fullName, String position, String phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
        this.id = number++;

    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPosition() {
        return this.position;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getFullInfo() {
        return this.id + " " + this.fullName + ", " + this.age + " лет, " + this.position + ", телефон " + this.phone + ", зарплата " + this.salary + " руб.";
    }

    public int getAge() {
        return this.age;
    }
}
