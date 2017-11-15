/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpsmethods;

import java.util.Random;
import java.util.Scanner;

/**
 *
 *
 * @author Admin
 */
public class RPSMethods {

    /**
     *
     *
     */
    //Displays(prints) an opening message for the Rock-Paper-Scissors Game.
    public static void displayOpeningMessage() {
        System.out.println("This program will play a game of Rock-Paper-Scissors. \nFor this program 0 will scissors, 1 will be rock, and 2 will be paper. ");
    }

    public static int getUserMove(Scanner input) {
        int temp = 0;
        boolean val = true;
        System.out.println("please enter:");
        while (val) {
            //Any input that is not a number should also be rejected.
            if (input.hasNextInt() == false) {
                System.out.println("The input is invalid.");
                System.out.println("Re-enter scissor (0) rock (1) or paper (2):");
                input.next();
            } else {
                //Any number less than 0 and bigger than 2 should be rejected.
                temp = input.nextInt();
                if (temp > 2 || temp < 0) {
                    System.out.println("The input is invalid.");
                    System.out.println("Re-enter scissor (0) rock (1) or paper (2):");
                } else {
                    val = false;
                }

            }
        }
        return temp;

    }
    //Generates a Random number between 0 and num-1
    public static int getCPUMove(int num) {
        return(new Random().nextInt(num));
    }
    //Given a user and cpu move, determines who wins 
    public static void determineWinner(int user, int cpu) {
        switch (user) {
            case 0:
                System.out.println("You are scissor.");
                break;
            case 1:
                System.out.println("You are rock.");
                break;
            case 2:
                System.out.println("You are paper.");
                break;
        }
        switch (cpu) {
            case 0:
                System.out.println("The computer is scissor.");
                break;
            case 1:
                System.out.println("The computer is rock.");
                break;
            case 2:
                System.out.println("The computer is paper.");
                break;
        }
        //Determine who wins with else-if statements and prints them to the console.
        if (user == cpu) {
            System.out.println("Game is a draw.");
        } else if (user - cpu == 1 || user - cpu == -2) {
            System.out.println("You win!");
        } else {
            System.out.println("The computer wins!");
        }
    }
    public static void main(String[] args) {
        displayOpeningMessage();
        Scanner input = new Scanner(System.in);
        int user = getUserMove(input);
        int cpu = getCPUMove(3);
        determineWinner(user, cpu);

    }
}
