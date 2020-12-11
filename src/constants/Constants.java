package constants;

import java.awt.*;

public class Constants {
    public static final String MENU[] = {
            "Dot", "Line","Rect","Oval","Free", "Undo","Clear"
    };
    public static final Color HOVERING[] = {
            Color.WHITE, Color.BLACK, Color.BLACK, Color.WHITE
    };

    public static final int DOT = 0;
    public static final int LINE = 1;
    public static final int RECT = 2;
    public static final int OVAL = 3;
    public static final int FREE = 4;
    public static final int UNDO = 5;
    public static final int CLEAR = 6;
    public static final int NONE = 7;


    public static final Integer DRAWMODE[] = {
            DOT, LINE, RECT, OVAL, FREE, UNDO, CLEAR
    };

    public static final Font CONSOLAS = new Font("Consolas", Font.BOLD, 16);

}
