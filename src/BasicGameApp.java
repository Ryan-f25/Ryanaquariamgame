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
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section
public class BasicGameApp implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    //Step one for arrays
    //

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
    public boolean isAlive;
    public boolean isCrashing;
    public Rectangle rec;
    public Image marlinPic;
    public Image marlinPic2;
    public Image yellowfinPic1;
    public Image yellowfinPic2;
    public Image orcaPic1;
    public Image orcaPic2;
    public Image barracudaPic1;
    public Image barracudaPic2;
    public Image background;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    private Fish marlin1;
    private Fish marlin2;
    private Fish yellowfin1;

    private Fish yellowfin2;
    public Image fishinghookPic;
    private Fish fishinghook;
    private Fish orca1;
    private Fish orca2;
    private Fish barracuda1;
    private Fish barracuda2;
    public Image dolphinPic;
    private Fish[] dolphin; // I declared an array of dolphin
    public boolean Startgame;
    public Image StartgamePic;
    public Image EndgamePic;
    public boolean mouseOnscreen;
	public boolean dolphinDied;


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

        dolphinPic = Toolkit.getDefaultToolkit().getImage("dolphin.jpg");
		dolphinDied = false;
        dolphin = new Fish[5];//construct the array to hold the dolphin, is it empty
        for (int x = 0; x < dolphin.length; x++) {
            dolphin[x] = new Fish((int) (Math.random() * 200) + 1, (int) (Math.random() * 200) + 1);
            //fill each slot in the "bookshelf"
            // then after this go through everywhere I use dolphin[] and change a number to x in the []
        }


        //variable and objects
        //create (construct) the objects needed for the game and load up
        background = Toolkit.getDefaultToolkit().getImage("Coral Reef.jpeg");
        StartgamePic = Toolkit.getDefaultToolkit().getImage("FortnitesavescreenX.jpeg");
        EndgamePic = Toolkit.getDefaultToolkit().getImage("Fortniteendscreen.png");

        barracudaPic1 = Toolkit.getDefaultToolkit().getImage("Barracuda left.jpg");
        barracuda1 = new Fish(10, 75);
        barracuda1.height = 10 + barracuda1.height;
        barracuda1.width = 50 + barracuda1.width;
        barracuda1.dx = 9;

        barracudaPic2 = Toolkit.getDefaultToolkit().getImage("Barracuda left.jpg");
        barracuda2 = new Fish(10, 75);
        barracuda2.height = 10 + barracuda2.height;
        barracuda2.width = 50 + barracuda2.width;
        barracuda2.dx = 9;


        orcaPic1 = Toolkit.getDefaultToolkit().getImage("Orca left.jpg");
        orca1 = new Fish(10, 275);
        orca1.height = 75 + orca1.height;
        orca1.width = 100 + orca1.width;
        orca1.dx = 2;
        orca1.dy = 2;

        orcaPic2 = Toolkit.getDefaultToolkit().getImage("Orca right.jpeg");
        orca2 = new Fish(10, 275);
        orca2.height = 75 + orca2.height;
        orca2.width = 100 + orca2.width;
        orca2.dx = 2;
        orca2.dy = 2;

        fishinghookPic = Toolkit.getDefaultToolkit().getImage("Fishing hook 2.png");
        fishinghook = new Fish(450, 450);
        fishinghook.height = 15 + fishinghook.height;
        fishinghook.width = fishinghook.width - 20;
        fishinghook.dy = -3;
        fishinghook.dx = 0;


        marlinPic = Toolkit.getDefaultToolkit().getImage("Stripped Marlin.png"); //load the picture
        marlin1 = new Fish(10, 175);
        marlin1.height = 15 + marlin1.height;
        marlin1.width = 50 + marlin1.width;
        //marlin1.dx = 3;
        //marlin1.dy =4;

        marlinPic2 = Toolkit.getDefaultToolkit().getImage("Stripped Marlin 2.jpg"); //load the picture
        marlin2 = new Fish(10, 175);
        marlin2.height = 15 + marlin2.height;
        marlin2.width = 50 + marlin2.width;
        //marlin2.dx = 3;
        //marlin2.dy = 4;

        yellowfinPic1 = Toolkit.getDefaultToolkit().getImage("Yellowfin Tuna 2.jpeg");
        yellowfin1 = new Fish(50, 450);
        yellowfin1.height = 10 + yellowfin1.height;
        yellowfin1.width = 35 + yellowfin1.width;
        yellowfin1.dx = 5;
        yellowfin1.dy = 3;

        yellowfinPic2 = yellowfinPic2 = Toolkit.getDefaultToolkit().getImage("Yellowfin Tuna.jpeg");
        yellowfin2 = new Fish(50, 450);
        yellowfin2.height = 10 + yellowfin2.height;
        yellowfin2.width = 35 + yellowfin2.width;
        yellowfin2.dx = 5;
        yellowfin2.dy = 3;


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


    public void moveThings() {
        //calls the move( ) code in the objects\
        if (mouseOnscreen == true) {

            marlin1.move();
            marlin1.bounce();
            marlin2.move();
            marlin2.bounce();
            yellowfin1.move();
            yellowfin1.bounce();
            yellowfin2.move();
            yellowfin2.bounce();
            fishinghook.move();
            fishinghook.wrap();
            orca1.move();
            orca1.bounce();
            orca2.move();
            orca2.bounce();
            barracuda1.move();
            barracuda1.bounce();
            barracuda2.move();
            barracuda2.bounce();
            marlincrash();
            yellowfincrash();
            orcacrash();
            barracudacrash();
            marlinfishbounce();
            yellowfinbounce();
            barracudabounce();
            dolphincrash();

            for (int x = 0; x < dolphin.length; x++) {
                dolphin[x].move();
            }
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
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
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);

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

    public void dolphincrash() {
        for (int x = 0; x < dolphin.length; x++) {
            if (dolphin[x].rec.intersects(fishinghook.rec)) {
                dolphin[x].isAlive = false;
            }
        }
    }


    public void marlinfishbounce() {
        if (marlin1.rec.intersects(yellowfin1.rec) && (marlin2.rec.intersects(yellowfin2.rec)) && marlin1.isCrashing == false && marlin2.isCrashing == false) {
            marlin1.isCrashing = true;
            marlin2.isCrashing = true;
            marlin1.dy = -marlin1.dy;
            marlin2.dy = -marlin2.dy;
            yellowfin1.dx = -yellowfin1.dx;
            yellowfin2.dx = -yellowfin2.dx;

        }
        if (!marlin1.rec.intersects(yellowfin1.rec)) {
            marlin1.isCrashing = false;
        }

        if (!marlin2.rec.intersects(yellowfin2.rec)) {
            marlin2.isCrashing = false;
        }
    }

    public void yellowfinbounce() {
        if (yellowfin1.rec.intersects(orca1.rec) && (yellowfin2.rec.intersects(orca2.rec)) && yellowfin1.isCrashing == false && yellowfin2.isCrashing == false) {
            yellowfin1.isCrashing = true;
            yellowfin2.isCrashing = true;
            yellowfin1.dx = yellowfin1.dx;
            yellowfin1.dy = -yellowfin1.dy;
            yellowfin2.dx = yellowfin2.dx;
            yellowfin2.dy = -yellowfin2.dy;
            orca1.dx = -orca1.dx;
            orca1.dy = -orca1.dy;
            orca2.dx = -orca2.dx;
            orca2.dy = -orca2.dy;
        }
        if (!yellowfin1.rec.intersects(orca1.rec)) {
            yellowfin1.isCrashing = false;
        }

        if (!yellowfin2.rec.intersects(orca2.rec)) {
            yellowfin2.isCrashing = false;
        }
    }

    public void barracudabounce() {
        if (barracuda1.rec.intersects(marlin1.rec) && (barracuda2.rec.intersects(marlin2.rec)) && barracuda1.isCrashing == false && barracuda2.isCrashing == false) {
            barracuda1.isCrashing = true;
            barracuda2.isCrashing = true;
            barracuda1.dx = -barracuda1.dx;
            barracuda1.dy = -barracuda1.dy;
            barracuda2.dx = -barracuda2.dx;
            barracuda2.dy = -barracuda2.dy;
            marlin1.dx = -marlin1.dx;
            marlin1.dy = -marlin1.dy;
            marlin2.dx = -marlin2.dx;
            marlin2.dy = -marlin2.dy;

        }
        if (!barracuda1.rec.intersects(marlin1.rec)) {
            barracuda1.isCrashing = false;
        }

        if (!barracuda2.rec.intersects(marlin1.rec)) {
            barracuda2.isCrashing = false;
        }
    }

    // We talk about why this wasn't working, and we couldn't figure it out
    // If I decided to keep it the rectangle would have detached from the image
//	public void orcabounce()
//	{
//		if(orca1.rec.intersects(marlin1.rec) && (orca2.rec.intersects(marlin2.rec)) && orca1.isCrashing == false && orca2.isCrashing == false){
//			orca1.isCrashing = true;
//			orca2.isCrashing = true;
//			orca1.dx=orca1.dx;
//			orca1.dy=-orca1.dy;
//			orca2.dx=orca2.dx;
//			orca2.dy=-orca2.dy;
//			marlin1.dx = -marlin1.dy;
//			marlin1.dy=-marlin1.dy;
//			marlin2.dx = -marlin2.dx;
//			marlin2.dy=-marlin2.dy;
//		}
//		if(!orca1.rec.intersects(marlin1.rec)){
//			orca1.isCrashing = false;
//		}
//		if(!orca2.rec.intersects(marlin2.rec)){
//			orca2.isCrashing = false;
//		}
//	}
    public void marlincrash() {
        if (marlin1.rec.intersects(fishinghook.rec) && (marlin2.rec.intersects(fishinghook.rec))) {
            marlin1.isAlive = false;
            marlin2.isAlive = false;
        }
    }

    public void yellowfincrash() {
        if (yellowfin1.rec.intersects(fishinghook.rec) && (yellowfin2.rec.intersects(fishinghook.rec))) {
            yellowfin1.isAlive = false;
            yellowfin2.isAlive = false;
        }
    }

    public void orcacrash() {
        if (orca1.rec.intersects(fishinghook.rec) && (orca2.rec.intersects(fishinghook.rec))) {
            orca1.isAlive = false;
            orca2.isAlive = false;
        }
    }

    public void barracudacrash() {
        if (barracuda1.rec.intersects(fishinghook.rec) && (barracuda2.rec.intersects(fishinghook.rec))) {
            barracuda1.isAlive = false;
            barracuda2.isAlive = false;
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        //g.drawImage(background, 0, 0, 1000, 700, null);

        if (Startgame == false) {
            g.drawImage(StartgamePic, 0, 0, 1000, 700, null);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
			g.drawString("If any fish touches the fishing hook they disappear", 10, 382);
			g.drawString("If the dolphin touches the fishing hook you lose the game", 10, 410);
            g.drawString("Move the dolphin with wasd",10, 440);
            g.drawString("Move the mouse to move the marlin",10 , 470);
            g.drawString("Press down on the mouse to increase the height and width of the fishing hook and yellowfin", 10, 500);
            g.drawString("Move the mouse off the screen to pause the game", 10, 530);

        }
        if (Startgame == true) {

			if (dolphinDied == true){
				g.drawImage(EndgamePic, 0, 0, 1000, 700, null);
			}
			else {

				g.drawImage(background, 0, 0, 1000, 700, null);


				g.drawImage(fishinghookPic, fishinghook.xpos, fishinghook.ypos, fishinghook.width, fishinghook.height, null);
				g.draw(new Rectangle(fishinghook.xpos, fishinghook.ypos, fishinghook.width, fishinghook.height));

				for (int x = 0; x < dolphin.length; x++) {
					if (dolphin[x].isAlive == true) {
						g.drawImage(dolphinPic, dolphin[x].xpos, dolphin[x].ypos, dolphin[x].width, dolphin[x].height, null);
					} else {
						dolphinDied =true;
					}
				}


				//my barracuda does not flip around when it bounces. The code is the same as the other fish
				//but it does not flip horizontally
				if (barracuda1.isAlive == true && barracuda2.isAlive == true) {
					g.draw(new Rectangle(barracuda1.xpos, barracuda1.ypos, barracuda1.width, barracuda1.height));
					if (barracuda2.dx < 0) {
						g.drawImage(barracudaPic1, barracuda1.xpos, barracuda1.ypos, barracuda1.width, barracuda1.height, null);
					} else {
						g.drawImage(barracudaPic2, barracuda2.xpos, barracuda2.ypos, barracuda2.width, barracuda2.height, null);
					}
				}

				if (orca1.isAlive == true && orca2.isAlive == true) {
					g.draw(new Rectangle(orca1.xpos, orca1.ypos, orca1.width, orca1.height));
					if (orca1.dx < 0) {
						g.drawImage(orcaPic1, orca1.xpos, orca1.ypos, orca1.width, orca1.height, null);
					} else {
						g.drawImage(orcaPic2, orca2.xpos, orca2.ypos, orca2.width, orca2.height, null);
					}
				}

				if (marlin1.isAlive == true && marlin2.isAlive == true) {
					g.draw(new Rectangle(marlin1.xpos, marlin1.ypos, marlin1.width, marlin1.height));
					if (marlin1.dx < 0) {
						g.drawImage(marlinPic, marlin1.xpos, marlin1.ypos, marlin1.width, marlin1.height, null);
					} else {
						g.drawImage(marlinPic2, marlin2.xpos, marlin2.ypos, marlin2.width, marlin2.height, null);
					}

				}
				if (yellowfin1.isAlive == true && yellowfin2.isAlive == true) {
					g.draw(new Rectangle(yellowfin1.xpos, yellowfin1.ypos, yellowfin1.width, yellowfin1.height));
					if (yellowfin1.dx < 0) {
						g.drawImage(yellowfinPic1, yellowfin1.xpos, yellowfin1.ypos, yellowfin1.width, yellowfin1.height, null);
					} else {
						g.drawImage(yellowfinPic2, yellowfin2.xpos, yellowfin2.ypos, yellowfin2.width, yellowfin2.height, null);
					}
				}
			}
        }
        g.dispose();

        bufferStrategy.show();

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int Code = e.getKeyCode();
        System.out.println(Code);
        int c = Math.abs(Code);

        if (Code == 88) {
            Startgame = true;
        }

        if (Code == 87) {
            for (int x = 0; x < dolphin.length; x++) {
                dolphin[x].dy = -6;
            }
        }

        if (Code == 83) {
            for (int x = 0; x < dolphin.length; x++) {
                dolphin[x].dy = 6;
            }
        }

        if (Code == 65) {
            for (int x = 0; x < dolphin.length; x++) {
                dolphin[x].dx = -6;
            }
        }

        if (Code == 68) {
            for (int x = 0; x < dolphin.length; x++) {
                dolphin[x].dx = 6;
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int Code = e.getKeyCode();
        System.out.println(Code);
        int c = Math.abs(Code);

        for (int x = 0; x < dolphin.length; x++) {
            dolphin[x].dy = 0;
            dolphin[x].dx = 0;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX());
        marlin1.xpos = e.getX();
        marlin1.ypos = e.getY();
        marlin2.xpos = e.getX();
        marlin2.ypos = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        yellowfin1.width = yellowfin1.width + 4;
        yellowfin2.width = yellowfin2.width + 4;
        fishinghook.height = fishinghook.height + 4;
        fishinghook.width = fishinghook.width + 4;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        marlin1.dx = 5;
        marlin2.dx = 5;
        marlin1.dy = 5;
        marlin2.dy = 5;
        marlin1.xpos = -marlin1.xpos;
        marlin1.ypos = -marlin1.ypos;
        marlin2.xpos = -marlin2.xpos;
        marlin2.ypos = -marlin2.ypos;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseOnscreen = true;

    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseOnscreen = false;

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        marlin1.xpos = e.getX();
        marlin1.ypos = e.getY();
        marlin2.xpos = e.getX();
        marlin2.ypos = e.getY();
    }
}
