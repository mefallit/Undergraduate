int[] x_position=new int[10]; //Make array for x coordinates of all 10 balls
int[] y_position=new int[10]; //Make array for y coordinates of all 10 balls
int[] radius=new int[10]; //Make array for radius of all balls

void setup(){
  background(0); //Black background screen
  size(400,400); //Open new window that size 400*400
  stroke(0);   
  frameRate(15);
  smooth();
  strokeWeight(3);
  for (int i=0; i<x_position.length; i++){ 
    x_position[i]=int(random(50,400)); //give random value (50 to 400) to x coordinate
    y_position[i]=int(random(50,400)); //give random value (50 to 400) to y coordinate
    radius[i]=int(random(0,100)); //give random value(0 to 100) to radius
  }
}

void draw(){
  for(int i=0;i<x_position.length;i++){
    ellipse(x_position[i],y_position[i],radius[i],radius[i]); //draw circle which given value before (x,y coordinate and radius)
    fill(random (100,255),random(100,255),random(100,255)); //color randomly
    radius[i]=(radius[i]+5)%120; //this function for start again after 120 radius
  }
}


