package vehicle.routing.system;


/**
 * @author Matthew Gutkin, Henry Dench, Sam Meyerowitz
 * @author Period 8
 */
import java.awt.Point;

public class Location {
    private int street = 0, ave = 0, houseNum = 0;
    private Point Coordinate;
    private String HouseLetter;
    
    public Location() { //DEFAULT CONSTRUCTOR FOR HOMES
        street = 0;
        ave = 0;
        houseNum = 0;
    }
    
    public Location(int s, int a, int h) { //OVERLOADED CONSTRUCTOR
        street = s;
        ave = a;
        houseNum = h;
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
    
    public void clearHouse() { //clears street, avenue and house number
        street = 0;
        ave = 0;
        houseNum = 0;
        Coordinate.x = 0;
        Coordinate.y = 0;
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
    
    public Point getCoordSimple() {
        Point coord = new Point(street, ave);
        return coord;
    }
    
    public String getHouseLetter()
    {
        return HouseLetter;
    }
    
    public void setHouseLetter(String hl)
    {
        HouseLetter = hl;
    }
}
