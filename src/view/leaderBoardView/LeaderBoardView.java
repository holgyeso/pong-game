package view.leaderBoardView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LeaderBoardView extends JFrame {
    private Font font;
    private Color colorYellow = new Color(251, 209, 4);


    public LeaderBoardView(List<HashMap<String, Integer>> players) {

        setLayout(new GridLayout(players.size() + 1, 1));
        getContentPane().setBackground(Color.black);

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../fonts/bierstadtNormal.ttf")).deriveFont(18f);
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        JLabel title = new JLabel("<html><center>" + "Below you can see the top 10 players of all times:" + "</center></html>");
        title.setFont(font);
        title.setForeground(colorYellow);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(new EmptyBorder(20, 0, 40, 0));
        add(title);

        players.stream().forEach(player -> {

            JPanel playerPanel = new JPanel();
            playerPanel.setBackground(Color.black);

            String playerName = player.keySet().stream().findFirst().get();
            JLabel playerLabel = new JLabel(playerName);
            playerLabel.setFont(font);
            playerLabel.setForeground(Color.white);
            playerLabel.setBorder(new EmptyBorder(0, 0, 0, 50));
            playerPanel.add(playerLabel);

            JLabel scoreLabel = new JLabel(String.valueOf(player.get(playerName)));
            scoreLabel.setFont(font);
            scoreLabel.setForeground(Color.white);
            playerPanel.add(scoreLabel);

            add(playerPanel);
        });

        setTitle("Leaderboard");
        setSize(500, 500);
        setLocationRelativeTo(null); //képernyő közepére helyezi a framet
        setResizable(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
    }

}
