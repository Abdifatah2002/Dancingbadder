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
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PImage;
import processing.core.PApplet;

/**
 * This class models a graphic Thing which can be drawn at a give (x,y) position within the display
 * window of a graphic application.
 *
 */
public class Thing {
    // Fields defined to draw a Thing in the application display window
    /**
     * PApplet object that represents the display window of this graphic application
     */
    protected static PApplet processing; // PApplet object that represents the display window

    /**
     * image of this graphic thing of type PImage
     */
    private PImage image; // image of this graphic thing

    /**
     * x-position of this thing in the display window
     */
    private float x; // x-position of this thing in the display window
    /**
     * y-position of this thing in the display window
     */
    private float y; // y-position of this thing in the display window

    /**
     * Creates a new graphic Thing located at a specific (x, y) position of the display window
     *
     * @param x             x-coordinate of this thing in the display window
     * @param y             y-coordinate of this thing in the display window
     * @param imageFilename filename of the image of this thing, for instance "name.png"
     */
    public Thing(float x, float y, String imageFilename) {
        // Set drawing parameters
        this.image = processing.loadImage("images" + File.separator + imageFilename);
        // sets the position of this decoration object
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this thing to the display window at its current (x,y) position
     */
    public void draw() {
        // draw the thing at its current position
        processing.image(this.image, this.x, y);

    }

    /**
     * Sets the PApplet object display window where this Thing will be drawn. The processing PApplet
     * static data field should be set to Badger.getProcessing() since Badgers and Thing objects are going
     * to be displayed to the same screen.
     */
    public static void setProcessing(PApplet processing) {
        Thing.processing = processing;
    }

    /**
     * Returns a reference to the image of this thing
     *
     * @return the image of type PImage of the thing object
     */
    public PImage image() {
        return image;
    }


    /**
     * Returns the x-position of this thing in the display window
     *
     * @return the X coordinate of the thing position
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-position of this thing in the display window
     *
     * @return the y-position of the thing
     */
    public float getY() {
        return y;
    }


    /**
     * Sets the x-position of this thing in the display window
     *
     * @param x the x-position to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Sets the y-position of this thing in the display window
     *
     * @param y the y-position to set
     */
    public void setY(float y) {
        this.y = y;
    }


    /**
     * Checks if the mouse is over this Thing object
     *
     * @return true if the mouse is over this Thing, otherwise returns false
     */
    public boolean isMouseOver() {
        // Check if the mouse coordinates are within the Thing's image boundaries
        if (processing.mouseX >= x && processing.mouseX <= x + image.width &&
                processing.mouseY >= y && processing.mouseY <= y + image.height) {
            return true;
        }
        return false;
    }
}




