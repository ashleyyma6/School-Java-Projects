/**
 * ***********************************************************************************
 *
 * This class represents a fraction whose numerator and denominator are
 * integers.
 *
 * Requirements: 1. Implement interfaces: SimpleFractionInterface and Comparable
 * (i.e. compareTo()) 2. Implement methods equals() and toString() from class
 * Object 3. Must work for both positive and negative fractions 4. Must not
 * reduce fraction to lowest term unless simplifySimpleFraction() is invoked 5.
 * For input 3/-10 & -3/-10, convert them to -3/10 & 3/10 respectively (see Hint
 * 2. below) 6. Must display negative fraction as -x/y, example: (-3)/10 or
 * 3/(-10), must display as -3/10 7. Must throw only SimpleFractionException in
 * case of errors 8. Must not add new or modify existing data fields 9. Must not
 * add new public methods 10.May add private methods
 *
 * Hints:
 *
 * 1. To reduce a fraction such as 4/8 to lowest terms, you need to divide both
 * the numerator and the denominator by their greatest common denominator. The
 * greatest common denominator of 4 and 8 is 4, so when you divide the numerator
 * and denominator of 4/8 by 4, you get the fraction 1/2. The recursive
 * algorithm which finds the greatest common denominator of two positive
 * integers is implemnted (see code)
 *
 * 2. It will be easier to determine the correct sign of a fraction if you force
 * the fraction's denominator to be positive. However, your implementation must
 * handle negative denominators that the client might provide.
 *
 * 3. You need to downcast reference parameter SimpleFractionInterface to
 * SimpleFraction if you want to use it as SimpleFraction. See add, subtract,
 * multiply and divide methods
 *
 * 4. Use "this" to access this object if it is needed
 *
 ***********************************************************************************
 */
package PJ1;

public class SimpleFraction implements SimpleFractionInterface, Comparable<SimpleFraction> {

    // integer numerator and denominator
    private int num;
    private int den;

    public SimpleFraction() {
        // set fraction to default = 0/1
        num = 0;
        den = 1;
        // implement this method!
    }	// end default constructor

    public SimpleFraction(int num, int den) {
        setSimpleFraction(num, den);
        // implement this method!
    }	// end constructor

    public void setSimpleFraction(int num, int den) {
        if (den == 0) {
            // return SimpleFractionException if initialDenominator is 0
            throw new SimpleFractionException("initial Denominator is 0");
        } else if (den < 0) {
            this.num = -num;//move the - in denominator to numinator & if both - change to +
            this.den = -den;
        } else {
            this.num = num;
            this.den = den;
        }
        // implement this method!
    }	// end setSimpleFraction

    public void simplifySimpleFraction() {
        reduceSimpleFractionToLowestTerms();//use reduce method
        // implement this method!
    }

    public double toDouble() {
        return (double) num / den;// return double floating point value
        // implement this method!
    }	// end toDouble 

    public SimpleFractionInterface add(SimpleFractionInterface secondFraction) {
        // create a new SimpleFraction object
        SimpleFraction add = new SimpleFraction(num * ((SimpleFraction) secondFraction).den + ((SimpleFraction) secondFraction).num * den, den * ((SimpleFraction) secondFraction).den);
        add.simplifySimpleFraction();//simplify the fraction
        return add;// return result which is a new reduced SimpleFraction object
        // implement this method!
        // a/b + c/d is (ad + cb)/(bd)
    }	// end add

    public SimpleFractionInterface subtract(SimpleFractionInterface secondFraction) {
        // create a new SimpleFraction object
        SimpleFraction subtract = new SimpleFraction(num * ((SimpleFraction) secondFraction).den - ((SimpleFraction) secondFraction).num * den, den * ((SimpleFraction) secondFraction).den);
        subtract.simplifySimpleFraction();//simplify the fraction
        // implement this method!
        // a/b - c/d is (ad - cb)/(bd)
        // return result which is a new reduced SimpleFraction object
        return subtract;
    }	// end subtract

    public SimpleFractionInterface multiply(SimpleFractionInterface secondFraction) {
        // create a new SimpleFraction object
        SimpleFraction multiply = new SimpleFraction(num * ((SimpleFraction) secondFraction).num, den * ((SimpleFraction) secondFraction).den);
        multiply.simplifySimpleFraction();//simplify the fraction
        return multiply;
        // return result which is a new reduced SimpleFraction object
        // implement this method!
        // a/b * c/d is (ac)/(bd)
    }	// end multiply

    public SimpleFractionInterface divide(SimpleFractionInterface secondFraction) {
        // return SimpleFractionException if secondFraction is 0
        if (((SimpleFraction) secondFraction).num == 0) {
            throw new SimpleFractionException("result Denominator is 0");
        } else {
            // create a new SimpleFraction object
            SimpleFraction divide = new SimpleFraction(num * ((SimpleFraction) secondFraction).den, den * ((SimpleFraction) secondFraction).num);
            divide.simplifySimpleFraction();//simplify the fraction
            return divide;// return result which is a new reduced SimpleFra`ction object
        }
        // implement this method!
        // a/b / c/d is (ad)/(bc)
    }	// end divide

    public boolean equals(Object other) {
        reduceSimpleFractionToLowestTerms();////simplify the fraction
        ((SimpleFraction) other).reduceSimpleFractionToLowestTerms();//simplify the other fraction
        if (num == ((SimpleFraction) other).num && den == ((SimpleFraction) other).den) {
            return true;
        } else {
            return false;
        }
        // implement this method!
    } // end equals

    public int compareTo(SimpleFraction other) {
        if (this.equals(other)) {
            return 0;
        } else if (toDouble() < other.toDouble()) {//compare double value
            return -1;
        } else {
            return 1;
        }
        // implement this method!
    } // end compareTo

    public String toString() {
        return num + "/" + den;
    } // end toString

    //-----------------------------------------------------------------
    //  private methods start here
    //-----------------------------------------------------------------
    /**
     * Task: Reduces a fraction to lowest terms.
     */
    private void reduceSimpleFractionToLowestTerms() {
        int gcd;
        if (num < 0) {//change num to + numbers for GCD work
            gcd = GCD(-num, den);
        } else {
            gcd = GCD(num, den);
        }

        this.num = num / gcd;//simplify
        this.den = den / gcd;//simplify
        // implement this method!
        //
        // Outline:
        // compute GCD of num & den
        // GCD works for + numbers.
        // So, you should eliminate - sign
        // then reduce numbers : num/GCD and den/GCD
    }	// end reduceSimpleFractionToLowestTerms

    /**
     * Task: Computes the greatest common divisor of two integers. This is a
     * recursive method!
     *
     * @param integerOne	an integer
     * @param integerTwo	another integer
     * @return the greatest common divisor of the two integers
     */
    private int GCD(int integerOne, int integerTwo) {
        int result;
        if (integerOne % integerTwo == 0) {
            result = integerTwo;
        } else {
            result = GCD(integerTwo, integerOne % integerTwo);
        }

        return result;
    }	// end GCD

    //-----------------------------------------------------------------
    //  Some tests are given here 
    public static void main(String[] args) {
        SimpleFractionInterface firstOperand = null;
        SimpleFractionInterface secondOperand = null;
        SimpleFractionInterface result = null;
        double doubleResult = 0.0;

        System.out.println("\n=========================================\n");
        firstOperand = new SimpleFraction(12, 20);
        System.out.println("Fraction before simplification:\t\t" + firstOperand);
        System.out.println("\tExpected result :\t\t12/20\n");
        firstOperand.simplifySimpleFraction();
        System.out.println("\nFraction after simplification:\t\t" + firstOperand);
        System.out.println("\tExpected result :\t\t3/5\n");

        firstOperand = new SimpleFraction(20, -40);
        System.out.println("\nFraction before simplification:\t\t" + firstOperand);
        System.out.println("\tExpected result :\t\t-20/40\n");
        firstOperand.simplifySimpleFraction();
        System.out.println("\nFraction after simplification:\t\t" + firstOperand);
        System.out.println("\tExpected result :\t\t-1/2\n");

        SimpleFraction nineSixteenths = new SimpleFraction(9, 16);  // 9/16
        SimpleFraction oneFourth = new SimpleFraction(1, 4);        // 1/4

        System.out.println("\n=========================================\n");
        // 7/8 + 9/16
        firstOperand = new SimpleFraction(7, 8);
        result = firstOperand.add(nineSixteenths);
        System.out.println("The sum of " + firstOperand + " and "
                + nineSixteenths + " is \t\t" + result);
        System.out.println("\tExpected result :\t\t23/16\n");

        // 9/16 - 7/8
        firstOperand = nineSixteenths;
        secondOperand = new SimpleFraction(7, 8);
        result = firstOperand.subtract(secondOperand);
        System.out.println("The difference of " + firstOperand
                + " and " + secondOperand + " is \t" + result);
        System.out.println("\tExpected result :\t\t-5/16\n");

        // 15/-2 * 1/4
        firstOperand = new SimpleFraction(15, -2);
        result = firstOperand.multiply(oneFourth);
        System.out.println("The product of " + firstOperand
                + " and " + oneFourth + " is \t" + result);
        System.out.println("\tExpected result :\t\t-15/8\n");

        // (-21/2) / (3/7)
        firstOperand = new SimpleFraction(-21, 2);
        secondOperand = new SimpleFraction(3, 7);
        result = firstOperand.divide(secondOperand);
        System.out.println("The quotient of " + firstOperand
                + " and " + secondOperand + " is \t" + result);
        System.out.println("\tExpected result :\t\t-49/2\n");

        // -21/2 + 7/8
        firstOperand = new SimpleFraction(-21, 2);
        secondOperand = new SimpleFraction(7, 8);
        result = firstOperand.add(secondOperand);
        System.out.println("The sum of " + firstOperand
                + " and " + secondOperand + " is \t\t" + result);
        System.out.println("\tExpected result :\t\t-77/8\n");

        // 0/10, 5/(-15), (-22)/7
        firstOperand = new SimpleFraction(0, 10);
        doubleResult = firstOperand.toDouble();
        System.out.println("The double floating point value of " + firstOperand + " is \t" + doubleResult);
        System.out.println("\tExpected result \t\t\t0.0\n");
        firstOperand = new SimpleFraction(1, -3);
        doubleResult = firstOperand.toDouble();
        System.out.println("The double floating point value of " + firstOperand + " is \t" + doubleResult);
        System.out.println("\tExpected result \t\t\t-0.333333333...\n");
        firstOperand = new SimpleFraction(-22, 7);
        doubleResult = firstOperand.toDouble();
        System.out.println("The double floating point value of " + firstOperand + " is \t" + doubleResult);
        System.out.println("\tExpected result \t\t\t-3.142857142857143");
        System.out.println("\n=========================================\n");
        firstOperand = new SimpleFraction(-21, 2);
        System.out.println("First = " + firstOperand);
        // equality check
        System.out.println("check First equals First: ");
        if (firstOperand.equals(firstOperand)) {
            System.out.println("Identity of fractions OK");
        } else {
            System.out.println("ERROR in identity of fractions");
        }

        secondOperand = new SimpleFraction(-42, 4);
        System.out.println("\nSecond = " + secondOperand);
        System.out.println("check First equals Second: ");
        if (firstOperand.equals(secondOperand)) {
            System.out.println("Equality of fractions OK");
        } else {
            System.out.println("ERROR in equality of fractions");
        }

        // comparison check
        SimpleFraction first = (SimpleFraction) firstOperand;
        SimpleFraction second = (SimpleFraction) secondOperand;

        System.out.println("\ncheck First compareTo Second: ");
        if (first.compareTo(second) == 0) {
            System.out.println("SimpleFractions == operator OK");
        } else {
            System.out.println("ERROR in fractions == operator");
        }

        second = new SimpleFraction(7, 8);
        System.out.println("\nSecond = " + second);
        System.out.println("check First compareTo Second: ");
        if (first.compareTo(second) < 0) {
            System.out.println("SimpleFractions < operator OK");
        } else {
            System.out.println("ERROR in fractions < operator");
        }

        System.out.println("\ncheck Second compareTo First: ");
        if (second.compareTo(first) > 0) {
            System.out.println("SimpleFractions > operator OK");
        } else {
            System.out.println("ERROR in fractions > operator");
        }

        System.out.println("\n=========================================");

        System.out.println("\ncheck SimpleFractionException: 1/0");
        try {
            SimpleFraction a1 = new SimpleFraction(1, 0);
            System.out.println("Error! No SimpleFractionException");
        } catch (SimpleFractionException fe) {
            System.err.printf("Exception: %s\n", fe);
        } // end catch
        System.out.println("Expected result : SimpleFractionException!\n");

        System.out.println("\ncheck SimpleFractionException: division");
        try {
            SimpleFraction a2 = new SimpleFraction();
            SimpleFraction a3 = new SimpleFraction(1, 2);
            a3.divide(a2);
            System.out.println("Error! No SimpleFractionException");
        } catch (SimpleFractionException fe) {
            System.err.printf("Exception: %s\n", fe);
        } // end catch
        System.out.println("Expected result : SimpleFractionException!\n");

    }	// end main
} // end SimpleFraction

