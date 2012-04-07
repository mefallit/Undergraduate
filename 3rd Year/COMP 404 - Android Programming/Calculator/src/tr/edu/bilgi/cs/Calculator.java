package tr.edu.bilgi.cs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	//define buttons and textView objects
	private Button one;
	private Button two;
	private Button three;
	private Button four;
	private Button five;
	private Button six;
	private Button seven;
	private Button eight;
	private Button nine;
	private Button zero;
	private Button dot;
	private Button plus;
	private Button minus;
	private Button multiplication;
	private Button divide;
	private Button equal;
	private Button clear;
	private Button delete;
	private TextView firstnumber;
	private TextView operator;
	private TextView secondnumber;
    

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    	firstnumber = (TextView) findViewById(R.id.firstnumber);
    	operator = (TextView) findViewById(R.id.operator);
    	secondnumber = (TextView) findViewById(R.id.secondnumber);
    	  
        //match the button and widget.id
        one = (Button) findViewById(R.id.bir);
        two = (Button) findViewById(R.id.iki);
        three = (Button) findViewById(R.id.uc);
        four = (Button) findViewById(R.id.dort);
        five = (Button) findViewById(R.id.bes);
        six = (Button) findViewById(R.id.alti);
        seven = (Button) findViewById(R.id.yedi);
        eight = (Button) findViewById(R.id.sekiz);
        nine = (Button) findViewById(R.id.dokuz);
        zero = (Button) findViewById(R.id.sifir);
        dot = (Button) findViewById(R.id.nokta);
        plus = (Button) findViewById(R.id.arti);
        minus = (Button) findViewById(R.id.eksi);
        multiplication = (Button) findViewById(R.id.carpi);
        divide = (Button) findViewById(R.id.bolu);
        clear = (Button) findViewById(R.id.clear);
        delete = (Button) findViewById(R.id.delete);
        equal = (Button) findViewById(R.id.esittir);
       
       
        //create new listener for all buttons and match them
		one.setOnClickListener(this);
    	two.setOnClickListener(this);
    	three.setOnClickListener(this);
    	four.setOnClickListener(this);
    	five.setOnClickListener(this);
    	six.setOnClickListener(this);
    	seven.setOnClickListener(this);
    	eight.setOnClickListener(this);
    	nine.setOnClickListener(this);
    	zero.setOnClickListener(this);
    	dot.setOnClickListener(this);
    	plus.setOnClickListener(this);
    	minus.setOnClickListener(this);
    	multiplication.setOnClickListener(this);
    	divide.setOnClickListener(this);
    	clear.setOnClickListener(this);
    	delete.setOnClickListener(this);
    	equal.setOnClickListener(this);
    }  	
    
    public void onClick(View v) {
    	//check what clicked and make its procedure

			if(v == one)
				controlAndChange("1");
			if(v == two)
				controlAndChange("2");
			if(v == three)
				controlAndChange("3");
			if(v == four)
				controlAndChange("4");
			if(v == five)
				controlAndChange("5");
			if(v == six)
				controlAndChange("6");
			if(v == seven)
				controlAndChange("7");
			if(v == eight)
				controlAndChange("8");
			if(v == nine)
				controlAndChange("9");
			if(v == zero)
				controlAndChange("0");
			if(v == clear)
				clearEverything();
			if(v == plus)
				Dortislem("+");
			if(v == minus)
				Dortislem("-");
			if(v == multiplication)
				Dortislem("*");
			if(v == divide)
				Dortislem("/");
			if(v == equal)
				Dortislem("");
			if(v == delete)
				deleteNumber();
			else if(v == dot ){
				
				if (operator.getText().equals("")){
					if (firstnumber.getText().equals("0")) {
						if(firstnumber.getText().toString().indexOf(".") == -1){
						firstnumber.setText("0.");
						}
					}
					else{ 
						if(firstnumber.getText().toString().indexOf(".") == -1){
							firstnumber.setText(firstnumber.getText() + ".");}}
				}
				else {
					if (secondnumber.getText().equals("0")){
						if(secondnumber.getText().toString().indexOf(".") == -1){
							secondnumber.setText("0.");
						}}
					else {
						if(secondnumber.getText().toString().indexOf(".") == -1){
							secondnumber.setText(secondnumber.getText() + ".");
						}}
				}
			}
			
	}
    
    
	private void controlAndChange(String s){ 
		if(operator.getText().equals("")){
					if(firstnumber.getText().equals("0"))
						//if first number equals 0, it will be given string
						firstnumber.setText(s);
					else
						//if not, it will write near first number
						firstnumber.setText(firstnumber.getText() + s);
		}
		else{
					if(secondnumber.getText().equals("0"))
						//if second number equals 0, it will be given string
						secondnumber.setText(s);
					else
						//if not, it will write near second number
						secondnumber.setText(secondnumber.getText() + s);
		}
	}
	
	private void clearEverything(){
		//delete everything
		firstnumber.setText("0");
		operator.setText("");
		secondnumber.setText("");
	}
	
	
	private void Dortislem(String s){
		if(operator.getText().equals("")){
			//if operator is empty 
			if(firstnumber.getText().equals("")){
				//first number can be empty but we have to do it 0
				//and replace operator with given string
				firstnumber.setText("0");
				operator.setText(s);
			}
			else 
				//Change operator with given string
				operator.setText(s);
		}
		else if(!secondnumber.getText().equals("")){
			double result= 0.0;
			double firstnumberDouble = new Double(firstnumber.getText().toString());
			double secondnumberDouble = new Double(secondnumber.getText().toString());
			
			if(operator.getText().equals("+")){
				result = firstnumberDouble + secondnumberDouble;
				firstnumber.setText(Double.toString(result));
				operator.setText(s);
				secondnumber.setText("");
			}
			if(operator.getText().equals("-")){
				result = firstnumberDouble - secondnumberDouble;
				firstnumber.setText(Double.toString(result));
				operator.setText(s);
				secondnumber.setText("");
			}
			if(operator.getText().equals("*")){
				result = firstnumberDouble * secondnumberDouble;
				firstnumber.setText(Double.toString(result));
				operator.setText(s);
				secondnumber.setText("");
			}
			if(operator.getText().equals("/")){
				result = firstnumberDouble / secondnumberDouble;
				firstnumber.setText(Double.toString(result));
				operator.setText(s);
				secondnumber.setText("");
			}
			
		}	
	}
	
	public void deleteNumber(){
		//delete last number that written before
		if (operator.getText().equals("")){
			if (firstnumber.getText().equals("0")) 
				firstnumber.setText("0");	
			else {
				if ((firstnumber.getText()).length() == 1) 
					firstnumber.setText("0");	
				else 
					firstnumber.setText((firstnumber.getText()).subSequence(0, ((firstnumber.getText()).length() - 1)));
			}
		}
		else {
			if (secondnumber.getText().equals("0")) 
				secondnumber.setText("0");
			else {
				if ((secondnumber.getText()).length() == 1) 
					secondnumber.setText("0");
				else 
					secondnumber.setText((secondnumber.getText()).subSequence(0, ((secondnumber.getText()).length() - 1)));
			}
		}
	}
    
}