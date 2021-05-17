package MainCourse_1Level.Lesson7.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMainWindow extends JFrame {
    private final int WIN_WIDTH = 500;
    private final int WIN_HEIGHT = 560;
    private final int WIN_POS_X = 650;
    private final int WIN_POS_Y = 300;

    private Settings settings;
    private MyGameMap gameMap;


    MyMainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocation(WIN_POS_X, WIN_POS_Y);
        setTitle("Game!!! Welcome player");
        setResizable(false);

        settings = new Settings(this);
        gameMap = new MyGameMap();

        JButton btnStart = new JButton("Start game");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            settings.setVisible(true);
            }
        });

        JButton btnExit = new JButton("Exit game");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panelForButtons = new JPanel();
        panelForButtons.setLayout(new GridLayout(1, 2));
        panelForButtons.add(btnStart);
        panelForButtons.add(btnExit);

        add(panelForButtons, BorderLayout.SOUTH);
        add(gameMap);

        setVisible(true);
    }

    void getUserSettingAndStartGame(int gameMode, int gameMapSizeX, int gameMapSizeY, int winLength) {
        gameMap.startGame(gameMode, gameMapSizeX, gameMapSizeY, winLength);
    }
}
