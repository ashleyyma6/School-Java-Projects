package PJ3;

import java.util.*;
import java.io.*;

// You may add new functions or data in this class 
// You may modify any functions or data members here
// You must use Customer, Teller and ServiceArea classes
// to implement Bank simulator
class BankSimulator {

  // input parameters
  private int numTellers, customerQLimit;
  private int simulationTime, dataSource;
  private int chancesOfArrival, maxTransactionTime;

  // statistical data
  private int numGoaway, numServed, totalWaitingTime;

  // internal data
  private int customerIDCounter;   // customer ID counter
  private ServiceArea servicearea; // service area object
  private Scanner dataFile;	   // get customer data from file
  private Random dataRandom;	   // get customer data using random function

  // most recent customer arrival info, see getCustomerData()
  private boolean anyNewArrival;  
  private int transactionTime;
  //add Array[]
  Teller BusyArray[];
  Teller FreeArray[];
  Customer CustomerArray[];
  
  // initialize data fields
  private BankSimulator()
  {
	// add statements
      numGoaway=0;
      numServed=0;
      totalWaitingTime=0;
      customerIDCounter=0;
      servicearea=null;
  }

        
    private void setupParameters() {
        System.out.println(" ***  Get Simulation Parameters  ***");
        //add check for not 1 or 0!!!
        // read input parameters
        // setup dataFile or dataRandom
        // add statements
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Enter simulation time (positive integer)       : ");
            simulationTime = input.nextInt();
        } while (simulationTime > 10000 || simulationTime <= 0);
        do {
            System.out.print("Enter the number of tellers                    : ");
            numTellers = input.nextInt();
        } while (numTellers > 10 || numTellers <= 0);
        do {
            System.out.print("Enter chances (0% < & <= 100%) of new customer : ");
            chancesOfArrival = input.nextInt();
        } while (chancesOfArrival > 100 || chancesOfArrival < 1);
        
        do {
            System.out.print("Enter maximum transaction time of customers    : ");
            maxTransactionTime = input.nextInt();
        } while (maxTransactionTime > 500 || maxTransactionTime <= 0);
        
        do {
            System.out.print("Enter customer queue limit                     : ");
            customerQLimit = input.nextInt();
        } while (customerQLimit > 50 || customerQLimit <= 0);
        do {
            System.out.print("Enter 0/1 to get data from Random/file         : ");
            dataSource = input.nextInt();
        } while (dataSource > 1 || dataSource < 0);
        
        if (dataSource == 1) {
            boolean check;
            do {
                check = false;
                System.out.print("Enter filename                                 : ");
                String filename=input.next();
                try {
                    dataFile = new Scanner(new File(filename));
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                    check = true;
                }
            } while (check);
        } else if (dataSource == 0) {
            System.out.println("Get Ramdom number. ");
            dataRandom = new Random();
        }
    }

  // Refer to step 1 in doSimulation()
  private void getCustomerData() {
      
        // get next customer data : from file or random number generator
        // set anyNewArrival and transactionTime
        // see Readme file for more info
        // add statements
        if (dataSource == 1) {
            if (dataFile.hasNextInt()) {
            anyNewArrival = (((dataFile.nextInt() % 100) + 1) <= chancesOfArrival);
            transactionTime = (dataFile.nextInt() % maxTransactionTime) + 1;
            }    
        } else {
            anyNewArrival = ((dataRandom.nextInt(100) + 1) <= chancesOfArrival);
            transactionTime = dataRandom.nextInt(maxTransactionTime) + 1;
        }
    }

    private void doSimulation() {
        System.out.println(" ***  Start Simulation  ***");
        // add statements

        // Initialize ServiceArea
        ServiceArea servicearea = new ServiceArea(numTellers, customerQLimit);
        // Time driver simulation loop
        for (int currentTime = 0; currentTime < simulationTime; currentTime++) {

            // Step 1: any new customer enters the bank?
            System.out.println("Time: "+currentTime);
            getCustomerData();
            if (anyNewArrival) {

                // Step 1.1: setup customer data
                // Step 1.2: check customer waiting queue too long?
                //           if it is too long, update numGoaway
                //           else enter customer queue
                customerIDCounter++;
                Customer customer = new Customer(customerIDCounter, transactionTime, currentTime);

                System.out.println("CustomerId# "+customer.getCustomerID()+" arrives with transaction time of "+ transactionTime + " units");
                if (servicearea.isCustomerQTooLong()) {
                    System.out.println("Customer#" + customer.getCustomerID() + " left");
                    numGoaway++;
                } else {
                    servicearea.insertCustomerQ(customer);
                    System.out.println("Customer#" + customer.getCustomerID() + " wait in customer queue");
          
                }
            } else {
                System.out.println("\tNo new customer!");
            }

            // Step 2: free busy tellers that are done at currentTime, add to free cashierQ
            // Step 3: get free tellers to serve waiting customers at currentTime
            while (!servicearea.emptyBusyTellerQ()) {
                if (servicearea.getFrontBusyTellerQ().getEndBusyTime() == currentTime) {
                    Teller teller = servicearea.removeBusyTellerQ();
                    System.out.println("Customer " + teller.getCustomer().getCustomerID() + " is free");
                    System.out.println("Teller " + teller.getTellerID() + " is free");
                    teller.busyToFree();
                    servicearea.insertFreeTellerQ(teller);
                } else {break;}
                
            }
            while (!servicearea.emptyFreeTellerQ() && !servicearea.emptyCustomerQ()) {
                Customer customer = servicearea.removeCustomerQ();
                Teller teller = servicearea.removeFreeTellerQ();
                teller.freeToBusy(customer, currentTime);
                servicearea.insertBusyTellerQ(teller);
                System.out.println("Customer " + customer.getCustomerID() + " gets a teller");
                System.out.println("Teller " + teller.getTellerID() + " starts serving customer " + customer.getCustomerID() + " for " + customer.getTransactionTime() + " units");
                totalWaitingTime += (currentTime - customer.getArrivalTime());
                numServed++;
            }
            System.out.println("---------------------------------------------");
        } // end simulation loop
        BusyArray= new Teller[servicearea.numBusyTellers()];
        for(int i=0;i<BusyArray.length;i++){
            BusyArray[i]=servicearea.removeBusyTellerQ();
        }
        FreeArray= new Teller[servicearea.numFreeTellers()];
        for(int i=0;i<FreeArray.length;i++){
            FreeArray[i]=servicearea.removeFreeTellerQ();
        }
        CustomerArray= new Customer[servicearea.numWaitingCustomers()];
        for(int i=0;i<CustomerArray.length;i++){
            CustomerArray[i]=servicearea.removeCustomerQ();
        }
        for (Teller BusyArray1 : BusyArray) {
            BusyArray1.setEndBusyTime(simulationTime);
            BusyArray1.updateTotalBusyTime();
        }
        for (Teller FreeArray1 : FreeArray) {
            FreeArray1.setEndFreeTime(simulationTime);
            FreeArray1.updateTotalFreeTime();
        }
        //dataFile.close();
        // clean-up - close scanner
    }

  private void printStatistics()
  {
	// add statements into this method!
	// print out simulation results
	// see the given example in project statement
        // you need to display all free and busy gas pumps
      System.out.println("---------------------------------------------");
      System.out.println("End of simulation report");
      System.out.println("# total arrival customers  :" + customerIDCounter);
      System.out.println("# customers gone-away      :" + numGoaway);
      System.out.println("# customers served         :" + numServed);
      System.out.println("*** Current Tellers Info. ***");
      //servicearea.printStatistics();
      System.out.println("# of waiting customers : " + CustomerArray.length);
      System.out.println("# of busy tellers      : " + BusyArray.length);
      System.out.println("# of free tellers      : " + FreeArray.length + "\n");
      System.out.println("Total waiting time         :" + totalWaitingTime);
      System.out.println("Average waiting time       :" + (double) (totalWaitingTime / numServed));
      System.out.println("Busy Tellers Info. :");
      for (Teller BusyArray1 : BusyArray) {
          BusyArray1.printStatistics();
      }
      System.out.println("Free Tellers Info. :");
      for (Teller FreeArray1 : FreeArray) {
          FreeArray1.printStatistics();
      }

      // need to free up all customers in queue to get extra waiting time.
        // need to free up all tellers in free/busy queues to get extra free & busy time.
  }

  // *** main method to run simulation ****

  public static void main(String[] args) {
   	BankSimulator runBankSimulator=new BankSimulator();
   	runBankSimulator.setupParameters();
   	runBankSimulator.doSimulation();
   	runBankSimulator.printStatistics();
  }

}