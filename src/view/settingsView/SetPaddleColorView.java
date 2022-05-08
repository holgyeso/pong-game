package view.settingsView;

import controller.settingsController.SettingsController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SetPaddleColorView extends SettingsView {
    JColorChooser colorChooser;
    JButton save;

    private Color colorYellow = new Color(251, 209, 4);


    public SetPaddleColorView() {

        super("Choose paddle color", "Set the desired color of the paddle and then press save!", 500);

        colorChooser = new JColorChooser();
        colorChooser.setBackground(Color.black);
        colorChooser.setBorder(new EmptyBorder(20, 0, 20, 0));
        colorChooser.getSelectionModel().setSelectedColor(SettingsController.getPaddleColor());

        add(colorChooser, BorderLayout.CENTER);

        save = new JButton("SAVE");
        save.setBackground(colorYellow);
        save.setForeground(Color.black);
        save.setBorderPainted(false);
        save.setFont(super.getFontHashMap().get("BUTTON_FONT_SIZE"));
        save.addActionListener(SettingsController.getInstance());
        add(save, BorderLayout.SOUTH);
    }

    public JButton getSaveButton() {
        return save;
    }

    public JColorChooser getColorChooser() {
        return colorChooser;
    }

}
