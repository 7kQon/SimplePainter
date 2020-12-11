package view;

import constants.Constants;
import model.Shape;
import model.SharedShape;
import observer.IShapeObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

public class DrawPanel extends JPanel implements IShapeObserver {
    private SharedShape sharedShape;
    private ArrayList<Shape> shapes;

    private MouseEventListener mouseEventListener;
    private boolean bDrag;

    public DrawPanel(){
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        mouseEventListener = new MouseEventListener();
        addMouseListener(mouseEventListener);
        addMouseMotionListener(mouseEventListener);

        sharedShape = SharedShape.getInstance();
        sharedShape.addObserver(this);
        shapes = new ArrayList<>();

        bDrag = false;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(bDrag){
            switch (sharedShape.nDrawMode){
                case Constants.LINE:
                    drawLine(g, sharedShape);
                    break;
                    case Constants.RECT:
                        drawRect(g, sharedShape);
                        break;
                        case Constants.OVAL:
                            drawOval(g, sharedShape);
                            break;
                            case Constants.FREE:
                                drawFreeLine(g, sharedShape);
                                break;
            }

        }

        for(Shape shape: shapes){
            switch (shape.nDrawMode){
                case Constants.DOT:
                    drawDot(g, shape);
                    break;
                    case Constants.LINE:
                        drawLine(g, shape);
                        break;
                        case Constants.RECT:
                            drawRect(g, shape);
                            break;
                            case Constants.OVAL:
                                drawOval(g, shape);
                                break;
                                case Constants.FREE:
                                    drawFreeLine(g, shape);
                                    break;
                default:
                    break;
            }
        }

    }

    private void drawLine(Graphics g, Shape shape){
        ((Graphics2D) g).setStroke(new BasicStroke(shape.nSize));
        g.setColor(shape.selectedColor);
        g.drawLine(shape.src.x, shape.src.y, shape.dest.x, shape.dest.y);
    }

    private void drawRect(Graphics g, Shape shape){
        ((Graphics2D) g).setStroke(new BasicStroke(shape.nSize));// init stroke
        g.setColor(shape.selectedColor);

        Bounds bounds = new Bounds();
        bounds.setBounds(shape.src, shape.dest);

        if(shape.bFill){ g.fillRect(bounds.min.x, bounds.min.y, bounds.width, bounds.height); return;}
        g.drawRect(bounds.min.x, bounds.min.y, bounds.width, bounds.height);
    }

    private void drawOval(Graphics g, Shape shape){
        ((Graphics2D) g).setStroke(new BasicStroke(shape.nSize));// init stroke
        g.setColor(shape.selectedColor);

        Bounds bounds = new Bounds();
        bounds.setBounds(shape.src, shape.dest);

        if(shape.bFill) { g.fillOval(bounds.min.x, bounds.min.y, bounds.width, bounds.height); return; }
        g.drawOval(bounds.min.x, bounds.min.y, bounds.width, bounds.height);
    }

    private void drawDot(Graphics g, Shape shape){
        ((Graphics2D) g).setStroke(new BasicStroke(1)); // init stroke
        g.setColor(shape.selectedColor);
        g.fillOval(shape.src.x - shape.nSize/2, shape.src.y - shape.nSize / 2, shape.nSize, shape.nSize);
    }

    private void drawFreeLine(Graphics g, Shape shape){
        ((Graphics2D) g).setStroke(new BasicStroke(shape.nSize));// init stroke
        g.setColor(shape.selectedColor);
        int max = shape.path.size();
        for(int i = 0; i < max - 1; i++){
            int sx, sy, dx, dy;
            sx = shape.path.get(i).x;
            sy = shape.path.get(i).y;
            dx = shape.path.get(i + 1).x;
            dy = shape.path.get(i + 1).y;
            g.drawLine(sx, sy, dx, dy);
        }
    }

    @Override
    public void noticeDrawModeChanged(int drawMode) {
        if(drawMode == Constants.UNDO){ undo(); return; }
        if(drawMode == Constants.CLEAR){ clear(); return; }
    }

    @Override
    public void noticeOptionChanged(){

    }

    private void undo(){
        if(shapes.isEmpty()) return;
        int last = shapes.size() - 1;
        shapes.remove(last);
        repaint();
    }

    private void clear(){
        if(shapes.isEmpty()) return;
        shapes.clear();
        repaint();
    }

    private class Bounds{
        Point min;
        int width, height;

        public Bounds(){
            min = new Point();
        }

        public void setBounds(Point p1, Point p2){
            min.x = Math.min(p1.x, p2.x);
            min.y = Math.min(p1.y, p2.y);
            width = Math.abs(p1.x - p2.x);
            height = Math.abs(p1.y - p2.y);
        }
    }

    private class MouseEventListener implements MouseListener, MouseMotionListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(sharedShape.nDrawMode == Constants.DOT){
                sharedShape.src = e.getPoint();
                shapes.add(new Shape(sharedShape));
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int drawMode = sharedShape.nDrawMode;
            switch (drawMode){
                case Constants.LINE, Constants.RECT, Constants.OVAL, Constants.FREE:
                    bDrag = true;
                    if(drawMode == Constants.FREE) {
                        sharedShape.path.add(e.getPoint());
                    }
                    else sharedShape.src = e.getPoint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int drawMode = sharedShape.nDrawMode;
            switch (drawMode){
                case Constants.LINE, Constants.RECT, Constants.OVAL, Constants.FREE:
                    if(drawMode == Constants.FREE){
                        sharedShape.path.add(e.getPoint());
                    }else sharedShape.dest = e.getPoint();
                    repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int drawMode = sharedShape.nDrawMode;
            switch (drawMode){
                case Constants.LINE, Constants.RECT, Constants.OVAL, Constants.FREE:
                    bDrag = false;
                    if(drawMode != Constants.FREE) sharedShape.dest = e.getPoint();
                    shapes.add(new Shape(sharedShape));
                    if(drawMode == Constants.FREE) sharedShape.path.clear();
                    repaint();
            }
        }
        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }

        @Override
        public void mouseMoved(MouseEvent e) { }
    }
}
