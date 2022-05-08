package view.settingsView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class SettingsView extends JFrame {

    private static final int WIDTH = 600;

    private HashMap<String, Font> fonts;
    private static final int TITLE_FONT_SIZE = 25;
    private static final int LABEL_FONT_SIZE = 20;
    private static final int BUTTON_FONT_SIZE = 25;

    private Color colorYellow = new Color(251, 209, 4);

    public SettingsView(String frameTitle, String titleToShow, int height) {
        fonts = new HashMap<>();

        getContentPane().setBackground(Color.black);

        setLayout(new BorderLayout());

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font tempFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../fonts/bierstadtNormal.ttf")).deriveFont((float) LABEL_FONT_SIZE);
            ge.registerFont(tempFont);
            fonts.put("LABEL_FONT_SIZE", tempFont);

            tempFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../fonts/bierstadtNormal.ttf")).deriveFont((float) TITLE_FONT_SIZE);
            ge.registerFont(tempFont);
            fonts.put("TITLE_FONT_SIZE", tempFont);

            tempFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../fonts/bierstadtNormal.ttf")).deriveFont((float) BUTTON_FONT_SIZE);
            ge.registerFont(tempFont);
            fonts.put("BUTTON_FONT_SIZE", tempFont);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        JLabel title = new JLabel("<html><center>" + titleToShow + "</center></html>");
        title.setFont(fonts.get("TITLE_FONT_SIZE"));
        title.setForeground(colorYellow);
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        setTitle(frameTitle);
        setSize(WIDTH, height);
        setLocationRelativeTo(null); //képernyő közepére helyezi a framet
        setResizable(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    public HashMap<String, Font> getFontHashMap() {
        return fonts;
    }

}
