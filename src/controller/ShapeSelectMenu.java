package controller;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeSelectMenu extends ShapeController {

    private JButton[] btnMenuArray;

    public ShapeSelectMenu(){
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Menu"));
        setLayout(new GridLayout(2,3));

        btnMenuArray = new JButton[6];
        for(int i = 0; i < btnMenuArray.length; i++){
            btnMenuArray[i] = new JButton(Constants.MENU[i]);
            btnMenuArray[i].setBackground(Constants.HOVERING[0]);
            btnMenuArray[i].setForeground(Constants.HOVERING[1]);
            btnMenuArray[i].addMouseListener(new HoveringListener());
            btnMenuArray[i].addActionListener(new MenuListener());
            add(btnMenuArray[i]);
        }
    }

    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Object obj = e.getSource();
            for(int i = 0; i < 6; i++){
                if(obj == btnMenuArray[i]){
                    sharedShape.setDrawMode(i);
                }
            }
        }
    }
}
