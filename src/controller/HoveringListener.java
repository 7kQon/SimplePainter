package controller;

import constants.Constants;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// UI feedback
public class HoveringListener implements MouseListener {
    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = ((JButton) e.getSource());
        button.setBackground(Constants.HOVERING[2]);
        button.setForeground(Constants.HOVERING[3]);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = ((JButton) e.getSource());
        button.setBackground(Constants.HOVERING[0]);
        button.setForeground(Constants.HOVERING[1]);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
