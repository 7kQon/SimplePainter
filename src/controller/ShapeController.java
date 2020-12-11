package controller;

import model.*;
import observer.*;
import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class ShapeController extends JPanel implements IShapeObserver {
    protected SharedShape sharedShape;
    public ShapeController(){
        sharedShape = SharedShape.getInstance();
    }

    @Override
    public void noticeDrawModeChanged(int drawMode) {

    }
}
