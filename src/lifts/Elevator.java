package lifts;

import java.util.concurrent.TimeUnit;

public class Elevator{


    public static void main(String[] args) throws InterruptedException {

        Functions f = new Functions();
       // f.checkEmployee();          // Check if this Person an Employee from IBM
        f.generateFloor();          // Generate all Floors and Elevators
        f.shareLifts();             // Share elevators randomly ind the Building
        f.printout();               // Print all elevators and floors to check if everything is OK

        System.out.println("\n\nButton gedrückt!");
        //TimeUnit.SECONDS.sleep(1);
        System.out.println("Aufzug kommt!");
        //TimeUnit.SECONDS.sleep(1);

        f.checkCurrentFloor();      // Check if the current floor between 0 and 54
        f.checkDestinationFloor();  // Check if the destination floor between 0 and 54
        f.findDirection();          // Find out which way the passenger is going

        int[][] lifts2 = new int[0][0];
        lifts2 = f.lifts;
        int newCurrentFloor = f.currentFloor;
        int newFindCloserLift = f.findCloserLift(newCurrentFloor, lifts2);  //  Find out next elevator is near
        int  newDestinationFloor = f.destinationFloor;

        f.moveliftToFloor(newDestinationFloor,newCurrentFloor);
        f.printout();


        //int countOfNextCloserLift = f.findCloserLift(newCurrentFlow, lifts2);  //  Find out next elevator is near
        // int long1 = lifts.length; 55
        // int long2 = lifts[0].length; 7

    }
}