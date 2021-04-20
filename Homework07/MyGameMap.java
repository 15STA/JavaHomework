package MainCourse_1Level.Lesson7.homework;

import javax.swing.*;
import java.awt.*;

public class MyGameMap extends JPanel {

    public static final int PVE_MODE = 0;
    public static final int PVP_MODE = 1;

    private int gameMode;
    private int NumberOfCellsX;
    private int NumberOfCellsY;
    private int winLength;
    private int map[][];

    private int cellWidth;
    private int cellHeight;


    MyGameMap() {
        setBackground(Color.BLACK);
    }

    void startGame(int gameMode, int NumberOfCellsX, int NumberOfCellsY, int winLength) {
        this.gameMode = gameMode;
        this.NumberOfCellsX = NumberOfCellsX;
        this.NumberOfCellsY = NumberOfCellsY;
        this.winLength = winLength;
        map = new int[NumberOfCellsX][NumberOfCellsY];
        repaint();
    }

    private void DrawGameField(Graphics g){
        int width = getWidth();
        int height = getHeight();
        cellWidth = width / NumberOfCellsX;
        cellHeight = height / NumberOfCellsY;

        g.setColor(Color.white);

        for (int i = 0; i < NumberOfCellsY; i++) {
           int y = i * cellHeight;
           g.drawLine(0, y, width, y);
        }

        for (int i = 0; i < NumberOfCellsX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        DrawGameField(g);
    }



}
