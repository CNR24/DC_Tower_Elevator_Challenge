/*
 *
 * When the code runs for the first time, you are asked whether you are an IBM employee, if you are an employee
 * you should write true, if not, false. If you are an employee, you will be informed on which floor the IBM Office is.
 * And the 55-floor elevator is printed. Elevators were randomly distributed at the beginning. You will be prompted to
 * enter your current floor. You will be directed to the nearest elevator. When the elevator arrives, information is
 * given and the destination floor is asked. Then, current floor, destination floor and direction information is given.
 * The elevator reaches the destination floor. And you are asked if you want a new lift.
 * The same action will be repeated if you type true. If you type no, the program terminates.
 *
 * */

package lifts;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Elevator{

    public static void main(String[] args) throws InterruptedException {

        boolean newElevator = true;

        do {

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
            //f.printout(); You can cancel this comment line to check if the process is working correctly.
            f.checkDestinationFloor();                            // Check if the destination floor between 0 and 54
            TimeUnit.SECONDS.sleep(1);
            f.findDirection();                                    // Find out which way the passenger is going
            TimeUnit.SECONDS.sleep(1);
            f.moveliftToDestinationFloor(f.destinationFloor, f.currentFloor);        // Move Lift to destination floor
            //f.printout(); You can cancel this comment line to check if the process is working correctly.

            Scanner scan0 = new Scanner(System.in);
            System.out.print("\nDo you want to order a new Elevator?\nPlese typ true or false:");
            newElevator = scan0.nextBoolean();

        }while(newElevator == true);

    }
}
