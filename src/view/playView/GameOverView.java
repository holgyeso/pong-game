package view.playView;

import controller.gameOverController.GameOverController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class GameOverView extends JPanel {

    private HashMap<String, Font> fonts;
    private static final int TITLE_FONT_SIZE = 40;
    private static final int DESC_FONT_SIZE = 18;
    private static final int BUTTON_FONT_SIZE = 25;

    private Color colorYellow = new Color(251, 209, 4);
    private GameOverController gameOverController;

    public GameOverView(String winnerPlayerName, GameOverController gameOverController) {
        this.gameOverController = gameOverController;

        setBackground(Color.black);
        setLayout(new BorderLayout());
        fonts = new HashMap<>();

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font tempFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../fonts/bierstadtNormal.ttf")).deriveFont((float) DESC_FONT_SIZE);
            ge.registerFont(tempFont);
            fonts.put("DESC_FONT_SIZE", tempFont);

            tempFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../fonts/bierstadtNormal.ttf")).deriveFont((float) TITLE_FONT_SIZE);
            ge.registerFont(tempFont);
            fonts.put("TITLE_FONT_SIZE", tempFont);

            tempFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../fonts/bierstadtNormal.ttf")).deriveFont((float) BUTTON_FONT_SIZE);
            ge.registerFont(tempFont);
            fonts.put("BUTTON_FONT_SIZE", tempFont);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        JLabel winner = new JLabel("Congrats " + winnerPlayerName + ", you won!");
        winner.setFont(fonts.get("TITLE_FONT_SIZE"));
        winner.setForeground(colorYellow);
        winner.setSize(getWidth(), TITLE_FONT_SIZE);
        winner.setHorizontalAlignment(JLabel.CENTER);
        winner.setBorder(new EmptyBorder(60, 0, 0, 0));
        add(winner, BorderLayout.NORTH);

        JLabel description = new JLabel("Check out the Leaderboard to see if you are among the top players!");
        description.setFont(fonts.get("DESC_FONT_SIZE"));
        description.setForeground(Color.white);
        description.setSize(getWidth(), DESC_FONT_SIZE);
        description.setHorizontalAlignment(JLabel.CENTER);
        description.setBorder(new EmptyBorder(0, 30, 0, 30));
        add(description, BorderLayout.CENTER);

        JButton replay = new JButton();
        replay.setText("REPLAY");
        replay.setFont(fonts.get("BUTTON_FONT_SIZE"));
        replay.setBackground(colorYellow);
        replay.setAlignmentX(Component.CENTER_ALIGNMENT);
        replay.setMargin(new Insets(20, 10, 20, 10));
        replay.setBorderPainted(false);
        replay.addActionListener(gameOverController);
        replay.setFocusable(false); //ennek hiányában a fókusz a gombon marad és miután elindul a play mode, ő fogja a KeyEventeket is fogadni
        add(replay, BorderLayout.SOUTH);
    }

}
