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
        DateFormat timef = new SimpleDateFormat("HH:mm");
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
        int rentingcost[] = new int[10];
        double time[] = new double[10];
        double price[] = new double[10];
        int minimum = 100000;
        int minimumslot = 0;
        int arraylength = 0;
        int boughttrucks = 0;
        int rentedtrucks[] = new int[10];
        double miles[] = new double[10];
        int start[] = new int[10];
        int current[] = new int[10];
        int finish[] = new int[10];
        double salary[] = new double[10];
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
        int employees = 1;
        double trucktime[] = new double[10];
        int truckvisited[] = new int[10];
        int truckdistance[] = new int[10];
        int barttime[] = new int[10];
        int lisatime[] = new int[10];
        double truckmiles[] = new double[10];
        double truckprice[] = new double[10];
        double trucktimeavg[] = new double[10];
        int runningtrucks = 0;
        int traveldistance = 0;
        for(int i = 0; i < 10; i ++) {
            if (i == 0) {
                trucktimeavg[i] = 20.53428571;
            }
            if (i == 1) {
                trucktimeavg[i] = 20.06;
            }
            if (i == 2) {
                trucktimeavg[i] = 18.42142857;
            }
            if (i == 3) {
                trucktimeavg[i] = 19.22;
            }
            if (i == 4) {
                trucktimeavg[i] = 23.024;
            }
            if (i == 5) {
                trucktimeavg[i] = 19;
            }
            if (i == 6) {
                trucktimeavg[i] = 20.03428571;
            }
            if (i == 7) {
                trucktimeavg[i] = 20.83;
            }
            if (i == 8) {
                trucktimeavg[i] = 20.00666667;
            }
            if (i == 9) {
                trucktimeavg[i] = 22.272;
            }
        }
        //SET AMOUNT OF TRUCKS BOUGHT
        //Scanner x = new Scanner(System.in);
        //System.out.println("How many trucks would you like to buy?");
        //boughttrucks = x.nextInt();
        boughttrucks = 3;
        //SET EMPLOYEES PER TRUCK
        //Scanner e = new Scanner(System.in);
        //System.out.println("1 or 2 employees per truck?");
        //employees = e.nextInt();
        employees = 2;
        
        String useless = "";
        Scanner run = new Scanner(System.in);
        
        for (int h = 0; h <= 9; h++)  { //Outside for loop used for performing code for all 9 cycles, h as cycle counter
            
            //RESETS BART AND LISA EVERY DAY
            b = 0;
            l = 0;
            Boolean bart = false;
            Boolean lisa = false;
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
                        
                        locations[i - 1] = new Location(s[i], a[i], houseNum[i]);
                        arraylength = i - 1;
                        locations[i - 1].setHouseLetter(houseLet[i]); 

                    }
                    
                }

            }
            
            //SETS STARTING POSITION OF FIRST TRUCK (BOTTOM LEFT CORNER)
            locations[0] = new Location(1,1,0);
            
            //SETS AMOUNT OF TOTAL TRUCKS PER DAY
            if(h == 0) {runningtrucks = 4; cy1W.println("The number of trucks used today was: " + runningtrucks);}
            if(h == 1) {runningtrucks = 4; cy2W.println("The number of trucks used today was: " + runningtrucks);}
            if(h == 2) {runningtrucks = 3; cy3W.println("The number of trucks used today was: " + runningtrucks);}
            if(h == 3) {runningtrucks = 3; cy4W.println("The number of trucks used today was: " + runningtrucks);}
            if(h == 4) {runningtrucks = 3; cy5W.println("The number of trucks used today was: " + runningtrucks);}
            if(h == 5) {runningtrucks = 5; cy6W.println("The number of trucks used today was: " + runningtrucks);}
            if(h == 6) {runningtrucks = 4; cy7W.println("The number of trucks used today was: " + runningtrucks);}
            if(h == 7) {runningtrucks = 3; cy8W.println("The number of trucks used today was: " + runningtrucks);}
            if(h == 8) {runningtrucks = 3; cy9W.println("The number of trucks used today was: " + runningtrucks);}
            if(h == 9) {runningtrucks = 3; cy10W.println("The number of trucks used today was: " + runningtrucks);}
            
            //CALCULATES AMOUNT OF RENTED TRUCKS PER CYCLE
            if(runningtrucks > boughttrucks) rentedtrucks[h] = Math.abs(runningtrucks - boughttrucks);
            
            
            //Loop that resets finishing and starting position array (does it per cycle)
            for(int int1 = 0; int1 < 10; int1++)
            {
                start[int1] = 0;
                finish[int1] = 0;
            }
            
            //Loop that fills each starting postion (per day) (depending on #of running trucks)
            for(int int2 = 0; int2 < runningtrucks; int2 ++)
            {
                start[int2] = (arraylength/runningtrucks) * int2;
            }
            
            //Loop that fills each finishing postion (per day) (depending on #of running trucks)
            for(int int2 = 0; int2 < runningtrucks; int2 ++)
            {
                finish[int2] = (start[int2 + 1] - 1);
                //If it is last truck, finishing point is the last point in the array
                if(int2 == (runningtrucks - 1))
                {
                    finish[int2] = arraylength;
                }
                    
            }
            
            //ADDS COST OF RENTED TRUCKS TO THE DAILY PRICE
            price[h] += rentedtrucks[h] * 15000;
            
            //SHOWS AMOUNT OF COST FOR RENTED TRUCKS PER DAY
            rentingcost[h] = rentedtrucks[h] * 15000;
            
            for(int tnum = 0; tnum <= runningtrucks; tnum++)
            {
                truckdistance[tnum] = 0;
                truckmiles[tnum] = 0;
                truckprice[tnum] = 0;
                truckvisited[tnum] = 0;
                trucktime[tnum] = 0;
                current[tnum] = finish[tnum];
                //if(tnum != 0) System.out.println((finish[tnum - 1] - start[tnum - 1]) + "   " + truckvisited[tnum - 1]);
               
                
                //If statement to see if truck will do Bart
                if(locations[start[tnum]].getCoordX() <= 200 && locations[current[tnum]].getCoordX() >= 200 && bart == false)
                {
                    trucktime[tnum] += (60 * b) / employees;
                    //System.out.println("Truck " + (tnum + 1) + " did Bart");
                    bart = true;
                    
                    //System.out.println("Truck " + (tnum + 1) + " drove to Bart and it added " + two.format(trucktime[tnum] / 3600) + " hours");
                }
                
                //If statement to see if truck will do Lisa
                if(locations[start[tnum]].getCoordX() <= 26900 && locations[current[tnum]].getCoordX() >= 26900 && lisa == false)
                {
                    trucktime[tnum] += (60 * l) / employees;
                    //System.out.println("Truck " + (tnum + 1) + " did Lisa");
                    lisa = true;
                    //System.out.println("Lisa Done");
                    //System.out.println("Truck " + (tnum + 1) + " drove to Lisa and it added " + two.format(trucktime[tnum] / 3600) + " hours");
                }
                
                //CALCULATES THE TRUCK MOVING FROM DISTRIBUTION CENTER TO START POINT AND ADDS DISTANCE IN FEET
                truckdistance[tnum] += Math.abs(locations[start[tnum]].getCoordX() - 24800) + Math.abs(locations[start[tnum]].getCoordY() - 21000);
                
                //EXECUTES LOOP PER TRUCK UNTIL ALL POINTS/HOMES ARE TRAVELLED TO
                while(current[tnum] != start[tnum])
                {
                    //RESETS MINIMUM DISTANCE TO ARBITRARY NUMBER
                    minimum = Math.abs(locations[start[tnum]].getCoordX() - locations[(start[tnum] + 1)].getCoordX()) + Math.abs(locations[start[tnum]].getCoordY() - locations[(start[tnum] + 1)].getCoordY());
                    //SETS MINIMUMSLOT TO THE NEXT ONE IN THE ARRAY
                    minimumslot = start[tnum] + 1;
                        
                    for(int k = start[tnum] + 1; k <= current[tnum]; k ++) 
                    {
                            //If new search spot is on same avenue
                        if(locations[start[tnum]].getAve() == locations[k].getAve() && locations[start[tnum]].getStreet() != locations[k].getStreet())
                        {
                            traveldistance = Math.abs(locations[start[tnum]].getCoordX() - locations[k].getCoordX()) + Math.abs(locations[start[tnum]].getCoordY() - locations[k].getCoordY()) + locations[start[tnum]].getHouse() + locations[k].getHouse();
                        }
                        if(locations[start[tnum]].getAve() != locations[k].getAve())
                        {
                            traveldistance = Math.abs(locations[start[tnum]].getCoordX() - locations[k].getCoordX()) + Math.abs(locations[start[tnum]].getCoordY() - locations[k].getCoordY());
                        }
                        if(traveldistance < minimum)
                        {
                            minimum = traveldistance;
                            minimumslot = k;
                        }
                            
                    }
                        
                    truckdistance[tnum] += minimum;
                    truckvisited[tnum] ++;
                    trucktime[tnum] += 60 / employees; 
                    trucktime[tnum] += (minimum / 5000.0) * 150;
                    locations[start[tnum]] = locations[minimumslot]; //reset 0 to the new point
                        
                    //OUTPUTS TO FILE EVERY HOUSE VISITED
                    int trucktimehours[] = new int[10];
                    int trucktimemins[] = new int[10];
                    int trucktimesecs[] = new int[10];
                    trucktimehours[tnum] = (int)Math.floor((trucktime[tnum]/3600));
                    trucktimemins[tnum] = (int)Math.floor((trucktime[tnum]%3600)/60);
                    trucktimesecs[tnum] = (int)Math.floor((trucktime[tnum]%3600)%60);
                    if (trucktimehours[tnum] < 10 && trucktimemins[tnum] < 10 && trucktimesecs[tnum] < 10) {
                    if (h == 0) cy1W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 1) cy2W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 2) cy3W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 3) cy4W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 4) cy5W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 5) cy6W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 6) cy7W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 7) cy8W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 8) cy9W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 9) cy10W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    }
                    else 
                    if(trucktimehours[tnum] >= 10 && trucktimemins[tnum] < 10 && trucktimesecs[tnum] < 10) {
                    if (h == 0) cy1W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 1) cy2W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 2) cy3W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 3) cy4W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 4) cy5W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 5) cy6W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 6) cy7W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 7) cy8W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 8) cy9W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 9) cy10W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    }
                    else 
                    if(trucktimehours[tnum] >= 10 && trucktimemins[tnum] >= 10 && trucktimesecs[tnum] < 10) {
                    if (h == 0) cy1W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 1) cy2W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 2) cy3W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 3) cy4W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 4) cy5W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 5) cy6W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 6) cy7W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 7) cy8W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 8) cy9W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 9) cy10W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    }
                    else 
                    if(trucktimehours[tnum] >= 10 && trucktimemins[tnum] >= 10 && trucktimesecs[tnum] >= 10) {
                    if (h == 0) cy1W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 1) cy2W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 2) cy3W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 3) cy4W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 4) cy5W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 5) cy6W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 6) cy7W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 7) cy8W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 8) cy9W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 9) cy10W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    }
                    else
                    if(trucktimehours[tnum] < 10 && trucktimemins[tnum] >= 10 && trucktimesecs[tnum] >= 10) {
                    if (h == 0) cy1W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 1) cy2W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 2) cy3W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 3) cy4W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 4) cy5W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 5) cy6W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 6) cy7W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 7) cy8W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 8) cy9W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 9) cy10W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    }
                    else
                    if(trucktimehours[tnum] < 10 && trucktimemins[tnum] < 10 && trucktimesecs[tnum] >= 10) {
                    if (h == 0) cy1W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 1) cy2W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 2) cy3W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 3) cy4W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 4) cy5W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 5) cy6W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 6) cy7W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 7) cy8W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 8) cy9W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 9) cy10W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    }
                    else
                    if(trucktimehours[tnum] < 10 && trucktimemins[tnum] >= 10 && trucktimesecs[tnum] < 10) {
                    if (h == 0) cy1W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 1) cy2W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 2) cy3W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 3) cy4W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 4) cy5W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 5) cy6W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 6) cy7W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 7) cy8W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 8) cy9W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 9) cy10W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately 0" + (trucktimehours[tnum]) + ":" + (trucktimemins[tnum]) + ":0" + (trucktimesecs[tnum]) + " Military Time");
                    }
                    else
                    if(trucktimehours[tnum] >= 10 && trucktimemins[tnum] < 10 && trucktimesecs[tnum] >= 10) {
                    if (h == 0) cy1W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 1) cy2W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 2) cy3W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 3) cy4W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 4) cy5W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 5) cy6W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 6) cy7W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 7) cy8W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 8) cy9W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    if (h == 9) cy10W.println("Truck " + (tnum + 1) + " travelled " + minimum + " feet to house at: street " + locations[start[tnum]].getStreet() + ", ave " + locations[start[tnum]].getAve() + ", house " + locations[start[tnum]].getHouseLetter() + " at approximately " + (trucktimehours[tnum]) + ":0" + (trucktimemins[tnum]) + ":" + (trucktimesecs[tnum]) + " Military Time");
                    }
                    //ERASES THE CURRENT HOUSE AND MOVES ALL OTHER HOUSES UP A SPOT IN THE LOCATIONS ARRAY
                    for(int k = start[tnum] + 1; k <= current[tnum]; k++)
                        {
                        if(k > minimumslot)
                        {
                            locations[k - 1] = locations[k];
                        }
                            
                    }
                        
                    current[tnum] --;
                        
                }
            }
                  
            for(int tnum = 0; tnum < runningtrucks; tnum++)
            {
                //CONVERTS FEET TO MILES
                truckmiles[tnum] = truckdistance[tnum] / 5000;
                //ADD PRICE OF GAS ($5 PER MILE)
                truckprice[tnum] += truckmiles[tnum] * 5;
                //ADDS TIME IN SECONDS (150 SECONDS PER MILE)
                //trucktime[tnum] += truckmiles[tnum] * 150;
                
                
                truckprice[tnum] += ((((Math.ceil(trucktime[tnum]/3600.0) - 8) * 45) + 240) * employees); //salary
                salary[h] += ((((Math.ceil(trucktime[tnum]/3600.0) - 8) * 45) + 240) * employees);
                
                //CALCULATES MAINTANENCE FEES PER BOUGHT TRUCK
                if(tnum < boughttrucks)
                {
                    truckprice[tnum] += 1000 * (int)(truckmiles[tnum] / 100);
                }
                
                //CALCULATES TRUCK THAT TOOK THE LONGEST TIME PER DAY (SAVES IT AS THE DAILY TIME)
                if(trucktime[tnum] > time[h])
                {
                    time[h] = trucktime[tnum];
                }
                //System.out.println("Truck " + (tnum + 1) + " time :" + (two.format(trucktime[tnum]/3600)));
                //System.out.println("Overall time :" + two.format(time[h]/3600));
               
                
                
                miles[h] += truckmiles[tnum];
                price[h] += truckprice[tnum];
                visited[h] += truckvisited[tnum];
                
                //Printing System to show time (in hours) per truck
                //System.out.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
                if (h == 0) cy1W.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
                if (h == 1) cy2W.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
                if (h == 2) cy3W.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
                if (h == 3) cy4W.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
                if (h == 4) cy5W.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
                if (h == 5) cy6W.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
                if (h == 6) cy7W.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
                if (h == 7) cy8W.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
                if (h == 8) cy9W.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
                if (h == 9) cy10W.println("Truck " + (tnum + 1) + " took " + two.format(trucktime[tnum] / 3600) + " hours");
            }
            
                    
                    
                    
                    
                    
                    
                    
                    System.out.println(two.format(time[h] / 3600 ) + " hours on Cycle " + (h+1) + " at $" + money.format(price[h]));
                    //System.out.println(visited[h] + "   " + arraylength);
                    //System.out.println("Salary Payout $" + money.format(salary[h]));
                   
        
        //CHANGES THE FILE THE LOOP IS READING IN FROM AND MOVES TO NEXT DAY FILE READY FOR START OF NEW LOOP                
        if (h == 0) cy1 = cy2;
        if (h == 1) cy1 = cy3;
        if (h == 2) cy1 = cy4;
        if (h == 3) cy1 = cy5;
        if (h == 4) cy1 = cy6;
        if (h == 5) cy1 = cy7;
        if (h == 6) cy1 = cy8;
        if (h == 7) cy1 = cy9;
        if (h == 8) cy1 = cy10;

        
        
         
        
                 
        }
        
        //CALCULATES COST OF BUYING TRUCKS
        double totalprice = boughttrucks * 100000;
            //ADDS IN DAILY COSTS (GAS, SALARY, MAINTANENCE)
            for (int i = 0; i < 10; i ++) 
            {
                totalprice += price[i];
            }
        
        //CALCULATES TOTAL MILES DRIVEN OVER 10 DAYS            
        double totalmiles = 0;
            for (int i = 0; i < 10; i ++) 
            {
                totalmiles += miles[i];
            }
            
        //OUTPUTS TO FILE COST OF THE SALARY PAID OUT EACH DAY    
        System.out.println("Total price = $" + money.format(totalprice));
        cy1W.println("The day's total salary paid was: $" + salary[0]);
        cy2W.println("The day's total salary paid was: $" + salary[1]);
        cy3W.println("The day's total salary paid was: $" + salary[2]);
        cy4W.println("The day's total salary paid was: $" + salary[3]);
        cy5W.println("The day's total salary paid was: $" + salary[4]);
        cy6W.println("The day's total salary paid was: $" + salary[5]);
        cy7W.println("The day's total salary paid was: $" + salary[6]);
        cy8W.println("The day's total salary paid was: $" + salary[7]);
        cy9W.println("The day's total salary paid was: $" + salary[8]);
        cy10W.println("The day's total salary paid was: $" + salary[9]);
        
        //OUTPUTES TO FILE COST OF THE TOTAL PRICE EACH DAY
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
        
        cy1W.println("The day's total mileage was " + miles[0] + " miles");
        cy2W.println("The day's total mileage was " + miles[1] + " miles");
        cy3W.println("The day's total mileage was " + miles[2] + " miles");
        cy4W.println("The day's total mileage was " + miles[3] + " miles");
        cy5W.println("The day's total mileage was " + miles[4] + " miles");
        cy6W.println("The day's total mileage was " + miles[5] + " miles");
        cy7W.println("The day's total mileage was " + miles[6] + " miles");
        cy8W.println("The day's total mileage was " + miles[7] + " miles");
        cy9W.println("The day's total mileage was " + miles[8] + " miles");
        cy10W.println("The day's total mileage was " + miles[9] + " miles");
        
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
        cyOW.println("The overall mileage of the combined trucks was " + totalmiles + " miles");
        cyOW.close();
        
  
    }
}