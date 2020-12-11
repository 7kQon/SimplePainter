package observer;

// Observer interface
public interface IShapeObserver {
    void noticeDrawModeChanged(int drawMode);
    void noticeOptionChanged();
}
