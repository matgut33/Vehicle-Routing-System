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
import java.awt.Point;


public class VehicleRoutingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int cycleDay = 0; //For labeling cycles
        int s[] = new int[100000], a[] = new int[100000], b = 0, l = 0; 
        int houseNum[] = new int[100000]; 
        int distance[] = new int[10];
        int minimum = 100000;
        int minimumslot = 0;
        String houseLet[] = new String[10000000], runAgain = "";
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
        Scanner run = new Scanner(System.in);
        
        for (int h = 0; h <= 9; h++)  {
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
                    if(houseLet[i].equals("A")) houseNum[i] = 0;
                    else if(houseLet[i].equals("AA")) houseNum[i] = 0;
                    else if(houseLet[i].equals("B")) houseNum[i] = 100;
                    else if(houseLet[i].equals("BB")) houseNum[i] = 100;
                    else if(houseLet[i].equals("C")) houseNum[i] = 200;
                    else if(houseLet[i].equals("CC")) houseNum[i] = 200;
                    else if(houseLet[i].equals("D")) houseNum[i] = 300;
                    else if(houseLet[i].equals("DD")) houseNum[i] = 300;
                    else if(houseLet[i].equals("E")) houseNum[i] = 400;
                    else if(houseLet[i].equals("EE")) houseNum[i] = 400;
                    else if(houseLet[i].equals("F")) houseNum[i] = 500;
                    else if(houseLet[i].equals("FF")) houseNum[i] = 500;
                    else if(houseLet[i].equals("G")) houseNum[i] = 600;
                    else if(houseLet[i].equals("GG")) houseNum[i] = 600;
                    else if(houseLet[i].equals("H")) houseNum[i] = 700;
                    else if(houseLet[i].equals("HH")) houseNum[i] = 700;
                    else if(houseLet[i].equals("I")) houseNum[i] = 800;
                    else if(houseLet[i].equals("II")) houseNum[i] = 800;
                    else if(houseLet[i].equals("J")) houseNum[i] = 900;
                    else if(houseLet[i].equals("JJ")) houseNum[i] = 900;
                    
                }
                distance[h] += 45800;
                distance[h] += locations[0].getCoordX();
                while(i != 0)
                {
                    for(int k = 0; k <= i; k ++) { //closest avenue values (y)
                    if(locations[k].getCoordX() == locations[k+1].getCoordX())
                    {
                       if(Math.abs(locations[k].getCoordY() - locations[k+1].getCoordY()) < minimum)
                       {
                       minimum = Math.abs(locations[k].getCoordY() - locations[k+1].getCoordY());
                       minimumslot = k + 1;
                       }
                      
                    }
                    
                    
                }
                }
            }
            
        }
        /*for (int j = 0; j < 10000; j++) {
            if (s[j] != 0){
            locations[j] = new Location(s[j], a[j], houseNum[j], b, l);
            System.out.println(locations[j].getCoord()); 
            }
        }
        */

        
        /*if (h == 0) cy1 = cy2;
        if (h == 1) cy1 = cy3;
        if (h == 2) cy1 = cy4;
        if (h == 3) cy1 = cy5;
        if (h == 4) cy1 = cy6;
        if (h == 5) cy1 = cy7;
        if (h == 6) cy1 = cy8;
        if (h == 7) cy1 = cy9;
        if (h == 8) cy1 = cy10;
        
        
        h++; //last thing in our code before printng out results
        */
        }
  
    }
}