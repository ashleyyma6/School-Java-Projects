/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangle;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Triangle {

    /**
     * @param args the command line arguments
     */
    private int sideA = 1;
    private int sideB = 1;
    private int sideC = 1;
    //a default constructor
    Triangle() {
    }
    //a constructor that takes in three arguments 
    Triangle(int newsideA, int newsideB, int newsideC) {
        sideA = newsideA;
        sideB = newsideB;
        sideC = newsideC;
    }
    //setters
    void setsideABC(int newsideA,int newsideB,int newsideC) {
        sideA = newsideA;
        sideB = newsideB;
        sideC = newsideC;
    }
    //getters
    int getsideA() {
        return sideA;
    }

    int getsideB() {
        return sideB;
    }

    int getsideC() {
        return sideC;
    }
// A scalene triangle has all unequal sides.

    public boolean isScalene() {
        if (sideA == sideB || sideB == sideC || sideA == sideC) {
            return false;
        } else {
            return true;
        }
    }

// A right triangle satisfies the Pythagorean theorem.
    public boolean isRight() {
        if ((sideA ^ 2 + sideB ^ 2) == (sideC ^ 2) || (sideA ^ 2 + sideC ^ 2) == (sideB ^ 2) || (sideC ^ 2 + sideB ^ 2) == (sideA ^ 2)) {
            return true;
        } else {
            return false;
        }// 
    }

// An isosceles triangle has TWO equal sides.
    public boolean isIsosceles() {
        if (sideA == sideB || sideB == sideC || sideA == sideC) {
            return true;
        } else {
            return false;
        }
    }

// An equilateral triangle has all equal sides. 
    public boolean isEquilateral() {
        if (sideA == sideB && sideB == sideC) {
            return true;
        } else {
            return false;
        }
    }
    //Given a Triangle object, see if the Triangle is valid 
    public boolean isValid() {
        if (sideA + sideB > sideC && sideA + sideC > sideB && sideC + sideB > sideA) {
            return true;
        } else {
            return false;
        }
    }
    //to get correct instance variables
    public static int getUser(Scanner input) {
        int temp = 0;
        boolean val = true;
        while (val) {
            //Any input that is not a number should also be rejected.
            if (input.hasNextInt() == false) {
                System.out.println("The input is invalid.");
                input.next();
            } else {
                //Any number less than 0 and bigger than 2 should be rejected.
                temp = input.nextInt();
                val = false;
            }
        }
        return temp;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a, b, c;
        while (true) {
            //Prompt user for side a
            System.out.print("Enter the length of side A:");
            a = getUser(input);
            //Prompt user for side b
            System.out.print("Enter the length of side B:");
            b = getUser(input);
            //Prompt user for side c
            System.out.print("Enter the length of side C:");
            c = getUser(input);
            //Create triangle object
            Triangle obj1 = new Triangle(a, b, c);
            //print values for triangle
            System.out.println("Valid Triangle: " + obj1.isValid());
            System.out.println("Equilateral Triangle: " + obj1.isEquilateral());
            System.out.println("Isosceles Triangle: " + obj1.isIsosceles());
            System.out.println("Right Triangle: " + obj1.isRight());
            System.out.println("Scalene Triangle: " + obj1.isScalene());
            System.out.println("Read in a new triangle?(-1 for no, 1 for yes): ");
            if (input.nextInt() < 0) {
                break;
            }
        }
    }
}
