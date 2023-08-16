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
 * This class models Starship Robot objects delivering food to badger fans
 *
 */
public class StarshipRobot extends MovingThing {

    /**
     * PApplet object representing the display window where this StarshipRobot is going to be drawn.
     */
    private static PApplet processing; // PApplet object that represents the display window
    /**
     * movement speed of this StarshipRobot
     */
    private int speed; // movement speed of this StarshipRobot
    /**
     * image of this StarshipRobot of type PImage
     */
    private PImage image; // image of this StarshipRobot

    /**
     * x-position of this StarshipRobot in the display window
     */
    private float x; // x-position of this StarshipRobot in the display window
    /**
     * y-position of this StarshipRobot in the display window
     */
    private float y; // y-position of this StarshipRobot in the display window

    /**
     * source point of this StarshipRobot at its current journey delivering food to badgers
     */
    private Thing source; // source point of this StarshipRobot at its current journey
    /**
     * destination point of this StarshipRobot at its current journey delivering food to badgers
     */
    private Thing destination; // destination point of this StarshipRobot at its current journey

    /**
     * Creates a new StarshipRobot and sets its source, destination, and speed. The (x,y) position of
     * this StarshipRobot is set to the (x,y) position of source.
     *
     * @param source      Thing object representing the start point of this StarshipRobot
     * @param destination Thing object representing the destination point of this StarshipRobot
     * @param speed       movement speed of this StarshipRobot
     */
    public StarshipRobot(Thing source, Thing destination, int speed) {
        super(source.getX(), source.getY(), speed, "images" + File.separator + "starshipRobot.png");
        this.source = source;
        this.destination = destination;
        this.image = Utility.loadImage("images" + File.separator + "starshipRobot.png");
    }


    /**
     * Returns a reference to the image of this starship robot
     *
     * @return the image of type PImage of the starship robot object
     */
    public PImage image() {
        return image;
    }


    /**
     * Returns the x-position of this starship robot in the display window
     *
     * @return the X coordinate of the starship robot position
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-position of this starship robot in the display window
     *
     * @return the y-position of the starship robot
     */
    public float getY() {
        return y;
    }


    /**
     * Sets the x-position of this starship robot in the display window
     *
     * @param x the x-position to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Sets the y-position of this starship robot in the display window
     *
     * @param y the y-position to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Draws this StarshipRobot to the display window while it is in action delivering food
     */
    @Override
    public void draw() {
        // draw the starship robot at its current position
        processing.image(this.image(), this.x,y);
        // call go()
        this.go();
    }

    /**
     * Checks whether this StarshipRobot is over a specific Thing
     *
     * @param thing a given Thing object
     * @return true if this StarshipRobot is over the Thing object passed as input, otherwise, returns
     *         false.
     */
    public boolean isOver(Thing thing) {
        // (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2)
        float x1 = x - this.image().width / 2;
        float x2 = x + this.image().width / 2;
        float y1 = y - this.image().height / 2;
        float y2 = y + this.image().height / 2;

        float x3 = thing.getX() - thing.image().width / 2;
        float x4 = thing.getX() + thing.image().width / 2;
        float y3 = thing.getY() - thing.image().height / 2;
        float y4 = thing.getY() + thing.image().height / 2;

        return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
    }

    /**
     * Helper method to move this StarshipRobot towards its destination
     */
    private void moveTowardsDestination() {
        float dx = destination.getX() - this.x; // x-move towards destination
        float dy = destination.getY() - this.y; // y-move towards destination
        int d = (int) Math.sqrt(dx * dx + dy * dy); // distance to destination
        if (d != 0) { // move!
            this.x += speed * dx / d;
            this.y += speed * dy / d;
        }
    }

    /**
     * Implements the action of this StarshipRobot. By default, an StarshipRobot object moves
     * back-and-forth between its source and destination.
     */
    public void go() {
        moveTowardsDestination();
        if (this.isOver(this.destination)) {
            Thing d = destination;
            destination = source;
            source = d;
        }
    }





    /**
     * Sets the PApplet object display window where this StarshipRobot will be drawn. The processing
     * PApplet data field is set to Badger.processing since Badger and StarshipRobot objects are going
     * to be displayed to the same screen.
     */
    public static void setProcessing(PApplet processing) {
        StarshipRobot.processing = processing;
    }
}









