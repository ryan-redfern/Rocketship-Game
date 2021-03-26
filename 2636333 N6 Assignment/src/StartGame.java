import java.awt.*;

import java.util.Random;
import java.awt.event.MouseEvent;


import javax.swing.JOptionPane;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;



import game2D.*;
@SuppressWarnings("serial")
// 2636333
public class StartGame extends GameCore {
	static int screenWidth = 700;
	int lastKey;
	static int screenHeight = 400;
	Animation rocketShip;
	Animation enemyship;
	Sprite ship = null;
	Sprite enemy_0 = null;
	Sprite enemy_1 = null;
	Sprite enemy_2 = null;
	Sprite enemy_3 = null;
	Sprite enemy_4 = null;
	Sprite enemy_5 = null;
	Sprite enemy_6 = null;
	Sprite enemy_7 = null;
	Sprite enemy_8 = null;
	Sprite enemy_9 = null;
	Sprite enemy_10 = null;
	Sprite enemy_11 = null;
	boolean paused = false;
	int mapNumber =1;
	Sound backgroundMusic = new Sound("sounds/backgroundMusic.wav");
	TileMap tmap = new TileMap();
	int total;
	int speed = 0;
	ArrayList<Sprite> asteroids = new ArrayList<Sprite>();
	public static void main(String[] args) {
		StartGame newGame = new StartGame(); //creates new object called newGame of class startGame
		newGame.init();
		newGame.run(false, screenWidth,screenHeight);
	}
	public void init()
	{
		if (mapNumber == 2){ //if map is number two
			
			tmap.loadMap("maps", "MapTwo.txt"); //loads the second map
			paused = true; //pauses the game
		}
		else {
		paused=true;	
		tmap.loadMap("maps", "MapOne.txt");
		}
		setSize(screenWidth, screenHeight); //sets size of the screen
		setVisible(true); //sets screen visible
		speed = 0; //speed equals 0
		//creates all the animations of the rocket ship and the enemy ship
		rocketShip = new Animation();
		rocketShip.loadAnimationFromSheet("images/ship.png", 1, 4, 60);
		ship = new Sprite(rocketShip);
		enemyship = new Animation();
		enemyship.addFrame(loadImage("images/enemy.png"), 50);
		enemy_0 = new Sprite(enemyship);
		enemy_1 = new Sprite(enemyship);
		enemy_2 = new Sprite(enemyship);
		enemy_3 = new Sprite(enemyship);
		enemy_4 = new Sprite(enemyship);
		enemy_5 = new Sprite(enemyship);
		enemy_6 = new Sprite(enemyship);
		enemy_7 = new Sprite(enemyship);
		enemy_8 = new Sprite(enemyship);
		if (mapNumber ==2) {
			enemy_9 = new Sprite(enemyship);
			enemy_10 = new Sprite(enemyship);
			enemy_11 = new Sprite(enemyship);
		
		}
		initialiseGame();
	}
    public void initialiseGame()
    {
    	//sets the location and the velocity of the all the ships
    	total = 0;
        ship.setX(tmap.getPixelWidth()-7400);
        ship.setY(tmap.getPixelHeight()-200);
        ship.setVelocityX(0);
        ship.setVelocityY(0);
        ship.show();
        enemy_0.setX(350);
        enemy_0.setY(330);
        enemy_1.setX(700);
        enemy_1.setY(330);
        enemy_2.setX(100);
        enemy_2.setY(330);
        enemy_3.setX(1350);
        enemy_3.setY(330);
        enemy_0.setVelocity((float) ((Math.random() * (0.10 - -0.10)) + -0.10),(float) ((Math.random() * (0.10 - -0.10)) + -0.10));
        enemy_1.setVelocity((float) ((Math.random() * (0.10 - -0.10)) + -0.10),(float) ((Math.random() * (0.10 - -0.10)) + -0.10));
        enemy_2.setVelocity((float) ((Math.random() * (0.10 - -0.10)) + -0.10),(float) ((Math.random() * (0.10 - -0.10)) + -0.10));
        enemy_3.setVelocity((float) ((Math.random() * (0.10 - -0.10)) + -0.10),(float) ((Math.random() * (0.10 - -0.10)) + -0.10));
        enemy_4.setX(1800);
        enemy_4.setY(330);
        enemy_5.setX(2100);
        enemy_5.setY(330);
        enemy_6.setX(2300);
        enemy_6.setY(330);
        enemy_7.setX(2800);
        enemy_7.setY(330);
        enemy_8.setX(3200);
        enemy_8.setY(330); 
        enemy_4.setVelocity((float) ((Math.random() * (0.10 - -0.10)) + -0.10),(float) ((Math.random() * (0.10 - -0.10)) + -0.10));
        enemy_5.setVelocity((float) ((Math.random() * (0.10 - -0.10)) + -0.10),(float) ((Math.random() * (0.10 - -0.10)) + -0.10));
        enemy_6.setVelocity((float) ((Math.random() * (0.10 - -0.10)) + -0.10),(float) ((Math.random() * (0.10 - -0.10)) + -0.10));
        enemy_7.setVelocity((float) ((Math.random() * (0.10 - -0.10)) + -0.10),(float) ((Math.random() * (0.10 - -0.10)) + -0.10));
        enemy_8.setVelocity((float) ((Math.random() * (0.10 - -0.10)) + -0.10),(float) ((Math.random() * (0.10 - -0.10)) + -0.10));
        if (mapNumber ==2) {
			enemy_9.setX(3500);
			enemy_9.setY(330);
			enemy_10.setX(3500);
			enemy_10.setY(330);
			enemy_11.setX(4200);
			enemy_11.setY(330);
		}
    }
    
    
    public void draw(Graphics2D g)
    {    	
        int xo = 0;
        int yo = 0;
        if (ship.getX() >= (screenWidth / 4)) { // if the ship has gone past the screenwidth divided by 4
            xo = -((int)ship.getX()) + (screenWidth / 4); //sets the xo offset to the negative value of the ship and the screenwidth
          } 
         
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        Animation asteroid = new Animation();
		asteroid.addFrame(loadImage("images/asteroid.png"), 50);
        Sprite s;
        int level_1 = 200;
        int boundary = getHeight(); //gets the height of the screen
        Random RANDOM = new Random();
        int c = RANDOM.nextInt(boundary); //gets a new integer based on the height of the screen
        if (mapNumber == 1) {
        if (RANDOM.nextInt(level_1) < 1) {
        for (int d=0; d<1; d++)
        {
        	//creates a new asteroid with a fixed velocity and adds it to the array
        	s = new Sprite(asteroid);
        	s.setX(7500);
        	s.setY(55 + (int)(c));
        	s.setVelocityX(-0.25);
        	s.show();
        	asteroids.add(s);
        }
        }
        }
        if (mapNumber == 2) {
            if (RANDOM.nextInt(level_1) < 5) {
            for (int d=0; d<1; d++)
            {
            	s = new Sprite(asteroid);
            	s.setX(7500);
            	s.setY((int)(c));
            	s.setVelocityX(-0.5);
            	s.show();
            	asteroids.add(s);
            }
            }
            }
        //changes the velocity of the enemy ships based off if the next int is less than 1
        if (RANDOM.nextInt(400) < 1) {
        	try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
        	enemy_0.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
        	enemy_1.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
        	enemy_2.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
        	enemy_3.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
        	enemy_4.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
        	enemy_5.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
        	enemy_6.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
        	enemy_7.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
        	enemy_8.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
        	if (mapNumber ==2) {
        		enemy_9.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
            	enemy_10.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
            	enemy_11.setVelocity((float) ((Math.random() * (0.25f - -0.25f)) + -0.25f),(float) ((Math.random() * (0.25f - -0.25f)) + -0.25f));
        		
        	}
        	
        	
        }
        for (Sprite b: asteroids) {
        	b.setOffsets(xo,yo);
        	b.draw(g);
        }
        // Apply offsets to player and draw 
        ship.setOffsets(xo, yo);
        ship.drawTransformed(g); 
        enemy_0.setOffsets(xo,yo);
        enemy_0.draw(g);
        enemy_1.setOffsets(xo,yo);
        enemy_1.draw(g);
        enemy_2.setOffsets(xo,yo);
        enemy_2.draw(g);
        enemy_3.setOffsets(xo, yo);
        enemy_3.draw(g);
        enemy_4.setOffsets(xo, yo);
        enemy_4.draw(g);
        enemy_5.setOffsets(xo, yo);
        enemy_5.draw(g);
        enemy_6.setOffsets(xo, yo);
        enemy_6.draw(g);
        enemy_7.setOffsets(xo, yo);
        enemy_7.draw(g) ;
        enemy_8.setOffsets(xo, yo);
        enemy_8.draw(g);
        if (mapNumber ==2) {
        	enemy_9.setOffsets(xo, yo);
            enemy_9.draw(g);
            enemy_10.setOffsets(xo, yo);
            enemy_10.draw(g) ;
            enemy_11.setOffsets(xo, yo);
            enemy_11.draw(g);
        
        }
        // Apply offsets to tile map and draw  it
        tmap.draw(g, xo, yo);
        // Show amount of stars, the map level and the amount of speed boosts remaining
        String stars = String.format("STARS : %d / 5", total);
        g.setColor(Color.white);
        g.drawString(stars, getWidth() - 90, 50);
        String map_level = String.format("MAP LEVEL :  %d", mapNumber);
        g.setColor(Color.white);
        g.drawString(map_level, getWidth() - 690, 50);
        if (mapNumber ==1) {
        String boosts_remaining = String.format("Speed Boosts :  %d / 30", speed);
        g.setColor(Color.white);
        g.drawString(boosts_remaining, getWidth() - 450, 50);
        }
        if (mapNumber ==2) {
            String boosts_remaining = String.format("Speed Boosts :  %d / 20", speed);
            g.setColor(Color.white);
            g.drawString(boosts_remaining, getWidth() - 450, 50);
            }
    }
    public void update(long elapsed)
    {
    	if (paused) return; //if no longer paused
    	if (backgroundMusic.isAlive() == false) { //if background music stopped
		backgroundMusic.start();
    	}
    	//update animation and positions of the sprite's based off time
        ship.update(elapsed);
        enemy_0.update(elapsed);
        enemy_1.update(elapsed);
        enemy_2.update(elapsed);
        enemy_3.update(elapsed);
        enemy_4.update(elapsed);
        enemy_5.update(elapsed);
        enemy_6.update(elapsed);
        enemy_7.update(elapsed);
        enemy_8.update(elapsed);
        if (mapNumber ==2) {
        	enemy_9.update(elapsed);
            enemy_10.update(elapsed);
            enemy_11.update(elapsed);
        	
        }
        for (Sprite s: asteroids) { //for each asteroid 
        	if (boundingBoxCollision(ship, s)){ //check if any collision with the rocket ship
            	System.out.println("gameover");
            	System.exit(0);
            }
       		s.update(elapsed);
        }
        if (total == 5) { //if the user collected 5 stars
        	mapNumber++; //increase the map number
        	if (mapNumber == 3) {
        		msgbox("Game Complete, Congratulations!");
        		System.exit(0);
        	}
        	init();
        
        }

        //if any collision of the rocket ship with an enemy ship
        if (boundingBoxCollision(ship, enemy_0)){
        	System.out.println("gameover");
        	System.exit(0);
        }
        if (boundingBoxCollision(ship, enemy_1)){
        	System.out.println("gameover");
        	System.exit(0);
        }
        if (boundingBoxCollision(ship, enemy_2)){
        	System.out.println("gameover");
        	System.exit(0);
        }
        if (boundingBoxCollision(ship, enemy_3)){
        	System.out.println("gameover");
        	System.exit(0);
        }
        if (boundingBoxCollision(ship, enemy_4)){
        	System.out.println("gameover");
        	System.exit(0);
        }
        if (boundingBoxCollision(ship, enemy_5)){
        	System.out.println("gameover");
        	System.exit(0);
        }
        if (boundingBoxCollision(ship, enemy_6)){
        	System.out.println("gameover");
        	System.exit(0);
        }
        if (boundingBoxCollision(ship, enemy_7)){
        	System.out.println("gameover");
        	System.exit(0);
        }
        if (boundingBoxCollision(ship, enemy_8)){
        	System.out.println("gameover");
        	System.exit(0);
        }
        
        if (mapNumber ==2) {
        	if (boundingBoxCollision(ship, enemy_9)){
            	System.out.println("gameover");
            	System.exit(0);
            }
            if (boundingBoxCollision(ship, enemy_10)){
            	System.out.println("gameover");
            	System.exit(0);
            }
            if (boundingBoxCollision(ship, enemy_11)){
            	System.out.println("gameover");
            	System.exit(0);
            
        
        }
        }
        //check collision of enemy on the tmap (stops the enemy from leaving the map)
        checkTileCollision(enemy_0,tmap);
        checkTileCollision(enemy_1,tmap);
        checkTileCollision(enemy_2,tmap);
        checkTileCollision(enemy_3,tmap);
        checkTileCollision(enemy_4,tmap);
        checkTileCollision(enemy_5,tmap);
        checkTileCollision(enemy_6,tmap);
        checkTileCollision(enemy_7,tmap);
        checkTileCollision(enemy_8,tmap);
        if (mapNumber ==2) {
        	checkTileCollision(enemy_9,tmap);
            checkTileCollision(enemy_10,tmap);
            checkTileCollision(enemy_11,tmap);
        	
        	
        }
        
      //check collision of rocket ship on the tmap (stops the rocket ship from leaving the map)
        checkTileCollision(ship, tmap);
    }

    public void checkTileCollision(Sprite s, TileMap tmap)
    {
    	// Take a note of a sprite's current position
    	float sx = s.getX();
    	
    	float sy = s.getY();
    	
    	// Find out how wide and how tall a tile is
    	float tileWidth = tmap.getTileWidth();
    	float tileHeight = tmap.getTileHeight();
    	//Collision Top left
    	int	xtile = (int)(sx / tileWidth);
    	int ytile = (int)(sy / tileHeight);
    	
    	
    	char tl = tmap.getTileChar(xtile, ytile);
    	//Collision Bottom Left
    	xtile = (int)(sx / tileWidth);
    	ytile = (int)((sy + s.getHeight())/ tileHeight); 
    	
    	char bl = tmap.getTileChar(xtile, ytile); 
    	//Collision Top Right
    	xtile = (int)((sx + s.getWidth()) / tileWidth);
    	ytile = (int)(sy / tileHeight);
    	
    	char tr = tmap.getTileChar(xtile, ytile);
    	
    	//Collision Bottom Right
    	xtile = (int)((sx + s.getWidth()) / tileWidth);
    	ytile = (int)((sy + s.getHeight()) / tileHeight);
    	
    	char br = tmap.getTileChar(xtile, ytile);
    	//if any collision
    	if (tl != '.' || bl != '.' || tr != '.' || br != '.') {
    		
    		if (s.equals(ship) && (tl == 's' || bl == 's' || tr == 's' || br == 's')){
    			//sets the tile as a .
    			xtile = (int)(sx / tileWidth);
        	    ytile = (int)(sy / tileHeight);
        		tmap.setTileChar('.',xtile,ytile);
        		xtile = (int)(sx / tileWidth);
            	ytile = (int)((sy + s.getHeight())/ tileHeight); 
            	tmap.setTileChar('.',xtile,ytile);
            	xtile = (int)((sx + s.getWidth()) / tileWidth);
            	ytile = (int)(sy / tileHeight);
            	tmap.setTileChar('.',xtile,ytile);
            	xtile = (int)((sx + s.getWidth()) / tileWidth);
            	ytile = (int)((sy + s.getHeight()) / tileHeight);
            	tmap.setTileChar('.',xtile,ytile);
            	Sound starSound = new Sound("sounds/starSound.wav");
        		
        		starSound.start(); //start the sound
        		
        		total++; //increase the total
    		}
        		else if (s.equals(ship) && (tl == 'x' || bl == 'x' || tr == 'x' || br == 'x')) {
        			//sets the tile as a .
            		xtile = (int)(sx / tileWidth);
            	    ytile = (int)(sy / tileHeight);
            		tmap.setTileChar('.',xtile,ytile);
            		xtile = (int)(sx / tileWidth);
                	ytile = (int)((sy + s.getHeight())/ tileHeight); 
                	tmap.setTileChar('.',xtile,ytile);
                	xtile = (int)((sx + s.getWidth()) / tileWidth);
                	ytile = (int)(sy / tileHeight);
                	tmap.setTileChar('.',xtile,ytile);
                	xtile = (int)((sx + s.getWidth()) / tileWidth);
                	ytile = (int)((sy + s.getHeight()) / tileHeight);
                	tmap.setTileChar('.',xtile,ytile);
                	
                	speed = speed - 5;
                	if (speed < 0) {
                		speed = 0;
                		
                	}
        		}
    		
    		 else {
    			 //if rocket ship collides with a planet
    			if (s.equals(ship) && (tl == 'a' || bl == 'a' || tr == 'a' || br == 'a')) {
            		System.out.println("gameover");
                	System.exit(0);
            		
            	}
            	if (s.equals(ship) && (tl == 'b' || bl == 'b' || tr == 'b' || br == 'b')) {
            		System.out.println("gameover");
                	System.exit(0);
            		
            	}
            	if (s.equals(ship) && (tl == 'c' || bl == 'c' || tr == 'c' || br == 'c')) {
            		System.out.println("gameover");
                	System.exit(0);
        	
            	}
            	if (s.equals(ship) && (tl == 'd' || bl == 'd' || tr == 'd' || br == 'd')) {
            		System.out.println("gameover");
                	System.exit(0);
        	
            	}
            	if (s.equals(ship) && (tl == 'e' || bl == 'e' || tr == 'e' || br == 'e')) {
            		System.out.println("gameover");
                	System.exit(0);
        	
            	}
            	if (s.equals(ship) && (tl == 'f' || bl == 'f' || tr == 'f' || br == 'f')) {
            		System.out.println("gameover");
                	System.exit(0);
        	
            	}	
            //reverses the velocity of the sprite
    		s.setVelocityX(-s.getVelocityX());
    		s.setVelocityY(-s.getVelocityY());
    		//flips the rotation of the sprite 
    		if (s.getRotation() == 90) {
    			s.setRotation(270);
    		} else if (s.getRotation() == 0) {
    			s.setRotation(180);
    		}
    		else if (s.getRotation() == 180) {
    			s.setRotation(0);
    		}else {
    			s.setRotation(90);
    		}
    		}
    		}
    	 
    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT && key != lastKey) {
        	lastKey = key;
        	paused = false;
        	ship.setVelocityY(0);
        	ship.setVelocityX(-0.075);
        	
        	if (ship.getRotation() == 0 || ship.getRotation() == 90 || ship.getRotation() == 270) {
        		ship.setRotation(180);	
        	}	
        }

        if (key == KeyEvent.VK_RIGHT && key != lastKey) {
        	lastKey = key;
        	paused = false;
        	ship.setVelocityY(0);
        	ship.setVelocityX(0.075);
        	
        	if (ship.getRotation() == 90 || ship.getRotation() == 180 || ship.getRotation() == 270)  {	
        		ship.setRotation(0);	
        	}
        }

        if (key == KeyEvent.VK_UP && key != lastKey) {
        	lastKey = key;
        	paused = false;
        	ship.setVelocityY(-0.075);
        	ship.setVelocityX(0);
        	
        	if (ship.getRotation() == 0 || ship.getRotation() == 90 || ship.getRotation() == 180) {
        		ship.setRotation(270);	
        	}
        }

        if (key == KeyEvent.VK_DOWN && key != lastKey) {
        	lastKey = key;
        	paused = false;
        	ship.setVelocityY(0.075);
        	ship.setVelocityX(0);
        	
        	if (ship.getRotation() == 0 || ship.getRotation() == 180 || ship.getRotation() == 270) {
        		ship.setRotation(90);	
        	}
        }
        
        if (key == KeyEvent.VK_P) {
        	paused = true;
        	
        }
        
        
        e.consume();
    }
  
    @Override
    public void mouseClicked(MouseEvent e) {
    	
    	if (mapNumber == 1) {
    	if (speed < 30) {
    		speed++;
    		Sound speedSound = new Sound("sounds/iceball.wav");
        	speedSound.start();
        	paused = false;
        	if (ship.getRotation() == 180) {
        		
        		ship.setVelocityX(-0.25);	
        	} else if (ship.getRotation() == 0) {
        		ship.setVelocityX(0.25);
        	}
        	else if (ship.getRotation() == 90) {
        		ship.setVelocityY(0.25);
        	}
        	else if (ship.getRotation() == 270){
        		ship.setVelocityY(-0.25);
        	}
        	}
    	}
    	if (mapNumber == 2) {
        	if (speed < 20) {
        		speed++;
        		Sound speedSound = new Sound("sounds/iceball.wav");
            	speedSound.start();
            	paused = false;
            	//depending on where the ship is facing, depends on where to increase the velocity
            	if (ship.getRotation() == 180) {
            		
            		ship.setVelocityX(-0.25);	
            	} else if (ship.getRotation() == 0) {
            		ship.setVelocityX(0.25);
            	}
            	else if (ship.getRotation() == 90) {
            		ship.setVelocityY(0.25);
            	}
            	else if (ship.getRotation() == 270){
            		ship.setVelocityY(-0.25);
            	}
            	}
        	}
        	
        	
    	
        
     }
    public boolean boundingBoxCollision(Sprite s1, Sprite s2)
    {
    return ((s1.getX() + s1.getImage().getWidth(null) > s2.getX()) &&
    (s1.getX() < (s2.getX() + s2.getImage().getWidth(null))) &&
    ((s1.getY() + s1.getImage().getHeight(null) > s2.getY()) &&
    (s1.getY() < s2.getY() + s2.getImage().getHeight(null))));
    }
    private void msgbox(String s){
    	   JOptionPane.showMessageDialog(null, s);
    	   
    	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

   
}
