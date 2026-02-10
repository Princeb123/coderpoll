import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Main {

        public static void main(String[] args){
            RideBookingSystem rideBookingSystem = new RideBookingSystem();

        rideBookingSystem.createRide(1,"Jaipur", "Delhi", 5, 7,725.00,new user(1, "Prince",  "prince@mail.com",  "pass1"));
        rideBookingSystem.createRide(2,"Greater Noida", "Agra", 3, 5,340.00,new user(2, "Krishna", "krishna@mail.com", "pass2"));
        rideBookingSystem.createRide(3,"Gurugram", "Faridabad", 2, 6,180.00,new user(3, "Jhatu",   "jhatu@mail.com",   "pass3"));
        rideBookingSystem.createRide(4,"Gurugram", "Delhi", 2, 5,80.00,new user(4, "Shalini", "shalini@mail.com", "pass4"));
        rideBookingSystem.createRide(5,"Gurugram", "Varanasi", 2, 5,340.00,new user(5, "beta",    "beta@mail.com",    "pass5"));
        System.out.println(rideBookingSystem.searchRide("Gurugram","Delhi",2));
        rideBookingSystem.signup(10,"Shalini","sgrggdfgdfgdf","ewfewfwf");
        rideBookingSystem.login("sgrggdfgdfgdf","ewfewfwf");
        rideBookingSystem.updateUserDetails(10,"maanus","sgrggdfgdfgdf","ewfewfwf");

        Connection conn =   DBConnection.getConnection();

        if (conn != null) {
            System.out.println("PostgreSQL connected successfully from Java!");
        } else {
            System.out.println("Database connection failed!");
        }
    }
}

