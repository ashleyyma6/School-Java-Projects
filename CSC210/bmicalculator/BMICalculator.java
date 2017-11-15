/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmicalculator;

import java.util.Scanner;

/**
 * 1.First and last name, String
 * 2.Height inches, int
 * 3.Weight in pounds, double
 * 4.BMI = weight(lb) / (height(in)*height(in))*703 
 * 5.println
 * 
 * @author Admin
 */
public class BMICalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);//Input scanner

        System.out.println("Enter your first name and last name:");//tell to enter name
        String name1 = input.next();//enter firts name
        String name2 = input.next();//enter last name
        
        System.out.println("Enter your height (inches):"); //tell to enter height
        int hi = input.nextInt();//enter height
        
        System.out.println("Enter your weight in pounds:");//tell to enter weight
        double w = input.nextDouble();//enter weight
        
        int hf = hi*12;//get height ft
        double BMI;
        BMI = w / (hi * hi);//calculate BMI
        BMI *= 703;//calculate BMI
        
        System.out.println("End user Input");
        
        System.out.println("Your name:" + name1+" "+name2);//show name
        System.out.println("Height:"+hf+"ft, "+hi+"in");//show height
        System.out.println("Weight:" + w);//show weight

        if (BMI >= 30) {
            System.out.println("BMI:Obisty");
        } else if (BMI >= 25 && BMI <= 29.9) {
            System.out.println("BMI:Overweight");
        } else if (BMI >= 18.5 && BMI <= 24.9) {
            System.out.println("BMI:Normal weight");
        } else if (BMI <= 18.5) {
            System.out.println("BMI:Underweight");

        }//judge and show BMI Category
		//ft is inch-high *0.8
		//remainder inch is inch-high substract(ft/0.8)
    }
}
