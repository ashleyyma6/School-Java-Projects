/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loops;

/**
 *
 * @author Admin
 */
public class Loops {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("---------- LOOP 1 ----------");
        // for-loop starts from 0, stops at 110, counts up by 2
        for (int i = 0; i <= 110; i += 2) {
            System.out.println(i);
        }
        //while-loop starts from 100, stops at 0, counts down by 25
        System.out.println("---------- LOOP 2 ----------");
        int i = 100;
        while (i >= 0) {
            System.out.println(i);
            i -= 25;
        }
        // do-while multiply 1 by 2 for 15 times, print the result of every multiplication
        System.out.println("---------- LOOP 3 ----------");
        int m = 0;
        int n = 1;
        do {
            System.out.println(n);
            n *= 2;
            m++;
        } while (m < 15);
        // for-loop starts at 100, ends at 250, print every multiples of 3*4
        System.out.println("---------- LOOP 4 ----------");
        for (int a = 100; a <= 250; a++) {
            if (a % 12 == 0) {
                System.out.println(a);
            }
        }
        // while-loop print the multiples of 9 between 0 and 150
        System.out.println("---------- LOOP 5 ----------");
        int b = 0;
        while (b < 150) {
            b++;
            if (b % 9 == 0) {
                System.out.println(b);
            }
        }
        // for-loop  multiply 1 by 2 for 15 times, print the final result
        System.out.println("---------- LOOP 6 ----------");
        int c = 1;
        for (int d = 0; d < 30; d++) {
            c *= 2;
        }
        System.out.println(c);
        // do-while addition
        System.out.println("---------- LOOP 7 ----------");
        double x = 1;// from 1 to 5000
        double y = 0;//result
        do {
            y += (1 / x);
            x++;
        } while (x <= 5000);// from 1 to 5000
        System.out.println(y);
        // while loop
        System.out.println("---------- LOOP 8 ----------");
        int d = 574837;
        int rev = 0;
        int f;
        while (d > 0) {
            f = d % 10;//get remainder
            rev = rev * 10 + f;//reverse
            d = d / 10;//delete the last number for next loop to get new remainder
        }
        System.out.print(rev);//print remainder in 1 line
    }

}
