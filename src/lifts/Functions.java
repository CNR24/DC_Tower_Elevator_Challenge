package lifts;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Functions{


    // ***********************************  Variables    *******************************************
    int[][] lifts = new int[55][7];

    public int currentFloor = 0, destinationFloor = 0;

    // ***********************************    Enums    *******************************************

    enum Direction {
        UP, DOWN
    }
    // ****************************     Check Employee      ***************************************

    void checkEmployee() {
        // In this function has been checked if this Person an Employee from IBM

        Scanner scan0 = new Scanner(System.in);
        System.out.println("IBM Employee?");
        int i = scan0.nextInt();
        if (i == 0) {
            System.out.println("No Access");
            System.exit(0);   // If the Person is not Employee, stop Program.
        } else if (i == 1) {
            System.out.println("Access is successfully" + "\nIBM office on the 35th floor ");
        } else {
            System.out.println("Employee check error!");
        }
    }

    // *****************************   Check current floor    *************************************

    void checkCurrentFloor() {
        do {
            System.out.println("\nPlease choose your current floor");
            Scanner scan2 = new Scanner(System.in);
            currentFloor = scan2.nextInt();
        } while (currentFloor < 0 || currentFloor > 54);
    }

    // *****************************   Check direction floor    *************************************

    void checkDirectionFloor() {
        do {
            System.out.println("\nPlease choose your direction floor");
            Scanner scan3 = new Scanner(System.in);
            destinationFloor = scan3.nextInt();
        } while (destinationFloor < 0 || destinationFloor > 54);
    }

    // ************************    Crate all Elevators with 0    **********************************

    void generateElevatorSystem() {

        for (int r = 0; r < lifts.length; r++) {
            for (int c = 0; c < lifts[r].length; c++) {
                lifts[r][c] = 0;
            }
        }
    }

    // ***************************  Share Elevators in Building    *******************************

    void shareLifts() {
        for (int rmNr = 0; rmNr < 7; rmNr++) {

            Random random = new Random();
            int randomfloor = random.nextInt(55);

            if (lifts[randomfloor][rmNr] == 0) {
                lifts[randomfloor][rmNr] = 1;
            }
        }
    }
    // *********************************    Print Elevators    ***********************************

    void printout() {
        for (int r = 0; r < lifts.length; r++) {
            System.out.println("\n");
            System.out.print("Floor: " + r + "\t");
            for (int c = 0; c < lifts[r].length; c++) {
                System.out.print(lifts[r][c] + "  ");
            }
        }
    }

    // *********************************    Find Direction    ***********************************
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

    // ***********************    Check if any Elevator on this Floor    ****************************

    public boolean isElevatorOnFloor(int[] elevatorFloor) {

        int[] sortedArray = new int[0];
        sortedArray = elevatorFloor;
        int clone_Array[] = sortedArray.clone();
        java.util.Arrays.sort(clone_Array);
        return Arrays.binarySearch(clone_Array, 1) > -1;

        /*

         for (int i = 0; i < elevatorFloor.length; i++) {
            if (elevatorFloor[i] == 1) {
                return true;
            } else {
                return false;
            }
        return false;
        }

        */
    }

    // ****************************    Check if the array Area    ************************************

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
                    System.out.println("\nElevator is at the floor: " + rowAbove + "and moving to your current floor:"+currentFloor+"!");
                    return rowAbove;
                }
            }
            if (isRowWithinGridLimit(area.length, rowBelow)) {                       // Checks in the direction DOWN
                if (isElevatorOnFloor(area[rowBelow])) {                             // If found any elevator on this floor. return it
                    System.out.println("\nElevator is at the floor: " + rowBelow + "and moving to your current floor:"+currentFloor+"!");
                    return rowBelow;
                }
            }

            i++;                                                                     // Otherwise increase the i and check next floor UP and DOWN
            rowAbove = floor + i;
            rowBelow = floor - i;

        }
        return -1;
    }

    // ******************************    Check Elevator Number    *************************************
    // Floor found, now find the Column(Elevator 1/2/3...7)

    private int findColumnOfLift(int[]liftcolumn){
        for(int i = 0;i<liftcolumn.length;i++){
            if(liftcolumn[i]==1){
                return i;
            }
        }
        return 0;
    }

    // ***********************   Move the Elevator to the Destination   ********************************

    public void callLiftToCurrentFloor(int destinationFloor, int selectedLift) {
        int columnOfLiftFloor = findColumnOfLift(lifts[selectedLift]);
        lifts[selectedLift][columnOfLiftFloor] = 0;                         // Make old place of elevator 0
        if (columnOfLiftFloor != 0) {                                       // Check if they are an elevator
            lifts[destinationFloor][columnOfLiftFloor] = 1;                 // Make new place of elevator 1
            System.out.println("\nElevator arrived!");
        }
    }

    // ***********************   Move the Elevator to the Destination   ********************************

    // f.moveliftToDestinationFloor(f.destinationFloor,f.currentFloor);
    public void moveliftToDestinationFloor(int destinationFloor, int selectedLift) {

        int columnOfLiftFloor = findColumnOfLift(lifts[selectedLift]);
        lifts[selectedLift][columnOfLiftFloor] = 0;                         // Make old place of elevator 0
        if (columnOfLiftFloor != 0)                                         // Check if they are an elevator
            lifts[destinationFloor][columnOfLiftFloor] = 1;                 // Make new place of elevator 1
        System.out.println("\nElevator is on the "+destinationFloor+". floor now\n\nGood bye!");

    }
}