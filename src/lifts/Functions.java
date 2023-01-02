package lifts;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Functions{


    // ***********************************  Variables    *******************************************
    int[][] lifts = new int[55][7];

    public int currentFloor = 0, destinationFloor = 0;

    // ***********************************    Enums    *******************************************

    enum Direction {
        UP, DOWN
    }

    // ******************************     Check employees     ***************************************

    void checkEmployee() throws InterruptedException {
        // In this function has been checked if this person an employee from IBM

        Scanner scan0 = new Scanner(System.in);
        System.out.print("\nPlease typ True for IBM Employee and false otherwise:");
        boolean employee = scan0.nextBoolean();
        System.out.println("\nChecking for IBM Employee...");
        TimeUnit.SECONDS.sleep(1);
        if (employee == false) {
            System.out.println("No Access!\nYou are not an IBM employee");
            System.exit(0);   // If the Person is not Employee, stop Program.
        } else if (employee == true) {
            System.out.println("\nAccess is successfully!" + "\n\nIBM office on the 35th floor.");
        } else {
            System.out.println("Employee check error!");
        }
    }

    // *****************************   Check current floor    *************************************

    void checkCurrentFloor() {
        do {
            System.out.println("\n\nPlease choose your current floor");
            Scanner scan2 = new Scanner(System.in);
            currentFloor = scan2.nextInt();
        } while (currentFloor < 0 || currentFloor > 54);
    }

    // *****************************   Check destination floor    *************************************

    void checkDestinationFloor() {
        do {
            System.out.println("\nPlease choose your destination floor");
            Scanner scan3 = new Scanner(System.in);
            destinationFloor = scan3.nextInt();
        } while (destinationFloor < 0 || destinationFloor > 54);
    }

    // ************************    Crate all elevators with 0    **********************************

    void generateElevatorSystem() {

        for (int r = 0; r < lifts.length; r++) {
            for (int c = 0; c < lifts[r].length; c++) {
                lifts[r][c] = 0;
            }
        }
    }

    // ***********************  Share elevators randomly in the building    ****************************

    void shareLifts() {
        for (int rmNr = 0; rmNr < 7; rmNr++) {

            Random random = new Random();
            int randomfloor = random.nextInt(55);

            if (lifts[randomfloor][rmNr] == 0) {
                lifts[randomfloor][rmNr] = 1;
            }
        }
    }
    // *********************************    Print elevators    ***********************************

    void printout() {
        for (int r = 0; r < lifts.length; r++) {
            System.out.println("\n");
            System.out.print("Floor: " + r + "\t");
            for (int c = 0; c < lifts[r].length; c++) {
                System.out.print(lifts[r][c] + "  ");
            }
        }
    }

    // *********************************    Find direction    ***********************************
    void findDirection() {

        if (currentFloor > destinationFloor) {
            Functions.Direction directionDown = Functions.Direction.DOWN;
            System.out.println("\n[current floor: " + currentFloor + ", destination floor: "
                    + destinationFloor + ", direction:" + directionDown + "]");
        } else if (currentFloor < destinationFloor) {
            Functions.Direction directionUp = Functions.Direction.UP;
            System.out.println("\n[current floor: " + currentFloor + ", destination floor: "
                    + destinationFloor + ", direction:" + directionUp + "]");
        } else {
            System.out.println("\n[You are already on the direction floor]");
        }
    }

    // ***********************    Check if any elevator on selected floor    ****************************

    public boolean isElevatorOnFloor(int[] elevatorFloor) {

        int[] sortedArray = new int[0];
        sortedArray = elevatorFloor;
        int clone_Array[] = sortedArray.clone();
        java.util.Arrays.sort(clone_Array);
        return Arrays.binarySearch(clone_Array, 1) > -1;

    }

    // ***************************   Check if the array area is OK    *********************************

    // This Function checks whether a row or column is in the Area.
    // Returns false if anyone is no longer in the Area
    public boolean isRowWithinGridLimit(int numRows, int row) {
        return row > -1 && row < numRows;
    }

    // *************************    Check Closer Elevator Distance    *********************************


    public int findCloserLift(int floor, int[][] area) {
        if (isElevatorOnFloor(area[floor])) {               // Returns 0 the elevator on the current floor,
            System.out.println("\nElevator is arrived!");
            return 0;
        }
        int i = 1;                                          // If the elevator is not on the current floor, check next down and up floors
        int rowAbove = floor + i;
        int rowBelow = floor - i;

        while (isRowWithinGridLimit(area.length, rowAbove) || isRowWithinGridLimit(area.length, rowBelow)) {  // Checks the Array Limits
            if (isRowWithinGridLimit(area.length, rowAbove)) {                      // Checks in the direction UP
                if (isElevatorOnFloor(area[rowAbove])) {                            // If found any elevator on this floor. return it
                    System.out.println("\nNext elevator is at the "+ rowAbove + ". floor and moving to your current floor:"+currentFloor+"!");
                    return rowAbove;
                }
            }
            if (isRowWithinGridLimit(area.length, rowBelow)) {                       // Checks in the direction DOWN
                if (isElevatorOnFloor(area[rowBelow])) {                             // If found any elevator on this floor. return it
                    System.out.println("\nNext elevator is at the " + rowBelow + ". floor and moving to your current floor:"+currentFloor+"!");
                    return rowBelow;
                }
            }

            i++;                                                                     // Otherwise increase the i and check next floor UP and DOWN
            rowAbove = floor + i;
            rowBelow = floor - i;

        }
        return -1;
    }

    // ************************    Check elevator number with column    *******************************
    // Floor found, now find the Column(Elevator 1/2/3...7)

    private int findColumnOfLift(int[]liftcolumn){
        for(int i = 0;i<liftcolumn.length;i++){
            if(liftcolumn[i]==1){
                return i;
            }
        }
        return 0;
    }

    // *********************   Call the elevator to the current floor   ******************************

    public void callLiftToCurrentFloor(int destinationFloor, int selectedLift) {
        int columnOfLiftFloor = findColumnOfLift(lifts[selectedLift]);
        lifts[selectedLift][columnOfLiftFloor] = 0;                         // Make old place of elevator 0
        if (columnOfLiftFloor != 0) {                                       // Check if they are an elevator
            lifts[destinationFloor][columnOfLiftFloor] = 1;                 // Make new place of elevator 1
            System.out.println("\nElevator has arrived!");
        }
    }

    // *******************   Move the elevator to the destination floor   ******************************

    public void moveliftToDestinationFloor(int destinationFloor, int selectedLift) {

        int columnOfLiftFloor = findColumnOfLift(lifts[selectedLift]);
        lifts[selectedLift][columnOfLiftFloor] = 0;                         // Make old place of elevator 0
        if (columnOfLiftFloor != 0)                                         // Check if they are an elevator
            lifts[destinationFloor][columnOfLiftFloor] = 1;                 // Make new place of elevator 1
        System.out.println("\nElevator is on the "+destinationFloor+". floor now\n\nGood bye!");

    }
}