package tr.edu.bilgi.cs;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class AnalogClock extends Activity {

	
	//Path : path
	//radius : radius of circle
	//pos : angle 
	//centerX: X of Center
	//CenterY : Y of Center
	//startingRad: starting point according to Radian
	//Ending: end point according to Radian
	private static void drawLine (Path path, float radius, float pos, float centerX, float centerY, float startingRad, float EndingRad) {
	        float theta = (float) (pos * (Math.PI * 2) - (Math.PI / 2));
	        float dx = (float) Math.cos(theta);
	        float dy = (float) Math.sin(theta);
	        
	        // Calculating starting & end points
	        float startX = centerX + dx * startingRad;
	        float startY = centerY + dy * startingRad;
	        float endX = centerX + dx * EndingRad;
	        float endY = centerY + dy * EndingRad;

	        float ox =  (endY - startY);
	        float oy = - (endX - startX);

	        
	        float norm = ((radius / 1.0f) / (float) Math.sqrt(ox * ox + oy * oy) / 2);
	        ox *= norm;
	        oy *= norm;

	        path.moveTo(startX - ox, startY - oy);
	        path.lineTo(endX - ox, endY - oy);
	        path.lineTo(endX + ox, endY + oy);
	        path.lineTo(startX + ox, startY + oy);
	        path.close();
	 }
	
    static public class AnalogView extends View{
    	Path clock;
    	private Paint tPaint;
    	float merkezY = 150;
		float merkezX = 120;
		float radius = 100;
		
		public AnalogView(Context context) {
			super(context);
			
			clock = new Path();
			//clock.addCircle(merkezX, merkezY, radius, Direction.CW);
			
			tPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	        tPaint.setColor(Color.BLACK);
	        tPaint.setTextSize(15f);
	        setBackgroundColor(Color.WHITE);
	         
		}
		
		protected void onDraw(Canvas canvas){
		    
			
			Calendar cal = Calendar.getInstance();
			int hour = cal.get(Calendar.HOUR_OF_DAY);
	        int minute = cal.get(Calendar.MINUTE);
	        int second = cal.get(Calendar.SECOND);
	        int millisec = cal.get(Calendar.MILLISECOND);
	        
	        
	        
	        //distance of hands..
	        float point0 = radius * 0.1f;
	        float point1 = radius * 0.4f;
	        float point2 = radius * 0.6f;
	        float point3 = radius * 0.75f;
	        float point4 = radius * 0.8f;
	        float point5 = radius * 0.9f;
	        
	        //Calculation with time elements
			float ss = second + millisec / 1000.0f;
	        float mm = minute + ss / 60.0f;
	        float hh = hour + mm / 60.0f;
	        
	        Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			paint.setStyle(Paint.Style.STROKE);
	        canvas.drawCircle(merkezX, merkezY, 100, paint);
	        paint.setStyle(Paint.Style.FILL_AND_STROKE);
	        paint.setColor(Color.GRAY);
	        canvas.drawCircle(merkezX,merkezY,10,paint);

	        // Tics for the hours
	        for (int i = 0; i < 24; i++) {
	            drawLine(clock, radius * 0.036f , i / 24.0f, merkezX, merkezY, point4, point5);
	        }
	        
	        //hourhand
			drawLine(clock, radius * 0.060f, hh / 24.0f , merkezX, merkezY, point0, point1);
			//minute
			drawLine(clock, radius * 0.060f, mm / 60.0f , merkezX, merkezY, point0, point2);
			//secondhand
			drawLine(clock, radius * 0.036f, ss / 60.0f, merkezX, merkezY, point0, point3); 
			paint.setStyle(Paint.Style.FILL);
			canvas.drawText("12", merkezX - 5 , merkezY + radius + 15 , tPaint);
			canvas.drawText("24", merkezX - 5 , merkezY - radius - 5 , tPaint);
			canvas.drawText("6", merkezX + radius , merkezY + 5 , tPaint);
			canvas.drawText("18", merkezX - radius - 15 , merkezY + 2 , tPaint);
			canvas.drawPath(clock,tPaint);
			
			clock.reset(); //delete redrawed before
			invalidate(); //make animatioon or redraw it
		}
    	
    }
    
    AnalogView aView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aView = new AnalogView(this);
        
        setContentView(aView);
        
    }
    
    
}