package com.aem.sticky.button.events;

import com.aem.sticky.button.Button;

/**
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public interface DragListener {
    
    /**
     * @param b
     * @param mx
     * @param my
     */
    public void onDragStart(Button b, float mx, float my);

    /**
     * @param b
     * @param mx
     * @param my
     */
    public void onDrag(Button b, float mx, float my);
   
    /**
     * @param b
     * @param mx
     * @param my
     */
    public void onDragStop(Button b, float mx, float my);
}
