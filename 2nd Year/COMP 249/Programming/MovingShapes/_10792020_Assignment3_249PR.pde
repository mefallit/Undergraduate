class Circle{

  // I've done it with arrays to use only one instance for every circle.
  float[] x= new float[5]; //for x location
  float[] y= new float[5]; //for y loaction
  float[] xspeed =new float[5]; //for different speed which related with x position
  float[] yspeed= new float[5]; //for different speed which related with y position
  float[] r =new float[5]; //for radius of circles

  //Constructor 

  Circle() {
    // I want to determine all circle's values in Constructor. Every Circle will have different x and y position, radius, and speed.
    for (int i=0; i<5 ; i++){
      x[i]= random (400);
      y[i] = random (400);
      r[i] = random(20,75);
      xspeed[i]= random (-10, 10);
      yspeed[i] =random (-10, 10);
    }
  }

  void show() {
    // this field will show or display our circles with random color.
    for (int i=0; i<5 ; i++){
      ellipse(x[i],y[i], r[i], r[i]);
      fill(random (100,255),random (100,255),random (100,255));
    }
  }

  void moveing() {
    // this field will organize how circles move. 
    for (int i=0; i<5; i++){

     // I use for move all circles. 
     
     //x position of every ball will decrease or increase according to xspeed (it was random)
      x[i] = x[i] + xspeed[i];
      if (x[i] > 400 || x[i] <0) {
        // if circle arrive to 400 or 0(weigth of my screen),  xspeed of circle will change for reverse moving
        xspeed[i] = xspeed[i] * -1;
      }
       
      //y position of every ball will decrease or increase according to yspeed (it was random)
      y[i] = y[i] + yspeed[i];
      if (y[i] > 400 || y[i] <0) {
        // if circle arrive to 400 or 0(heigth of my screen),  yspeed of circle will change for reverse moving
        yspeed[i] = yspeed[i] * -1;
      }
    }
  }

}

Circle circle;

void setup() {
  size(400,400);
  frameRate(20);
  strokeWeight(3);
  smooth();
  circle = new Circle();
  // I created an instance in setup, because it is similar with main function. In my instance no need to create new instance for every circle because I used arrays. 
}

void draw() {
  background(0);
  circle.moveing(); // This will use moving field which is in Class
  circle.show();    //this will use show field whish is in Class.
}






