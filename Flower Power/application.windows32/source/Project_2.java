import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Project_2 extends PApplet {



SoundFile blop;
SoundFile spring;

int gamestate = 0; // Assume 0 is initial screen
int numFrames = 4;// for the array of Roses
int frame = 0;
int twinkle = 80; //distance change in ray length
int rbound = 200; 
int numPoints=130; //number of rays

float radius;   
float angle=TWO_PI/(float)numPoints;//creates rays

PImage[] redRoses = new PImage[numFrames];//arrays for each color
PImage[] yellowRoses = new PImage[numFrames];
PImage[] blueRoses = new PImage[numFrames];
PImage[] purpleRoses = new PImage[numFrames];

public void setup() {
  //nice size screen to capture the rose in all its beauty
  

  //These images were edited using GIMP. Together they should have a dancing illusion
  redRoses[0] = loadImage("Red Rose.png");
  redRoses[1] = loadImage("Red Rose Stem Flipped.png");
  redRoses[2] = loadImage("Red Rose Head Flipped.png");
  redRoses[3] = loadImage("Red Rose Flipped.png");

  yellowRoses[0] = loadImage("Yellow Rose.png");
  yellowRoses[1] = loadImage("Yellow Rose Stem Flipped.png");
  yellowRoses[2] = loadImage("Yellow Rose Head Flipped.png");
  yellowRoses[3] = loadImage("Yellow Rose Flipped.png");

  blueRoses[0] = loadImage("Blue Rose.png");
  blueRoses[1] = loadImage("Blue Rose Stem Flipped.png");
  blueRoses[2] = loadImage("Blue Rose Head Flipped.png");
  blueRoses[3] = loadImage("Blue Rose Flipped.png");

  purpleRoses[0] = loadImage("Purple Rose.png");
  purpleRoses[1] = loadImage("Purple Rose Stem Flipped.png");
  purpleRoses[2] = loadImage("Purple Rose Head Flipped.png");
  purpleRoses[3] = loadImage("Purple Rose Flipped.png");

  PFont arialFont;
  frameRate(8);

  blop = new SoundFile(this, "Blop.wav");
  spring = new SoundFile(this, "Spring.mp3");
  arialFont = loadFont("Arial-Black-36.vlw");

  fill(0);
  textFont(arialFont);
  color(0);
  background(0xff03FA78);
  spring.play();
}

public void draw() {

  // initial screen
  if (gamestate == 0) {
    background(0xff03FA78); 
    fill( 255, 246, 64 ); //yellow
    stroke( 255, 246, 64 ); //yellow
    strokeWeight(5);


    // sun
    for (int i=0; i<numPoints; i++) {
      radius = rbound - (int)random( 0, twinkle );
      line(100, 100, radius*sin(angle*i)+100, radius*cos(angle*i)+100);
    } 

    frame = (frame + 1) % numFrames;
    image(redRoses[frame], 100, 100);
    image(blueRoses[frame], 0, 100);
    image(yellowRoses[frame], -100, 100);
    image(purpleRoses[frame], -200, 100);
    fill(0);
    text("Welcome to Rose Power!\nWould you like to have a custom rose?\nIf yes, press Space to begin", 5, 800);

    if (key == ' ') {
      gamestate = 1;//Color Selector Screen
    }
  } else if (gamestate == 1) {
    background(0xff03FA78);
    fill( 255, 246, 64 ); //yellow
    stroke( 255, 246, 64 ); //yellow
    strokeWeight(5);

    //sun
    for (int i=0; i<numPoints; i++) {
      radius = rbound - (int)random( 0, twinkle );
      line(100, 100, radius*sin(angle*i)+100, radius*cos(angle*i)+100);
    } 
    fill(0);
    text("What color would you like your rose?\nType R for Red\nType B for Blue\nType P for Purple\nType Y for Yellow", 5, 800);

    if (key == 'R') {
      gamestate = 2;//Red
      background(0xff03FA78);
      fill( 255, 246, 64 ); //yellow
      stroke( 255, 246, 64 ); //yellow
      strokeWeight(5);

      // sun
      for (int i=0; i<numPoints; i++) {
        radius = rbound - (int)random( 0, twinkle );
        line(100, 100, radius*sin(angle*i)+100, radius*cos(angle*i)+100);
      }
    }

    if (key == 'B') {
      gamestate = 3;//Blue
      background(0xff03FA78);
      fill( 255, 246, 64 ); //yellow
      stroke( 255, 246, 64 ); //yellow
      strokeWeight(5);

      // sun
      for (int i=0; i<numPoints; i++) {
        radius = rbound - (int)random( 0, twinkle );
        line(100, 100, radius*sin(angle*i)+100, radius*cos(angle*i)+100);
      }
    }

    if (key == 'P') {
      gamestate = 4;//Purple Rose
      background(0xff03FA78);
      fill( 255, 246, 64 ); //yellow
      stroke( 255, 246, 64 ); //yellow
      strokeWeight(5);

      // sun 
      for (int i=0; i<numPoints; i++) {
        radius = rbound - (int)random( 0, twinkle );
        line(100, 100, radius*sin(angle*i)+100, radius*cos(angle*i)+100);
      }
    }

    if (key == 'Y') {
      gamestate = 5;// Yellow Rose
      background(0xff03FA78);
      fill( 255, 246, 64 ); //yellow
      stroke( 255, 246, 64 ); //yellow
      strokeWeight(5);

      for (int i=0; i<numPoints; i++) {
        radius = rbound - (int)random( 0, twinkle );
        line(100, 100, radius*sin(angle*i)+100, radius*cos(angle*i)+100);
      }
    }
  } else if (gamestate == 2) {
    fill(0);
    text("Click anywhere to grow your Roses!\nPress Q to quit", 0, 800);

    //"Grows" your rose
    if (mousePressed == true) {
      image(redRoses[0], mouseX-500, mouseY-500);
      blop.play();
    }
    if (key == 'Q') {
      fill( 255, 246, 64 ); //yellow
      stroke( 255, 246, 64 ); //yellow
      strokeWeight(5);

      for (int i=0; i<numPoints; i++) {
        radius = rbound - (int)random( 0, twinkle );
        line(100, 100, radius*sin(angle*i)+100, radius*cos(angle*i)+100);
      } 
      gamestate = 0;//Back to initial screen
    }
  } else if (gamestate == 3) {
    fill(0);
    text("Click anywhere to grow your Roses!\nPress Q to quit", 0, 800);

    //"Grows" your rose
    if (mousePressed == true) {
      image(blueRoses[0], mouseX-500, mouseY-500);
      blop.play();
    }
    if (key == 'Q') {
      fill( 255, 246, 64 ); //yellow
      stroke( 255, 246, 64 ); //yellow
      strokeWeight(5);

      for (int i=0; i<numPoints; i++) {
        radius = rbound - (int)random( 0, twinkle );
        line(100, 100, radius*sin(angle*i)+100, radius*cos(angle*i)+100);
      } 
      gamestate = 0;//Back to initial screen
    }
  } else if (gamestate == 4) {
    fill(0);
    text("Click anywhere to grow your Roses!\nPress Q to quit", 0, 800);
    if (mousePressed == true) {
      image(purpleRoses[0], mouseX-500, mouseY-500);
      blop.play();
    }
    if (key == 'Q') {
      background(0xff03FA78);
      fill( 255, 246, 64 ); //yellow
      stroke( 255, 246, 64 ); //yellow
      strokeWeight(5);

      for (int i=0; i<numPoints; i++) {
        radius = rbound - (int)random( 0, twinkle );
        line(100, 100, radius*sin(angle*i)+100, radius*cos(angle*i)+100);
      } 
      gamestate = 0;
    }
  } else if (gamestate == 5) {
    fill(0);
    text("Click anywhere to grow your Roses!\nPress Q to quit", 0, 800);
    if (mousePressed == true) {
      image(yellowRoses[0], mouseX-500, mouseY-500);
      blop.play();
    }
    if (key == 'Q') {
      fill( 255, 246, 64 ); //yellow
      stroke( 255, 246, 64 ); //yellow
      strokeWeight(5);

      for (int i=0; i<numPoints; i++) {
        radius = rbound - (int)random( 0, twinkle );
        line(100, 100, radius*sin(angle*i)+100, radius*cos(angle*i)+100);
      } 
      gamestate = 0;
    }
  }
}
  public void settings() {  size(1000, 1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Project_2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
