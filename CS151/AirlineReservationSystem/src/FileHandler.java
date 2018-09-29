import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * <h1>FileHandler</h1>
 * FileHandler is used to take care all the operations to reservation file.
 */public class FileHandler {

    File file;
    Plane plane;


    FileHandler(File _file, Plane _plane) {
        file = _file;
        plane = _plane;
    }

    /**
     * <h1>Core method</h1>
     */

    /**
     * This is the method that save the reservation information before quit.
     * @param _plane This is the plane object, whose reservation info will be saved
     * @throws IOException
     */

    public void saveFile(Plane _plane) throws IOException {
        FileWriter fWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        saveClass(_plane.first,bWriter);
        saveClass(_plane.economy,bWriter);
        System.out.println("Reservation saved.");
        bWriter.close();
        fWriter.close();

    }

    /**
     * This is the method that read and load the prior reservation info from file.
     * It run at the beginning of the program after the plane is created.
     * @throws IOException
     */

    public void readFile() throws IOException {
        String pName= "";
        String serviceClass;
        int seatRow;
        int seatColumn;
        String groupName;

        Scanner fileRead;
        try {
            fileRead = new Scanner(file);
        } catch (FileNotFoundException ex) {
            file.createNewFile();
            fileRead = new Scanner(file);
        }
        fileRead.useDelimiter(",");

        while (fileRead.hasNext()) {
            pName = fileRead.next();
            serviceClass = fileRead.next();
            seatRow = fileRead.nextInt();
            seatColumn = fileRead.nextInt();
            groupName=fileRead.next();
            addPassengerToPlane(new Passenger(pName,serviceClass,seatRow,seatColumn,groupName));
        }
        fileRead.close();
    }

    /**
     * <h1>Assistance/partial method</h1>
     */

    /**
     * This is the method to write the reservation of a specific service class into the file.
     * @param seats This is the first/economy class array, to get the access to reservation.
     * @param _bWriter BufferedWriter for file write.
     * @exception IOException
     */

    public void saveClass(Seat[][] seats,BufferedWriter _bWriter)throws IOException{

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].getPassenger() != null) {
                    _bWriter.write(seats[i][j].getPassenger().getName()
                            + "," + seats[i][j].getPassenger().getClassService()
                            + "," + seats[i][j].getPassenger().getRow()
                            + "," + seats[i][j].getPassenger().getColumn()
                            + "," + seats[i][j].getPassenger().getGroupName()+",");
                }
            }
        }
    }

    /**
     *This method is to check if a group is already exists inside of the "groups" (store all reservation group)
     * @param _groupName This is the name of the group need to be checked
     * @return boolean The result of if the group exists
     */

    public boolean checkGroupExist(String _groupName){
        for (int i = 0;i<plane.groups.size();i++){
            if (plane.groups.get(i).getGroupName().equals(_groupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is to add a prior passenger reservation into the current plane
     * It contains the branch of adding a passenger into a reservation group
     * @param temp This is the passenger object contains passenger & reservation info
     */

    public void addPassengerToPlane(Passenger temp){
        //class
        Seat[][] seats = plane.getSeatList(temp.getClassService());
        seats[temp.getRow()][temp.getColumn()].setPassenger(temp);

        if (!temp.getGroupName().equals("")) {
            Group tempGroup = getGroup(temp.getGroupName(), temp.getClassService());
            tempGroup.passengers.add(temp);
        }
        plane.addPassengerClassCounter(temp.getClassService(), temp.getColumn());
    }

    /**
     * This method is to get the access to the group object that the passenger will be added
     * It will check if the group already exists,
     * If not, create a new group and new passenger list and added
     * @param _groupName This is the name of the group
     * @param _classService This is the service class of the group, for create a new group when it does not exist
     * @return Group A group object.
     */

    public Group getGroup(String _groupName, String _classService){
            if(!checkGroupExist(_groupName)){ //if not exists
                ArrayList<Passenger> tempList = new ArrayList<>();
                Group temp = new Group(_groupName,_classService,tempList);
                plane.groups.add(temp);
                return temp;
            }else{
                return plane.groups.get(plane.findGroupIndex(_groupName));
        }
    }
}