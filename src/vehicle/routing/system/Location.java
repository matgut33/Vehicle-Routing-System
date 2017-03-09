package vehicle.routing.system;


/**
 * @author Matthew Gutkin, Henry Dench, Sam Meyerowitz
 * @author Period 8
 */
public class Location {
    private int street = 0, ave = 0;
    private String houseNum = "";
    
    public Location() { //DEFAULT CONSTRUCTOR FOR HOMES
        street = 1;
        ave = 1;
        houseNum = "A";
    }
    
    public Location(int s, int a, String h) { //OVERLOADED CONSTRUCTOR
        street = s;
        ave = a;
        houseNum = h;
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
    
    public void setHouse(String h) {
        houseNum = h;
    }
    
    public String getHouse() {
        return houseNum;
    }
    
    public void clearHouse() {
        street = 1;
        ave = 1;
        houseNum = "A";
    }
}
