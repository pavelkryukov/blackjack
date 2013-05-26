package com.aem.sticky.button;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

import com.aem.sticky.button.events.ButtonListener;
import com.aem.sticky.button.events.ClickListener;
import com.aem.sticky.button.events.NullListener;

/**
 * Simple button to serve as the foundation for more complex buttons.
 * 
 * @author Alexander Schearer <aschearer@gmail.com>
 * 
 */
public class ButtonSkeleton implements Button {

    private Shape shape;
    private boolean occupied;
    private ClickListener clickListener;
    private ButtonListener buttonListener;

    public ButtonSkeleton() {
        clickListener = NullListener.getSingleton();
        buttonListener = NullListener.getSingleton();
    }

    /**
     * Allowing for deferred setting for convenience downstream.
     * 
     * @param s
     */
    public void setShape(Shape s) {
        shape = s;
    }

    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (!contains(x, y)) {
            return;
        }
        switch (button) {
            case Input.MOUSE_LEFT_BUTTON:
                switch (clickCount) {
                    case 1:
                        clickListener.onClick(this, x, y);
                        break;
                    case 2:
                        clickListener.onDoubleClick(this, x, y);
                        break;
                    default:
                        break;
                }
                break;
            case Input.MOUSE_RIGHT_BUTTON:
                clickListener.onRightClick(this, x, y);
                break;
            default:
                break;
        }
    }

    public void mousePressed(int button, int x, int y) {
        // don't distinguish between holds and clicks
    }

    public void mouseReleased(int button, int x, int y) {
        // don't distinguish between holds and clicks
    }

    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        // don't track the mouse movement
    }

    public void addListener(ClickListener l) {
        clickListener = l;
    }

    public void addListener(ButtonListener b) {
        buttonListener = b;
    }

    public void render(GameContainer container, Graphics g) {
        g.draw(shape);
    }

    public void update(GameContainer container, int delta) {
        Input in = container.getInput();
        float mx = in.getMouseX();
        float my = in.getMouseY();
        if (!contains(mx, my)) {
            testForExit();
            return;
        }

        testForEnter();
    }

    protected boolean contains(float mx, float my) {
        return shape.contains(mx, my);
    }

    private void testForExit() {
        if (occupied) {
            occupied = false;
            buttonListener.onMouseExit(this);
        }
    }

    private void testForEnter() {
        if (!occupied) {
            occupied = true;
            buttonListener.onMouseEnter(this);
        }
    }

}
