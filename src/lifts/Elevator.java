import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class Elevator{

    enum State{
        MOVING, STOPPED, IDLE
    }
    enum Direction{
        UP,DOWN
    }

    public static void main(String[] args) {

        // Hier wird kontrolliert ob der Person ein Mitarbeiter von IBM ist

        Scanner scan  = new Scanner(System.in);

        System.out.println("IBM Mitarbeiter?");
        int i = scan.nextInt();
        if(i == 0)
            System.out.println("Kein Zutritt");
        else if(i == 1)
            System.out.println("Zutritt genehmigt"+"\nIBM Büro befindet sich im 35. Stock ");

        else System.out.println("Mitarbeiter Error!");

// **************************    Stockwerke 0 setzen    **********************************


       int[][] lifts = new int [55][7];

        for (int r = 0;r < lifts.length; r++){
            for (int c = 0; c < lifts[r].length; c++){
                lifts[r][c] = 0;
            }
        }

// ***************************  Aufzuege Random Verteilen    *******************************


        for (int rmNr = 0; rmNr<7; rmNr++){

            Random random = new Random();
            int randomfloor = random.nextInt(55);

            if(lifts[randomfloor][rmNr] == 0){
                lifts[randomfloor][rmNr]= 1;
            }
        }

 // *********************************    AUSGABE    ****************************************

        for (int r = 0;r < lifts.length; r++){
            System.out.println("\n");
            for (int c = 0; c < lifts[r].length; c++){
                System.out.print(lifts[r][c]+"  ");
            }
        }

        System.out.println("Button gedrückt!\n Aufzug kommt!");
        int button = 0;

        if(button == 1){
            check(lifts[0][1])
        }


    }
}