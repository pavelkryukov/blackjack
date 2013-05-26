package com.aem.sticky;

/**
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public interface Clickable {

    /**
     * @param button
     * @param x
     * @param y
     * @param clickCount
     */
    public void mouseClicked(int button, int x, int y, int clickCount);

    /**
     * @param button
     * @param x
     * @param y
     */
    public void mousePressed(int button, int x, int y);

    /**
     * @param button
     * @param x
     * @param y
     */
    public void mouseReleased(int button, int x, int y);
    
    /**
     * @param oldx
     * @param oldy
     * @param newx
     * @param newy
     */
    public void mouseMoved(int oldx, int oldy, int newx, int newy);
}
