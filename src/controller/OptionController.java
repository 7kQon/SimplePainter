package controller;

import constants.Constants;
import model.OptionModel;

import javax.swing.*;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class OptionController extends JPanel {
    private static OptionController instance = null;

    private OptionModel option;
    private JButton buttonColorChooser;
    private JTextField txtFieldSize;
    private JCheckBox checkBoxFill;

    public static OptionController getInstance(){
        if(instance == null) instance = new OptionController();
        return instance;
    }

    private OptionController(){
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Option"));
        setLayout(new GridLayout(3,1));

        option = new OptionModel();

        buttonColorChooser = new JButton("Color Chooser");
        buttonColorChooser.setFont(Constants.CONSOLAS);
        buttonColorChooser.setBackground(Constants.HOVERING[0]);
        buttonColorChooser.setForeground(Constants.HOVERING[1]);
        buttonColorChooser.addMouseListener(new HoveringListener());
        buttonColorChooser.addActionListener(new ColorChooserButtonClicked());
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

    public OptionModel getOptionModel(){return this.option;}

    public void setOptionColor(Color color){
        System.out.println("set color");
        option.color = color;
    }
    public void setOptionSize(int size){
        System.out.println("set Size");
        txtFieldSize.setText(Integer.toString(size));
        option.size = size;
    }
    public void setOptionFill(boolean fill){
        checkBoxFill.setSelected(fill);
        option.fill = fill;
    }

    public void setTxtFieldVisible(boolean visible){
        txtFieldSize.setVisible(visible);
    }

    public void setCheckBoxVisible(boolean visible){
        checkBoxFill.setVisible(visible);
    }

    class ColorChooserButtonClicked implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setOptionColor(JColorChooser.showDialog(buttonColorChooser, "Color Chooser", Color.BLACK));
            DrawController.getInstance().setOption(option);
        }
    }

    class TextFieldChanged implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField txtField = ((JTextField) e.getSource());
            setOptionSize(Integer.parseInt(txtField.getText()));
            DrawController.getInstance().setOption(option);
        }
    }

    class CheckBoxClicked implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = ((JCheckBox) e.getSource());
            setOptionFill(checkBox.isSelected());
            DrawController.getInstance().setOption(option);
        }
    }
}
