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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VehicleRoutingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH;mm;ss");
        Date date = new Date();
        String runDate = sdf.format(date);
        String sysPath = System.getProperty("user.dir");
        new File("Output/" + runDate).mkdirs();
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
        int start[] = new int[10];
        int finish[] = new int[10];
        String houseLet[] = new String[10000000], runAgain = "";
        Location locations[] = new Location[10000000]; //Used for taking in locations
        Scanner cy1 = new Scanner(new File("Cycles/cycle1.txt")); //Takes in cycle 1 data
        Scanner cy2 = new Scanner(new File("Cycles/cycle2.txt")); //Takes in cycle 2 data
        Scanner cy3 = new Scanner(new File("Cycles/cycle3.txt")); //Takes in cycle 3 data
        Scanner cy4 = new Scanner(new File("Cycles/cycle4.txt")); //Takes in cycle 4 data
        Scanner cy5 = new Scanner(new File("Cycles/cycle5.txt")); //Takes in cycle 5 data
        Scanner cy6 = new Scanner(new File("Cycles/cycle6.txt")); //Takes in cycle 6 data
        Scanner cy7 = new Scanner(new File("Cycles/cycle7.txt")); //Takes in cycle 7 data
        Scanner cy8 = new Scanner(new File("Cycles/cycle8.txt")); //Takes in cycle 8 data
        Scanner cy9 = new Scanner(new File("Cycles/cycle9.txt")); //Takes in cycle 9 data
        Scanner cy10 = new Scanner(new File("Cycles/cycle10.txt")); //Takes in cycle 10 data
        PrintWriter cy1W = new PrintWriter("Output/" + runDate + "/Output, Cycle 1.txt", "UTF-8");
        cy1W.println("The locations, in order, visisted today were: ");
        PrintWriter cy2W = new PrintWriter("Output/" + runDate + "/Output, Cycle 2.txt", "UTF-8");
        PrintWriter cy3W = new PrintWriter("Output/" + runDate + "/Output, Cycle 3.txt", "UTF-8");
        PrintWriter cy4W = new PrintWriter("Output/" + runDate + "/Output, Cycle 4.txt", "UTF-8");
        PrintWriter cy5W = new PrintWriter("Output/" + runDate + "/Output, Cycle 5.txt", "UTF-8");
        PrintWriter cy6W = new PrintWriter("Output/" + runDate + "/Output, Cycle 6.txt", "UTF-8");
        PrintWriter cy7W = new PrintWriter("Output/" + runDate + "/Output, Cycle 7.txt", "UTF-8");
        PrintWriter cy8W = new PrintWriter("Output/" + runDate + "/Output, Cycle 8.txt", "UTF-8");
        PrintWriter cy9W = new PrintWriter("Output/" + runDate + "/Output, Cycle 9.txt", "UTF-8");
        PrintWriter cy10W = new PrintWriter("Output/" + runDate + "/Output, Cycle 10.txt", "UTF-8");
        PrintWriter cyOW = new PrintWriter("Output/" + runDate + "/Output, Overall.txt", "UTF-8");
        int counter = 1; int num = 0;
        double trucktime[] = new double[10];
        int truckvisited[] = new int[10];
        int truckdistance[] = new int[10];
        double truckmiles[] = new double[10];
        double truckprice[] = new double[10];
        int runningtrucks = 0;
        
        String useless = "";
        Scanner run = new Scanner(System.in);
        
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
            
            if(h == 0) runningtrucks = 8;
            if(h == 1) runningtrucks = 8;
            if(h == 2) runningtrucks = 7;
            if(h == 3) runningtrucks = 6;
            if(h == 4) runningtrucks = 6;
            if(h == 5) runningtrucks = 9;
            if(h == 6) runningtrucks = 8;
            if(h == 7) runningtrucks = 7;
            if(h == 8) runningtrucks = 6;
            if(h == 9) runningtrucks = 6;
            
            for(int int1 = 0; int1 < 10; int1++)
            {
                start[int1] = 0;
                finish[int1] = 0;
            }
            
            for(int int2 = 0; int2 < runningtrucks; int2 ++)
            {
                start[int2] = arraylength/runningtrucks * int2;
                finish[int2] = arraylength/runningtrucks * (int2 + 1);
            }
            
            price[h] += (runningtrucks - 6) * 15000;
            
            for(int tnum = 0; tnum <= runningtrucks; tnum++)
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
                        if (h == 0) cy1W.println(locations[h].getCoordSimple());
                        if (h == 1) cy2W.println(locations[h].getCoordSimple());
                        if (h == 2) cy3W.println(locations[h].getCoordSimple());
                        if (h == 3) cy4W.println(locations[h].getCoordSimple());
                        if (h == 4) cy5W.println(locations[h].getCoordSimple());
                        if (h == 5) cy6W.println(locations[h].getCoordSimple());
                        if (h == 6) cy7W.println(locations[h].getCoordSimple());
                        if (h == 7) cy8W.println(locations[h].getCoordSimple());
                        if (h == 8) cy9W.println(locations[h].getCoordSimple());
                        if (h == 9) cy10W.println(locations[h].getCoordSimple());
                    }
            }
                  
            for(int tnum = 0; tnum <= runningtrucks; tnum++)
            {
                truckmiles[tnum] = truckdistance[tnum] / 5000;
                truckprice[tnum] += truckmiles[tnum] * 5;
                trucktime[tnum] += truckmiles[tnum] * 150;
                truckprice[tnum] += (Math.ceil((trucktime[tnum]/3600.0) - 7) * 45) + 240; //ADD HERE PUTA: returns number of hours
                if(tnum <= 5)
                {
                    truckprice[tnum] += 1000 * (int)(truckmiles[tnum] / 100);
                }
                
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
         double totalprice = 600000;
                    for (int i = 0; i < 10; i ++) {
                        totalprice += price[i];
                    }
         double totalmiles = 0;
                    for (int i = 0; i < 10; i ++) {
                        totalmiles += miles[i];
                    }
        System.out.println("Total price = $" + money.format(totalprice));
        cy1W.println("The day's total price was: $" + price[0]);
        cy2W.println("The day's total price was: $" + price[1]);
        cy3W.println("The day's total price was: $" + price[2]);
        cy4W.println("The day's total price was: $" + price[3]);
        cy5W.println("The day's total price was: $" + price[4]);
        cy6W.println("The day's total price was: $" + price[5]);
        cy7W.println("The day's total price was: $" + price[6]);
        cy8W.println("The day's total price was: $" + price[7]);
        cy9W.println("The day's total price was: $" + price[8]);
        cy10W.println("The day's total price was: $" + price[9]);
        
        cy1W.println("The day's toal milege was " + miles[0] + " miles");
        cy2W.println("The day's toal milege was " + miles[1] + " miles");
        cy3W.println("The day's toal milege was " + miles[2] + " miles");
        cy4W.println("The day's toal milege was " + miles[3] + " miles");
        cy5W.println("The day's toal milege was " + miles[4] + " miles");
        cy6W.println("The day's toal milege was " + miles[5] + " miles");
        cy7W.println("The day's toal milege was " + miles[6] + " miles");
        cy8W.println("The day's toal milege was " + miles[7] + " miles");
        cy9W.println("The day's toal milege was " + miles[8] + " miles");
        cy10W.println("The day's toal milege was " + miles[9] + " miles");
        
        cy1W.close();
        cy2W.close();
        cy3W.close();
        cy4W.close();
        cy5W.close();
        cy6W.close();
        cy7W.close();
        cy8W.close();
        cy9W.close();
        cy10W.close();
        
        cyOW.println("The overall price was $" + totalprice);
        cyOW.println("The overall milege of the combined trucks was " + totalmiles + " miles");
        cyOW.close();
        
  
    }
}