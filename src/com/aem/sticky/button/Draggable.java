package com.aem.sticky.button;

import com.aem.sticky.button.events.DragListener;

/**
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public interface Draggable extends Button {

    /**
     * Set a single click listener for this button.
     * 
     * @param d
     */
    public void addListener(DragListener d);
}
