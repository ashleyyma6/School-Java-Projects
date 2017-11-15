package rps;

import java.util.Random;
import java.util.Scanner;

/**
 * scissor (0), rock (1), paper (2), user enter a number, make sure the input is
 * valid, computer generate a radom number, determines the winner or draw
 *
 * @author Admin
 */
public class RPS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int userwin = 0;//the time of "user win the game"
        int cpuwin = 0;//the time of "computer win the game"
        int game = 3;//To limit game in 3 times
        while (game > 0) {
            //let use enter a number
            System.out.println("Enter scissor (0) rock (1) or paper (2): ");
            Scanner input = new Scanner(System.in);//to scan a input
            int user = 0;
            boolean val = true;
            //make sure input is valid
            while (val) {
                //input invalid, re-enter
                if (input.hasNextInt() == false) {
                    System.out.println("The input is invalid.");
                    System.out.println("Re-enter scissor (0) rock (1) or paper (2):");
                    input.next();
                } else {
                    //input valid
                    user = input.nextInt();
                    if (user > 2 || user < 0) {
                        System.out.println("The input is invalid.");
                        System.out.println("Re-enter scissor (0) rock (1) or paper (2):");
                    } else {
                        val = false;
                    }
                }
            }
            //to declare and initialize a random object 
            Random rnd = new Random();
            //to generate a number between 0 and 2
            int cpu = rnd.nextInt(3);
            //to show user's choice
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
            //to display cpu's choice
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
            //To count the time of win and show the result every time
            if (user == cpu) {
                System.out.println("Game is a draw.");
            } else if (user - cpu == 1 || user - cpu == -2) {
                System.out.println("You win!");
                userwin++;
            } else {
                System.out.println("The computer wins!");
                cpuwin++;
            }
            //To limit game in 3 times
            game--;
        }
        //To determine who win 2 games first and display who is final winner
        if (userwin >= 2) {
            System.out.println("You win the game!");
        } else if (cpuwin >= 2) {
            System.out.println("You win the game!");
        } else {
            System.out.println("Neither you nor computer wins the game.");
        }
    }
}
