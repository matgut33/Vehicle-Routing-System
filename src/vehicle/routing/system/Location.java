package vehicle.routing.system;


/**
 * @author Matthew Gutkin, Henry Dench, Sam Meyerowitz
 * @author Period 8
 */
public class Location {
    private int street = 0, ave = 0, houseNum = 0, bart = 0, lisa = 0;
    
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
}
