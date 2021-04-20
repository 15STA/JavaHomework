package MainCourse_1Level.Lesson7.homework;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.04.2021
 */

public class Settings extends JFrame {

    private final int WIN_WIDTH = 350;
    private final int WIN_HEIGHT = 250;

    private MyMainWindow mainWindow;

    private JRadioButton pveRb;
    private JRadioButton pvpRb;

    private JLabel currentMapSizeLabel;
    private JSlider sliderMapSize;
    private final int MIN_MAP_SIZE = 3;
    private final int MAX_MAP_SIZE = 10;

    private JLabel currentWinLengthLabel;
    private JSlider sliderWinLength;
    private final int MIN_WIN_LEN = 3;

    private JButton btnSaveUserSettings;

    private final String WIN_LEN_LB_PREFIX = "Current win length is ";
    private final String MAP_SIZE_LB_PREFIX = "Current map size is ";

    Settings(MyMainWindow mainWindow) {
        this.mainWindow = mainWindow;

        Rectangle mainWindowBounds = mainWindow.getBounds();
        int coordinateX = (int) mainWindowBounds.getCenterX() - WIN_WIDTH / 2;
        int coordinateY = (int) mainWindowBounds.getCenterY() - WIN_HEIGHT / 2;
        setLocation(coordinateX, coordinateY);
        setResizable(false);
        setTitle("Settings game!");
        setSize(WIN_WIDTH, WIN_HEIGHT);

        setLayout(new GridLayout(11, 1));

        setupGameMode();
        setupMapSizeSettings();
        setupWinLength();
        collectAndSendUserSetting();
    }

    private void setupGameMode() {
        add(new JLabel("Setup game mode"));
        pveRb = new JRadioButton("PVE", true);
        pvpRb = new JRadioButton("PVP");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(pveRb);
        buttonGroup.add(pvpRb);
        add(pveRb);
        add(pvpRb);
    }

    private void setupMapSizeSettings() {
        add(new JLabel("Setup map size"));
        currentMapSizeLabel = new JLabel(MAP_SIZE_LB_PREFIX + MIN_MAP_SIZE + "x" + MIN_MAP_SIZE);
        sliderMapSize = new JSlider(MIN_MAP_SIZE, MAX_MAP_SIZE, MIN_MAP_SIZE);
        sliderMapSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = sliderMapSize.getValue();
                currentMapSizeLabel.setText(MAP_SIZE_LB_PREFIX + currentValue + "x" + currentValue);
                sliderWinLength.setMaximum(currentValue);
            }
        });

        add(currentMapSizeLabel);
        add(sliderMapSize);

    }

    private void setupWinLength() {
        add(new JLabel("Setup win length"));
        currentWinLengthLabel = new JLabel(WIN_LEN_LB_PREFIX + MIN_WIN_LEN);
        sliderWinLength = new JSlider(MIN_WIN_LEN, MIN_MAP_SIZE, MIN_MAP_SIZE);
        sliderWinLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentWinLengthLabel.setText(WIN_LEN_LB_PREFIX + sliderWinLength.getValue());
            }
        });

        add(currentWinLengthLabel);
        add(sliderWinLength);
    }

    private void collectAndSendUserSetting() {
        btnSaveUserSettings = new JButton("Save settings & Start Game");
        btnSaveUserSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collectUserSettings();

            }
        });

        add(btnSaveUserSettings);

    }

    private void collectUserSettings() {
        int gameMode;

        if (pveRb.isSelected()) {
            gameMode = MyGameMap.PVE_MODE;
        } else if (pvpRb.isSelected()) {
            gameMode = MyGameMap.PVP_MODE;
        } else {
            JOptionPane.showMessageDialog(this, "Something wrong in collectUserSettings method");
            throw new RuntimeException("Something wrong in collectUserSettings method");
        }

        int mapSize = sliderMapSize.getValue();
        int winLength = sliderWinLength.getValue();

        mainWindow.getUserSettingAndStartGame(gameMode, mapSize, mapSize,winLength);
        setVisible(false);
    }


}
