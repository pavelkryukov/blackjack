package com.aem.sticky.button.events;

import com.aem.sticky.button.Button;

/**
 * Capture events related to buttons.
 *
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public interface ButtonListener {

    /**
     * @param b
     */
    public void onMouseEnter(Button b);
    
    /**
     * @param b
     */
    public void onMouseExit(Button b);
}
