package lifts;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Functions{


    // ***********************************  Variables    *******************************************
    int[][] lifts = new int[55][7];

    int Person = 0, currentFloor = 0, destinationFloor = 0;


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
            System.out.println("Please choose your current floor");
            Scanner scan2 = new Scanner(System.in);
            currentFloor = scan2.nextInt();
        } while (currentFloor < 0 || currentFloor > 54);
    }

    // *****************************   Check direction floor    *************************************

    void checkDestinationFloor() {
        do {
            System.out.println("Please choose your direction floor");
            Scanner scan3 = new Scanner(System.in);
            destinationFloor = scan3.nextInt();
        } while (destinationFloor < 0 || destinationFloor > 54);
    }

    // ************************    Crate all Elevators with 0    **********************************

    void generateFloor() {

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
            System.out.print("Stock: " + r + "   ");
            for (int c = 0; c < lifts[r].length; c++) {
                System.out.print(lifts[r][c] + "  ");
            }
        }
    }

    // *********************************    Find Direction    ***********************************
    void findDirection() {

        if (currentFloor > destinationFloor) {
            Functions.Direction directionDown = Functions.Direction.DOWN;
            System.out.println("[current floor: " + currentFloor + ", destination floor: "
                    + destinationFloor + ", direction:" + directionDown + "]");
        } else if (currentFloor < destinationFloor) {
            Functions.Direction directionUp = Functions.Direction.UP;
            System.out.println("[current floor: " + currentFloor + ", destination floor: "
                    + destinationFloor + ", direction:" + directionUp + "]");
        } else {
            System.out.println("[You are already on the direction floor]");
        }
    }
    // *********************************    Check Avaible Elevators    ****************************************


    public boolean isElevatorOnFloor(int[] elevatorFloor) {
        for (int i = 0; i < elevatorFloor.length; i++) {
            if (elevatorFloor[i] == 1) {
                return true;
            } else {
                return false;
            }
      /*  int[] sortedArray = new int[0];
        sortedArray = elevatorFloor;
        java.util.Arrays.sort(sortedArray);
        return Arrays.binarySearch(sortedArray, 1) > -1;*/
        }
        return false;
    }

    public boolean isRowWithinGridLimit(int numRows, int row) {
        return row > -1 && row < numRows;
    }

    public int findCloserLift(int floor, int[][] area) {
        if (isElevatorOnFloor(area[floor])) {
            System.out.println("da da da");
            return 0;
        }
        int i = 1;
        int rowAbove = floor + i;
        int rowBelow = floor - i;

        while (isRowWithinGridLimit(area.length, rowAbove) || isRowWithinGridLimit(area.length, rowBelow)) {
            if (isRowWithinGridLimit(area.length, rowAbove)) {
                if (isElevatorOnFloor(area[rowAbove])) {
                    System.out.println("Asansör " + rowAbove + ". katta!");
                    return rowAbove;
                }
            }
            if (isRowWithinGridLimit(area.length, rowBelow)) {
                if (isElevatorOnFloor(area[rowBelow])) {
                    System.out.println("Asansör " + rowBelow + ". katta!");
                    return rowBelow;
                }
            }

            i++;
            rowAbove = floor + i;
            rowBelow = floor - i;

        }
        return -1;
    }
    private int findColumnOfLift(int[]liftcolumn){
        for(int i = 0;i<liftcolumn.length;i++){
            if(liftcolumn[i]==1){
                return i;
            }
        }
        return 0;
    }

    public void moveliftToFloor(int destinationFloor, int selectedLift) {

        int columnOfLiftFloor = findColumnOfLift(lifts[selectedLift]);
        lifts[selectedLift][columnOfLiftFloor] = 0;
        lifts[destinationFloor][columnOfLiftFloor] = 1;
    }


}
    /*

    public int findFloorWhereLiftIs(int[] liftColumn) {
        for (int i = 0; i < liftColumn.length; i++) {
            if (liftColumn[i] == 1) {
                System.out.println("i = "+i);
                return i;
            }

        }
        return -1;
    }

        int countOfNextCloserLift = findCloserLift(currentFloor, lifts);  //  Find out next elevator is near




    public int findCloserLift(int destinationFloor) {
        int liftMinimumJourney = 0;
        int minDistance = 55;

        for(int lift= 0; lift < lifts.length; lift++){
            int indexOfLift = findFloorWhereLiftIs(lifts[lift]);
            int distance = Math.abs(destinationFloor-indexOfLift);
            if(distance<minDistance){
                minDistance=distance;
                liftMinimumJourney=lift;
            }

        }
        System.out.println("Lift Minimum = "+liftMinimumJourney);
        return liftMinimumJourney;
    }
    public void moveliftToFloor(int destinationFloor, int selectedLift){
        int floorWhereLiftIs = findFloorWhereLiftIs(lifts[selectedLift]);
        lifts[selectedLift][floorWhereLiftIs]= 0;
        lifts[selectedLift][destinationFloor]= 1;

    }

    public int findFloorWhereLiftIs(int[] liftColumn) {
        for(int i=0; i<liftColumn.length; i++){
            if(liftColumn[i]==1){
                return i;
            }
        }
        return 0;
    }

}
*/