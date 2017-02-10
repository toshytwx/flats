package com.gmail.dimon0272;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by User on 08.02.2017.
 */
public class DBManipulations {
    private Connection connection;
    private static final String DATA_BASE_PATH = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
    private String userParametr;

    public void createDBconnection(){
        try {
            connection = DriverManager.getConnection(DATA_BASE_PATH, "root","root");
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
        boolean flag = true;
        while(flag) {
            System.out.println("1:Show info about flats at your district.");
            System.out.println("2:Delete flat at your district.");
            System.out.println("3:If you want to previous menu.");
            int index  = sc.nextInt();
            switch (index) {
                case 1:
                    processingSelectQuery("SELECT * FROM flats WHERE district =?", "district");
                    break;
                case 2:
                    processingDeleteQuery("DELETE FROM flats WHERE district=?","district");
                    break;
                case 3:
                    flag = false;
                    break;
            }
        }
    }

    private void manipulationsWithAddress() throws SQLException{
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while(flag) {
            System.out.println("1:Show info about flats at your address.");
            System.out.println("2:Delete flat at your address.");
            System.out.println("3:If you want to previous menu.");
            int index  = sc.nextInt();
            switch (index) {
                case 1:
                    processingSelectQuery("SELECT * FROM flats WHERE address =?", "address");
                    break;
                case 2:
                    processingDeleteQuery("DELETE FROM flats WHERE address=?", "address");
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
                    processingSelectQuery("SELECT * FROM flats WHERE rooms =?", "rooms");
                    break;
                case 2:
                    processingDeleteQuery("DELETE FROM flats WHERE rooms=?", "rooms");
                    break;
                case 3:
                    processingSelectQuery("SELECT * FROM flats WHERE rooms >?", "rooms");
                    break;
                case 4:
                    processingSelectQuery("SELECT * FROM flats WHERE rooms <?", "rooms");
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
                    processingSelectQuery("SELECT * FROM flats WHERE price=?", "price");
                    break;
                case 2:
                    processingDeleteQuery("DELETE FROM flats WHERE price=?", "price");
                    break;
                case 3:
                    processingSelectQuery("SELECT * FROM flats WHERE price >?", "price");
                    break;
                case 4:
                    processingSelectQuery("SELECT * FROM flats WHERE price <?", "price");
                    break;
                case 5:
                    flag = false;
                    break;
            }
        }
    }
    private void processingSelectQuery(String query, String parametr) throws SQLException{
        Scanner scc = new Scanner(System.in);
        System.out.println("Please input your " +parametr+": ");
        userParametr= scc.nextLine();
        PreparedStatement ps1 = connection.prepareStatement(query);
        ps1.setString(1, userParametr);
        ResultSet rs = ps1.executeQuery();
        processingResultSet(rs);
    }
    private void processingDeleteQuery(String query, String parametr) throws SQLException{
        Scanner scc = new Scanner(System.in);
        System.out.println("Please input your "+parametr+": ");
        userParametr = scc.nextLine();
        PreparedStatement ps2 = connection.prepareStatement(query);
        ps2.setString(1,userParametr);
        ps2.executeUpdate();
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
