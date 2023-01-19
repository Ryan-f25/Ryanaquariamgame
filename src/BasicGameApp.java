//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image marlinPic;
	public Image yellowfinPic;
	public Image background;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Fish marlin1;
	private Fish yellowfin1;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up
		background = Toolkit.getDefaultToolkit().getImage("Coral Reef.jpeg");
		marlinPic = Toolkit.getDefaultToolkit().getImage("Stripped Marlin.jpg"); //load the picture
		marlin1 = new Fish(10,175);
		marlin1.height = 15+marlin1.height;
		marlin1.width = 50+marlin1.width;
		marlin1.dx=3;

		yellowfinPic = Toolkit.getDefaultToolkit().getImage("Yellowfin Tuna.jpeg");
		yellowfin1 = new Fish(50,450);
		yellowfin1.height = 10+yellowfin1.height;
		yellowfin1.width = 35+yellowfin1.width;
		yellowfin1.dx=3;




	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		marlin1.move();
		marlin1.bounce();
		yellowfin1.move();
		yellowfin1.bounce();

	}
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
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


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(background, 0, 0, 1000, 700, null);

      //draw the image of the astronaut
		g.drawImage(marlinPic, marlin1.xpos, marlin1.ypos, marlin1.width, marlin1.height, null);
		g.draw(new Rectangle(marlin1.xpos, marlin1.ypos, marlin1.width, marlin1.height));
		g.drawImage(yellowfinPic, yellowfin1.xpos, yellowfin1.ypos, yellowfin1.width, yellowfin1.height, null);
		g.draw(new Rectangle(yellowfin1.xpos, yellowfin1.ypos, yellowfin1.width, yellowfin1.height));

		g.dispose();

		bufferStrategy.show();
	}
}