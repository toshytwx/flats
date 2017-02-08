package com.gmail.dimon0272;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by User on 08.02.2017.
 */
public class DBManipulations {
    private Connection connection;

    public void createDBconnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false", "root","root");
            if(!connection.isClosed()){
                System.out.println("Соединение с БД установленно");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void viewInfo() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1:Go to manipulations with districts");
            System.out.println("2:Go to manipulations with address");
            System.out.println("3:Go to manipulations with rooms");
            System.out.println("4:Go to manipulations with prices");
            int flag = sc.nextInt();
            try {
                switch (flag) {
                    case 1:
                        manipulationsWithDistricts();
                        break;
                    case 2:
                        manipulationsWithAddress();
                        break;
                    case 3:
                        manipulationsWithRooms();
                        break;
                    case 4:
                        manipulationsWithPrices();
                        break;
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void manipulationsWithDistricts() throws SQLException{
        Scanner sc = new Scanner(System.in);
        String userDistrict = "";
        boolean flag = true;
        while(flag) {
            System.out.println("1:Show info about flats at your district.");
            System.out.println("2:Delete flat at your district.");
            System.out.println("3:If you want to previous menu.");
            int index  = sc.nextInt();
            switch (index) {
                case 1:
                    Scanner scc = new Scanner(System.in);
                    System.out.println("Please input name of your district: ");
                    userDistrict = scc.nextLine();
                    PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM flats WHERE district =?");
                    ps1.setString(1, userDistrict);
                    ResultSet rs = ps1.executeQuery();
                    processingResultSet(rs);
                    break;
                case 2:
                    Scanner sccc = new Scanner(System.in);
                    System.out.println("Please input name of your district: ");
                    userDistrict = sccc.nextLine();
                    PreparedStatement ps2 = connection.prepareStatement("DELETE FROM flats WHERE district=?");
                    ps2.setString(1,userDistrict);
                    ps2.executeUpdate();
                    break;
                case 3:
                    flag = false;
                    break;
            }
        }
    }
    private void manipulationsWithAddress() throws SQLException{
        Scanner sc = new Scanner(System.in);
        String userAddress = "";
        boolean flag = true;
        while(flag) {
            System.out.println("1:Show info about flats at your address.");
            System.out.println("2:Delete flat at your address.");
            System.out.println("3:If you want to previous menu.");
            int index  = sc.nextInt();
            switch (index) {
                case 1:
                    Scanner scc = new Scanner(System.in);
                    System.out.println("Please input name of your address: ");
                    userAddress = scc.nextLine();
                    PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM flats WHERE address =?");
                    ps1.setString(1, userAddress);
                    ResultSet rs = ps1.executeQuery();
                    processingResultSet(rs);
                    break;
                case 2:
                    Scanner sccc = new Scanner(System.in);
                    System.out.println("Please input name of your address: ");
                    userAddress = sccc.nextLine();
                    PreparedStatement ps2 = connection.prepareStatement("DELETE FROM flats WHERE address=?");
                    ps2.setString(1,userAddress);
                    ps2.executeUpdate();
                    break;
                case 3:
                    flag = false;
                    break;
            }
        }
    }
    private void manipulationsWithRooms() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Integer userRooms;
        boolean flag = true;
        while(flag) {
            System.out.println("1:Show info about flats with ? number of rooms.");
            System.out.println("2:Delete flat with ? number of rooms.");
            System.out.println("3:Show info about flats with number of flats more than ?.");
            System.out.println("4:Show info about flats with number of flats less than ?.");
            System.out.println("5:If you want to previous menu.");
            int index  = sc.nextInt();
            switch (index) {
                case 1:
                    Scanner scc = new Scanner(System.in);
                    System.out.println("Please input number of rooms ");
                    userRooms = scc.nextInt();
                    PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM flats WHERE rooms =?");
                    ps1.setString(1, userRooms.toString());
                    ResultSet rs1 = ps1.executeQuery();
                    processingResultSet(rs1);
                    break;
                case 2:
                    Scanner sccc = new Scanner(System.in);
                    System.out.println("Please input number of rooms: ");
                    userRooms = sccc.nextInt();
                    PreparedStatement ps2 = connection.prepareStatement("DELETE FROM flats WHERE rooms=?");
                    ps2.setString(1, userRooms.toString());
                    ps2.executeUpdate();
                    break;
                case 3:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please input number of rooms: ");
                    userRooms = scanner.nextInt();
                    PreparedStatement ps3 = connection.prepareStatement("SELECT * FROM flats WHERE rooms >?");
                    ps3.setString(1, userRooms.toString());
                    ResultSet rs2 = ps3.executeQuery();
                    processingResultSet(rs2);
                    break;
                case 4:
                    Scanner scannerr = new Scanner(System.in);
                    System.out.println("Please input number of rooms: ");
                    userRooms = scannerr.nextInt();
                    PreparedStatement ps4 = connection.prepareStatement("SELECT * FROM flats WHERE rooms <?");
                    ps4.setString(1, userRooms.toString());
                    ResultSet rs3 = ps4.executeQuery();
                    processingResultSet(rs3);
                    break;
                case 5:
                    flag = false;
                    break;
            }
        }
    }
    private void manipulationsWithPrices() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Double userPrice;
        boolean flag = true;
        while(flag) {
            System.out.println("1:Show info about flats with ? price.");
            System.out.println("2:Delete flat with ? price.");
            System.out.println("3:Show info about flats with price more than ?.");
            System.out.println("4:Show info about flats with price less than ?.");
            System.out.println("5:If you want to previous menu.");
            int index  = sc.nextInt();
            switch (index) {
                case 1:
                    Scanner scc = new Scanner(System.in);
                    System.out.println("Please input price of flat ");
                    userPrice = scc.nextDouble();
                    PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM flats WHERE price=?");
                    ps1.setString(1, userPrice.toString());
                    ResultSet rs1 = ps1.executeQuery();
                    processingResultSet(rs1);
                    break;
                case 2:
                    Scanner sccc = new Scanner(System.in);
                    System.out.println("Please input price of flat: ");
                    userPrice = sccc.nextDouble();
                    PreparedStatement ps2 = connection.prepareStatement("DELETE FROM flats WHERE price=?");
                    ps2.setString(1, userPrice.toString());
                    ps2.executeUpdate();
                    break;
                case 3:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please input price of flat: ");
                    userPrice = scanner.nextDouble();
                    PreparedStatement ps3 = connection.prepareStatement("SELECT * FROM flats WHERE price >?");
                    ps3.setString(1, userPrice.toString());
                    ResultSet rs2 = ps3.executeQuery();
                    processingResultSet(rs2);
                    break;
                case 4:
                    Scanner scannerr = new Scanner(System.in);
                    System.out.println("Please input price of flat: ");
                    userPrice = scannerr.nextDouble();
                    PreparedStatement ps4 = connection.prepareStatement("SELECT * FROM flats WHERE price <?");
                    ps4.setString(1, userPrice.toString());
                    ResultSet rs3 = ps4.executeQuery();
                    processingResultSet(rs3);
                    break;
                case 5:
                    flag = false;
                    break;
            }
        }
    }
    private void processingResultSet(ResultSet resultSet){
        ArrayList <Flat> flatArrayList= new ArrayList<Flat>();
        try {
            while (resultSet.next()) {
                Flat flat = new Flat();
                flat.setDistrict(resultSet.getString("district"));
                flat.setAddress(resultSet.getString("address"));
                flat.setPrice(resultSet.getDouble("price"));
                flat.setRooms(resultSet.getInt("rooms"));
                flat.setSquare(resultSet.getDouble("square"));
                flatArrayList.add(flat);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if(flatArrayList.isEmpty()){
            System.out.println("There are no such flats according to your request!");
        }else{
            System.out.println(flatArrayList.toString());
        }
    }

}
