/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.lang.Math;
import java.text.DecimalFormat;

/*Sample class for Question 1 */
class sample {

    double randNummean;//mean of the Question 1 sample
    double randNumSize;

    public sample() {
    }

    public sample(double _randNumSize) {
        randNumSize = _randNumSize;
    }

    public double getSampleMean() {
        /* Question1:Generate 1 sample 4000 random numbers from U(-2,10)*/
        double sumRandNum = 0.00;
        for (int i = 0; i < (int) randNumSize; i++) {
            double rand = Math.random();
            double randTest = rand * (10.0 - (-2.0)) + (-2.0);
            sumRandNum += randTest;
        }
        /*Calculate 1 sample mean*/
        randNummean = sumRandNum / randNumSize;
        return randNummean;
    }
}
/*Sample class for Question 2 */
class PDSample {

    double randNummean;//mean of the Question 2 sample
    double randNumSize;

    public PDSample() {
    }

    public PDSample(double _randNumSize) {
        randNumSize = _randNumSize;
    }

    public double getSampleMean() {
        /*Question2: Generate 1 sample 4000 random numbers from U(0,1)*/
        double sumRandNum = 0.00;
        for (int i = 0; i < (int) randNumSize; i++) {
            double y = Math.random();
            double x = Math.pow((1 - y), -0.25);
            sumRandNum += x;
        }
        /*Calculate 1 sample mean*/
        randNummean = sumRandNum / randNumSize;
        return randNummean;
    }
}

public class Assignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*limit the digits of decimal numbers*/
        DecimalFormat df = new DecimalFormat("0.0000");
        
        /*Question 1 variables*/
        double sampleSize = 1000.00;//1000 samples
        double randNumSize = 40000.00;//40000 random numbers
        double sumOfSampleMean = 0.00;
        double simuProbCouter = 0.00;

        /*Generate 1000 samples*/
        sample[] samples = new sample[(int) sampleSize];
        for (int i = 0; i < sampleSize; i++) {
            samples[i] = new sample(randNumSize);
            /*Question 1: Get the mean of the sample*/
            samples[i].getSampleMean();
            sumOfSampleMean += samples[i].randNummean;
            /*Question 1: Check if the mean is between 3.99 and 4.1 inclusive*/
            if ((samples[i].randNummean >= 3.99) && (samples[i].randNummean <= 4.1)) {
                simuProbCouter += 1.00;
            }
        }
        /*Question1.A.Simulated Probability*/
        double simuProb = simuProbCouter / sampleSize;

        /*Question1.B.The mean of the means*/
        double meanOfMean = sumOfSampleMean / sampleSize;

        /*Question1.C.Standard deviation of the means*/
        double sumOfxu2 = 0.00;
        for (int i = 0; i < sampleSize; i++) {
            double temp = (samples[i].randNummean - meanOfMean);
            (sumOfxu2) += temp * temp;
        }
        double SD = Math.sqrt((sumOfxu2 / sampleSize));

        System.out.println("Question#1");
        System.out.println("1.A. the simulated probability that the mean is between 3.99 and 4.1 inclusive: " + simuProb);
        System.out.println("1.B. The mean of the means: " + meanOfMean);
        System.out.println("1.C. the standard deviation of the means: " + SD);
        
        /*Question1.D Histogram*/
        double upper = meanOfMean + (4.0 * SD);
        double lower = meanOfMean - (4.0 * SD);
        System.out.println("1.D. Histogram: ");
        for (double i = lower; i < upper; i += 0.005) {
            System.out.print(df.format(i) + ": ");
            for (int j = 0; j < samples.length; j++) {
                int counter = 0;
                if ((samples[j].randNummean >= i) && (samples[j].randNummean < (i + 0.005))) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        System.out.println();
        
        /*Question 2 variables*/
        double PDsampleSize = 1000.00;//1000 samples
        double PDrandNumSize = 40000.00;//40000 random numbers
        double PDsumOfSampleMean = 0.00;
        double PDsimuProbCouter = 0.00;
        
        PDSample[] PDsamples = new PDSample[(int) PDsampleSize];
        /*Question 2: Generate 1000 samples*/
        for (int i = 0; i < PDsampleSize; i++) {
            PDsamples[i] = new PDSample(PDrandNumSize);
            /*Question 2: Get the mean of the sample*/
            PDsamples[i].getSampleMean();
            PDsumOfSampleMean += PDsamples[i].randNummean;
            /*Question 2: Check if the mean is between between 30 and 33 exclusive*/
            if ((PDsamples[i].randNummean > 30.00) && (PDsamples[i].randNummean < 33.00)) {
                PDsimuProbCouter += 1.00;
            }
        }
        /*Question 2.A. Simulated Probability*/
        double PDsimuProb = PDsimuProbCouter / PDsampleSize;

        /*Question 2.B.The mean of the means*/
        double PDmeanOfMean = PDsumOfSampleMean / PDsampleSize;

        /*Question 2.C.Standard deviation of the means*/
        double PDsumOfxu2 = 0.00;
        for (int i = 0; i < PDsampleSize; i++) {
            double temp = (PDsamples[i].randNummean - PDmeanOfMean);
            (PDsumOfxu2) += temp * temp;
        }
        double PD_SD = Math.sqrt((PDsumOfxu2 / PDsampleSize));

        System.out.println("Question#2");
        System.out.println("1.A. the simulated probability that the mean is between 30 and 33 exclusive: " + PDsimuProb);
        System.out.println("1.B. the simulated mean of the means: " + PDmeanOfMean);
        System.out.println("1.C. the simulated standard deviation of the means: " + PD_SD);

        /*Question2.D Histogram*/
        double PDupper = PDmeanOfMean+(4*PD_SD);
        double PDlower = PDmeanOfMean-(4*PD_SD);
        System.out.println("1.D. Histogram: ");
        for (double i = PDlower; i < PDupper; i += 0.0005) {
            System.out.print(df.format(i) + ": ");
            for (int j = 0; j < PDsamples.length; j++) {
                if ((PDsamples[j].randNummean >= i) && (PDsamples[j].randNummean < (i + 0.0005))) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
