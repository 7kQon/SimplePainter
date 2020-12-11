package observer;

import java.beans.PropertyChangeEvent;

public interface IShapeObserver {
    void noticeDrawModeChanged(int drawMode);
    void noticeOptionChanged();
}
