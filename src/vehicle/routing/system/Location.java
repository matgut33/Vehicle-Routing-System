package vehicle.routing.system;


/**
 * @author Matthew Gutkin, Henry Dench, Sam Meyerowitz
 * @author Period 8
 */
import java.awt.Point;

public class Location {
    private int street = 0, ave = 0, bart = 0, lisa = 0, houseNum = 0;
    private Point Coordinate;
    
    public Location() { //DEFAULT CONSTRUCTOR FOR HOMES
        street = 0;
        ave = 0;
        houseNum = 0;
        bart = 0;
        lisa = 0;
    }
    
    public Location(int s, int a, int h, int b, int l) { //OVERLOADED CONSTRUCTOR
        street = s;
        ave = a;
        houseNum = h;
        bart = b;
        lisa = l;
        Coordinate = new Point((street - 1) * 200, (((ave - 1) * 1000) + houseNum));
    }
    
    public void setStreet(int s) {
        street = s;
    }
    
    public int getStreet() {
        return street;
    }
    
    public void setAve(int a) {
        ave = a;
    }
    
    public int getAve() {
        return ave;
    }
    
    public void setHouse(int h) {
        houseNum = h;
    }
    
    public int getHouse() {
        return houseNum;
    }
    
    public void setBart(int b) {
        bart = b;
    }
    
    public int getBart() {
        return bart;
    }
     
    public void setLisa(int l) {
        lisa = l;
    }
    
    public int getLisa() {
        return lisa;
    }
    
    public void clearHouse() { //clears street, avenue and house number
        street = 0;
        ave = 0;
        houseNum = 0;
    }
    
    public void setCoord(int x, int y) {
        Coordinate.x = x;
        Coordinate.y = y;
    }
    
    public Point getCoord() {
        return Coordinate;
    }
    
    public void setCoordX(int x) {
        Coordinate.x = x;
    }
    
    public int getCoordX() {
        return Coordinate.x;
    }
    
    public void setCoordY(int y) {
        Coordinate.y = y;
    }
    
    public int getCoordY() {
        return Coordinate.y;
    }
}
