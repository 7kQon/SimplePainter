import java.awt.*;

public class SimplePainterModel {
    public int      nDrawMode;
    public Point    point1, point2;
    public int      nSize;
    public boolean  bFill;
    public Color    selectedColor;

    public SimplePainterModel(){
        nDrawMode = Constants.NONE;
        point1 = new Point();
        point2 = new Point();
        nSize = 1;
        bFill = false;
        selectedColor = Color.BLACK;
    }

    public SimplePainterModel(SimplePainterModel data){
        nDrawMode = data.nDrawMode;
        point1 = data.point1;
        point2 = data.point2;
        nSize = data.nSize;
        bFill = data.bFill;
        selectedColor = data.selectedColor;
    }

}
