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
import java.util.Arrays;
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
        int s[] = new int[10000], a[] = new int[10000], organ[][] = new int[250][50], b = 0, l = 0; 
        String organhouselet[][] = new String[250][50];
        int houseNum[] = new int[10000]; 
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
        String houseLet[] = new String[1000001], runAgain = "";
        Location locations[] = new Location[1000001]; //Used for taking in locations
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
        int ii = 0;
        
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
        
        for (int h = 0; h <= 9; h++)  { //Outside for loop used for performing code for all 9 cycles, h as cycle counter
            
            //RESETS BART AND LISA EVERY DAY
            b = 0;
            l = 0;
            Boolean bart = false;
            Boolean lisa = false;
            for (int i = 0; cy1.hasNext(); i++) { //Inside loop used for each individual cycle, i as text file line counter
                ii = i - 2;
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
                        s[ii] = Integer.parseInt(useless.substring(0, num));
                        counter = useless.indexOf("a");
                        a[ii] = Integer.parseInt(useless.substring((num+2), counter));
                        num = useless.length();
                        houseLet[ii] = (useless.substring((counter+2), num));
                        if(houseLet[ii].equals("A")) houseNum[ii] = 0;
                        else if(houseLet[ii].equals("AA")) houseNum[ii] = 0;
                        else if(houseLet[ii].equals("B")) houseNum[ii] = 100;
                        else if(houseLet[ii].equals("BB")) houseNum[ii] = 100;
                        else if(houseLet[ii].equals("C")) houseNum[ii] = 200;
                        else if(houseLet[ii].equals("CC")) houseNum[ii] = 200;
                        else if(houseLet[ii].equals("D")) houseNum[ii] = 300;
                        else if(houseLet[ii].equals("DD")) houseNum[ii] = 300;
                        else if(houseLet[ii].equals("E")) houseNum[ii] = 400;
                        else if(houseLet[ii].equals("EE")) houseNum[ii] = 400;
                        else if(houseLet[ii].equals("F")) houseNum[ii] = 500;
                        else if(houseLet[ii].equals("FF")) houseNum[ii] = 500;
                        else if(houseLet[ii].equals("G")) houseNum[ii] = 600;
                        else if(houseLet[ii].equals("GG")) houseNum[ii] = 600;
                        else if(houseLet[ii].equals("H")) houseNum[ii] = 700;
                        else if(houseLet[ii].equals("HH")) houseNum[ii] = 700;
                        else if(houseLet[ii].equals("I")) houseNum[ii] = 800;
                        else if(houseLet[ii].equals("II")) houseNum[ii] = 800;
                        else if(houseLet[ii].equals("J")) houseNum[ii] = 900;
                        else if(houseLet[ii].equals("JJ")) houseNum[ii] = 900;
                        
                        arraylength = ii;
                         

                    }
                    
                }

            }
            
            int streetcount = 0;
            int avecount = 0;
            
            for(int i = 0; i <= 248; i++) {
                for(int j = 0; j <= 49; j++) {
                    organ[i][j] = 0;
                    organhouselet[i][j] = "";
                }
            }
                
            for(int count = 1; count < arraylength + 3; count++)
            {
                
                if(count == 1) organ[streetcount][avecount] = ((a[0] - 1) * 1000) + houseNum[0];
                organ[streetcount][avecount] = (a[count - 1] - 1) * 1000 + houseNum[count - 1];
                organhouselet[streetcount][avecount] = houseLet[count - 1];
                if(s[count] != s[count - 1]) 
                {
                    streetcount ++;
                    avecount = -1;
                }
                avecount ++;
            }
            
            int organmin = 100000;
            int organsorted[][] = new int[250][50];
            int organminindex = 0;
            String organhouseletsorted[][] = new String[250][50];
            for(int i = 0; i <= 248; i++) {
                for(int k = 0; k <= 49; k++) {
                for(int j = 0; j <= 49; j++) {
                    if(organ[i][j] != 0 && organ[i][j] < organmin) {
                        organmin = organ[i][j];
                        organminindex = j;
                    }
                }
                organsorted[i][k] = organmin;
                organhouseletsorted[i][k] = organhouselet[i][organminindex];
                organ[i][organminindex] = 0;
                organhouselet[i][organminindex] = "";
                organmin = 100000;
                }
            }    
            
            for(int i = 0; i <= 248; i++) {
                for(int k = 0; k <= 49; k++) {
                    if(organsorted[i][k] != 100000) {
                    organ[i][k] = organsorted[i][k];
                    organhouselet[i][k] = organhouseletsorted[i][k];
                    }
                }
            }
            
            avecount = -1;
            for(int i = 0; i <= arraylength; i++)
            {
                locations[i] = new Location(0,0,0);
                avecount ++;
                String letter = "";
                int x = 0;
                int y = 0;
                x = (s[i] - 1) * 200;
                if(i > 0)
                {
                    if(s[i] != s[i-1])
                    {
                    avecount = 0;
                    }
                    y = organ[s[i] - 1][avecount];
                    letter = organhouselet[s[i] - 1][avecount];
                    locations[i].setCoord(x, y, letter);
                }
                if(i == 0) 
                {
                    y = organ[0][0];
                    letter = organhouselet[0][0];
                    locations[i].setCoord(0, y, letter);
                }
                
                
                
                //locations[i].setStreet(s[i]);
                //locations[i].setHouseLetter(letter);
                
                
            }

            locations[1000000] = new Location(125, 22, 0);
            
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
                if(locations[start[tnum]].getCoordX() <= 200 && locations[finish[tnum]].getCoordX() >= 200 && bart == false)
                {
                    trucktime[tnum] += (60 * b) / employees;
                    //System.out.println("Truck " + (tnum + 1) + " did Bart");
                    bart = true;
                    
                    //System.out.println("Truck " + (tnum + 1) + " drove to Bart and it added " + two.format(trucktime[tnum] / 3600) + " hours");
                }
                
                //If statement to see if truck will do Lisa
                if(locations[start[tnum]].getCoordX() <= 26900 && locations[finish[tnum]].getCoordX() >= 26900 && lisa == false)
                {
                    trucktime[tnum] += (60 * l) / employees;
                    //System.out.println("Truck " + (tnum + 1) + " did Lisa");
                    lisa = true;
                    //System.out.println("Lisa Done");
                    //System.out.println("Truck " + (tnum + 1) + " drove to Lisa and it added " + two.format(trucktime[tnum] / 3600) + " hours");
                }
                
                
                //EXECUTES LOOP PER TRUCK UNTIL ALL POINTS/HOMES ARE TRAVELLED TO
                while(current[tnum] != start[tnum])
                {
                    //RESETS MINIMUM DISTANCE TO ARBITRARY NUMBER
                    minimum = Math.abs(locations[start[tnum]].getCoordX() - locations[(start[tnum] + 1)].getCoordX()) + Math.abs(locations[start[tnum]].getCoordY() - locations[(start[tnum] + 1)].getCoordY());
                    //SETS MINIMUMSLOT TO THE NEXT ONE IN THE ARRAY
                    minimumslot = start[tnum] + 1;
                        
                    if(current[tnum] == finish[tnum])
                    {
                        minimum = 100000000;
                        for(int k = start[tnum]; k <= current[tnum]; k ++) 
                    {
                        
                        traveldistance = Math.abs(locations[1000000].getCoordX() - locations[k].getCoordX()) + Math.abs(locations[1000000].getCoordY() - locations[k].getCoordY());
                        if(traveldistance < minimum)
                        {
                            minimum = traveldistance;
                            minimumslot = k;
                        } 
                    } 
                        
                    }
                    if(current[tnum] != finish[tnum])
                    {
                       for(int k = start[tnum] + 1; k <= current[tnum]; k ++) 
                        {
                            //If new search spot is on same avenue
                            if(locations[start[tnum]].getAve() == locations[k].getAve() && locations[start[tnum]].getStreet() != locations[k].getStreet())
                            {
                            traveldistance = Math.abs(locations[start[tnum]].getCoordX() - locations[k].getCoordX()) + (1000 - Math.abs(1000 - (locations[start[tnum]].getHouse() + locations[k].getHouse())));
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
                    
                            
                    }
                        
                    truckdistance[tnum] += minimum;
                    truckvisited[tnum] ++;
                    trucktime[tnum] += 60 / employees; 
                    trucktime[tnum] += (minimum / 5000.0) * 150;
                    if(current[tnum] != finish[tnum]) locations[start[tnum]] = locations[minimumslot]; //reset 0 to the new point
                    
                        
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
                    if(current[tnum] != finish[tnum])
                    {
                        for(int k = start[tnum] + 1; k <= current[tnum]; k++)
                        {
                        if(k > minimumslot)
                        {
                            locations[k - 1] = locations[k];
                        }
                            
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
                truckprice[tnum] += Math.floor(truckmiles[tnum]) * 5;
                //ADDS TIME IN SECONDS (150 SECONDS PER MILE)
                //trucktime[tnum] += truckmiles[tnum] * 150;
                
                
                truckprice[tnum] += (((((trucktime[tnum]/3600.0) - 8) * 45) + 240) * employees); //salary
                salary[h] += (((((trucktime[tnum]/3600.0) - 8) * 45) + 240) * employees);
                
                
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
            //organized truckmiles[] for the day
            for (int i = 0; i < runningtrucks; i++) 
            {
                
                for (int j = i + 1; j < runningtrucks; j++) 
                {
                    if (truckmiles[i] < truckmiles[j] && truckmiles[i] != 0) 
                    {
                        double temp = truckmiles[i];
                        truckmiles[i] = truckmiles[j];
                        truckmiles[j] = temp;
                    }
                }
            }
            //adds maintenance costs for trucks (lowest possible)
            for(int i = 0; i < (boughttrucks - 1); i++)
            {
                price[h] += 1000 * Math.floor(truckmiles[i] / 100.0);
                
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