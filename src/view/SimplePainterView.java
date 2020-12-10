package view;

import controller.*;
import javax.swing.*;
import java.awt.*;

public class SimplePainterView extends JPanel {
    private DrawController drawController;
    private MenuController menuController;
    private OptionController optionController;
    private JPanel messagePanel;

    public SimplePainterView(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(820, 830));
        setLayout(null);

        menuController = new MenuController();
        menuController.setBounds(10,610,300,200);
        add(menuController);

        drawController = DrawController.getInstance();
        drawController.setBounds(10,10,800,600);
        add(drawController);

        optionController = OptionController.getInstance();
        optionController.setBounds(310, 610, 200,200);
        add(optionController);

        messagePanel = new JPanel();
        messagePanel.setBounds(510, 610, 300, 200);
        messagePanel.setBackground(Color.WHITE);
        messagePanel.setBorder(BorderFactory.createTitledBorder("Message"));
        add(messagePanel);

    } // view.SimplePainterView()
} // view.SimplePainterView
