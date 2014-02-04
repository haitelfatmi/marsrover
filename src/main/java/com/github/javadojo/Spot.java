package com.github.javadojo;

public class Spot {

    private int longitude;
    private int latitude;
    private String view;

    public Spot(int longitude, int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    


    public Spot forwardThroughLongitude(){
        return new Spot(this.longitude + 1,  this.latitude);
    }
    
    public Spot forwardThroughLatitude(){
        return new Spot(this.longitude,  this.latitude  + 1);
    }
    
    public Spot backwardThroughLongitude(){
        return new Spot(this.longitude - 1,  this.latitude);
    }
    
    public Spot backwardThroughLatitude(){
        return new Spot(this.longitude,  this.latitude  - 1);
    }


    public void drawInto(String[][] map) {
       map[this.longitude][this.latitude] = this.view;
    }

    
  
    
    public Spot withView(String view) {
        this.view = view;
        return this;
    }



    
    
    

}
