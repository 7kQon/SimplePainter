package view;

import constants.Constants;
import model.SharedShape;
import observer.IShapeObserver;

import javax.swing.*;
import java.awt.*;

public class SelectedOptions extends JPanel implements IShapeObserver {
    private SharedShape sharedShape;
    private JLabel lblColor, lblSize, lblShape, lblFill;
    private JLabel[] labels;
    private String[] stringFormat = { " Color: ", " Size: ", " Shape: ", " Fill: "};

    public SelectedOptions(){
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Selected Options"));
        setLayout(new GridLayout(4,1));

        labels = new JLabel[4];

        lblColor = new JLabel(stringFormat[0]); // color
        lblSize = new JLabel(stringFormat[1]); // size
        lblShape = new JLabel(stringFormat[2]); // shape
        lblFill = new JLabel(stringFormat[3]); // draw or fill

        labels[0] = lblColor;
        labels[1] = lblSize;
        labels[2] = lblShape;
        labels[3] = lblFill;

        for(int i = 0; i < labels.length; i++){
            labels[i].setFont(Constants.CONSOLAS);
            labels[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
            labels[i].setVisible(true);
            add(labels[i]);
        }

        sharedShape = SharedShape.getInstance();
        sharedShape.addObserver(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(sharedShape.nDrawMode > Constants.OVAL) return;
        int x = lblColor.getX() + 80 - 10;
        int y = lblColor.getY() + lblColor.getHeight() / 2 - 10;
        g.setColor(sharedShape.selectedColor);
        g.fillRect(x, y, 20, 20);
    }

    @Override
    public void noticeDrawModeChanged(int drawMode) {
        if(drawMode == Constants.NONE) return;

        if(drawMode == Constants.UNDO || drawMode == Constants.CLEAR){
            // no options
            lblColor.setVisible(false);
            lblSize.setVisible(false);
            lblShape.setVisible(false);
            lblFill.setVisible(false);
            return;
        }
        String shapeText = " Shape: " + Constants.MENU[drawMode];
        lblShape.setText(shapeText);

        if(drawMode == Constants.DOT || drawMode == Constants.LINE){
            lblColor.setVisible(true);
            lblSize.setVisible(true);
            lblShape.setVisible(true);
            lblFill.setVisible(false);
            return;
        }

        if(drawMode == Constants.RECT || drawMode == Constants.OVAL){
            lblColor.setVisible(true);
            lblSize.setVisible(true);
            lblShape.setVisible(true);
            lblFill.setVisible(true);
            return;
        }
    }
}
