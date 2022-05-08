package view.settingsView;

import controller.settingsController.SettingsController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SetPlayersNameView extends SettingsView {

    private final JButton save;
    private final JTextField nameLeftInput;
    private final JTextField nameRightInput;

    private Color colorYellow = new Color(251, 209, 4);

    public SetPlayersNameView() {
        super("Set players' name", "Change the players' name in the text boxes and then press save!", 400);

        setLayout(new GridLayout(4, 1));

        JPanel playerLeft = new JPanel();
        playerLeft.setBorder(new EmptyBorder(10, 0, 20, 0));
        playerLeft.setBackground(Color.black);

        JLabel playerLeftLabel = new JLabel("Player on left: ");
        playerLeftLabel.setFont(super.getFontHashMap().get("LABEL_FONT_SIZE"));
        playerLeftLabel.setForeground(Color.white);
        playerLeftLabel.setBorder(new EmptyBorder(0, 0, 0, 25));
        playerLeft.add(playerLeftLabel);

        nameLeftInput = new JTextField();
        nameLeftInput.setText(SettingsController.getPlayerLeftName());
        nameLeftInput.setFont(super.getFontHashMap().get("LABEL_FONT_SIZE"));
        nameLeftInput.setPreferredSize(new Dimension(200, 30));
        nameLeftInput.setBackground(Color.black);
        nameLeftInput.setForeground(Color.white);
        nameLeftInput.setBorder(new MatteBorder(0, 0, 2, 0, colorYellow));
        playerLeft.add(nameLeftInput);

        JPanel playerRight = new JPanel();
        playerRight.setBorder(new EmptyBorder(10, 0, 20, 0));
        playerRight.setBackground(Color.black);

        JLabel playerRightLabel = new JLabel("Player on right: ");
        playerRightLabel.setFont(super.getFontHashMap().get("LABEL_FONT_SIZE"));
        playerRightLabel.setForeground(Color.white);
        playerRightLabel.setBorder(new EmptyBorder(0, 0, 0, 25));
        playerRight.add(playerRightLabel);

        nameRightInput = new JTextField();
        nameRightInput.setText(SettingsController.getPlayerRightName());
        nameRightInput.setFont(super.getFontHashMap().get("LABEL_FONT_SIZE"));
        nameRightInput.setPreferredSize(new Dimension(200, 30));
        nameRightInput.setBackground(Color.black);
        nameRightInput.setForeground(Color.white);
        nameRightInput.setBorder(new MatteBorder(0, 0, 2, 0, colorYellow));
        playerRight.add(nameRightInput);

        add(playerLeft);
        add(playerRight);

        save = new JButton("SAVE");
        save.setBackground(colorYellow);
        save.setForeground(Color.black);
        save.setBorderPainted(false);
        save.setFont(super.getFontHashMap().get("BUTTON_FONT_SIZE"));
        save.addActionListener(SettingsController.getInstance());
        add(save);
    }

    public JButton getSaveButton() {
        return save;
    }

    public JTextField getNameLeftInput() {
        return nameLeftInput;
    }

    public JTextField getNameRightInput() {
        return nameRightInput;
    }
}
