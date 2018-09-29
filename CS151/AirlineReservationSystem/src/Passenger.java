/**
 * <h1>Passenger</h1>
 * This class is to store the information of a single passenger
 */
public class Passenger {

    //Passenger info
    private String name;
    private String classService;
    private String groupName;

    //Seat info, mainly for group passengers
    private int seatRow;
    private int seatColumn;

    /**
     * Constructor when only know passenger name and service class
     * @param _name Passenger name
     * @param _classService Passenger's service class first/economy
     */

    Passenger(String _name, String _classService) {

        this(_name,_classService,0,0,null);
    }

    /**
     * Constructor when know passenger name, service class and seats
     * @param _name Passenger's name
     * @param _classService Passenger's service class
     * @param _seatRow Passenger's seat row number
     * @param _seatColumn Passenger's seat column number
     */
    Passenger(String _name, String _classService, int _seatRow, int _seatColumn) {

        this(_name,_classService,_seatRow,_seatColumn,null);
    }

    /**
     * Constructor when know passenger's information above also group name
     * @param _name Passenger's name
     * @param _classService Passenger's service class
     * @param _seatRow Passenger's seat row number
     * @param _seatColumn Passenger's seat column number
     * @param _groupName Passenger's group name
     */

    Passenger(String _name, String _classService, int _seatRow, int _seatColumn,String _groupName){
        name = _name;
        classService = _classService;
        seatRow=_seatRow;
        seatColumn=_seatColumn;
        groupName = _groupName;
    }

    //setter

    /**
     * Setter: set Passenger's name
     * @param name Passenger's name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setter: set Passenger's service class to passenger
     * @param classService Passenger's service class
     */

    public void setClassService(String classService) {
        this.classService = classService;
    }

    /**
     * Setter: set Passenger's seat row number to passenger
     * @param row Passenger's seat row number
     */

    public void setRow(int row) {
        this.seatRow = row;
    }

    /**
     * Setter: set Passenger's seat column number to passenger
     * @param seatColumn Passenger's seat column number
     */

    public void setColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    //getter

    /**
     * Getter: get passenger's name
     * @return String Passenger's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter: get passenger's group name
     * @return String Passenger's group name
     */

    public String getGroupName() {
        return groupName;
    }

    /**
     * Getter: get passenger's service class name, first/econmy
     * @return String Passenger's service class name
     */

    public String getClassService() {
        return classService;
    }

    /**
     * Getter: get passenger's seat row number
     * @return Passenger's seat row number
     */

    public int getRow() {
        return seatRow;
    }

    /**
     * Getter: get passenger's seat column number
     * @return Passenger's seat column number
     */

    public int getColumn() {
        return seatColumn;
    }

}