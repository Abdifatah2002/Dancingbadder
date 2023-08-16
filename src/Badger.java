//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P03 Dancing Badger Part 2
// Course:   CS 300 Spring 2023
//
// Author:   Abdifatah Abdi
// Email:    aaabdi2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
/// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//
////   _X__ Write-up states that pair programming is allowed for this assignment.
//
////   _X__ We have both read and understand the course Pair Programming Policy.
//
////   _X__ We have registered our team prior to the team registration deadline.
//
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//// Persons:         TA: TA Snehal Wadhwani  help with little help on my isOver method in the starshiprobot
// TA: Yiwei Zhang help with little help on my moveTowardsDestination


//// Online Sources:  i used the https://cs300-www.cs.wisc.edu/w for my fields and methods
// : i used the https://stackoverflow.com/questions/23302698/java-check-if-two-rectangles-overlap-at-any-point for my isOver method
// i also used https://www.w3schools.com to refresh my meomry past content i forget how to do it

//
///////////////////////////////////////////////////////////////////////////////
// File header comes here
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PImage;
import processing.core.PApplet;

/**
 * This class models a Badger object in the P03 Dancing Badgers programming assignment
 *
 */
public class Badger {


    // badger's identification fields

    // Fields defined to draw the badger in the application display window
    /**
     * PApplet object that represents the display window
     */
    private static PApplet processing; // PApplet object that represents the display window

    /**
     * badger's image of type PImage
     */
    private PImage image; // badger's image

    /**
     * badger's x-position in the display window
     */
    private float x; // badger's x-position in the display window

    /**
     * badger's y-position in the display window
     */
    private float y; // badger's y-position in the display window

    /**
     * indicates whether the badger is being dragged or not
     */
    private boolean isDragging; // indicates whether the badger is being dragged or not

    /**
     * old x-position of the mouse
     */
    private static int oldMouseX; // old x-position of the mouse

    /**
     * old y-position of the mouse
     */
    private static int oldMouseY; // old y-position of the mouse

    /**
     * indicates where this badger is dancing or not
     */
    private boolean isDancing;//indicates whether this badger is dancing or not


    /**
     * array storing this Badger's dance show steps
     */
    private DanceStep[] danceSteps; // array storing this Badger's dance show steps

    /**
     * stores the next dance (x,y) postion of this badger
     * next dance postion [0] x-postion
     * nextDancepostion [1]; y-postion
     */
    private float[] nextDancePosition;//stores
    /**
     * index position of the current dance step of this badger
     */
    private int stepIndex;


    /**
     * Creates a new badger object positioned at the center of the display window
     *
     */

    public Badger() {
        this(processing.width / 2.0f, processing.height / 2.0f);

    }

    /**
     * Creates a new badger object positioned at a specific position of the display window
     *
     * @param x x-position of this Badger object within the display window
     * @param y y-position of this Badger object within the display window
     */
    public Badger(float x, float y) {
        // Set badger draw parameters
        this.image = processing.loadImage("images" + File.separator + "badger.png");
        // sets the position of the badger object
        this.x = x;
        this.y = y;
        isDragging = false; // initially the badger is not dragging
    }

    /**
     * Draws this badger to the display window. It sets also its position to the mouse position if
     * this badger is being dragged (i.e. if its isDragging field is set to true).
     */
    public void draw() {
        // if the badger is dragging, set its position to the mouse position
        if (this.isDragging) {
            drag();
        }

        // draw the badger at its current position
        processing.image(this.image, x, y);

    }



    /**
     * Returns a reference to the image of this badger
     *
     * @return the image of type PImage of the badger object
     */
    public PImage image() {
        return image;
    }


    /**
     * Returns the x-position of this badger in the display window
     *
     * @return the X coordinate of the badger position
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-position of this badger in the display window
     *
     * @return the y-position of the badger
     */
    public float getY() {
        return y;
    }


    /**
     * Sets the x-position of this badger in the display window
     *
     * @param x the x-position to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Sets the y-position of this badger in the display window
     *
     * @param y the y-position to set
     */
    public void setY(float y) {
        this.y = y;
    }


    /**
     * Checks whether this badger is being dragged
     *
     * @return true if the badger is being dragged, false otherwise
     */
    public boolean isDragging() {
        return isDragging;
    }


    /**
     * Helper method to drag this Badger object to follow the mouse moves
     *
     */
    private void drag() {

        int dx = processing.mouseX - oldMouseX;
        int dy = processing.mouseY - oldMouseY;
        x+=dx;
        y+=dy;

        if(x > 0)
            x = Math.min(x, processing.width);
        else
            x = 0;
        if(y > 0)
            y = Math.min(y, processing.height);
        else
            y = 0;
        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;
    }

    /**
     * Starts dragging this badger
     *
     */
    public void startDragging() {
        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;
        this.isDragging = true;
        drag();
    }

    /**
     * Stops dragging this Badger object
     *
     */
    public void stopDragging() {
        this.isDragging = false;
    }

    /**
     * Checks if the mouse is over this badger
     *
     * @return true if the mouse is over this badger object, otherwise returns false.
     */
    public boolean isMouseOver() {
        int badgerWidth = this.image().width;
        int badgerHeight = this.image().height;

        // checks if the mouse is over this badger
        return Utility.mouseX() >= this.getX() - badgerWidth / 2
                && Utility.mouseX() <= this.getX() + badgerWidth / 2
                && Utility.mouseY() >= this.getY() - badgerHeight / 2
                && Utility.mouseY() <= this.getY() + badgerHeight / 2;
    }

    /**
     * Sets the PApplet object display window where this badger will be drawn
     *
     * @param processing PApplet object that represents the display window
     */
    public static void setProcessing(PApplet processing) {
        Badger.processing = processing;
    }

    /**
     * Gets the PApplet object display window where this badger is drawn
     *
     * @return PApplet object that represents the display window
     */
    public static PApplet getProcessing() {
        return processing;
    }

}

