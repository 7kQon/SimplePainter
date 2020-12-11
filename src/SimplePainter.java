import view.*;
import javax.swing.*;

public class SimplePainter {
    public static void main(String[] args){
        JFrame frame = new JFrame("Simple Painter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        SimplePainterView view = new SimplePainterView(); // primary view
        frame.getContentPane().add(view); // add view to frame

        frame.pack();
        frame.setVisible(true);
    }
}
