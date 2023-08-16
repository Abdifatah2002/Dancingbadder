import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PImage;
import processing.core.PApplet;

public class MovingThing extends Thing implements Comparable<MovingThing> {
    /**
     * indicates whether this movingThing is facing right ot not
     */
    protected boolean isFacingRight;
    /**
     * Movement speed of this MovingThing
     */
    protected int speed;

    /**
     * Creates a new MovingThing and sets its speed, image file, and initial x and y position. A MovingThing object is initially facing right.
     * @param x -  starting x-position of this MovingThing
     * @param y - starting y-position of this MovingThing
     * @param speed - movement speed of this MovingThing
     * @param imageFileName - filename of the image of this MovingThing, for instance "name.png"
     */
    public MovingThing( float x, float y, int speed, String imageFileName){
            // Call the parent class constructor
            super(x, y, imageFileName);

            // Set the speed of this MovingThing
            this.speed = speed;


        }


    /**
     * Draws this MovingThing at its current position. The implementation details of this method is fully provided in the write-up of p05.
     */
    @Override
    public void draw() {
        processing.pushMatrix();
        processing.rotate(0.0f);
        processing.translate(getX(), getY());
        if (!isFacingRight) {
            processing.scale(-1.0f, 1.0f);
        }
        processing.image(image(), 0.0f, 0.0f);
        processing.popMatrix();
    }



    /**
     *Compares this object with the specified MovingThing for order, in the increasing order of their speeds.
     * @param other - the MovingThing object to be compared.
     * @return- zero if this object and other have the same speed, a negative integer if the speed of this moving object is less than the speed of other, and a positive integer otherwise.
     */
    @Override
    public int compareTo(MovingThing other) {
        return Integer.compare(this.speed, other.speed);
    }
}
