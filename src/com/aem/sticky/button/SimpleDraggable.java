package com.aem.sticky.button;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.aem.sticky.button.events.DragListener;
import com.aem.sticky.button.events.NullListener;

/**
 * A button which can be dragged and dropped.
 * 
 * This won't work if your shape changes sizes...
 * 
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public class SimpleDraggable extends DraggableSkeleton implements DragListener {

    private Shape shape;
    private Vector2f dimensions, offset;
    private DragListener dragListener;

    public SimpleDraggable(Shape s) {
        shape = s;
        dimensions = new Vector2f();
        dimensions.x = s.getMaxX() - s.getX();
        dimensions.y = s.getMaxY() - s.getY();
        setShape(s);
        offset = new Vector2f(s.getX(), s.getY());
        super.addListener((DragListener) this);
        dragListener = NullListener.getSingleton();
    }

    @Override
    public void addListener(DragListener d) {
        dragListener = d;
    }

    public void onDrag(Button b, float mx, float my) {
        followMouse(mx, my);
        dragListener.onDrag(this, mx, my);
    }

    public void onDragStart(Button b, float mx, float my) {
        findOffset(mx, my);
        dragListener.onDragStart(this, mx, my);
    }

    public void onDragStop(Button b, float mx, float my) {
        // do nothing
        dragListener.onDragStop(this, mx, my);
    }

    private void findOffset(float mx, float my) {
        offset.x = shape.getCenterX() - dimensions.x / 2 - mx;
        offset.y = shape.getCenterY() - dimensions.y / 2 - my;
    }

    private void followMouse(float mx, float my) {
        shape.setX(mx + offset.x);
        shape.setY(my + offset.y);
    }
}
