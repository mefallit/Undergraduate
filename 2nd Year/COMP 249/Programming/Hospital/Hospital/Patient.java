
public class Patient {
	private String illnessType = ""; // prints type of illness accoring to number choice
	String patientName = ""; // must be a string at least 5 characters long
	
	public Patient(String patientName, String illnessType){
		this.setPatientName(patientName);
		this.setIllnessType(illnessType);
	}

	public void setIllnessType(String illnessType) {
		this.illnessType = illnessType;
	}

	public String getIllnessType() {
		return illnessType;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientName() {
		return patientName;
	}
	
	public String toString() {

        String s = "==================================\n";
               s = s + "Patient's Name: " + this.patientName;
               s = s + "\nIllness Type: " + this.illnessType;

        return s;

    }
}
