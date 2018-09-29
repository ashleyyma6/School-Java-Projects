import java.util.ArrayList;

/**
 * <h1>Group</h1>
 * This class is for store the information of a group of passengers
 */
public class Group {

    private String classService;
    private String groupName;
    ArrayList<Passenger> passengers = new ArrayList<>();

    /**
     * Constructor for a group
     * @param _groupName The name of a group
     * @param _classService First/economy class
     * @param _passengers The list contain all group passengers
     */
    Group(String _groupName, String _classService, ArrayList<Passenger> _passengers){
        groupName=_groupName;
        classService = _classService;
        passengers = _passengers;
    }

    /**
     * Getter: to get the first/economy class of the group
     * @return String The name of the class
     */
    String getClassService(){
        return classService;
    }

    /**
     * Getter: to get the name of the group
     * @return String the name of the group
     */
    String getGroupName(){
        return groupName;
    }
}