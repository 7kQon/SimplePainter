import constants.Constants;
import controller.DrawController;
import controller.MenuController;
import controller.OptionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimplePainterView extends JPanel {
    private DrawController drawController;
    private OptionController optionController;

    private MenuController      menuPanel;
    private OptionController optionPanel;
    private JPanel messagePanel;
    private JButton[]   btnMenuArray;
    private JTextField  txtSize;
    private JButton     btnColorChooser;
    private JCheckBox   checkFill;

    public SimplePainterView(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(820, 830));
        setLayout(null);

        menuPanel = new MenuController();
        menuPanel.setBounds(10,610,300,200);
        add(menuPanel);

        drawController = DrawController.getInstance();
        drawController.setBounds(10,10,800,600);
        add(drawController);

        optionPanel = OptionController.getInstance();
        optionPanel.setBounds(310, 610, 200,200);
        add(optionPanel);

        messagePanel = new JPanel();
        messagePanel.setBounds(510, 610, 300, 200);
        messagePanel.setBackground(Color.WHITE);
        messagePanel.setBorder(BorderFactory.createTitledBorder("Message"));
        add(messagePanel);

    } // SimplePainterView()
} // SimplePainterView
