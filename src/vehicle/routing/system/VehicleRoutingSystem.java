/*
 * This program is an algroithm to find the lowest time
 * and save the most money on delivering items to customers
 * max 100 packages per day
 * 1 day is a cycle, max 10 cycles
 * Read in locations from a txt file
 */
package vehicle.routing.system;

/**
 * @author Matthew Gutkin, Henry Dench, Sam Meyerowitz
 * @author Period 8
 */

import java.util.Scanner;
import java.io.*;
public class VehicleRoutingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Takes in cycle 1 data
        File c1 = new File("cycle1.txt");
        Scanner cy1 = new Scanner(c1);
        
        //Takes in cycle 2 data
        File c2 = new File("cycle2.txt");
        Scanner cy2 = new Scanner(c2);
        
        //Takes in cycle 3 data
        File c3 = new File("cycle3.txt");
        Scanner cy3 = new Scanner(c3);
        
        //Takes in cycle 4 data
        File c4 = new File("cycle4.txt");
        Scanner cy4 = new Scanner(c4);
        
        //Takes in cycle 5 data
        File c5 = new File("cycle5.txt");
        Scanner cy5 = new Scanner(c1);
        
        //Takes in cycle 6 data
        File c6 = new File("cycle6.txt");
        Scanner cy6 = new Scanner(c6);
        
        //Takes in cycle 7 data
        File c7 = new File("cycle7.txt");
        Scanner cy7 = new Scanner(c7);
        
        //Takes in cycle 8 data
        File c8 = new File("cycle8.txt");
        Scanner cy8 = new Scanner(c8);
        
        //Takes in cycle 9 data
        File c9 = new File("cycle9.txt");
        Scanner cy9 = new Scanner(c9);
        
        //Takes in cycle 10 data
        File c10 = new File("cycle10.txt");
        Scanner cy10 = new Scanner(c10);
    }

}
