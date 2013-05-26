package com.aem.sticky.button.events;

import com.aem.sticky.button.Button;

/**
 * Instance of the null-pattern to simplify things in buttons.
 * 
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public class NullListener implements ButtonListener, ClickListener,
        DragListener {

    private static NullListener instance;
    
    /**
     * Please use the getSingleton method to access this class.
     */
    private NullListener() {
        
    }

    /**
     * Let's avoid creating too many objects.
     * 
     * @return
     */
    public static NullListener getSingleton() {
        if (instance == null) {
            instance = new NullListener();
        }
        return instance;
    }

    public void onMouseEnter(Button b) {
        // null listener doesn't do anything
    }

    public void onMouseExit(Button b) {
        // null listener doesn't do anything
    }

    public void onClick(Button clicked, float mx, float my) {
        // null listener doesn't do anything
    }

    public void onDoubleClick(Button clicked, float mx, float my) {
        // null listener doesn't do anything
    }

    public void onRightClick(Button clicked, float mx, float my) {
        // null listener doesn't do anything
    }

    public void onDrag(Button b, float mx, float my) {
        // null listener doesn't do anything
    }

    public void onDragStart(Button b, float mx, float my) {
        // null listener doesn't do anything
    }

    public void onDragStop(Button b, float mx, float my) {
        // null listener doesn't do anything
    }

}
