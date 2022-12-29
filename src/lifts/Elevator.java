package lifts;

import java.util.concurrent.TimeUnit;

public class Elevator{

    public static void main(String[] args) throws InterruptedException {

        Functions f = new Functions();
        f.checkEmployee();                                   // Check if this Person an Employee from IBM
        f.generateElevatorSystem();                          // Generate all Floors and Elevators
        f.shareLifts();                                      // Share elevators randomly ind the Building
        f.printout();                                        // Print all elevators and floors to check if everything is OK
        f.checkCurrentFloor();                               // Check if the current floor between 0 and 54

        System.out.println("Button gedrückt!");
        TimeUnit.SECONDS.sleep(1);                           // Sleep 1 second

        int countOfNextCloserLift = f.findCloserLift(f.currentFloor, f.lifts);  //  Find out next elevator is near
        TimeUnit.SECONDS.sleep(1);
        f.callLiftToCurrentFloor(f.currentFloor, countOfNextCloserLift);        //  Call Lift to current floor
        f.printout();
        f.checkDirectionFloor();                              // Check if the destination floor between 0 and 54
        TimeUnit.SECONDS.sleep(1);
        f.findDirection();                                    // Find out which way the passenger is going
        TimeUnit.SECONDS.sleep(1);
        f.moveliftToDestinationFloor(f.destinationFloor,f.currentFloor);        // Move Lift to destination floor
        f.printout();

    }
}
