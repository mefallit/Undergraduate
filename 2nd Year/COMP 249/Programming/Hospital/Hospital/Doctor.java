/*
 * Sezgi Þensöz - 10792020
 */

public class Doctor {
	//dr include name
private String drName = "";
	
	public Doctor(String drName){
		this.drName = drName;
	}
	
	public void setDrName(String dr) {
		this.drName = dr;
	}

	public String getDrName() {
		return drName;
	}
	
	public String toString() {

        String s = " ";
               s = s + "Doctor's Name: " + this.drName;
               

        return s;

    }
}
