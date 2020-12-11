package controller;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

// shape option form controller
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
        txtFieldSize.setText("1");
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
        sharedShape.notifyOptionChanged();
    } // set sharedShape's color and notifies

    public void setShapeSize(int size){
        sharedShape.nSize = size;
        sharedShape.notifyOptionChanged();
        txtFieldSize.setText(Integer.toString(size));
    } // set sharedShape's size and notifies

    public void setShapeFill(boolean fill){
        sharedShape.bFill = fill;
        sharedShape.notifyOptionChanged();
        checkBoxFill.setSelected(fill);
    } // set sharedShape's fill and notifies

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

        setShapeColor(Color.BLACK);
        buttonColorChooser.setVisible(true);

        setShapeSize(10);
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
    } // color chooser button action

    class TextFieldChanged implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField txtField = ((JTextField) e.getSource());
            int size = Integer.parseInt(txtField.getText());
            setShapeSize(size);
        }
    } // text field action

    class CheckBoxClicked implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = ((JCheckBox) e.getSource());
            boolean fill = checkBox.isSelected();
            setShapeFill(fill);
        }
    } // check box action
}
