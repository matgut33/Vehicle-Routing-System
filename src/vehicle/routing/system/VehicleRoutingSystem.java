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
        int cycleDay = 0; //For labeling cycles
        int s = 0;
        Location locations[] = new Location[10000]; //Used for taking in locations
        Scanner cy1 = new Scanner(new File("cycle1.txt")); //Takes in cycle 1 data
        Scanner cy2 = new Scanner(new File("cycle2.txt")); //Takes in cycle 2 data
        Scanner cy3 = new Scanner(new File("cycle3.txt")); //Takes in cycle 3 data
        Scanner cy4 = new Scanner(new File("cycle4.txt")); //Takes in cycle 4 data
        Scanner cy5 = new Scanner(new File("cycle5.txt")); //Takes in cycle 5 data
        Scanner cy6 = new Scanner(new File("cycle6.txt")); //Takes in cycle 6 data
        Scanner cy7 = new Scanner(new File("cycle7.txt")); //Takes in cycle 7 data
        Scanner cy8 = new Scanner(new File("cycle8.txt")); //Takes in cycle 8 data
        Scanner cy9 = new Scanner(new File("cycle9.txt")); //Takes in cycle 9 data
        Scanner cy10 = new Scanner(new File("cycle10.txt")); //Takes in cycle 10 data

        int counter = 1; int num = 0;
        String useless = "";
        for (int i = 0; cy1.hasNext(); i++) {
            if (i < 2) {
                useless = "";
            }
            else {
            useless = cy1.nextLine();
            num = useless.indexOf("s");
            s = Integer.parseInt(useless.substring(0, num));
            System.out.println(s);
            }
        }

        String input[] = TakeInInput(cy1);
        
        
        

    }
        
        static String[] TakeInInput(Scanner a) { //Reads in text files
            int i = 0;
            String useless = "";
            String[] input = new String[10000];
            while (a.hasNext()) {
                if (i < 2) {
                    useless = a.nextLine();
                    i ++;
                }
                else {
                input[i] = a.nextLine();
                System.out.println(input[i]);
                i++;
                }
            }
            return input;
        }
}