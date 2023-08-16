//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P05 Dancing Badger Part 3
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
 * This is the main class for the p03 Dancing Bangers II program
 *
 */
public class DancingBadgers  extends PApplet {

    /**
     * array storing badger's dance show steps
     */
    private  static DanceStep[] badgersDanceSteps= new DanceStep[] {DanceStep.LEFT,
            DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN,
            DanceStep.LEFT, DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP};;// array storing badger's dance show steps
    /**
     * Tells wether the dance show is on. Intially
     */
    private boolean danceShowOn; //Tells wether dance show is on. Initially
    /**
     * array storing the postions of the dancing badgers at the start of the dance show
     */
    private static float[][] startDancePositions = new float[][] {{300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};// array storing the postions of the dancing badgers at the start of the dance show

    /**
     * backgound image
     */
    private static PImage backgroundImage; // backgound image
    /**
     * arraylist storing Badger objects
     */

    private static ArrayList<Badger> badgers; // arraylist storing Badger objects

    /**
     * arraylist storing Thing objects
     */
   private static ArrayList<Thing> things; // arraylist storing Thing objects

    /**
     * arraylist storing StarshipRobot objects
     */
    private static ArrayList<StarshipRobot> robots; // arraylist storing StarshipRobot objects

    /**
     * Generator of random numbers
     */
    private static Random randGen; // Generator of random numbers

    /**
     * Maximum number of Badger objects allowed in the basketball court
     */
    private static int badgersCountMax;

    //public DancingBadgers(){

    //}

    /**
     * Driver method to run this graphic application
     *
     * @param args list of input arguments if any
     */
    public static void main(String[] args) {

        PApplet.main("DancingBadgers");

    }

    /**
     * sets the size of the display window of this graphic application
     */
    @Override
 public void settings(){
        this.size(800, 600);

    }


    /**
     * Defines initial environment properties of this graphic application. This method initializes all
     * the data fields defined in this class.
     */

    @Override
    public void setup() {
        Utility.runApplication();
        // set processing data fields for Thing and StarshipRobot classes
        Thing.setProcessing(this);
        StarshipRobot.setProcessing(this);

        // data fields initialization
        backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
        badgers = new ArrayList<Badger>();
        randGen = new Random();
        badgersCountMax = 5;

        things = new ArrayList<Thing>();
        robots = new ArrayList<StarshipRobot>();

        // create 4 Things and add them to the things list
        things.add(new Thing(50, 50, "target.png"));
        things.add(new Thing(750, 550, "target.png"));
        things.add(new Thing(750, 50, "shoppingCounter.png"));
        things.add(new Thing(50, 550, "shoppingCounter.png"));

        // create 2 startship robots and add them to the robots list
        robots.add(new StarshipRobot(things.get(0), things.get(2), 3));
        robots.add(new StarshipRobot(things.get(1), things.get(3), 5));

        this.getSurface().setTitle("P5 Dancing Badgers"); // displays the title of the screen
        this.textAlign(3, 3); // sets the alignment of the text
        this.imageMode(3); // interprets the x and y position of an image to its center
        this.focused = true; // confirms that this screen is "focused", meaninig initialize badgers

        setStartDancePositions();
    }


    /**
     * Callback method that draws and updates the application display window. This method runs in an
     * infinite loop until the program exits.
     */
    @Override
    public  void draw() {
        // set the background color and draw the background image to the center of the screen
        Utility.background(Utility.color(255, 218, 185));
        Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

        // draw things, robots, and then badgers
        for (int i = 0; i < things.size(); i++)
            things.get(i).draw();

        for (int i = 0; i < robots.size(); i++)
            robots.get(i).draw();

        for (int i = 0; i < badgers.size(); i++)
            badgers.get(i).draw();


    }

    /**
     * Callback method called each time the user presses the mouse
     */
    public  void mousePressed() {
        // traverse the list of badgers abd start dragging the first clicked one
        for (int i = 0; i < badgers.size(); i++)
            if (badgers.get(i).isMouseOver()) {
                badgers.get(i).startDragging();
                break;
            }
    }

    /**
     * Callback method called each time the mouse is released
     */
    public  void mouseReleased() {
        // traverse the list of badgers and stop dragging any badger
        for (int i = 0; i < badgers.size(); i++)
            badgers.get(i).stopDragging();
    }


    public int badgersCount() {
        return badgers.size();
    }



    private void setStartDancePositions() {
        // start dance positions of the badgers are provided in the startDancePositions array
        float[][] startDancePositions = {{300, 150}, {200, 400}, {500, 50}, {650, 350}, {100, 350}};
        int numBadgers = Math.min(badgersCountMax, startDancePositions.length);
        for (int i = 0; i < numBadgers; i++) {
            Badger badger = new Badger(startDancePositions[i][0], startDancePositions[i][1]);
            badgers.add(badger);
        }
        for (int i = numBadgers; i < badgersCountMax; i++) {
            Badger badger = new Badger(startDancePositions[0][0], startDancePositions[0][1]);
            badgers.add(badger);
        }
    }



    /**
     * Callback method called each time the user presses a key
     */

    public  void keyPressed() {
        switch (Character.toUpperCase(Utility.key())) {
            case 'B':
                // add new badgers as long as the maximum number of badgers allowed to be
                // present in the field is not reached
                if (badgers.size() < badgersCountMax) {
                    badgers.add(new Badger(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height())));
                }
                break;
            case 'R':
                // delete the badger being pressed
                for (int i = 0; i < badgers.size(); i++) {
                    if (badgers.get(i).isMouseOver()) {
                        badgers.remove(i);
                        break;
                    }
                }
                break;
        }
    }
}
