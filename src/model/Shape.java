package model;

import constants.Constants;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Shape {
    public int      nDrawMode;
    public Point    src, dest;
    public int      nSize;
    public boolean  bFill;
    public Color    selectedColor;
    public List<Point> path;

    public Shape(){
        nDrawMode = Constants.NONE;
        src = new Point();
        dest = new Point();
        nSize = 1;
        bFill = false;
        selectedColor = Color.BLACK;
        path = new ArrayList<>();
    }

    public Shape(Shape model){
        this.nDrawMode = model.nDrawMode;
        this.src = model.src;
        this.dest = model.dest;
        this.nSize = model.nSize;
        this.bFill = model.bFill;
        this.selectedColor = model.selectedColor;
        if(!model.path.isEmpty()){
            this.path = new ArrayList<>();
            this.path.addAll(model.path);
        }
    }
}
