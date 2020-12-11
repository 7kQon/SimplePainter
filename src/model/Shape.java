package model;

import constants.Constants;
import java.awt.*;

public class Shape {
    public int      nDrawMode;
    public Point src, dest;
    public int      nSize;
    public boolean  bFill;
    public Color    selectedColor;

    public Shape(){
        nDrawMode = Constants.NONE;
        src = new Point();
        dest = new Point();
        nSize = 1;
        bFill = false;
        selectedColor = Color.BLACK;
    }

    public Shape(Shape model){
        this.nDrawMode = model.nDrawMode;
        this.src = model.src;
        this.dest = model.dest;
        this.nSize = model.nSize;
        this.bFill = model.bFill;
        this.selectedColor = model.selectedColor;
    }
}
