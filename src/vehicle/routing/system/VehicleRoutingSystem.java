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
        int s[] = new int[100000], a[] = new int[100000], b = 0, l = 0, houseNum[] = new int[100000]; 
        String houseLet[] = new String[10000000];
        Location locations[] = new Location[10000000]; //Used for taking in locations
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
        for (int h = 0; h <= 10; h++) {
        for (int i = 0; cy1.hasNext(); i++) {
            if (i < 2) {
                useless = cy1.nextLine();
            } else {
                useless = cy1.nextLine();
                if (useless.equals("Bart Complex")) {
                    b = cy1.nextInt();
                    useless = cy1.nextLine();
                    useless = cy1.nextLine();
                    l = cy1.nextInt();
                    break;
                } else {
                    num = useless.indexOf("s");
                    s[i] = Integer.parseInt(useless.substring(0, num));
                    counter = useless.indexOf("a");
                    a[i] = Integer.parseInt(useless.substring((num+2), counter));
                    num = useless.length();
                    houseLet[i] = (useless.substring((counter+2), num));
                    if(houseLet[i].equals("A")) houseNum[i] = 1;
                    else if(houseLet[i].equals("AA")) houseNum[i] = 2;
                    else if(houseLet[i].equals("B")) houseNum[i] = 3;
                    else if(houseLet[i].equals("BB")) houseNum[i] = 4;
                    else if(houseLet[i].equals("C")) houseNum[i] = 5;
                    else if(houseLet[i].equals("CC")) houseNum[i] = 6;
                    else if(houseLet[i].equals("D")) houseNum[i] = 7;
                    else if(houseLet[i].equals("DD")) houseNum[i] = 8;
                    else if(houseLet[i].equals("E")) houseNum[i] = 9;
                    else if(houseLet[i].equals("EE")) houseNum[i] = 10;
                    else if(houseLet[i].equals("F")) houseNum[i] = 11;
                    else if(houseLet[i].equals("FF")) houseNum[i] = 12;
                    else if(houseLet[i].equals("G")) houseNum[i] = 13;
                    else if(houseLet[i].equals("GG")) houseNum[i] = 14;
                    else if(houseLet[i].equals("H")) houseNum[i] = 15;
                    else if(houseLet[i].equals("HH")) houseNum[i] = 16;
                    else if(houseLet[i].equals("I")) houseNum[i] = 17;
                    else if(houseLet[i].equals("II")) houseNum[i] = 18;
                    else if(houseLet[i].equals("J")) houseNum[i] = 19;
                    else if(houseLet[i].equals("JJ")) houseNum[i] = 20;
                    System.out.println(s[i] + " " + a[i] + " " + houseLet[i]);
                }
            }
        }
        for (int j = 0; j < 10000; j++) {
            locations[j] = new Location(s[j], a[j], houseNum[j], b, l);
        }
        
        if (h == 1) cy1 = cy2;
        if (h == 2) cy1 = cy3;
        if (h == 3) cy1 = cy4;
        if (h == 4) cy1 = cy5;
        if (h == 5) cy1 = cy6;
        if (h == 6) cy1 = cy7;
        if (h == 7) cy1 = cy8;
        if (h == 8) cy1 = cy9;
        if (h == 9) cy1 = cy10;
        }
    }
}