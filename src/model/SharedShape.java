package model;

import observer.IShapeObserver;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class SharedShape extends Shape {
    private static SharedShape instance = null;
    protected List<IShapeObserver> observers;

    private SharedShape(){
        super();
        observers = new ArrayList<>();
    }

    public static SharedShape getInstance(){
        if(instance == null) instance = new SharedShape();
        return instance;
    }

    public void addObserver(IShapeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IShapeObserver observer){
        try{
            observers.remove(observer);
        }catch (Exception exception){

        }
    }

    public void setDrawMode(int drawMode){
        nDrawMode = drawMode;
        noticeDrawModeChanged();
    }

    private void noticeDrawModeChanged(){
        for(var observer: observers){
            observer.noticeDrawModeChanged(nDrawMode);
        }
    }
}
