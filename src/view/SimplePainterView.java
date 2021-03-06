package view;

import controller.*;
import javax.swing.*;
import java.awt.*;

public class SimplePainterView extends JPanel {
    private DrawPanel drawPanel;
    private ShapeSelectMenu shapeSelectMenu;
    private ShapeOptionForm shapeOptionForm;
    private SelectedOptions selectedOptions;

    public SimplePainterView(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(820, 830));
        setLayout(null);

        shapeSelectMenu = new ShapeSelectMenu();
        shapeSelectMenu.setBounds(10,610,300,200);
        add(shapeSelectMenu);

        drawPanel = new DrawPanel();
        drawPanel.setBounds(10,10,800,600);
        add(drawPanel);

        shapeOptionForm = new ShapeOptionForm();
        shapeOptionForm.setBounds(310, 610, 200,200);
        add(shapeOptionForm);

        selectedOptions = new SelectedOptions();
        selectedOptions.setBounds(510, 610, 300, 200);

        add(selectedOptions);
    }
}
