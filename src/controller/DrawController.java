package controller;

import constants.Constants;
import model.DrawModel;
import model.OptionModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;

public class DrawController extends JPanel {
    private static DrawController instance = null;
    private DrawModel model;
    private ArrayList<DrawModel> modelList;
    private DrawListener drawListener;
    private boolean bDrag;

    private OptionController optionController;

    public static DrawController getInstance(){
        if(instance == null) instance = new DrawController();
        return instance;
    }
    private DrawController(){
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        drawListener = new DrawListener();
        addMouseListener(drawListener);
        addMouseMotionListener(drawListener);

        optionController = OptionController.getInstance();

        model = new DrawModel();
        modelList = new ArrayList<>();

        model.nDrawMode = Constants.NONE;
        bDrag = false;
    }

    public void setDrawMode(int mode){
        model.nDrawMode = mode;
    }

    public void setOption(OptionModel option){
        model.selectedColor = option.color;
        model.nSize = option.size;
        model.bFill = option.fill;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(bDrag){
            switch (model.nDrawMode){
                case Constants.LINE:
                    g.setColor(model.selectedColor);
                    Graphics2D g2 = ((Graphics2D) g);
                    g2.setStroke(new BasicStroke(model.nSize));
                    g.drawLine(model.src.x, model.src.y, model.dest.x, model.dest.y);
                    break;
                case Constants.RECT:
                    break;
                case Constants.OVAL:
                    break;
            }

        }

        for(DrawModel data: modelList){
            switch (data.nDrawMode){
                case Constants.DOT:
                    g.setColor(data.selectedColor);
                    g.fillOval(data.src.x - data.nSize/2, data.src.y - data.nSize / 2, data.nSize, data.nSize);
                    break;
                case Constants.LINE:
                    g.setColor(data.selectedColor);
                    Graphics2D g2 = ((Graphics2D) g);
                    g2.setStroke(new BasicStroke(data.nSize));
                    g.drawLine(data.src.x, data.src.y, data.dest.x, data.dest.y);
                    break;
                case Constants.RECT:
                    // TODO : 기능 완성
                    break;
                case Constants.OVAL:
                    // TODO : 기능 완성
                    break;
                default:
                    break;
            }
        }

    }

    private class DrawListener implements MouseListener, MouseMotionListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("draw clicked");
            if(model.nDrawMode == Constants.DOT){
                model.src = e.getPoint();
                model.nSize = optionController.getOptionModel().size;
                modelList.add(new DrawModel(model));
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(model.nDrawMode == Constants.LINE){
                bDrag = true;
                model.src = e.getPoint();
                model.nSize = optionController.getOptionModel().size;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(model.nDrawMode == Constants.LINE){
                bDrag = false;
                model.dest = e.getPoint();
                modelList.add(new DrawModel(model));
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(model.nDrawMode == Constants.LINE){
                model.dest = e.getPoint();
                repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }



        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
