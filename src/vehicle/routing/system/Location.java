package vehicle.routing.system;


/**
 * @author Matthew Gutkin, Henry Dench, Sam Meyerowitz
 * @author Period 8
 */
public class Location {
    private int street = 0, ave = 0;
    private String houseNum = "";
    private int bart = 0, lisa = 0;
    
    public Location() { //DEFAULT CONSTRUCTOR FOR HOMES
        street = 1;
        ave = 1;
        houseNum = "A";
        bart = 1;
        lisa = 1;
    }
    
    public Location(int s, int a, String h, int b, int l) { //OVERLOADED CONSTRUCTOR
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
    
    public void setHouse(String h) {
        houseNum = h;
    }
    
    public String getHouse() {
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
        street = 1;
        ave = 1;
        houseNum = "A";
    }
}
