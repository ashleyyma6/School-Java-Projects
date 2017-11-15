/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayoperations;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class ArrayOperations {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //1. Arrays, 10000 items, int
        int num[] = new int[10000];
        //2. a loop fill array with radom integers 0-200 
        int i;
        for (i = 0; i < num.length; i++) {
            Random rnd = new Random();
            num[i] = rnd.nextInt(201);
        }

        i = 0;
        int sum = 0;
        while (i < num.length) {
            //3. a loop for sum
            sum = sum + num[i];
            i++;
        }
        System.out.println("The Sum is : " + sum);
        //4.a loop for average
        i = 0;
        sum = 0;
        int ave = 0;
        while (i < num.length) {
            sum = sum + num[i];
            i++;
            ave = sum / num.length;
        }
        System.out.println("The average is : " + ave);
        //5. a loop for minimum value
        i = 0;
        int min = num[i];
        int minindex = 0;
        while (i < num.length) {

            if (min > num[i]) {
                min = num[i];
                minindex = i;
            }
            i++;
        }
        System.out.println("The minimum value is : " + min + " and its index is " + minindex);
        //6. a loop for maximum value
        i = 0;
        int max = num[i];
        int maxindex = 0;
        while (i < num.length) {

            if (max < num[i]) {
                maxindex = i;
                max = num[i];
            }
            i++;
        }
        System.out.println("The maximum value is : " + max + " and its index is " + maxindex);

        i = 0;
        int evennum = 0;
        int oddnum = 0;
        int evensum=0;
        int oddsum=0;
        while (i < num.length) {

            //7. a loop for the number of even number
            if (num[i] % 2 == 0) {
                evennum++;
                evensum+=num[i];
            } //8. a loop for the number of odd number
            else {
                oddnum++;
                oddsum+=num[i];
            }
            i++;
        }
        System.out.println("The array contains " + evennum + " even numbers. ");
        System.out.println("The array contains " + oddnum + " odd numbers. ");
        System.out.println("The Sum of the odd numbers " + evensum);
        System.out.println("The Sum of the even numbers " + oddsum);
    }
}
