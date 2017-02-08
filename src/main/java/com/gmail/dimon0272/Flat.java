package com.gmail.dimon0272;

/**
 * Created by User on 08.02.2017.
 */
public class Flat {
    private String district;
    private String address;
    private double square;
    private int rooms;
    private double price;

    @Override
    public String toString(){
        String districInfo = "District is: " + district;
        String addressInfo = "Address is: " + address;
        String squareInfo = "Square is: " +square;
        String roomsInfo = "Number of rooms: " + rooms;
        String priceInfo = "Price is: " + price;
        return "\n"+ districInfo +"\n"+ addressInfo+"\n" + squareInfo+"\n" + roomsInfo+"\n" + priceInfo+"\n";
    }
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
