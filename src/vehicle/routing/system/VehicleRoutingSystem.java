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
import java.text.DecimalFormat;


public class VehicleRoutingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DecimalFormat two = new DecimalFormat("#.##");
        DecimalFormat money = new DecimalFormat("#.##");
        int cycleDay = 0; //For labeling cycles
        int s[] = new int[100000], a[] = new int[100000], b = 0, l = 0; 
        int houseNum[] = new int[100000]; 
        int distance[] = new int[10]; 
        int visited[] = new int[10];
        double time[] = new double[10];
        double price[] = new double[10];
        int minimum = 100000;
        int minimumslot = 0;
        int arraylength = 0;
        double miles[] = new double[10];
        int start[] = new int[8];
        int finish[] = new int[8];
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
        int trucktime[] = new int[8];
        int truckvisited[] = new int[8];
        int truckdistance[] = new int[8];
        double truckmiles[] = new double[8];
        double truckprice[] = new double[8];
        
        String useless = "";
        Scanner run = new Scanner(System.in);
        price[0] = 800000;
        
        for (int h = 0; h <= 9; h++)  { //Outside for loop used for performing code for all 9 cycles, h as cycle counter
            for (int i = 0; cy1.hasNext(); i++) { //Inside loop used for each individual cycle, i as text file line counter
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
                        
                        locations[i - 1] = new Location(s[i], a[i], houseNum[i], b, l);
                        arraylength = i - 1;

                    }
                    
                }

            }
            locations[0] = new Location(1,1,0,b,l);
            start[0] = 0;
            finish[0] = arraylength/8;
            start[1] = arraylength/8;
            finish[1] = arraylength/8 * 2;
            start[2] = arraylength/8 * 2;
            finish[2] = arraylength/8 * 3;
            start[3] = arraylength/8 * 3;
            finish[3] = arraylength/8 * 4;
            start[4] = arraylength/8 * 4;
            finish[4] = arraylength/8 * 5;
            start[5] = arraylength/8 * 5;
            finish[5] = arraylength/8 * 6;
            start[6] = arraylength/8 * 6;
            finish[6] = arraylength/8 * 7;
            start[7] = arraylength/8 * 7;
            finish[7] = arraylength;
            
            
            
            for(int tnum = 0; tnum <= 7; tnum++)
            {
                truckdistance[tnum] = 0;
                truckmiles[tnum] = 0;
                truckprice[tnum] = 0;
                truckvisited[tnum] = 0;
                trucktime[tnum] = 0;
                
                truckdistance[tnum] += Math.abs(locations[start[tnum]].getCoordX() - 24800) + Math.abs(locations[start[tnum]].getCoordY() - 21000);
                while(finish[tnum] != start[tnum])
                    {
                        minimum = 100000;
                        minimumslot = start[tnum] + 1;
                        for(int k = start[tnum] + 1; k <= finish[tnum]; k ++) 
                        { //closest avenue values (y)
                            if(locations[start[tnum]].getCoordX() == locations[k].getCoordX())
                            {
                                if(Math.abs(locations[start[tnum]].getCoordY() - locations[k].getCoordY()) < minimum)
                                    {
                                    minimum = Math.abs(locations[start[tnum]].getCoordY() - locations[k].getCoordY());
                                    minimumslot = k;
                                    }
                            }
                        }
                        if(minimum == 100000)
                        {
                            for (int k = start[tnum] + 1; k <= finish[tnum]; k++) { //closest avenue values (y)
                                if (locations[start[tnum]].getCoordX() + 200 == locations[k].getCoordX()) 
                                {
                                    if(locations[start[tnum]].getCoordY() > 25000)
                                    {
                                        if(locations[k].getCoordY() > locations[minimumslot].getCoordY())
                                        {
                                            minimum = Math.abs(locations[start[tnum]].getCoordY() - locations[k].getCoordY()) + 200;
                                            minimumslot = k;
                                            if (locations[start[tnum]].getAve() == locations[k].getAve()) 
                                            {
                                                minimum = ((locations[start[tnum]].getCoordY() % 1000) + (locations[k].getCoordY() % 1000) + 200);
                                            }
                                        }      
                                    }
                                    if(locations[start[tnum]].getCoordY() <= 25000)
                                    {
                                        if(locations[k].getCoordY() < locations[minimumslot].getCoordY())
                                        {
                                            minimum = Math.abs(locations[start[tnum]].getCoordY() - locations[k].getCoordY()) + 200;
                                            minimumslot = k;
                                            if(locations[start[tnum]].getAve() == locations[k].getAve())
                                            {
                                                minimum = ((locations[start[tnum]].getCoordY() % 1000) + (locations[k].getCoordY() % 1000) + 200);
                                            }
                                        }      
                                    }
                                }

                            } 
                        }
                        if(minimum == 100000 && minimumslot == start[tnum] + 1)
                        {
                            minimum = Math.abs(locations[start[tnum]].getCoordY() - locations[start[tnum] + 1].getCoordY()) + 200;  
                        }
                        truckdistance[tnum] += minimum;
                        truckvisited[tnum] ++;
                        trucktime[tnum] += 60; 
                        finish[tnum] --;
                        locations[start[tnum]] = locations[minimumslot]; //reset 0 to the new point
                        for(int k = start[tnum] + 1; k <= finish[tnum]; k++)
                        {
                            if(k > minimumslot)
                            {
                                locations[k - 1] = locations[k];
                            }
                            
                        }
                    }
            }
            time[h] = 0;        
            for(int tnum = 0; tnum <= 6; tnum++)
            {
                truckmiles[tnum] = truckdistance[tnum] / 5000;
                truckprice[tnum] = truckmiles[tnum] * 5;
                trucktime[tnum] += truckmiles[tnum] * 150;
                if(trucktime[tnum] > time[h])
                {
                    time[h] = trucktime[tnum];
                }
                miles[h] += truckmiles[tnum];
                price[h] += truckprice[tnum];
            }
            
                    
                    
                    
                    
                    
                    
                    
                    System.out.println(two.format(time[h] / 3600 ) + " hours on Cycle " + (h+1) + " at $" + money.format(price[h]));
                    

        if (h == 0) cy1 = cy2;
        if (h == 1) cy1 = cy3;
        if (h == 2) cy1 = cy4;
        if (h == 3) cy1 = cy5;
        if (h == 4) cy1 = cy6;
        if (h == 5) cy1 = cy7;
        if (h == 6) cy1 = cy8;
        if (h == 7) cy1 = cy9;
        if (h == 8) cy1 = cy10;
        
        
         //last thing in our code before printing out results
        
                    
        }
        
  
    }
}