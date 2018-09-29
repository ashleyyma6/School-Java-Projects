import java.io.IOException;
import java.util.Scanner;
import java.io.File;


/**
 * <h1>ReservationSystem</h1>
 * ReservationSystem is the entrance of the system.
 */
class ReservationSystem {

    /**
     * Simply displays the reservation system menu,take user's choice input and pass into method.
     * Contains the plane object and file handler.
     * @param args The file stored prior reservations
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {

        //plane, like real plane
        Plane plane = new Plane();

        //to get prior reservation from file
        File inputFile = new File(args[0]);
        FileHandler fileHandle = new FileHandler(inputFile, plane);
        fileHandle.readFile();

        //ready take user choice input
        Scanner userInput = new Scanner(System.in);

        //while loop to make sure the system only stop when quit
        boolean reEnter = true;
        while (reEnter) {
            System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart,\n" +
                    "Print [M]anifest, [Q]uit");
            //get input & change to uppercase
            String choice = userInput.nextLine().toUpperCase();

            //call method
            switch (choice) {
                case "P":
                    System.out.println(plane.addPassenger());
                    break;
                case "G":
                    plane.addGroup();
                    break;
                case "C":
                    System.out.println(plane.cancelReservation());
                    break;
                case "A":
                    plane.seatAvailabilityChart();
                    break;
                case "M":
                    plane.printManifest();
                    break;
                case "Q":
                    fileHandle.saveFile(plane);//save current all reservations to file
                    reEnter = false;//quit, stop while loop
                    System.out.println("Thanks for using.");
                    break;
                default:
                    //make sure valid input
                    System.out.println("Wrong input. Please re-enter.");
                    break;
            }//end switch
        }//end while
    }//end main

}