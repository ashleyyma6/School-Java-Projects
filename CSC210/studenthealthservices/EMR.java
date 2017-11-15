/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studenthealthservices;

/**
 *
 * @author Admin
 */
public class EMR {

    //PRIVATE data-ﬁelds
    private String Name;
    private String Dateofbirth;
    private String Reasonforvisit;
    private double Bodytemperature;
    private double Heartrate;
    private String Diagnosis;
    private String Prescribedmedicine;
    //Declare a static variable that keeps count
    static int num = 0;

    //Create a default no-arg constructor+increment the patient counter static variable
    EMR() {
        num = num+1;
    }

    //Create a constructor that initializes ONLY the name and date-of-birth data-ﬁelds
    //+increment the patient counter static variable
    EMR(String NewName, String NewDateofbirth) {
        Name = NewName;
        Dateofbirth = NewDateofbirth;
        num = num+1;
    }

    //Create a constructor that initializes ALL data-ﬁelds. 
    //+increment the patient counter static variable
    EMR(String NewName, String NewDateofbirth, String NewReasonforvisit, double NewBodytemperature, double NewHeartrate, String NewDiagnosis, String NewPrescribedmedicine) {
        Name = NewName;
        Dateofbirth = NewDateofbirth;
        Reasonforvisit = NewReasonforvisit;
        Bodytemperature = NewBodytemperature;
        Heartrate = NewHeartrate;
        Diagnosis = NewDiagnosis;
        Prescribedmedicine = NewPrescribedmedicine;
        num = num+1;
    }
    //Create getters/setters for ALL the above data-ﬁelds. Should be 14 in total.
    void setName(String Name) {
        this.Name = Name;
    }

    void setDateofbirth(String Dateofbirth) {
        this.Dateofbirth = Dateofbirth;
    }

    void setReasonforvisit(String Reasonforvisit) {
        this.Reasonforvisit = Reasonforvisit;
    }

    void setBodytemperature(double Bodytemperature) {
        this.Bodytemperature = Bodytemperature;
    }

    void setHeartrate(double Heartrate) {
        this.Heartrate = Heartrate;
    }

    void setDiagnosis(String Diagnosis) {
        this.Diagnosis = Diagnosis;
    }

    void setPrescribedmedicine(String Prescribedmedicine) {
        this.Prescribedmedicine = Prescribedmedicine;
    }

    String getName() {
        return Name;
    }

    String getDateofbirth() {
        return Dateofbirth;
    }

    String getReasonforvisit() {
        return Reasonforvisit;
    }

    double getBodytemperature() {
        return Bodytemperature;
    }

    double getHeartrate() {
        return Heartrate;
    }

    String getDiagnosis() {
        return Diagnosis;
    }

    String getPrescribedmedicine() {
        return Prescribedmedicine;
    }
    //a getter method for the static variables 
    //keep track how many patient objects are created. 
    int getnum() {
        return num;
    }
    //Implement an instance method called redFlags.
    boolean redFlags(double Heartrate, double Bodytemperature) {
        if (Heartrate < 60.0 || Heartrate > 100.0 || Bodytemperature < 97.3 || Bodytemperature > 97.3) {
            return true;
        } else {
            return false;
        }
    }
    //Implement a toString method
    //build a String of all the dataﬁelds of the EMR class
    public String toString() {
        return "\nName: "+this.Name+"\nDOB: "+this.Dateofbirth + "\nReason For Visit: "+this.Reasonforvisit + "\nBody Temp: "+this.Bodytemperature + "\nHeart Rate: "+this.Heartrate + "\nDiagnosis: "+this.Diagnosis + "\nPrescribed Medicine: "+this.Prescribedmedicine;
    }
}
