/**
 * <h1>Seat</h1>
 * This class is the object for every seats in the plane.
 */
public class Seat {
    private Passenger passenger = null;
    private int seatRow;
    ;
    private int seatColumn;
    private String seatPref;
    private String classService;

    Seat(int _seatRow, int _seatColumn, String _classService) {
        seatRow = _seatRow;
        seatColumn = _seatColumn;
        classService = _classService;
    }

    /**
     * Setter to get seat row number
     * @return int seat row number
     */
    int getSeatRow() {
        return seatRow;
    }

    /**
     * Getter: to get the seat row number for display to the user
     * @return int The display-use seat row number
     */

    int getDisplaySeatRow() {
        switch (classService) {
            case "first":
                return getSeatRow() + 1;
            case "economy":
                return getSeatRow() + 10;
        }
        return 0;
    }

    /**
     * Getter: to get the column number of the seat
     * @return int The seat column number
     */

    int getSeatColumn() {
        return seatColumn;
    }

    /**
     * Getter: to get the seat preference type of the seat
     * @return String Seat preference type
     */
    String getSeatPref() {
        return seatPref;
    }

    /**
     * Getter: to get the seat letter for display to user
     * @return String seat letter
     */

    String getSeatLetter() {
        String seatLetter;
        switch (seatColumn) {
            case 0:
                seatLetter = "A";
                break;
            case 1:
                seatLetter = "B";
                break;
            case 2:
                seatLetter = "C";
                break;
            case 3:
                seatLetter = "D";
                break;
            case 4:
                seatLetter = "E";
                break;
            case 5:
                seatLetter = "F";
                break;
            default:
                seatLetter = "";
                break;
        }
        return seatLetter;
    }

    /**
     * Getter: to get the passenger of the seat
     * @return Passenger The current passenger in this seat
     */

    Passenger getPassenger() {
        return passenger;
    }

    /**
     * Getter: get the service class name, economy/first
     * @return String The service class name
     */
    String getClassService() {
        return classService;
    }

    /**
     * Setter: to set the seat row number
     * @param _seatRow The seat row number
     */
    void setSeatRow(int _seatRow) {
        seatRow = _seatRow;
    }

    /**
     * Setter: to set the seat column number
     * @param _seatColumn The seat column number
     */

    void setSeatColumn(int _seatColumn) {
        seatColumn = _seatColumn;
    }

    /**
     * Setter: to set the seat preference
     * @param _seatPref The seat preference type
     */

    void setSeatPref(String _seatPref) {
        seatPref = _seatPref;
    }

    /**
     * Setter: to set passenger to the seat
     * @param _newPassenger Passenger
     */

    void setPassenger(Passenger _newPassenger) {
        passenger = _newPassenger;
    }
}
