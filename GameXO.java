package MainCourse_1Level.Lesson3.Homework;

import java.util.Random;
import java.util.Scanner;

/**
 * Полностью разобраться с кодом, попробовать переписать с нуля;
 * ^ Усовершенствовать метод проверки победы (через циклы);
 * ^ Расширить поле до 5х5 и в качестве условий победы установить серию равной 4.
 * ^^ Проработать базовый искусственный интеллект, чтобы он мог блокировать ходы игрока.
 * + Доработала логику игры компьютера
 */


public class GameXO {

    public static char[][] field;
    public static int fieldSizeX;
    public static int fieldSizeY;

    public static char human = 'X';
    public static char ai = 'O';
    public static char empty = '_';

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random(); //[0;1)
    public static int sizeWin = 4;


    public static void createField() {   // создание игрового поля
        fieldSizeY = 5;
        fieldSizeX = 5;
        field = new char[fieldSizeY][fieldSizeX];

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = empty;
            }
        }
    }

    public static void viewField() {    // отображение игрового поля
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    public static void turnHuman() {  // ход человека
        int cellX;
        int cellY;

        do {
            System.out.println("Введите координаты ячейки (строка, столбец)");

            cellY = scanner.nextInt() - 1;  //строка
            cellX = scanner.nextInt() - 1;  //столбец
        } while (!validCell(cellY, cellX) || !emptyCell(cellY, cellX));
       field[cellY][cellX] = human;
    }

    public static void turnAI() {    // Ход компьютера
        boolean flag = false;
        int cellX;
        int cellY;
        for (int y = 0; y < fieldSizeY; y++) {  // проверка игрового поля на предмет выигрыша "0" на текущем шаге
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == empty) {
                    if (maxCountSymbol(ai, y, x) >= sizeWin-1) {
                        field[y][x] = ai;
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) break;
        }

        for (int y = 0; y < fieldSizeY; y++) {  // проверка игрового поля на предмет выигрыша "Х" на следующем шаге
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == empty) {
                        if (maxCountSymbol(human, y, x) >= sizeWin-1) {
                        field[y][x] = ai;
                        flag = true;
                        break;
                        }
                }
            }
            if (flag) break;
        }
        if (!flag) {
            for (int y = 0; y < fieldSizeY; y++) {  // проверка игрового поля на предмет куда пойти "0"
                    for (int x = 0; x < fieldSizeX; x++) {
                        if (field[y][x] == empty) {  //
                                if (maxCountSymbol(ai, y, x) >= 1) {  // если рядом с текущей пустой ячейкой есть "0", то ходит в эту ячейку
                                field[y][x] = ai;
                                flag = true;
                                break;
                                }
                        }
                    }
                    if (flag) break;
            }
        }

        if (!flag)  {
        do {
            cellX = random.nextInt(fieldSizeX); //[0;3)
            cellY = random.nextInt(fieldSizeY);
        } while (!emptyCell(cellY, cellX));
                  field[cellY][cellX] = ai;
        }
    }


   public static boolean winGame(char player) { // проверка выигрыша
       for (int y = 0; y < fieldSizeY; y++) {
           for (int x = 0; x < fieldSizeX; x++) {
               if (field[y][x] == player) {
                   if (maxCountSymbol(player, y, x) >=sizeWin-1){
                       return true;
                   }
               }
           }
       }
       return false;
   }

    public static int countSymbol (char player, int y, int x, int shiftLine, int shiftRow) { // считает количество одинаковых символов по одному направлению для клетки с координатами y,x
        int count = 0;
        if (validCell(y+shiftLine, x+shiftRow)) {
            if (field[y + shiftLine][x + shiftRow] == player) {
                count=1+countSymbol(player,y+shiftLine, x+shiftRow, shiftLine, shiftRow);
            }
        }
        return count;
    }

    public static int maxCountSymbol (char player, int y, int x){ // считает максимальное количество символов по всем направлениям для клетки с координатами y,x
        int playerSymbolLine = countSymbol(player, y, x, 0, 1) + countSymbol(player, y, x, 0, -1);
        int playerSymbolColumn = countSymbol(player, y, x, 1, 0) + countSymbol(player, y, x, -1, 0);
        int playerSymbolDiagonalLeftToRight = countSymbol(player, y, x, 1, 1) + countSymbol(player, y, x, -1, -1);
        int playerSymbolDiagonalRightToLeft = countSymbol(player, y, x, 1, -1) + countSymbol(player, y, x, -1, 1);
        return Math.max(Math.max(playerSymbolLine, playerSymbolColumn),Math.max(playerSymbolDiagonalLeftToRight, playerSymbolDiagonalRightToLeft));
    }


    public static boolean draw() {  // проверка на заполненность поля
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == empty) return false;
            }
        }
        return true;
    }

    public static boolean validCell(int cellY, int cellX) {  // проверка на нахождение ячейки в пределах поля
        return cellX >= 0 && cellX < fieldSizeX && cellY >= 0 && cellY < fieldSizeY;
    }

    public static boolean emptyCell(int cellY, int cellX) {
        return field[cellY][cellX] == empty;
    }

    public static void main(String[] args) {


        createField();
        viewField();

        while(true) {

            turnHuman();
            viewField();

            if (winGame(human)) {
                System.out.println("Вы выиграли!!!");
                break;
            }

            if (draw()) {
                System.out.println("Ничья!!!");
                break;
            }

            turnAI();
            viewField();

            if (winGame(ai)) {
                System.out.println("Компьютер выиграл!!!");
                break;
            }

            if (draw()) {
                System.out.println("Ничья!!!");
                break;
            }
        }
    }
}


