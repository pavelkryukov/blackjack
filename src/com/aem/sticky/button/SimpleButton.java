package com.aem.sticky.button;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

import com.aem.sticky.button.events.ButtonListener;
import com.aem.sticky.button.events.ClickListener;
import com.aem.sticky.button.events.NullListener;

/**
 * A button with graphics and sound effects which reacts to the mouse.
 * 
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public class SimpleButton extends ButtonSkeleton implements ButtonListener,
        ClickListener {

    private ClickListener clickListener;
    private ButtonListener buttonListener;

    private Shape shape;
    private Image current, up, down;

    public SimpleButton(Shape s, Image up, Image down) {
        shape = s;
        setShape(s);
        this.up = up;
        this.down = down;
        current = up;
        clickListener = NullListener.getSingleton();
        buttonListener = NullListener.getSingleton();
        super.addListener((ButtonListener) this);
        super.addListener((ClickListener) this);

    }

    @Override
    public void addListener(ClickListener l) {
        clickListener = l;
    }

    @Override
    public void addListener(ButtonListener b) {
        buttonListener = b;
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        current.draw(shape.getX(), shape.getY());
    }

    public void onClick(Button clicked, float mx, float my) {
        clickListener.onClick(this, mx, my);
    }

    public void onDoubleClick(Button clicked, float mx, float my) {
        clickListener.onDoubleClick(this, mx, my);
    }

    public void onRightClick(Button clicked, float mx, float my) {
        clickListener.onRightClick(this, mx, my);
    }

    public void onMouseEnter(Button b) {
        buttonListener.onMouseEnter(this);
        current = down;
    }

    public void onMouseExit(Button b) {
        buttonListener.onMouseExit(this);
        current = up;
    }

}
