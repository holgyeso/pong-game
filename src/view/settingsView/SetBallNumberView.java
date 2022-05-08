package view.settingsView;

import controller.settingsController.SettingsController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SetBallNumberView extends SettingsView {

    private final JSpinner spinner;
    private Color colorYellow = new Color(251,209,4);
    private final JButton save;

    public SetBallNumberView() {
        super("Set ball number", "Set balls' number using the arrows and then press save!", 300);

        setLayout(new GridLayout(3,1));

        JPanel spinnerPanel = new JPanel();
        spinnerPanel.setBackground(Color.black);
        spinnerPanel.setBorder(new EmptyBorder(10,0,20,0));

        JLabel spinnerLabel = new JLabel("Balls' number:");
        spinnerLabel.setFont(super.getFontHashMap().get("LABEL_FONT_SIZE"));
        spinnerLabel.setForeground(Color.white);
        spinnerLabel.setBorder(new EmptyBorder(0,0,0,25));
        spinnerPanel.add(spinnerLabel);

        SpinnerModel spec = new SpinnerNumberModel(SettingsController.getBallNumber(), 1, 5, 1);
        spinner = new JSpinner(spec);
        spinner.getEditor().getComponent(0).setBackground(Color.black);
        spinner.getEditor().getComponent(0).setForeground(Color.white);
        spinner.setPreferredSize(new Dimension(70, 30));
        spinner.setBorder(new MatteBorder(0,0,2,0, colorYellow));
        spinner.setFont(super.getFontHashMap().get("LABEL_FONT_SIZE"));
        spinnerPanel.add(spinner);
        add(spinnerPanel);

        save = new JButton("SAVE");
        save.setBackground(colorYellow);
        save.setForeground(Color.black);
        save.setBorderPainted(false);
        save.setPreferredSize(new Dimension(getWidth(), 30));
        save.setFont(super.getFontHashMap().get("BUTTON_FONT_SIZE"));
        save.addActionListener(SettingsController.getInstance());
        add(save);
    }

    public JButton getSaveButton() {
        return save;
    }

    public JSpinner getBallNumberSpinner() {
        return spinner;
    }

}
