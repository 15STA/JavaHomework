package MainCourse_1Level.Lesson7.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MyGameMap extends JPanel {

    public static final int PVE_MODE = 0;  //человек против компьютера
    public static final int PVP_MODE = 1;  //человек против человека
    public Random random = new Random(); //[0;1)

    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int HUMAN_2_DOT = 3;
    private final int EMPTY_DOT = 0;

    private int count = 0;

    private final int PADDING_DOT = 5;

    private int gameMode;
    private int numberOfCellsX;  //количество ячеек по Х
    private int numberOfCellsY;  //количество ячеек по Y
    private int winLength;
    private int map[][];

    private int cellWidth;
    private int cellHeight;

    private boolean isGameOver;
    private boolean isInitMap;

    private int stateGameOver;
    private final int STATE_DRAW = 0;
    private final int STATE_HUMAN_WIN = 1;
    private final int STATE_AI_WIN = 2;
    private final int STATE_HUMAN_1_WIN = 3;
    private final int STATE_HUMAN_2_WIN = 4;


    private final String MSG_HUMAN_WIN = "Human WIN!";
    private final String MSG_PLAYER1_WIN = "Player 1 WIN";
    private final String MSG_PLAYER2_WIN = "Player 2 WIN";
    private final String MSG_AI_WIN = "AI WIN!";
    private final String MSG_DRAW = "DRAW!";


    MyGameMap() {
        setBackground(Color.BLACK);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (gameMode == PVE_MODE) {
                    gameUpdatePvE(e);
                }
                else
                gameUpdatePvP(e);
            }
        });


        isInitMap = false;
    }

    void startGame(int gameMode, int NumberOfCellsX, int NumberOfCellsY, int winLength) {
        this.gameMode = gameMode;
        this.numberOfCellsX = NumberOfCellsX;
        this.numberOfCellsY = NumberOfCellsY;
        this.winLength = winLength;
        map = new int[NumberOfCellsY][NumberOfCellsX];
        isGameOver = false;
        isInitMap = true;
        count = 0;
        repaint();
    }

    private void setGameOver(int stateGameOver) {
        isGameOver = true;
        this.stateGameOver = stateGameOver;
        repaint();
    }

    private void gameUpdatePvE(MouseEvent mouse){
        if (!isInitMap) return;
        if (isGameOver) return;

        int cellX = mouse.getX() / cellWidth;
        int cellY = mouse.getY() / cellHeight;

        if (!validCell(cellY, cellX) || !emptyCell(cellY, cellX)) return;

        map[cellY][cellX] = HUMAN_DOT;
        repaint();

        if (winGame(HUMAN_DOT)) {
           setGameOver(STATE_HUMAN_WIN);
            return;
        }

        if (draw()) {
            setGameOver(STATE_DRAW);
            return;
        }

        turnAI();
        repaint();

        if (winGame(AI_DOT)) {
            setGameOver(STATE_AI_WIN);
            return;
        }

        if (draw()) {
            setGameOver(STATE_DRAW);
            return;
        }

    }

    private void gameUpdatePvP(MouseEvent mouse){
        if (!isInitMap) return;
        if (isGameOver) return;

        int cellX = mouse.getX() / cellWidth;
        int cellY = mouse.getY() / cellHeight;

        if (!validCell(cellY, cellX) || !emptyCell(cellY, cellX)) return;
        count++;

        if (count % 2 == 0) {
            map[cellY][cellX] = HUMAN_2_DOT;
            repaint();

            if (winGame(HUMAN_2_DOT)) {
                setGameOver(STATE_HUMAN_2_WIN);
                return;
            }
        }
        else {
            map[cellY][cellX] = HUMAN_DOT;
            repaint();

            if (winGame(HUMAN_DOT)) {
                setGameOver(STATE_HUMAN_1_WIN);
                return;
            }
        }
        if (draw()) {
            setGameOver(STATE_DRAW);
            return;
        }
    }

    private void drawGameField (Graphics gr){
        Graphics2D g = (Graphics2D)gr;

        // "кисть" для рисования крестиков
        BasicStroke pen1 = new BasicStroke(10); //толщина линии

        if (!isInitMap) return;
        int width = getWidth();
        int height = getHeight();
        cellWidth = width / numberOfCellsX;
        cellHeight = height / numberOfCellsY;

        gr.setColor(Color.white);

        drawLineMap(gr, width, height);

        g.setStroke(pen1);

        for (int y = 0; y < numberOfCellsY; y++) {
            for (int x = 0; x < numberOfCellsX; x++) {
                if (emptyCell(y,x)) continue;

                if (map[y][x] == HUMAN_DOT) {
                  gr.setColor(Color.GREEN);
                    g.drawLine(x*cellWidth+3*PADDING_DOT, y*cellHeight+3*PADDING_DOT,x*cellWidth+cellWidth-3*PADDING_DOT, y*cellHeight+cellHeight-3*PADDING_DOT);
                    g.drawLine(x*cellWidth+cellWidth-3*PADDING_DOT, y*cellHeight+3*PADDING_DOT,x*cellWidth+3*PADDING_DOT, y*cellHeight+cellHeight-3*PADDING_DOT);
                }
                else if ((map[y][x] == AI_DOT) || (map[y][x] == HUMAN_2_DOT)) {
                    gr.setColor(Color.RED);
                    gr.fillOval(x*cellWidth+PADDING_DOT, y*cellHeight+PADDING_DOT, cellWidth - 2*PADDING_DOT, cellHeight - 2*PADDING_DOT);
                }
                else {
                    showErrorMessage("Something wrong in drawGameField method: X = " + x + " Y = " + y);
                    return;
                }
            }
        }
        if (isGameOver) {
            showMessageGameOver(g);
        }
    }

    private void showMessageGameOver (Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, getHeight()/3, getWidth(), getWidth()/3);
        g.setColor(Color.ORANGE);
        Font fontLetter = new Font("Times new roman", Font.BOLD, 50);
        g.setFont(fontLetter);

        switch (stateGameOver) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 140, getHeight() / 2 + 15);
                break;
            case STATE_HUMAN_WIN:
                g.drawString(MSG_HUMAN_WIN, 90, getHeight() / 2 + 15);
                break;
            case STATE_AI_WIN:
                g.drawString(MSG_AI_WIN, 150, getHeight() / 2 + 15);
                break;
            case STATE_HUMAN_1_WIN:
                g.drawString(MSG_PLAYER1_WIN, 90, getHeight() / 2 + 15);
                break;
            case STATE_HUMAN_2_WIN:
                g.drawString(MSG_PLAYER2_WIN, 90, getHeight() / 2 + 15);
                break;
            default:
                showErrorMessage("Something wrong in showMessageGameOver method");
        }
    }

    private void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
        throw new RuntimeException(msg);
    }

    private void drawLineMap(Graphics g, int width, int height) {
        for (int i = 0; i < numberOfCellsY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }

        for (int i = 0; i < numberOfCellsX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGameField(g);
    }

    public void turnAI() {    // Ход компьютера
        boolean flag = false;
        int cellX;
        int cellY;
        for (int y = 0; y < numberOfCellsY; y++) {  // проверка игрового поля на предмет выигрыша AI на текущем шаге
            for (int x = 0; x < numberOfCellsX; x++) {
                if (map[y][x] == EMPTY_DOT) {
                    if (maxCountSymbol(AI_DOT, y, x) >= winLength-1) {
                        map[y][x] = AI_DOT;
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) break;
        }

        for (int y = 0; y < numberOfCellsY; y++) {  // проверка игрового поля на предмет выигрыша человека на следующем шаге
            for (int x = 0; x < numberOfCellsX; x++) {
                if (map[y][x] == EMPTY_DOT) {
                    if (maxCountSymbol(HUMAN_DOT, y, x) >= winLength-1) {
                        map[y][x] = AI_DOT;
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) break;
        }
        if (!flag) {
            for (int y = 0; y < numberOfCellsY; y++) {  // проверка игрового поля на предмет куда пойти AI
                for (int x = 0; x < numberOfCellsX; x++) {
                    if (map[y][x] == EMPTY_DOT) {  //
                        if (maxCountSymbol(AI_DOT, y, x) >= 1) {  // если рядом с текущей пустой ячейкой есть "0", то ходит в эту ячейку
                            map[y][x] = AI_DOT;
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
                cellX = random.nextInt(numberOfCellsX); //[0;3)
                cellY = random.nextInt(numberOfCellsY);
            } while (!emptyCell(cellY, cellX));
            map[cellY][cellX] = AI_DOT;
        }
    }

    public boolean winGame(int player) { // проверка выигрыша
        for (int y = 0; y < numberOfCellsY; y++) {
            for (int x = 0; x < numberOfCellsX; x++) {
                if (map[y][x] == player) {
                    if (maxCountSymbol(player, y, x) >=winLength-1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int countSymbol (int player, int y, int x, int shiftLine, int shiftRow) { // считает количество одинаковых символов по одному направлению для клетки с координатами y,x
        int count = 0;
        if (validCell(y+shiftLine, x+shiftRow)) {
            if (map[y + shiftLine][x + shiftRow] == player) {
                count=1+countSymbol(player,y+shiftLine, x+shiftRow, shiftLine, shiftRow);
            }
        }
        return count;
    }

    public int maxCountSymbol (int player, int y, int x){ // считает максимальное количество символов по всем направлениям для клетки с координатами y,x
        int playerSymbolLine = countSymbol(player, y, x, 0, 1) + countSymbol(player, y, x, 0, -1);
        int playerSymbolColumn = countSymbol(player, y, x, 1, 0) + countSymbol(player, y, x, -1, 0);
        int playerSymbolDiagonalLeftToRight = countSymbol(player, y, x, 1, 1) + countSymbol(player, y, x, -1, -1);
        int playerSymbolDiagonalRightToLeft = countSymbol(player, y, x, 1, -1) + countSymbol(player, y, x, -1, 1);
        return Math.max(Math.max(playerSymbolLine, playerSymbolColumn),Math.max(playerSymbolDiagonalLeftToRight, playerSymbolDiagonalRightToLeft));
    }


    public boolean draw() {  // проверка на заполненность поля
        for (int y = 0; y < numberOfCellsY; y++) {
            for (int x = 0; x < numberOfCellsX; x++) {
                if (map[y][x] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    public boolean validCell(int cellY, int cellX) {  // проверка на нахождение ячейки в пределах поля
        return cellX >= 0 && cellX < numberOfCellsX && cellY >= 0 && cellY < numberOfCellsY;
    }

    public boolean emptyCell(int cellY, int cellX) {
        return map[cellY][cellX] == EMPTY_DOT;
    }


}
