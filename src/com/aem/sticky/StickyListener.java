package com.aem.sticky;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;

/**
 * Concrete implementation of the InputListener interface; passes events to
 * clickables.
 * 
 * @author Alexander Schearer <aschearer@gmail.com>
 * 
 */
public class StickyListener implements InputListener {

    private List<Clickable> clickables;

    public StickyListener() {
        clickables = new LinkedList<Clickable>();
    }

    public void add(Clickable button) {
        clickables.add(button);
    }

    public void controllerButtonPressed(int controller, int button) {
        // ignore for now
    }

    public void controllerButtonReleased(int controller, int button) {
        // ignore for now
    }

    public void controllerDownPressed(int controller) {
        // ignore for now
    }

    public void controllerDownReleased(int controller) {
        // ignore for now
    }

    public void controllerLeftPressed(int controller) {
        // ignore for now
    }

    public void controllerLeftReleased(int controller) {
        // ignore for now
    }

    public void controllerRightPressed(int controller) {
        // ignore for now
    }

    public void controllerRightReleased(int controller) {
        // ignore for now
    }

    public void controllerUpPressed(int controller) {
        // ignore for now
    }

    public void controllerUpReleased(int controller) {
        // ignore for now
    }

    public void inputEnded() {
        // ignore for now
    }

    public boolean isAcceptingInput() {
        return true;
    }

    public void keyPressed(int key, char c) {
        // ignore for now
    }

    public void keyReleased(int key, char c) {
        // ignore for now
    }

    public void mouseClicked(int button, int x, int y, int clickCount) {
        for (Clickable c : clickables) {
            c.mouseClicked(button, x, y, clickCount);
        }
    }

    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        for (Clickable c : clickables) {
            c.mouseMoved(oldx, oldy, newx, newy);
        }
    }

    public void mousePressed(int button, int x, int y) {
        for (Clickable c : clickables) {
            c.mousePressed(button, x, y);
        }
    }

    public void mouseReleased(int button, int x, int y) {
        for (Clickable c : clickables) {
            c.mouseReleased(button, x, y);
        }
    }

    public void mouseWheelMoved(int change) {
        // ignore for now
    }

    public void setInput(Input input) {
        // ignore for now
    }

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

}
