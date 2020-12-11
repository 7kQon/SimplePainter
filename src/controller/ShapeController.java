package controller;

import model.*;
import observer.*;
import javax.swing.*;
import java.beans.PropertyChangeEvent;

// base class of shape(model) controller
public class ShapeController extends JPanel implements IShapeObserver {
    protected SharedShape sharedShape;
    public ShapeController(){
        sharedShape = SharedShape.getInstance();
    }

    @Override
    public void noticeDrawModeChanged(int drawMode) {}

    @Override
    public void noticeOptionChanged(){}
}
