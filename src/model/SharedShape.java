package model;

import observer.IShapeObserver;

import java.util.ArrayList;
import java.util.List;

// only shared instance
public class SharedShape extends Shape {
    private static SharedShape instance = null; // implement Singleton pattern
    protected List<IShapeObserver> observers;

    private SharedShape(){
        // implement Singleton pattern
        super();
        observers = new ArrayList<>();
    }

    public static SharedShape getInstance(){
        // implement Singleton pattern
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
        notifyDrawModeChanged();
    }

    public void notifyDrawModeChanged(){
        for(var observer: observers){
            observer.noticeDrawModeChanged(nDrawMode);
        }
    }

    public void notifyOptionChanged(){
        for(var observer: observers){
            observer.noticeOptionChanged();
        }
    }
}
