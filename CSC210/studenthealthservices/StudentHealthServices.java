/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.a
 */
package studenthealthservices;

/**
 *
 * @author Admin
 */
public class StudentHealthServices {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Print the number of created patien records
        System.out.println("Number of Patients: " + EMR.num);
        //Create 5 new patient records
        //Patient1
        String name1 = "Laura";
        String Dateofbirth1 = "1/1/1960 ";
        String Reasonforvisit1 = "Headaches";
        double Bodytemperatur1 = 99.500000;
        double Heartrate1 = 77.000000;
        String Diagnosis1 = "Bad Diet";
        String Prescribedmedicine1 = "Exercise and Better Diet";
        EMR emr1 = new EMR(name1, Dateofbirth1, Reasonforvisit1, Bodytemperatur1, Heartrate1, Diagnosis1, Prescribedmedicine1);
        //Patient2
        String name2 = "Lawrence";
        String Dateofbirth2 = "2/5/1981";
        String Reasonforvisit2 = "Pain in Foot";
        double Bodytemperatur2 = 98.500000;
        double Heartrate2 = 66.000000;
        String Diagnosis2 = "Bruised Heel";
        String Prescribedmedicine2 = "Light Walking for 3 weeks.";
        EMR emr2 = new EMR(name2, Dateofbirth2, Reasonforvisit2, Bodytemperatur2, Heartrate2, Diagnosis2, Prescribedmedicine2);
        //Patient3
        String name3 = "Rick";
        String Dateofbirth3 = "12/22/1940";
        String Reasonforvisit3 = "Dizziness and Fatigue";
        double Bodytemperatur3 = 101.100000;
        double Heartrate3 = 88.000000;
        String Diagnosis3 = "Fever";
        String Prescribedmedicine3 = "Rest";
        EMR emr3 = new EMR(name3, Dateofbirth3, Reasonforvisit3, Bodytemperatur3, Heartrate3, Diagnosis3, Prescribedmedicine3);
        //Patient4
        EMR emr4 = new EMR();
        //Patient5
        EMR emr5 = new EMR();
        //Print the number of created patien records
        System.out.println("Number of Patients: " + EMR.num);
        //Print the number of patient records
        System.out.println(emr1.toString());
        System.out.println(emr2.toString());
        System.out.println(emr3.toString());
    }

}
