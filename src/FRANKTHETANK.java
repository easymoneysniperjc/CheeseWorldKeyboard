
import java.awt.*;

public class FRANKTHETANK {


    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public Rectangle rec;
    public Image pic;

    //movement booleans
    public boolean right;
    public boolean left;
    public boolean down;
    public boolean up;

    public int score;


    public FRANKTHETANK(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 100;
        height = 200;
        dx = dxParameter;
        dy = dyParameter;
        pic = picParameter;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);

    } // constructor

    //move( ) method for a keyboard controlled character
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if(right == true){
           dx = 2;
        } else if (left == true) {
            dx = -2;
        } else { // (right == false && left == false)
            dx = 0;
        }

        if(down == true){
            dy = 2;
        } else if (up == true) {
            dy = -2;
        } else {
            dy = 0;
        }

        if(xpos>1000-width){ // right
            xpos = 1000-width;
        }
        if(xpos < 0) { // left
            xpos = 0;
        }
        if(ypos>650-height){ // down
            ypos = 650-height;
        }
        if(ypos < 0) { // up
            ypos = 0;
        }


        //always put this after you've done all the changing of the xpos and ypos values
        rec = new Rectangle(xpos, ypos, width, height);

    }

    public void move2() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (ypos < 250) {
            dy = dy + 1;
        }
        if (ypos > 250) {
            ypos = 250;
        }

        if(xpos>1000-width){ // right
            xpos = 1000-width;
        }
        if(xpos < 0) { // left
            xpos = 0;
        }
        if(ypos>650-height){ // down
            ypos = 650-height;
        }
        if(ypos < 0) { // up
            ypos = 0;
        }
    }

}
