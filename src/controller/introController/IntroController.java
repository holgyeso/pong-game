package controller.introController;

import controller.MainController;
import view.playView.IntroView;
import view.playView.PlayFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroController implements ActionListener {

    private MainController mainController;
    private PlayFrame frame;


    public IntroController(MainController controller) {
        this.mainController = controller;
        this.frame = controller.getFrame();

        IntroView introView = new IntroView(this);
        introView.setPreferredSize(new Dimension(frame.getWidth() - frame.frameInsetRight() - frame.frameInsetLeft(), frame.getHeight() - frame.frameInsetTop() - frame.frameInsetBottom()));


        mainController.getFrame().setContentPane(introView);
        frame.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainController.changeToPlayController();
    }
}
