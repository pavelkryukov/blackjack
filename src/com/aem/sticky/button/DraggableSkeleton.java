package com.aem.sticky.button;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

import com.aem.sticky.button.events.DragListener;
import com.aem.sticky.button.events.NullListener;

/**
 * Simple draggable button to serve as the foundation for more complex buttons.
 * 
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public class DraggableSkeleton extends ButtonSkeleton implements Draggable {

    private DragListener dragListener;
    private boolean pressed, dragged;

    public DraggableSkeleton() {
        dragListener = NullListener.getSingleton();
    }

    /**
     * Allowing for deferred setting for convenience downstream.
     * 
     * @param s
     */
    public void setShape(Shape s) {
        super.setShape(s);
    }

    public void addListener(DragListener d) {
        dragListener = d;
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        pressed = wasClicked(button, x, y);
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        super.mouseReleased(button, x, y);
        pressed = false;
        if (dragged) {
            dragged = false;
            dragListener.onDragStop(this, x, y);
        }
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        super.mouseMoved(oldx, oldy, newx, newy);
        if (!dragged && pressed && (oldx != newx || oldy != newy)) {
            dragListener.onDragStart(this, newx, newy);
            dragged = true;
            return;
        }

        if (dragged) {
            dragListener.onDrag(this, newx, newy);
            return;
        }
    }

    private boolean wasClicked(int button, int x, int y) {
        return button == Input.MOUSE_LEFT_BUTTON && contains(x, y);
    }

}
