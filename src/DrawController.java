import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;

public class DrawController extends JPanel {
    private SimplePainterModel model;
    private ArrayList<SimplePainterModel> modelList;
    private DrawListener drawListener;
    private SimplePainterView view;
    private boolean bDrag;

    public DrawController(SimplePainterView v){
        view = v;
        setBackground(Color.WHITE);

        drawListener = new DrawListener();
        addMouseListener(drawListener);
        addMouseMotionListener(drawListener);

        model = new SimplePainterModel();
        modelList = new ArrayList<>();

        model.nDrawMode = Constants.NONE;
        bDrag = false;
    }

    public void setDrawMode(int mode){
        model.nDrawMode = mode;
        if(model.nDrawMode == Constants.DOT) view.setTxtSize(10);
        else view.setTxtSize(1);
    }

    public void setSelectedColor(Color color){
        model.selectedColor = color;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(bDrag){
            switch (model.nDrawMode){
                case Constants.LINE:
                    g.setColor(model.selectedColor);
                    Graphics2D g2 = ((Graphics2D) g);
                    g2.setStroke(new BasicStroke(model.nSize));
                    g.drawLine(model.point1.x, model.point1.y, model.point2.x, model.point2.y);
                    break;
                case Constants.RECT:
                    break;
                case Constants.OVAL:
                    break;
            }

        }

        for(SimplePainterModel data: modelList){
            switch (data.nDrawMode){
                case Constants.DOT:
                    g.setColor(data.selectedColor);
                    g.fillOval(data.point1.x - data.nSize/2, data.point1.y - data.nSize / 2, data.nSize, data.nSize);
                    break;
                case Constants.LINE:
                    g.setColor(data.selectedColor);
                    Graphics2D g2 = ((Graphics2D) g);
                    g2.setStroke(new BasicStroke(data.nSize));
                    g.drawLine(data.point1.x, data.point1.y, data.point2.x, data.point2.y);
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
            if(model.nDrawMode == Constants.DOT){
                model.point1 = e.getPoint();
                model.nSize = view.getTxtSize();
                modelList.add(new SimplePainterModel(model));
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(model.nDrawMode == Constants.LINE){
                bDrag = true;
                model.point1 = e.getPoint();
                model.nSize = view.getTxtSize();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(model.nDrawMode == Constants.LINE){
                bDrag = false;
                model.point2 = e.getPoint();
                modelList.add(new SimplePainterModel(model));
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(model.nDrawMode == Constants.LINE){
                model.point2 = e.getPoint();
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
