

import java.awt.*;

	public class RodneysKidBrotherCheeeese {
		//VARIABLE DECLARATION SECTION
		//Here's where you state which variables you are going to use.

		public int xpos;                //the x position
		public int ypos;                //the y position
		public int width;
		public int height;
		public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
		public int dx;                    //the speed of the hero in the x direction
		public int dy;                    //the speed of the hero in the y direction
		public Rectangle rec;
		public Image pic;
		public int hits;


		// METHOD DEFINITION SECTION

		//This is a constructor that takes 3 parameters.  This allows us to specify the object's name and position when we build it.
		// if you put in a String, an int and an int the program will use this constructor instead of the one above.
		public RodneysKidBrotherCheeeese(int pXpos, int pYpos) {

			xpos = pXpos;
			ypos = pYpos;
			width = 50;
			height = 50;
			dx = 5;
			dy = -5;
			isAlive = true;
			hits = 0;
			rec = new Rectangle(xpos, ypos, width, height);


		} // constructor


		public RodneysKidBrotherCheeeese(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

			xpos = pXpos;
			ypos = pYpos;
			width = 75;
			height = 100;
			dx = dxParameter;
			dy = dyParameter;
			pic = picParameter;
			isAlive = true;
			hits = 0;
			rec = new Rectangle(xpos, ypos, width, height);


		} // constructor


		//The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
		public void move() {
			xpos = xpos + dx;
			ypos = ypos + dy;

			if (xpos > 1000 - width || xpos < 0) {
				dx = -dx;
			}

			if (ypos < 0 || ypos + height > 700) {
				dy = -dy;
			}

			rec = new Rectangle(xpos, ypos, width, height);

		}


	} //end of the RodneysKidBrotherCheeeese object class  definition
