package controller;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeSelectMenu extends ShapeController {

    private DrawModeButton[] btnMenuArray;

    public ShapeSelectMenu(){
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Menu"));
        setLayout(new GridLayout(2,3));

        btnMenuArray = new DrawModeButton[6];
        for(int i = 0; i < btnMenuArray.length; i++){
            btnMenuArray[i] = new DrawModeButton(Constants.MENU[i]);
            btnMenuArray[i].setDrawMode(i);

            btnMenuArray[i].setBackground(Constants.HOVERING[0]);
            btnMenuArray[i].setForeground(Constants.HOVERING[1]);

            btnMenuArray[i].addMouseListener(new HoveringListener());
            btnMenuArray[i].addActionListener(e -> sharedShape.setDrawMode(((DrawModeButton) e.getSource()).drawMode));

            add(btnMenuArray[i]);
        }
    }

    private class DrawModeButton extends JButton{
        int drawMode;
        public DrawModeButton(){
            super();
            drawMode = Constants.NONE;
        }

        public DrawModeButton(String text){
            super(text);
            drawMode = Constants.NONE;
        }

        public void setDrawMode(int drawMode){
            this.drawMode = drawMode;
        }
    }
}
