package view.playView;

import controller.introController.IntroController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class IntroView extends JPanel {

    private HashMap<String, Font> fonts;
    private static final int TITLE_FONT_SIZE = 40;
    private static final int DESC_FONT_SIZE = 18;
    private static final int BUTTON_FONT_SIZE = 25;

    private Color colorYellow = new Color(251, 209, 4);

    private final IntroController introController;

    public IntroView(IntroController introController) {
        this.introController = introController;

        setBackground(Color.black);
        setLayout(new BorderLayout());
        setAlignmentY(Component.CENTER_ALIGNMENT);
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

        JLabel welcome = new JLabel("Welcome to the Pong Game!");
        welcome.setFont(fonts.get("TITLE_FONT_SIZE"));
        welcome.setForeground(colorYellow);
        welcome.setSize(getWidth(), TITLE_FONT_SIZE);
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setBorder(new EmptyBorder(60, 0, 0, 0));
        add(welcome, BorderLayout.NORTH);

        JLabel description = new JLabel();
        description.setText("<html><center>The player on the left can move up and down with keys W and S, the one on the right can move with keys O and K. By default you start with two balls and the game will end when one of you achieves 5 points, but these can be modified in the settings.<center></html>");
        description.setFont(fonts.get("DESC_FONT_SIZE"));
        description.setForeground(Color.white);
        description.setBorder(new EmptyBorder(0, 30, 0, 30));
        add(description, BorderLayout.CENTER);

        JButton start = new JButton();
        start.setText("START GAME");
        start.setFont(fonts.get("BUTTON_FONT_SIZE"));
        start.setBackground(colorYellow);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        FontMetrics metrics = getFontMetrics(fonts.get("BUTTON_FONT_SIZE"));
        start.setMargin(new Insets(20, 10, 20, 10));
        start.setBorderPainted(false);
        start.setSize(new Dimension(metrics.stringWidth("START GAME >> "), BUTTON_FONT_SIZE));
        start.addActionListener(introController);
        start.setFocusable(false); //ennek hiányában a fókusz a gombon marad és miután elindul a play mode, ő fogja a KeyEventeket is fogadni
        add(start, BorderLayout.SOUTH);
    }

}
