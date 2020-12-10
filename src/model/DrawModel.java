package model;

import constants.Constants;

import java.awt.*;

public class DrawModel {
    public int      nDrawMode;
    public Point src, dest;
    public int      nSize;
    public boolean  bFill;
    public Color    selectedColor;

    public DrawModel(){
        nDrawMode = Constants.NONE;
        src = new Point();
        dest = new Point();
        nSize = 1;
        bFill = false;
        selectedColor = Color.BLACK;
    }

    public DrawModel(DrawModel model){
        this.nDrawMode = model.nDrawMode;
        this.src = model.src;
        this.dest = model.dest;
        this.nSize = model.nSize;
        this.bFill = model.bFill;
        this.selectedColor = model.selectedColor;
    }
}
