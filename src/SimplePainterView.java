import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimplePainterView extends JPanel {
    private DrawController drawController;

    private JPanel      menuPanel, optionPanel, messagePanel;
    private JButton[]   btnMenuArray;
    private JTextField  txtSize;
    private JButton     btnColorChooser;
    private JCheckBox   checkFill;

    public SimplePainterView(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(820, 830));
        setLayout(null);

        drawController = new DrawController(this);
        drawController.setBounds(10,10,800,600);
        drawController.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(drawController);

        menuPanel = new JPanel();
        menuPanel.setBounds(10,610,300,200);
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        menuPanel.setLayout(new GridLayout(2,3));
        add(menuPanel);

        optionPanel = new JPanel();
        optionPanel.setBounds(310, 610, 200,200);
        optionPanel.setBackground(Color.WHITE);
        optionPanel.setBorder(BorderFactory.createTitledBorder("Option"));
        optionPanel.setLayout(new GridLayout(3,1));
        add(optionPanel);

        messagePanel = new JPanel();
        messagePanel.setBounds(510, 610, 300, 200);
        messagePanel.setBackground(Color.WHITE);
        messagePanel.setBorder(BorderFactory.createTitledBorder("Message"));
        add(messagePanel);

        btnMenuArray = new JButton[6];
        for(int i = 0; i < btnMenuArray.length; i++){
            btnMenuArray[i] = new JButton(Constants.MENU[i]);
            btnMenuArray[i].setBackground(Constants.HOVERING[0]);
            btnMenuArray[i].setForeground(Constants.HOVERING[1]);
            btnMenuArray[i].addMouseListener(new HoveringListener());
            btnMenuArray[i].addActionListener(new MenuListener());
            menuPanel.add(btnMenuArray[i]);
        }

        btnColorChooser = new JButton("Color Chooser");
        btnColorChooser.setFont(new Font("Consolas", Font.BOLD, 16));
        btnColorChooser.setBackground(Constants.HOVERING[0]);
        btnColorChooser.setForeground(Constants.HOVERING[1]);
        btnColorChooser.addMouseListener(new HoveringListener());
        btnColorChooser.addActionListener(new MenuListener());
        optionPanel.add(btnColorChooser);

        txtSize = new JTextField();
        txtSize.setFont(new Font("Consolas", Font.BOLD, 16));
        txtSize.setVisible(false);
        optionPanel.add(txtSize);

        checkFill = new JCheckBox("Fill");
        checkFill.setFont(new Font("Consolas", Font.BOLD, 16));
        checkFill.setVisible(false);
        optionPanel.add(checkFill);
    } // SimplePainterView()

    public void setTxtSize(int size){
        txtSize.setText(Integer.toString(size));
    }
    public int getTxtSize(){return Integer.parseInt(txtSize.getText());}

    public boolean getCheckFill(){return checkFill.isSelected();}

    private class HoveringListener implements MouseListener{

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton btnMenu = ((JButton) e.getSource());
            btnMenu.setBackground(Constants.HOVERING[2]);
            btnMenu.setForeground(Constants.HOVERING[3]);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton btnMenu = ((JButton) e.getSource());
            btnMenu.setBackground(Constants.HOVERING[0]);
            btnMenu.setForeground(Constants.HOVERING[1]);
        }

        @Override
        public void mouseClicked(MouseEvent e){}
        @Override
        public void mousePressed(MouseEvent e){}
        @Override
        public void mouseReleased(MouseEvent e){}
    }

    private class MenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            txtSize.setVisible(true);
            checkFill.setVisible(false);

            if(obj == btnColorChooser){
                Color c = JColorChooser.showDialog(btnColorChooser, "Color Chooser", Color.BLACK);
                drawController.setSelectedColor(c);
            }
            for(int i = 0; i < 6; i++){
                if(obj == btnMenuArray[i]){

                    drawController.setDrawMode(i);

                    if(i == Constants.RECT || i == Constants.OVAL){
                        checkFill.setVisible(true);
                        break;
                    }
                }
            }
        }
    }
} // SimplePainterView
