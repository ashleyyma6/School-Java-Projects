import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Plane</h1>
 * This class implements all the core functions of the reservation system based on the idea of a real plane
 */
public class Plane {
    //Seat array to store seats
    Seat[][] first;
    Seat[][] economy;

    //To save Groups
    ArrayList<Group> groups = new ArrayList<>();

    //To track the num of empty seats
    private int economySeats;
    private int firstSeats;

    //Seat number counter
    private int firstWSeats;
    private int firstASeats;
    private int economyWSeats;
    private int economyCSeats;
    private int economyASeats;
    private int numOfAdjacentSeats;

    /**
     * The constructor of the plane prepared the basic setting of seats and counter.
     */
    //constructor
    Plane() {
        first = new Seat[2][4];//2 rows, 4 columns
        economy = new Seat[20][6];//20 rows, 6 columns

        firstSeats = first.length * first[0].length;//8
        economySeats = economy.length * economy[0].length;//120

        firstWSeats = first.length * 2;//4
        firstASeats = first.length * 2;//4
        economyWSeats = economy.length * 2;//40
        economyCSeats = economy.length * 2;//40
        economyASeats = economy.length * 2;//40

        setAllSeats();
    }

    /**
     * <h1>Core method</h1>
     */

    /**
     * This is the method of adding individual passenger.
     * Take the passenger info input, find empty seats, make reservation, print reservation info
     * Variation: 1.Wrong service class input, 2. Wrong seats input or preferred seats are full
     * @return String the seats info of the reservation, if reservation succeed
     */

    public String addPassenger() {

        String result = "";

        //get passenger info
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String pName = scanner.nextLine();

        //class is always change to lower case, for easy
        System.out.print("Class: ");
        String pClass = scanner.nextLine().toLowerCase();
        while(!(pClass.equals("first")||pClass.equals("economy"))) {
            System.out.print("Please enter either first or economy: ");
            pClass = scanner.nextLine().toLowerCase();
        }
        while (getClassCounter(pClass) < 1) {
            System.out.print("No empty seats in this service class.\nPlease choose another class: ");
            pClass = scanner.nextLine().toLowerCase();
        }
        //Passenger passenger = new Passenger(pName, pClass);

        boolean stop = false;//to check re-enter, add variation
        while (!stop) {
            //seat preference is always upper case, for easy
            System.out.print("Seat preference W(indow), C(enter), or A(aisle):");
            String pSeatPref = scanner.nextLine().toUpperCase();

            if (getSeatCounter(pClass, pSeatPref) < 1) {
                System.out.println("No empty seats in preference. Please re-enter a preference.");
            } else {
                Seat tempSeat = getNextEmpty(getSeatList(pClass), pSeatPref);
                tempSeat.setPassenger(new Passenger(pName, pClass,tempSeat.getSeatRow(),tempSeat.getSeatColumn()));
                addPassengerClassCounter(pClass, tempSeat.getSeatColumn());
                stop = true;
                return "Seat number:" + (tempSeat.getDisplaySeatRow()) + tempSeat.getSeatLetter();
            }
        }
        return "";
    }

    /**
     * This is the method of adding a group of passengers
     * Take the group info input, find adjacent seats, make reservations, print reservation info
     * Variation: Group name cannot be empty
     */

    public void addGroup() {

        //get group info
        Scanner scanner = new Scanner(System.in);
        System.out.print("Group name:");
        String groupName = scanner.nextLine();
        while(groupName.equals("")){
            System.out.println("Group name cannot be ''.");
            groupName = scanner.nextLine();
        }

        System.out.print("Names:");
        String names = scanner.nextLine();
        String[] nameList = names.split(",");

        //class is always change to lower case, for easy
        System.out.print("Class: ");
        String pClass = scanner.nextLine().toLowerCase();
        while(!(pClass.equals("first")||pClass.equals("economy"))) {
            System.out.print("Please enter either first or economy: ");
            pClass = scanner.nextLine().toLowerCase();
        }

        ArrayList<Passenger> gPassenger = new ArrayList<>();
        for (int i = 0; i < nameList.length; i++) {
            gPassenger.add(new Passenger(nameList[i], pClass));
        }

        //to store groups
        Group group = new Group(groupName, pClass, gPassenger);


        int totalPassenger = nameList.length;
        int seatedPassenger = 0;

        if (getClassCounter(pClass) < gPassenger.size()) {
            System.out.println("No enough seats for the group.");
        } else {
            //fill seats
            while (seatedPassenger < totalPassenger) {
                Seat beginningSeat = getAdjacentSeats(getSeatList(pClass),(totalPassenger-seatedPassenger));
                Seat[][] tempSeats = getSeatList(pClass);
                int seatRow = beginningSeat.getSeatRow();
                int seatColumn = beginningSeat.getSeatColumn();

                for (int i = 0; (i < numOfAdjacentSeats) && (seatedPassenger < totalPassenger); i++) {
                    tempSeats[seatRow][seatColumn].setPassenger(new Passenger(nameList[i], pClass, seatRow, seatColumn, groupName));
                    System.out.println("Name:" + nameList[i] + " Seat number:" + tempSeats[seatRow][seatColumn].getDisplaySeatRow() + tempSeats[seatRow][seatColumn].getSeatLetter());
                    addPassengerClassCounter(pClass,seatColumn);
                    seatedPassenger++;
                    seatColumn++;
                    }
                }
            groups.add(group);
            }
        }

    /**
     * This is the method of operate cancellation to both group and individual passenger
     * User input to choose cancel group or individual
     * Individual: take name input, find passenger, cancel
     * Group: take name input, find group, cancel every passenger
     * Variation: Group name cannot be empty
     * @return String Cancellation succeed message
     */

    public String cancelReservation() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Cancellation for Individual [P]assenger or [G]roup?");
        String choice = userInput.nextLine().toUpperCase();

        String result = "";
        //search customer
        switch (choice) {
            case "P":
                System.out.print("Passenger name: ");
                String name = userInput.nextLine();

                Seat temp;
                //cannot cancel a person in group
                if(findSeat(name,first)!= null){
                    temp = findSeat(name,first);
                    temp.setPassenger(null);
                    cancelPassengerClassCounter(temp.getClassService(),temp.getSeatColumn());
                    return "Cancellation succeed. ";
                }else if (findSeat(name,economy)!= null){
                    temp = findSeat(name,economy);
                    cancelPassengerClassCounter(temp.getClassService(),temp.getSeatColumn());
                    temp.setPassenger(null);
                    return "Cancellation succeed. ";
                }

            case "G":
                System.out.print("Group name: ");
                name = userInput.nextLine();
                while(name.equals("")){
                    System.out.println("Group name cannot be ''.");
                    name = userInput.nextLine();
                }
                //cannot cancel a person in group

                int groupIndex = findGroupIndex(name);
                Group tempGroup = groups.get(groupIndex);
                Seat[][] tempSeats = getSeatList(tempGroup.getClassService());
                for (int i = 0; i < tempGroup.passengers.size(); i++) {
                    tempSeats[tempGroup.passengers.get(i).getRow()][tempGroup.passengers.get(i).getColumn()].setPassenger(null);
                    cancelPassengerClassCounter(tempGroup.getClassService(),tempGroup.passengers.get(i).getColumn());
                }
                groups.remove(groupIndex);
                return "Cancellation succeed. ";
            default:
                System.out.println("Please enter your choice.");
                break;
        }
        return result;
    }

    /**
     * This is the method that print the available seats of the whole plane
     * Call method to print the seats of every class
     */

    public void seatAvailabilityChart() {
        System.out.println("Available seats chart");
        System.out.println("First: ");
        printClassAvailableSeat(first);
        System.out.println();
        System.out.println("Economy:");
        printClassAvailableSeat(economy);
        System.out.println();
    }

    /**
     * This is the method that print the seated seats and passenger of the whole plane
     * Call method to print the seats of every class
     */

    public void printManifest() {
        System.out.println("Manifest");
        System.out.println("First: ");
        printClassManifest(first);
        System.out.println();
        System.out.println("Economy:");
        printClassManifest(economy);
        System.out.println();
    }

    /**
     * <h1>Assistance/partial method</h1>
     */

    /**
     * This method is to find the index of a specific group in the "groups"
     * @param _groupName The name of the group we want to find
     * @return int The index of a specific group in the "groups"
     */
    public int findGroupIndex(String _groupName){
        for (int i = 0;i<groups.size();i++){
            if (groups.get(i).getGroupName().equals(_groupName)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Getter
     * This method is to get the seats array of a specific class
     * @param serviceClass the string name of the class
     * @return Seat[][] the seats array of the class
     */

    public Seat[][] getSeatList(String serviceClass) {
        if (serviceClass.equals("first")) {
            return first;
        }
        return economy;
    }

    /**
     * Getter
     * This method is to get the number of remaining seats of a class
     * This is a getter for counters, counter is for make sure there is empty seats
     * @param serviceClass Service class first/economy
     * @return int the number of remaining seats of a class
     */

    public int getClassCounter(String serviceClass) {
        if (serviceClass.equals("first")) {
            return firstSeats;
        }
        if (serviceClass.equals("economy")) {
            return economySeats;
        }
        return 0;
    }

    /**
     * Getter
     * This method is to get the number of remaining seats of a preferred seat in a class
     * This is a getter for counters, counter is for make sure there is empty seats
     * @param serviceClass Service class first/economy
     * @param seatPref The preferred seat
     * @return int The number of remaining seats of a preferred seat in a class
     */

    public int getSeatCounter(String serviceClass, String seatPref) {
        switch (serviceClass) {
            case "first":
                switch (seatPref) {
                    case "W":
                        return firstWSeats;
                    case "A":
                        return firstASeats;
                }
                break;
            case "economy":
                switch (seatPref) {
                    case "W":
                        return economyWSeats;
                    case "C":
                        return economyCSeats;
                    case "A":
                        return economyASeats;
                }
                break;
        }
        return 0;
    }

    /**
     * This method is to change the counter of a preference seat in a class
     * @param serviceClass Service class first/economy
     * @param seatColumn To get the type of the preferred seat
     */

    public void addPassengerClassCounter(String serviceClass, int seatColumn) {
        switch (serviceClass) {
            case "first":
                firstSeats--;
                switch (seatColumn) {
                    case 0:
                    case 3:
                        firstWSeats--;
                        break;
                    case 1:
                    case 2:
                        firstASeats--;
                        break;
                }
                break;
            case "economy":
                economySeats--;
                switch (seatColumn) {
                    case 0:
                    case 5:
                        economyWSeats--;
                        break;
                    case 1:
                    case 4:
                        economyCSeats--;
                        break;
                    case 2:
                    case 3:
                        economyASeats--;
                        break;
                }
                break;
            default:
                break;
        }
    }

    /**
     * This method is to get the next empty preferred seat for add individual passenger
     * @param seats This is first/economy class array
     * @param seatPref This is the type of the preferred seats
     * @return Seat Get the empty seat that fits requirement
     */

    public Seat getNextEmpty(Seat[][] seats, String seatPref) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].getPassenger() == null && seats[i][j].getSeatPref().equals(seatPref)) {
                    {
                        return seats[i][j];
                    }
                }
            }
        }
        return seats[0][0];
    }

    /**
     * This is the method to initialize all the seats on the plane when the plan is created
     * This run before any reservation operation.
     */

    public void setAllSeats() {
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[0].length; j++) {
                first[i][j] = new Seat(i, j, "first");
                switch (j) {
                    case 0:
                    case 3:
                        first[i][j].setSeatPref("W");
                        break;
                    case 1:
                    case 2:
                        first[i][j].setSeatPref("A");
                        break;
                }
            }
        }
        for (int i = 0; i < economy.length; i++) {
            for (int j = 0; j < economy[0].length; j++) {
                economy[i][j] = new Seat(i, j, "economy");
                switch (j) {
                    case 0:
                    case 5:
                        economy[i][j].setSeatPref("W");
                        break;
                    case 1:
                    case 4:
                        economy[i][j].setSeatPref("C");
                        break;
                    case 2:
                    case 3:
                        economy[i][j].setSeatPref("A");
                        break;
                }
            }
        }
    }

    /**
     * This is the method to find the adjacent seats for group passengers.
     * The number of the adjacent seats will be set inside of the method
     * @param seats This is first/economy class.
     * @param pSize This is the peopl size of the group
     * @return Seat get the beginning seats of the adjacent seats
     */

    public Seat getAdjacentSeats(Seat[][] seats, int pSize) {
        int counter = 0;
        int highestNumAdjacentSeats = 0;
        Seat beginningSeat = null;
        for (int i = 0; i < seats.length; i++) {
            counter = 0;
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].getPassenger() == null) {
                    counter++;
                    int m = j + 1;
                    while (m < seats[0].length) {
                        if (seats[i][m].getPassenger() == null) counter++;
                        m++;
                    }
                    if (counter >= pSize) {
                        numOfAdjacentSeats = counter;
                        return seats[i][j];
                    }

                    if (counter > highestNumAdjacentSeats) {
                        highestNumAdjacentSeats = counter;
                        beginningSeat = seats[i][j];
                    }
                }
            }
        }
        numOfAdjacentSeats = highestNumAdjacentSeats;
        return beginningSeat;
    }

    /**
     * This method is to change the counter when make cancellations
     * @param serviceClass First/economy class, for class empty seats counter
     * @param seatColumn The type of the preferred seats, for preferred seats counter in the class
     */

    public void cancelPassengerClassCounter(String serviceClass, int seatColumn) {

        switch (serviceClass) {
            case "first":
                firstSeats++;
                switch (seatColumn) {
                    case 0:
                    case 3:
                        firstWSeats++;
                        break;
                    case 1:
                    case 2:
                        firstASeats++;
                        break;
                }
                break;
            case "economy":
                economySeats++;
                switch (seatColumn) {
                    case 0:
                    case 5:
                        economyWSeats++;
                        break;
                    case 1:
                    case 4:
                        economyCSeats++;
                        break;
                    case 2:
                    case 3:
                        economyASeats++;
                        break;
                }
                break;
            default:
                break;
        }
    }

    /**
     * This method is to print the manifest of a class
     * @param seats Economy/First class
     */

    public void printClassManifest(Seat[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].getPassenger() != null) {
                    System.out.print(seats[i][j].getDisplaySeatRow() + seats[i][j].getSeatLetter() + ": " + seats[i][j].getPassenger().getName() + " ");
                }
            }
        }
    }

    /**
     * This method is to print the empty seats of a class
     * @param seats Economy/First class
     */

    public void printClassAvailableSeat(Seat[][] seats){
        boolean addComma = false;
        for (int i = 0; i < seats.length; i++) {
            System.out.print(seats[i][0].getDisplaySeatRow() + ": ");
            addComma = false;
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].getPassenger() == null) {
                    if (addComma) {
                        System.out.print(", ");
                    }
                    System.out.print(seats[i][j].getSeatLetter());
                    addComma = true;
                }
            }
            System.out.println();
        }
    }

    /**
     * This method is to find a seat for a passenger during cancellation
     * Variation:
     * @param pName Passenger name
     * @param seats Passenger's first/ecomoy class
     * @return Seat the seat that the passenger reserved
     */

    public Seat findSeat(String pName, Seat[][] seats){
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j <seats[0].length; j++) {
                if (seats[i][j].getPassenger() != null && seats[i][j].getPassenger().getName().equals(pName)) {
                    if (!seats[i][j].getPassenger().getGroupName().equals("")) {
                        System.out.println ("Cannot cancel a person from a group.");
                        return null;
                    } else {
                        return seats[i][j];
                    }
                }
            }
        }
        return null;
    }
}

