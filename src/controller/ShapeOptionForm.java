package controller;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

public class ShapeOptionForm extends ShapeController {

    private JButton buttonColorChooser;
    private JTextField txtFieldSize;
    private JCheckBox checkBoxFill;

    public ShapeOptionForm(){
        super();
        sharedShape.addObserver(this);

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Option"));
        setLayout(new GridLayout(3,1));

        buttonColorChooser = new JButton("Color Chooser");
        buttonColorChooser.setFont(Constants.CONSOLAS);
        buttonColorChooser.setBackground(Constants.HOVERING[0]);
        buttonColorChooser.setForeground(Constants.HOVERING[1]);
        buttonColorChooser.addMouseListener(new HoveringListener());
        buttonColorChooser.addActionListener(new ColorChooserButtonClicked());
        buttonColorChooser.setVisible(false);
        add(buttonColorChooser);

        txtFieldSize = new JTextField();
        txtFieldSize.setFont(Constants.CONSOLAS);
        txtFieldSize.addActionListener(new TextFieldChanged());
        txtFieldSize.setVisible(false);
        add(txtFieldSize);

        checkBoxFill = new JCheckBox("Fill");
        checkBoxFill.setFont(Constants.CONSOLAS);
        checkBoxFill.addActionListener(new CheckBoxClicked());
        checkBoxFill.setVisible(false);
        add(checkBoxFill);
    }

    private void setShapeColor(Color color){
        sharedShape.selectedColor = color;
    }
    public void setShapeSize(int size){
        sharedShape.nSize = size;
        txtFieldSize.setText(Integer.toString(size));
    }
    public void setShapeFill(boolean fill){
        sharedShape.bFill = fill;
        checkBoxFill.setSelected(fill);
    }

    @Override
    public void noticeDrawModeChanged(int mode) {
        super.noticeDrawModeChanged(mode);
        onDrawModeChanged(mode);
    }

    private void onDrawModeChanged(int drawMode){

        if(drawMode == Constants.UNDO || drawMode == Constants.CLEAR){
            buttonColorChooser.setVisible(false);
            txtFieldSize.setVisible(false);
            checkBoxFill.setVisible(false);
            return;
        }

        buttonColorChooser.setVisible(true);

        setShapeSize(10); // init 10
        txtFieldSize.setVisible(true);
        if(drawMode == Constants.RECT || drawMode == Constants.OVAL){
            setShapeFill(false); // init false
            checkBoxFill.setVisible(true);
            return;
        }
        checkBoxFill.setVisible(false);
    }

    class ColorChooserButtonClicked implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = JColorChooser.showDialog(buttonColorChooser, "Color Chooser", Color.BLACK);
            setShapeColor(color);
        }
    }

    class TextFieldChanged implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField txtField = ((JTextField) e.getSource());
            int size = Integer.parseInt(txtField.getText());
            setShapeSize(size);
        }
    }

    class CheckBoxClicked implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = ((JCheckBox) e.getSource());
            boolean fill = checkBox.isSelected();
            setShapeFill(fill);
        }
    }
}
