package view.playView;

import controller.settingsController.SettingsController;

import javax.swing.*;

public class PlayFrame extends JFrame {

    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;

    private JMenuBar menuBar;

    public PlayFrame() {
        JMenu settings = new JMenu("Settings");

        JMenuItem setBackgroundItem = new JMenuItem("Change background image");
        JMenuItem setPaddleItem = new JMenuItem("Set paddle color");
        JMenuItem ballNumberItem = new JMenuItem("Set ball number");
        JMenuItem playersNameItem = new JMenuItem("Set players' name");
        JMenuItem winnerScoreItem = new JMenuItem("Set winner's score");

        setBackgroundItem.addActionListener(SettingsController.getInstance());
        setPaddleItem.addActionListener(SettingsController.getInstance());
        ballNumberItem.addActionListener(SettingsController.getInstance());
        playersNameItem.addActionListener(SettingsController.getInstance());
        winnerScoreItem.addActionListener(SettingsController.getInstance());

        settings.add(setBackgroundItem);
        settings.add(setPaddleItem);
        settings.add(ballNumberItem);
        settings.add(playersNameItem);
        settings.add(winnerScoreItem);


        JMenu leaderboard = new JMenu("Leaderboard");

        JMenuItem leaderboardItem = new JMenuItem("By points");
        JMenuItem seeStaticsItem = new JMenuItem("By games won");
        leaderboardItem.addActionListener(SettingsController.getInstance());
        seeStaticsItem.addActionListener(SettingsController.getInstance());

        leaderboard.add(leaderboardItem);
        leaderboard.add(seeStaticsItem);

        menuBar = new JMenuBar();
        menuBar.add(settings);
        menuBar.add(leaderboard);

        setJMenuBar(menuBar);

        setTitle("Pong game");
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //képernyő közepére helyezi a framet

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public int frameInsetTop() {
        return this.getInsets().top;
    }

    public int frameInsetBottom() {
        return this.getInsets().bottom;
    }

    public int frameInsetLeft() {
        return this.getInsets().left;
    }

    public int frameInsetRight() {
        return this.getInsets().right;
    }

    public int getHeightWithoutInsets() {
        return this.getHeight() - this.frameInsetBottom() - this.frameInsetTop() - this.menuBar.getHeight();
    }

    public int getWidthWithoutInsets() {
        return this.getWidth() - this.frameInsetLeft() - this.frameInsetRight();
    }

}
