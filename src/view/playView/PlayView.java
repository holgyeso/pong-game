package view.playView;

import controller.MainController;
import controller.settingsController.SettingsController;
import model.Ball;
import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayView extends JPanel {

    private List<Player> players;
    private List<Ball> balls;
    private Font font;
    private int fontSize = 40;
    private Font nameFont;
    private int nameFontSize = 20;
    private BufferedImage backgImg;

    public PlayView() {
        backgImg = null;
        setBackground(Color.black);
        if (SettingsController.getBackgroundImageFile() == null) {
            setBackground(Color.black);
        } else {
            try {
                backgImg = ImageIO.read(SettingsController.getBackgroundImageFile());
            } catch (IOException e) {
                System.err.println("! ERROR - file could not be selected: " + e);
                setBackground(Color.black);
            }
            repaint();
        }

        players = new ArrayList<>();
        balls = new ArrayList<>();

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../fonts/bierstadtNormal.ttf")).deriveFont((float) fontSize);
            ge.registerFont(font);

            nameFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("../fonts/bierstadtNormal.ttf")).deriveFont((float) nameFontSize);
            ge.registerFont(font);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
        repaint();
    }

    public void addBall(Ball ball) {
        balls.add(ball);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgImg != null) {
            g.drawImage(backgImg, 0, 0, null);
        }


        players.stream().forEach(player -> {
            g.setColor(SettingsController.getPaddleColor());
            g.fillRect(player.getxCoord(), player.getyCoord(), player.getWidth(), player.getHeight());
        });
        balls.stream().forEach(ball -> {
            Graphics2D g2d = (Graphics2D) g;
            Shape circle = new Ellipse2D.Double(ball.getxCoord(), ball.getyCoord(), ball.getSize(), ball.getSize());
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON); //simítás, hogy ne legyen sokszög a körből :)
            g2d.setColor(SettingsController.getPaddleColor());
            g2d.fill(circle);
        });

        g.setFont(font);
        String score = players.get(0).getScore() + " : " + players.get(1).getScore();
        FontMetrics metrics = g.getFontMetrics(); //a StringWidth metódusa meg fogja adni, hogy az adott font milyen széles

        g.setColor(SettingsController.getPaddleColor());

        g.drawString(score, (getWidth()) / 2 - getInsets().right - getInsets().left - metrics.stringWidth(score) / 2, fontSize);


        g.setFont(nameFont);
        g.drawString(MainController.getLeftPlayer().getName(), 5, nameFontSize);

        g.drawString(MainController.getRightPlayer().getName(), getWidth() - getInsets().left - getInsets().right - 5 - metrics.stringWidth(MainController.getRightPlayer().getName()) / 2, nameFontSize);
    }
}
