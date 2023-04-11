//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import javafx.scene.layout.Background;
import sun.awt.image.ToolkitImage;
import sun.plugin2.message.GetAppletMessage;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;
import java.util.Collection;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class OldSchool implements Runnable, KeyListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 650;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images
    public Image BackgroundPic;
    public Image TapeRecorderPic;
    public Image RodneysKidBrotherCheeeesePic;
    public Image FRANKTHETANKPic;

    //Declare the character objects
    public RodneysKidBrotherCheeeese RodneysKidBrotherCheeeese1;
    public TapeRecorder theTapeRecorder;
    public TapeRecorder[] tarray;
    public FRANKTHETANK user;

    public boolean start;

    public boolean GameOver;

    public boolean Win;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        OldSchool myApp = new OldSchool();   //creates a new instance of the game
        new Thread(myApp).start();               //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method - setup portion of the program
    // Initialize your variables and construct your program objects here.
    public OldSchool() {

        setUpGraphics();

        /***
         * Step 2 for keyboard control - addKeyListener(this) to the canvas
         */
        canvas.addKeyListener(this);

        //load images
        BackgroundPic = Toolkit.getDefaultToolkit().getImage("CollegeLectureHall.jpeg");
        TapeRecorderPic = Toolkit.getDefaultToolkit().getImage("TapeRecorder.png");
        RodneysKidBrotherCheeeesePic = Toolkit.getDefaultToolkit().getImage("Cheeeese.png");
        FRANKTHETANKPic = Toolkit.getDefaultToolkit().getImage("FRANKTHETANK.png");

        //create (construct) the objects needed for the game
        RodneysKidBrotherCheeeese1 = new RodneysKidBrotherCheeeese(200, 300, 4, 4, RodneysKidBrotherCheeeesePic);
     //   theTapeRecorder = new TapeRecorder(400, 300, 3, -4, TapeRecorderPic);
        user = new FRANKTHETANK(500, 0, 0, 0, FRANKTHETANKPic);

        tarray= new TapeRecorder[3];
        for(int x=0; x< tarray.length; x++){
            tarray[x] = new TapeRecorder((int)(Math.random()*800), (int)(Math.random()*600), 3, 3, TapeRecorderPic);

        }

    } // OldSchool()


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void moveThings() {
       // CollegeLectureHall.move();
        RodneysKidBrotherCheeeese1.move();
       // theTapeRecorder.move();
        user.move();
        for(int x=0; x< tarray.length; x++){
            tarray[x].move();
        }
    }
    /*
    public void collision() {
        if (astro.rec.intersects(Thor.rec)) {
            astro.isAlive = false;
            System.out.println("astro is dead");
        }

        if (Lucky.rec.intersects(Thor.rec)) {
            Lucky.isAlive = false;
            System.out.println("Lucky is dead");
        }

        if (astro.rec.intersects(TommyShelby.rec)) {
            astro.isAlive = false;
            System.out.println("astro is dead");
        }

        if (Lucky.rec.intersects(TommyShelby.rec)) {
            Lucky.isAlive = false;
            System.out.println("Lucky is dead");
        }
    }*/

    public void checkIntersections() {
        for (int i=0; i< tarray.length; i++){
            if(tarray[i]!= null){
                if(user.rec.intersects(tarray[i].rec) && tarray[i].isAlive == true){
                    tarray[i].isAlive = false;
                    user.score= user.score+1;
                }
            }
        }
        if(user.score==3){
            GameOver=true;
            Win=true;
        }
        if(user.rec.intersects(RodneysKidBrotherCheeeese1.rec)){
            user.isAlive = false;
            user.xpos = -1000;
            user.ypos = -1000;
            GameOver=true;
        }
    }

    public void run() {
        while (true) {
            moveThings();           //move all the game objects
            checkIntersections();   // check character crashes
            render();               // paint the graphics
            pause(20);         // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if(start==false){
            //draw start screen
            g.drawString("Start", 500,350);
        } else if (GameOver==false) {

            //draw characters to the screen
            g.drawImage(BackgroundPic, 0, 0, WIDTH, HEIGHT, null);

            for (int x = 0; x < tarray.length; x++) {
                if (tarray[x].isAlive) {
                    g.drawImage(tarray[x].pic, tarray[x].xpos, tarray[x].ypos, tarray[x].width, tarray[x].height, null);
                }


            }

            g.drawImage(RodneysKidBrotherCheeeese1.pic, RodneysKidBrotherCheeeese1.xpos, RodneysKidBrotherCheeeese1.ypos, RodneysKidBrotherCheeeese1.width, RodneysKidBrotherCheeeese1.height, null);
            //  g.drawImage(theTapeRecorder.pic, theTapeRecorder.xpos, theTapeRecorder.ypos, theTapeRecorder.width, theTapeRecorder.height, null);
            if (user.isAlive) {
                g.drawImage(user.pic, user.xpos, user.ypos, user.width, user.height, null);
            }

            g.drawString("score: " + user.score, 200, 100);

        } else if(Win==true) {//GameOver
            //GameOver You Win screen
            g.drawString("You Won!", 500, 350);
        } else {
            g.drawString("You Lost", 500, 350);
        }
        g.dispose();
        bufferStrategy.show();
    }

    /***
     * Step 3 for keyboard control - add required methods
     * You need to have all 3 even if you aren't going to use them all
     */
    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 68) { // d
            user.right = true;
        }
        if (keyCode == 65) { // a
            user.left = true;
        }

        if (keyCode == 83) { // s
            user.down = true;
        }
        if (keyCode == 87) { // w
            user.up = true;
        }

        if (keyCode == 10){
            start=true;
        }
    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 68) { // d
            user.right = false;
        }
        if (keyCode == 65) { // a
            user.left = false;
        }
        if (keyCode == 83) { // s
            user.down = false;
        }
        if (keyCode == 87) { // w
            user.up = false;
        }

    }//keyReleased()

    public void keyTyped(KeyEvent event) {
        // handles a press of a character key (any key that can be printed but not keys like SHIFT)
        // we won't be using this method, but it still needs to be in your program
    }//keyTyped()


    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("OldSchool");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

}//class
